package com.locatemybus.myorderbox.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.adapter.ListViewAdapter;
import com.locatemybus.myorderbox.Helper.BadgeDrawable;
import com.locatemybus.myorderbox.Helper.CheckOutAdapter;
import com.locatemybus.myorderbox.Helper.Constants;
import com.locatemybus.myorderbox.Helper.Events;
import com.locatemybus.myorderbox.Helper.GlobalBus;
import com.locatemybus.myorderbox.Helper.NotificationListAdapter;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.dto.dtoAddress;
import com.locatemybus.myorderbox.dto.dtoDealDetail;
import com.locatemybus.myorderbox.dto.dtoDeliveryPostcode;
import com.locatemybus.myorderbox.dto.dtoItemOrder;
import com.locatemybus.myorderbox.dto.dtoMyStore;
import com.locatemybus.myorderbox.dto.dtoMyStoreResponse;
import com.locatemybus.myorderbox.dto.dtoNotificationData;
import com.locatemybus.myorderbox.dto.dtoProduct;
import com.locatemybus.myorderbox.dto.dtoSignInResponse;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import io.paperdb.Paper;

import static android.widget.Toast.LENGTH_SHORT;

public class MenuNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    DrawerLayout drawer;
    TextView textView_HeaderName,textView_Header_Address;
    ImageView imageView_LocationImage;
    View dialogView;
    Intent intent;
    TextView textView_Home, textView_Login, textView_MyOrder, textView_Notification, textView_NotificationPref, textView_Help,textView_TotalPrice;
    android.app.AlertDialog dialog;
    ImageView imageView_Cart;
    CheckOutAdapter checkOutAdapter;
    Button button_CheckOut;
    ArrayList<dtoItemOrder> itemOrders;

    SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    SimpleDateFormat timeFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    String currentTime;
    ArrayList<String> timeArray;
    dtoAddress address;

    protected int getLayoutResource() {
        return 0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        GlobalBus.getBus().register(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dtoMyStoreResponse  myStoreResponse=Paper.book().read(Constants.StoreInformation);
        address=myStoreResponse.getStore().getAddress();


        imageView_Cart=findViewById(R.id.imageView_Cart);
        textView_TotalPrice=findViewById(R.id.textView_TotalPrice);
        double cartTotalAmount=Constants.calculateTotalPrice();
        String price=String.format("%.2f", cartTotalAmount);
        textView_TotalPrice.setText(getString(R.string.Currency)+price);

        imageView_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckOutDialog();
            }
        });
        drawer = findViewById(R.id.drawer_layout);

        /*
        * Navigation Header Items
        * */

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        imageView_LocationImage = (ImageView) headerview.findViewById(R.id.imageView_Header_Image);
        textView_Header_Address = (TextView) headerview.findViewById(R.id.textView_Header_Address);
        textView_Header_Address.setText(address.getLine1()+" , "+address.getCity());//Set Store address
        MapView mMapView = (MapView) headerview.findViewById(R.id.Header_Map);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                double lat= (double) address.getLat();
                double lng= (double) address.getLng();
                LatLng ResLocation = new LatLng(lat,lng);
                googleMap.addMarker(new MarkerOptions().position(ResLocation));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(ResLocation));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ResLocation,24));
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        imageView_LocationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp();
            }
        });

        dtoSignInResponse signInResponse=Paper.book().read(Constants.LoginInformation);

        textView_Home = findViewById(R.id.textView_Home);
        textView_Login = findViewById(R.id.textView_Login);
        textView_MyOrder = findViewById(R.id.textView_MyOrder);
        textView_Notification = findViewById(R.id.textView_Notification);
        textView_NotificationPref = findViewById(R.id.textView_NotificationPref);
        textView_Help = findViewById(R.id.textView_Help);

        if (signInResponse!=null){
            textView_Login.setText("Logout");
        }else{
            textView_Login.setText(getString(R.string.menu_Login));
        }

        textView_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), HomePage.class);
                v.getContext().startActivity(intent);
            }
        });
        textView_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textValue=textView_Login.getText().toString();
                if (textValue.equals("Login")){
                    intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                }
                else if (textValue.equals("Logout")){
                    /*
                    * Remove login saved data from Database
                    * */

                    Paper.book().delete(Constants.LoginInformation);
                    intent = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(intent);
                    textView_Login.setText(getString(R.string.menu_Login));
                }

            }
        });
        textView_MyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dtoSignInResponse signInResponse= Paper.book().read(Constants.LoginInformation);
                if (signInResponse!=null){
                    intent = new Intent(getApplicationContext(), MyOrders.class);
                    startActivity(intent);
                }
                else {
                    intent=new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                }
            }
        });
        textView_Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotifications();
            }
        });
        textView_NotificationPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), NotificationPreferences.class);
                startActivity(intent);
            }
        });
        textView_Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().delete(Constants.HashMap);
                Paper.book().delete(Constants.SelectedDealsList);
                Paper.book().delete(Constants.SelectedProductsList);
//                Paper.book().delete(Constants.DeliveryType);
                intent=new Intent(getApplicationContext(),HomePage.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {
            intent = new Intent(this, HomePage.class);
            startActivity(intent);
        } else if (id == R.id.nav_Login) {
            intent = new Intent(this, Login.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_MyOrder) {
            dtoSignInResponse signInResponse= Paper.book().read(Constants.LoginInformation);
            if (signInResponse!=null && signInResponse.getId()!=null){
                intent = new Intent(this, MyOrders.class);
                startActivity(intent);
            }
            else {
                intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_Notification) {
            showNotifications();
        } else if (id == R.id.nav_NotificationPreferences) {
            intent = new Intent(this, NotificationPreferences.class);
            startActivity(intent);
        } else if (id == R.id.nav_Help) {
//            showNotifications();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void showPopUp() {

        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        if (dialogView != null) {
            ViewGroup parentViewGroup = (ViewGroup) dialogView.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViewsInLayout();
            }
        }
        try {
            dialogView = inflater.inflate(R.layout.custom_menu_location, null);
            builder.setView(dialogView);
            builder.setCancelable(true);

            dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();

            GoogleMap googleMap;
            MapsInitializer.initialize(getApplicationContext());

            MapView mMapView = (MapView) dialog.findViewById(R.id.mapView);
            mMapView.onCreate(dialog.onSaveInstanceState());
            mMapView.onResume();// needed to get the map to display immediately
            mMapView.getMapAsync(this);

//            Fragment fragment_map=new MapFragment();
//            FragmentManager fragmentManager = this.getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.linearLayout_MapLayout, fragment_map);
//            fragmentTransaction.commit();

            dtoMyStoreResponse  myStoreResponse=Paper.book().read(Constants.StoreInformation);
            Button button_ResturantNumber = (Button) dialogView.findViewById(R.id.button_ResturantNumber);
            ImageView imageView_LocationCross = (ImageView) dialogView.findViewById(R.id.imageView_LocationCross);

            button_ResturantNumber.setText(myStoreResponse.getStore().getTelephone());

            imageView_LocationCross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    dialogView=null;
                }
            });
            button_ResturantNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   dialog.dismiss();
                   dialogView=null;
                }
            });

        } catch (InflateException e) {
            Log.d("Tag", "showPopUp: "+e);
        }
    }
    //Show notifications list in dialog view
    private void showNotifications() {

        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        if (dialogView != null) {
            ViewGroup parentViewGroup = (ViewGroup) dialogView.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViewsInLayout();
            }
        }
        try {
            dialogView = inflater.inflate(R.layout.notification, null);

            builder.setView(dialogView);
            builder.setCancelable(true);

            final android.app.AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();

            ListView listView = (ListView) dialogView.findViewById(R.id.listView_AllNotifications);
            ImageView imageView_NotificationCross=(ImageView)dialogView.findViewById(R.id.imageView_NotificationCross);

            ArrayList<dtoNotificationData> notificationData = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                notificationData.add(new dtoNotificationData("Lorem Ipsum", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."));
            }
            NotificationListAdapter notificationListAdapter = new NotificationListAdapter(this, notificationData);
            listView.setAdapter(notificationListAdapter);
            notificationListAdapter.notifyDataSetChanged();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    notification_Explanation();
                }
            });

            imageView_NotificationCross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    dialogView=null;
                }
            });


        } catch (InflateException e) {
        }



    }
    //show selected notification in detail
    private void notification_Explanation() {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_notification_selecteditem, null);
        builder.setView(dialogView);
        builder.setCancelable(true);

        final android.app.AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        Button button_notifiation = (Button) dialogView.findViewById(R.id.button_NotificationBack);
        button_notifiation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }//Function end
    public Drawable setBadgeCount(Context context, int res, int badgeCount){
        LayerDrawable icon = (LayerDrawable) ContextCompat.getDrawable(context, R.drawable.ic_badge_drawable);
        Drawable mainIcon = ContextCompat.getDrawable(context, res);
        BadgeDrawable badge = new BadgeDrawable(context);
        badge.setCount(String.valueOf(badgeCount));
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
        icon.setDrawableByLayerId(R.id.ic_main_icon, mainIcon);

        return icon;
    }

    /*
    * Modules for CheckOut Dialog
    * */
    public Date UTCToLocal(String strDate) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = df.parse(strDate);
            return date;
        }
        catch (Exception ex)
        {
            System.out.println("Exception");
        }
        return  new Date();
    }
    private Date getTime(String DateTime){
        Date time=null;
        try {
            time=timeFormater.parse(DateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
    private String addMinutes(Calendar calendar,Date date,int minutes){
        String currentTime;
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE,minutes);
        currentTime=dateTimeFormat.format(calendar.getTime());
        return currentTime;
    }
    private Calendar addOrderMinutes(String orderType,Date date){

        dtoMyStoreResponse  myStoreResponse=Paper.book().read(Constants.StoreInformation);

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);

        int prepTime=myStoreResponse.getStore().getPrepTime();
        int deliveryTime=myStoreResponse.getStore().getDeliveryTimeMins();

        if (orderType.equals("delivery")){
            calendar.add(Calendar.MINUTE,prepTime);
            calendar.add(Calendar.MINUTE,deliveryTime);
        }else if (orderType.equals("collection")){
            calendar.add(Calendar.MINUTE,prepTime);
        }

        return calendar;
    }
    private ArrayList<String> getTimeArray(ArrayList<String> timeArray){

        dtoMyStoreResponse myStoreResponse=Paper.book().read(Constants.StoreInformation);

        /*
        * Get Closing Time
        * */
        Date closingDateTime=UTCToLocal(myStoreResponse.getStore().getClosingTime());
        Date openingDateTime=UTCToLocal(myStoreResponse.getStore().getOpeningTime());
        Date deliveryFromDateTime=UTCToLocal(myStoreResponse.getStore().getDeliveryFrom());
        Date deliveryToDateTime=UTCToLocal(myStoreResponse.getStore().getDeliveryTo());
        Date currentDateTime=Calendar.getInstance().getTime();

        String closingTime=timeFormat.format(closingDateTime);//only get time in string format
        String openingTime=timeFormat.format(openingDateTime);//only get time in string format
        String deliveryFromTime=timeFormat.format(deliveryFromDateTime);//only get time in string format
        String deliveryToTime=timeFormat.format(deliveryToDateTime);//only get time in string format
        String currentTime=timeFormat.format(currentDateTime);//only get time in string format

        Calendar calendar=Calendar.getInstance();

        /*
        * Get Current Time
        * */
        String orderType=Paper.book().read(Constants.DeliveryType);
        if (orderType.equals("delivery")){
            if (currentDateTime.after(deliveryFromDateTime) && currentDateTime.before(deliveryToDateTime)){
                /*
                * Current time as Base time
                * */

                calendar=addOrderMinutes(orderType,currentDateTime);

            }else {
                /*
                 * Delivery from time as Base time
                 * */
                calendar=addOrderMinutes(orderType,deliveryFromDateTime);
            }
        }
        else if (orderType.equals("collection")){
            if (currentDateTime.after(openingDateTime) && currentDateTime.before(closingDateTime)){
                /*
                 * Current time as Base time
                 * */
                calendar=addOrderMinutes(orderType,currentDateTime);
            }else {
                /*
                 * Opening time as Base time
                 * */
                calendar=addOrderMinutes(orderType,openingDateTime);
            }
        }


        int round = calendar.get(Calendar.MINUTE) % 15;
        calendar.add(Calendar.MINUTE, round > 0 ?  (15-round):0);
        calendar.set( Calendar.SECOND, 0 );

        String strDateTime=dateTimeFormat.format(calendar.getTime());//only date and time in string format
        Date date=getTime(strDateTime);//Convert string into date
        String time=timeFormat.format(date);//only get time in string format
        timeArray.add(time);

        /*
        * Getting Intervals between Delivery time and closing time
        * */
        do {
            currentTime=addMinutes(calendar,date,15);
            date=getTime(currentTime);
            time=timeFormat.format(date);
            timeArray.add(time);
        }
        while (date.before(closingDateTime));

        return timeArray;
    }
    private void CheckOutDialog(){

        dtoMyStoreResponse storeResponse=Paper.book().read(Constants.StoreInformation);
        String orderType=Paper.book().read(Constants.DeliveryType);

        ArrayAdapter<String> adapter=null;
        timeArray=new ArrayList<>();
        if (orderType.equals("delivery")){
            timeArray.add("ASAP");
        }
        timeArray=getTimeArray(timeArray);

        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this,R.style.FullScreenDialogStyle);
        LayoutInflater inflater =getLayoutInflater();
        if (dialogView != null) {
            ViewGroup parentViewGroup = (ViewGroup) dialogView.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViewsInLayout();
            }
        }
        try {
            dialogView = inflater.inflate(R.layout.checkout, null);
        }
        catch (InflateException e) {}

        builder.setView(dialogView);
        builder.setCancelable(true);

        final android.app.AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        ListView listView=(ListView)dialogView.findViewById(R.id.listView_CheckOut);



        final Button button_CO_Delivery=(Button)dialogView.findViewById(R.id.button_CO_Delivery);
        final Button button_CO_Collection=(Button)dialogView.findViewById(R.id.button_CO_Collection);
        final Button button_CO_DineIn=(Button)dialogView.findViewById(R.id.button_CO_DineIn);
        ImageView imageView_CO_Cross=(ImageView)dialogView.findViewById(R.id.imageView_CO_Cross);
        final Spinner spinner_DeliveryTime=(Spinner) dialogView.findViewById(R.id.spinner_DeliveryTime);
        Button button_COCheckOut=(Button)dialogView.findViewById(R.id.button_COCheckOut);
        Button button_COPrice=(Button)dialogView.findViewById(R.id.button_COPrice);

        //For Swipe Function
        final SwipeToDismissTouchListener<ListViewAdapter> touchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(listView),
                        new SwipeToDismissTouchListener.DismissCallbacks<ListViewAdapter>() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListViewAdapter view, int position) {
                                checkOutAdapter.removeItem(position);
                            }
                        });
        listView.setOnTouchListener(touchListener);
        listView.setOnScrollListener((AbsListView.OnScrollListener) touchListener.makeScrollListener());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (touchListener.existPendingDismisses()) {
                    touchListener.undoPendingDismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Position " + position, LENGTH_SHORT).show();
                }
            }
        });

        /*
        * Show Buttons
        * */
        dtoMyStore myStore=storeResponse.getStore();
        if (myStore.getOfferDelivery()){
            button_CO_Delivery.setVisibility(View.VISIBLE);
        }
        if (myStore.getOfferCollection()){
            button_CO_Collection.setVisibility(View.VISIBLE);
        }

        /*
        * Selected Order Type Color Change
        * */
        if (orderType!=null){
            if (orderType.equals("delivery")){

                button_CO_Delivery.setTextColor(getResources().getColor(R.color.colorBlack));
                button_CO_Delivery.setBackgroundColor(getResources().getColor(R.color.fontColor));

                button_CO_Collection.setTextColor(getResources().getColor(R.color.fontColor));
                button_CO_Collection.setBackgroundColor(getResources().getColor(R.color.Button_BG));

                button_CO_DineIn.setTextColor(getResources().getColor(R.color.fontColor));
                button_CO_DineIn.setBackgroundColor(getResources().getColor(R.color.Button_BG));
            }
            else if (orderType.equals("collection")){
                button_CO_Collection.setTextColor(getResources().getColor(R.color.colorBlack));
                button_CO_Collection.setBackgroundColor(getResources().getColor(R.color.fontColor));

                button_CO_Delivery.setTextColor(getResources().getColor(R.color.fontColor));
                button_CO_Delivery.setBackgroundColor(getResources().getColor(R.color.Button_BG));

                button_CO_DineIn.setTextColor(getResources().getColor(R.color.fontColor));
                button_CO_DineIn.setBackgroundColor(getResources().getColor(R.color.Button_BG));
            }
        }

        /*
        * Setting Spinner
        * */
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timeArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_DeliveryTime.setAdapter(adapter);

        button_COCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

                dtoSignInResponse signInResponse= Paper.book().read(Constants.LoginInformation);
                if (signInResponse!=null){
                    Constants.DeliveryTime=spinner_DeliveryTime.getSelectedItem().toString();
                    Paper.book().write(Constants.DeliveryTime,Constants.DeliveryTime);
                    intent=new Intent(getApplicationContext(),CheckOut.class);
                    startActivity(intent);
                }
                else {
                    intent=new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                }
            }
        });

        final ArrayAdapter<String> finalAdapter = adapter;
        button_CO_Collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_CO_Collection.setTextColor(getResources().getColor(R.color.colorBlack));
                button_CO_Collection.setBackgroundColor(getResources().getColor(R.color.fontColor));

                button_CO_Delivery.setTextColor(getResources().getColor(R.color.fontColor));
                button_CO_Delivery.setBackgroundColor(getResources().getColor(R.color.Button_BG));

                button_CO_DineIn.setTextColor(getResources().getColor(R.color.fontColor));
                button_CO_DineIn.setBackgroundColor(getResources().getColor(R.color.Button_BG));

                Paper.book().write(Constants.DeliveryType,"collection");

                timeArray.clear();
                timeArray=getTimeArray(timeArray);
                finalAdapter.notifyDataSetChanged();
            }
        });

        final ArrayAdapter<String> finalAdapter1 = adapter;
        button_CO_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_CO_Delivery.setTextColor(getResources().getColor(R.color.colorBlack));
                button_CO_Delivery.setBackgroundColor(getResources().getColor(R.color.fontColor));

                button_CO_Collection.setTextColor(getResources().getColor(R.color.fontColor));
                button_CO_Collection.setBackgroundColor(getResources().getColor(R.color.Button_BG));

                button_CO_DineIn.setTextColor(getResources().getColor(R.color.fontColor));
                button_CO_DineIn.setBackgroundColor(getResources().getColor(R.color.Button_BG));

                Paper.book().write(Constants.DeliveryType,"delivery");

                dtoDeliveryPostcode postcode=Paper.book().read(Constants.SelectedPostalCode);
                if (postcode==null){
                    intent=new Intent(getApplicationContext(),HomePage.class);
                    startActivity(intent);
                }

                timeArray.clear();
                timeArray.add("ASAP");
                timeArray=getTimeArray(timeArray);
                finalAdapter1.notifyDataSetChanged();
            }
        });

        button_CO_DineIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_CO_DineIn.setTextColor(getResources().getColor(R.color.colorBlack));
                button_CO_DineIn.setBackgroundColor(getResources().getColor(R.color.fontColor));

                button_CO_Collection.setTextColor(getResources().getColor(R.color.fontColor));
                button_CO_Collection.setBackgroundColor(getResources().getColor(R.color.Button_BG));

                button_CO_Delivery.setTextColor(getResources().getColor(R.color.fontColor));
                button_CO_Delivery.setBackgroundColor(getResources().getColor(R.color.Button_BG));
            }
        });
        imageView_CO_Cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Events.UpdateMPT updateMPT = new Events.UpdateMPT();
                GlobalBus.getBus().post(updateMPT);

                String price=String.format("%.2f", Constants.CartTotalAmount);
                textView_TotalPrice.setText(getString(R.string.Currency)+price);
            }
        });

        itemOrders=new ArrayList<>();

        Constants.selectedProducts=Paper.book().read(Constants.SelectedProductsList);
        if (Constants.selectedProducts!=null){
            for (dtoProduct product:Constants.selectedProducts){
                itemOrders.add(new dtoItemOrder(product.getId(),product.getQuantity(),product.getName(),String.valueOf(product.getItemAmount()),product.getDescription(),product.getImage().getUrl()));
//                if (product.getHasAttributes()){
//                    itemOrders.add(new dtoItemOrder(product.getQuantity(),product.getName(),String.valueOf(product.getTotalAmount()),product.getDescription(),product.getImage().getUrl()));
//                }
//                else {
//                    itemOrders.add(new dtoItemOrder(product.getQuantity(),product.getName(),String.valueOf(product.getTotalAmount()),product.getDescription(),product.getImage().getUrl()));
//                }
            }
        }
        Constants.selectedDeals=Paper.book().read(Constants.SelectedDealsList);
        if (Constants.selectedDeals!=null){
            for (dtoDealDetail dealDetail:Constants.selectedDeals){
                if (dealDetail.getImage()==null){
                    itemOrders.add(new dtoItemOrder(dealDetail.getId(),dealDetail.getQuantity(),dealDetail.getName(),dealDetail.getMinCartValue(),dealDetail.getDescription(),null));
                }
                else {
                    itemOrders.add(new dtoItemOrder(dealDetail.getId(),dealDetail.getQuantity(),dealDetail.getName(),dealDetail.getMinCartValue(),dealDetail.getDescription(),dealDetail.getImage().getUrl()));
                }
            }
        }

        checkOutAdapter=new CheckOutAdapter(this,itemOrders);
        listView.setAdapter(checkOutAdapter);
        checkOutAdapter.notifyDataSetChanged();

        calculateTotalPrice();
        String price=String.format("%.2f", Constants.CartTotalAmount);
        button_COPrice.setText(price);

    }
    private void calculateTotalPrice(){
        Constants.CartTotalAmount=0;
        Constants.selectedProducts= Paper.book().read(Constants.SelectedProductsList);
        Constants.selectedDeals=Paper.book().read(Constants.SelectedDealsList);
        if (Constants.selectedProducts!=null){
            for (dtoProduct product:Constants.selectedProducts){
                Constants.CartTotalAmount=Constants.CartTotalAmount+(product.getItemAmount()*product.getQuantity());
            }
        }
        if (Constants.selectedDeals!=null){
            for (dtoDealDetail dealDetail:Constants.selectedDeals){
                Constants.CartTotalAmount=Constants.CartTotalAmount+(Double.parseDouble(dealDetail.getMinCartValue())*dealDetail.getQuantity());
            }
        }

        button_CheckOut=dialogView.findViewById(R.id.button_COPrice);
        String price=String.format("%.2f", Constants.CartTotalAmount);
        button_CheckOut.setText(getString(R.string.Currency)+price);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTrigger(Events.CheckOutIncrement checkOutIncrement) {
        int childId=checkOutIncrement.getId();
        int childPosition=checkOutIncrement.getChildPosition();
        int count=checkOutIncrement.getCount();
        calculatePrice(childId,childPosition,count);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTrigger(Events.CheckOutDecrement checkOutDecrement) {
        int childId=checkOutDecrement.getId();
        int childPosition=checkOutDecrement.getChildPosition();
        int count=checkOutDecrement.getCount();
        calculatePrice(childId,childPosition,count);
    }
    private void calculatePrice(int childId,int childPosition,int count){
        Constants.CartTotalAmount=0;
        Constants.selectedProducts= Paper.book().read(Constants.SelectedProductsList);
        Constants.selectedDeals=Paper.book().read(Constants.SelectedDealsList);


        if (Constants.selectedProducts!=null){

//            for (dtoProduct product:Constants.selectedProducts){
//                if (product.getId()==childId){
//                    product.setQuantity(itemOrders.get(childPosition).getCount());
//                }
//            }

            for (int i=0;i<Constants.selectedProducts.size();i++){
                dtoProduct product=Constants.selectedProducts.get(i);
                if (product.getId()==childId){
                    product.setQuantity(count);
                    Constants.selectedProducts.set(i,product);
                    Paper.book().write(Constants.SelectedProductsList,Constants.selectedProducts);
                }
            }

        }

        if (Constants.selectedDeals!=null){

            for (int i=0;i<Constants.selectedDeals.size();i++){
                dtoDealDetail dealDetail=Constants.selectedDeals.get(i);
                if (dealDetail.getId()==childId){
                    dealDetail.setQuantity(count);
                    Constants.selectedDeals.set(i,dealDetail);
                    Paper.book().write(Constants.SelectedDealsList,Constants.selectedDeals);
                }
            }


//            for (dtoDealDetail dealDetail:Constants.selectedDeals){
//                if (dealDetail.getId()==childId){
//                    dealDetail.setQuantity(itemOrders.get(childPosition).getCount());
//                }
//            }

        }


        calculateTotalPrice();

        button_CheckOut=dialogView.findViewById(R.id.button_COPrice);
        String price=String.format("%.2f", Constants.CartTotalAmount);
        button_CheckOut.setText(price);
    }
    @Override
    protected void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        double lat= (double) address.getLat();
        double lng= (double) address.getLng();
        LatLng ResLocation = new LatLng(lat,lng);
        googleMap.addMarker(new MarkerOptions().position(ResLocation));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ResLocation));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ResLocation,14));
    }
}
