package com.locatemybus.myorderbox.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.locatemybus.myorderbox.Helper.Constants;
import com.locatemybus.myorderbox.Helper.Events;
import com.locatemybus.myorderbox.Network.ApiService;
import com.locatemybus.myorderbox.Network.AppWebServices;
import com.locatemybus.myorderbox.Network.RequestCode;
import com.locatemybus.myorderbox.Network.RestClient;
import com.locatemybus.myorderbox.Network.RestRequestCallback;
import com.locatemybus.myorderbox.Network.ServerConnectListener;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.dto.dtoDeliveryPostCodeResponse;
import com.locatemybus.myorderbox.dto.dtoDeliveryPostcode;
import com.locatemybus.myorderbox.dto.dtoMyStore;
import com.locatemybus.myorderbox.dto.dtoMyStoreResponse;
import com.nex3z.notificationbadge.NotificationBadge;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.paperdb.Paper;
import retrofit2.Call;

import static com.locatemybus.myorderbox.Network.RequestCode.DeliveryPostCode_GetRequest;
import static com.locatemybus.myorderbox.Network.RequestCode.StoreInformation_PostRequest;

public class HomePage extends MenuNavigation implements View.OnClickListener,LocationListener,ServerConnectListener {

    Button button_HP_Order,button_HP_Delivery,button_HP_Collection,button_HP_DineIn;
    EditText editText_PostalCode;
    private LocationManager mLocationManager = null;
    private String provider = null;
    public String userPostalCode=null;
    public static int MY_PERMISSIONS_REQUEST_FINE_LOCATION=99;
    String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    private ApiService apiService;
    private Context context;
    private Activity activity;
    String postalCode;

    ImageView imageView_AB_Image,imageView_Cart,menuImage,imageView_HP_Location,imageView_HP_Cross;
    TextView textView_AB_Heading,textView_TotalPrice, StoreOpenCloseText;
    String imageUrl;

    NotificationBadge badge;
    LinearLayout LinearLayout_HP_Location;
    boolean collection,delivery;
    dtoMyStoreResponse storeResponse;
    CoordinatorLayout CoordinateLayout_Root;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home_page;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context=this;
        activity=this;

        storeResponse=Paper.book().read(Constants.StoreInformation);

        Toast.makeText(context, storeResponse.getStore().getHomePageMessage(), Toast.LENGTH_LONG).show();

        initComponent(storeResponse);
        initListeners();
        hideButtons();
        showButtons(storeResponse);

        getStoreInformation();
        getDeliveryPostCodes();

        if (isProviderAvailable() && (provider != null)) {
            if (hasPermissions(context,PERMISSIONS)){
                ActivityCompat.requestPermissions(activity, PERMISSIONS, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            }
        }
        else {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
            Toast.makeText(this, "Not satisfied:" + isProviderAvailable(), Toast.LENGTH_SHORT).show();
        }

    }
    private void initComponent(dtoMyStoreResponse storeResponse){

        context = this;
        activity = this;
        apiService = RestClient.getInstance(context, false, false);

        badge = findViewById(R.id.badge);
        int count=Constants.updateCartCount();
        if (count>0){
            badge.setNumber(count);
        }

        CoordinateLayout_Root=findViewById(R.id.CoordinateLayout_Root);
        button_HP_Order=findViewById(R.id.button_HP_Order);
        button_HP_Delivery=findViewById(R.id.button_HomeDelivery);
        button_HP_Collection=findViewById(R.id.button_HomeCollection);
        button_HP_DineIn=findViewById(R.id.button_HomeDineIn);

        editText_PostalCode=findViewById(R.id.editText_PostalCode);
        StoreOpenCloseText=findViewById(R.id.textView12);
        imageView_AB_Image=findViewById(R.id.imageView_AB_Image);
        imageView_Cart=findViewById(R.id.imageView_Cart);
        menuImage=findViewById(R.id.menuImage);
        textView_AB_Heading=findViewById(R.id.textView_AB_Heading);

        LinearLayout_HP_Location=findViewById(R.id.LinearLayout_HP_Location);
        LinearLayout_HP_Location.setBackgroundColor(getResources().getColor(R.color.fontColor));
        imageView_HP_Location=findViewById(R.id.imageView_HP_Location);
        imageView_HP_Cross=findViewById(R.id.imageView_HP_Cross);

        textView_TotalPrice=findViewById(R.id.textView_TotalPrice);
        double cartTotalAmount=Constants.calculateTotalPrice();
        String price=String.format("%.2f", cartTotalAmount);
        textView_TotalPrice.setText(getString(R.string.Currency)+price);

        /*Set Company logo or Heading*/
        dtoMyStore myStore=storeResponse.getStore();
        imageUrl=myStore.getOnlineSettings().getLogo().getUrl();
        if (imageUrl!=null){
            Glide.with(this).load(AppWebServices.BASE_URL+imageUrl).into(imageView_AB_Image);
        }else {
            textView_AB_Heading.setText(myStore.getName().toString());
        }

        String OpeningTime = myStore.getOpeningTime();
        Date LocalOpeningTime = UTCToLocal(OpeningTime);
        String ClosingTime = myStore.getClosingTime();
        Date LocalClosingTime = UTCToLocal(ClosingTime);
        Date LocalTime = new Date();

        if(LocalTime.before(LocalClosingTime) && LocalTime.after(LocalOpeningTime)){
            StoreOpenCloseText.setText("Store Is Open");
        }

        String orderType=null;
        orderType=Paper.book().read(Constants.DeliveryType);
        if (orderType!=null){
            if (orderType.equals("delivery")){
                imageView_Cart.setImageResource(R.drawable.delivery_icon);
            }
            else if (orderType.equals("collection")){
                imageView_Cart.setImageResource(R.drawable.collection);
            }
        }

    }
    public Date UTCToLocal(String strDate) {
        try {
           // 2019-06-11T09:00:00.000+01:00

//            2019-06-11T09:00:00.000+01:00
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = df.parse(strDate);
            return date;

//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));



        }
        catch (Exception ex)
        {
            System.out.println("Exception");
        }
        return  new Date();
    }
    private void initListeners(){
        button_HP_Order.setOnClickListener(this);
        button_HP_Delivery.setOnClickListener(this);
        button_HP_Collection.setOnClickListener(this);
        button_HP_DineIn.setOnClickListener(this);
        menuImage.setOnClickListener(this);

        imageView_HP_Location.setOnClickListener(this);
        imageView_HP_Cross.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v==button_HP_Order){
            if (validatePostalCode()){
                if (checkPostalCode(postalCode)){
                    Paper.book().write(Constants.DeliveryType,"delivery");

                    if (storeResponse.getStore().getDeliveryTempDisabled()){
                        Toast.makeText(context, "Sorry, Delivery Service is not Available Temporarily.", Toast.LENGTH_SHORT).show();
                    }else {
                        intent=new Intent(this,MenuPageTabular.class);
                        startActivity(intent);
                    }
                }
            }
        }
        else if (v==button_HP_Delivery){
            resetButtonsBG();
            resetButtonsFC();

            Paper.book().write(Constants.DeliveryType,"delivery");
            imageView_Cart.setImageResource(R.drawable.delivery_icon);

            button_HP_Delivery.setBackgroundColor(getResources().getColor(R.color.lightGrayColor));
            button_HP_Delivery.setTextColor(getResources().getColor(R.color.colorBlack));

            if (storeResponse.getStore().getDeliveryTempDisabled()){
                Toast.makeText(context, "Sorry, Delivery Service is not Available Temporarily.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (v==button_HP_Collection){
            resetButtonsBG();
            resetButtonsFC();
            button_HP_Collection.setBackgroundColor(getResources().getColor(R.color.lightGrayColor));
            button_HP_Collection.setTextColor(getResources().getColor(R.color.colorBlack));

            Paper.book().write(Constants.DeliveryType,"collection");
            imageView_Cart.setImageResource(R.drawable.collection);

            if (storeResponse.getStore().getCollectionTempDisabled()){
                Toast.makeText(context, "Sorry, Collection Service is not Available Temporarily.", Toast.LENGTH_SHORT).show();
            }else {
                intent=new Intent(this,MenuPageTabular.class);
                startActivity(intent);
            }

        }
        else if (v==button_HP_DineIn){
            resetButtonsBG();
            resetButtonsFC();
            button_HP_DineIn.setBackgroundColor(getResources().getColor(R.color.lightGrayColor));
            button_HP_DineIn.setTextColor(getResources().getColor(R.color.colorBlack));
        }
        else if (v==menuImage){
            drawer.openDrawer(Gravity.START);
        }
        else if (v==imageView_HP_Cross){
            editText_PostalCode.setText("");
        }
        else if (v==imageView_HP_Location){
            if (isProviderAvailable() && (provider != null)) {
                if (hasPermissions(context,PERMISSIONS)){
                    ActivityCompat.requestPermissions(activity, PERMISSIONS, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
                }
            }
            else {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
                Toast.makeText(this, "Not satisfied:" + isProviderAvailable(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void hideButtons(){
        button_HP_Delivery.setVisibility(View.GONE);
        button_HP_Collection.setVisibility(View.GONE);
    }
    private void showButtons(dtoMyStoreResponse storeResponse){
        dtoMyStore myStore=storeResponse.getStore();
        if (myStore.getOfferDelivery()){

            String orderType= Paper.book().read(Constants.DeliveryType);
            if (orderType==null){
                Paper.book().write(Constants.DeliveryType,"delivery");
            }

            button_HP_Delivery.setVisibility(View.VISIBLE);

            button_HP_Delivery.setBackgroundColor(getResources().getColor(R.color.lightGrayColor));
            button_HP_Delivery.setTextColor(getResources().getColor(R.color.colorBlack));
        }
        if (myStore.getOfferCollection()){
            button_HP_Collection.setVisibility(View.VISIBLE);
        }
    }
    private void resetButtonsBG(){
        button_HP_Collection.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        button_HP_Delivery.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        button_HP_DineIn.setBackgroundColor(getResources().getColor(R.color.colorBlack));
    }
    private void resetButtonsFC(){
        button_HP_Delivery.setTextColor(getResources().getColor(R.color.fontColor));
        button_HP_DineIn.setTextColor(getResources().getColor(R.color.fontColor));
        button_HP_Collection.setTextColor(getResources().getColor(R.color.fontColor));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locateCurrentPosition();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    public void onLocationChanged(Location location) {

    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
    @Override
    public void onProviderEnabled(String provider) {

    }
    @Override
    public void onProviderDisabled(String provider) {

    }
    /*Get user Current location*/
    private boolean isProviderAvailable() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        provider = mLocationManager.getBestProvider(criteria, true);
        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
            return true;
        }
        if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
            return true;
        }
        if (provider != null) {
            return true;
        }
        return false;
    }
    private void locateCurrentPosition() {

        int status = getPackageManager().checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION, getPackageName());

        if (status == PackageManager.PERMISSION_GRANTED) {

            Location location = mLocationManager.getLastKnownLocation(provider);
            updateWithNewLocation(location);
            long minTime = 5000;// ms
            float minDist = 5.0f;// meter
            mLocationManager.requestLocationUpdates(provider, minTime, minDist, this);
        }
    }
    private void updateWithNewLocation(Location location) {

        if (location != null && provider != null) {
            double lng = location.getLongitude();
            double lat = location.getLatitude();
            getAddress(location.getLatitude(),location.getLongitude());

        } else {
            Log.d("Location error", "Something went wrong");
        }

        return;
    }
    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String   add = obj.getAddressLine(0);
            String  currentAddress = obj.getSubAdminArea() + ","
                    + obj.getAdminArea();
            double   latitude = obj.getLatitude();
            double longitude = obj.getLongitude();
            String currentCity= obj.getSubAdminArea();
            String currentState= obj.getAdminArea();
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();

            userPostalCode=obj.getPostalCode();
            editText_PostalCode.setText(userPostalCode);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    /*Check for Location Pemissions*/
    private void checkForPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);
        } else {
            locateCurrentPosition();
        }
    }
    public boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_FINE_LOCATION);
                }
            }
        }
        return true;
    }
    /*Get all data for store*/
    private void getStoreInformation(){
        Call<dtoMyStoreResponse> storeInformationCall = apiService.getStoreInformation();
        RestRequestCallback callbackObject = new RestRequestCallback(HomePage.this, (ServerConnectListener) this, StoreInformation_PostRequest);
        storeInformationCall.enqueue(callbackObject);
    }
    /*Get PostCodes for Delivery*/
    private void getDeliveryPostCodes(){
        Call<dtoDeliveryPostCodeResponse> deliveryPostCodeCall = apiService.getDeliveryPostCode();
        RestRequestCallback callbackObject = new RestRequestCallback(HomePage.this, (ServerConnectListener) this, DeliveryPostCode_GetRequest);
        deliveryPostCodeCall.enqueue(callbackObject);
    }
    private boolean checkPostalCode(String postalCode){

        dtoMyStoreResponse  myStoreResponse=Paper.book().read(Constants.StoreInformation);
        String regex = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(postalCode);
        if(matcher.matches()) {
            List<dtoDeliveryPostcode> DeliveryPostCodeList = myStoreResponse.getStore().getDeliveryPostcodes();
            for (dtoDeliveryPostcode temp : DeliveryPostCodeList) {
                if(postalCode.contains(temp.getPostcode())){
                    Paper.book().write(Constants.SelectedPostalCode,temp);//Store Selected PostalCode Object
                    return true;
                }
            }
         }


        Snackbar.make(CoordinateLayout_Root,"Sorry, we do not serve this postcode.", Snackbar.LENGTH_INDEFINITE).show();
//        Toast.makeText(context, "Sorry, we do not serve this postcode.", Toast.LENGTH_SHORT).show();
        return false;
    }
    private boolean validatePostalCode(){
        postalCode=editText_PostalCode.getText().toString();
        if (postalCode!=""){
            postalCode=postalCode.toUpperCase();
            return true;
        }
        return false;
    }
    @Override
    public void onFailure(String error, RequestCode requestCode) {
        if (requestCode==DeliveryPostCode_GetRequest){
            Toast.makeText(context, "Un-able to get postal Codes for Delivery."+error, Toast.LENGTH_SHORT).show();
        }
        else if (requestCode==StoreInformation_PostRequest){
            Toast.makeText(context, "Un-able to get Store information."+error, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onSuccess(Object object, RequestCode requestCode) {
        if (requestCode==DeliveryPostCode_GetRequest){
            dtoDeliveryPostCodeResponse  deliveryPostCodeResponse = (dtoDeliveryPostCodeResponse) object;

            if (deliveryPostCodeResponse!=null){
                Paper.book().write(Constants.DeliveryPostalCode,deliveryPostCodeResponse);
            }
        }
        else if (requestCode==StoreInformation_PostRequest){
            dtoMyStoreResponse  myStoreResponse = (dtoMyStoreResponse) object;

            if (myStoreResponse!=null){
                Constants.StoreId=myStoreResponse.getStore().getId();
                Paper.book().write(Constants.StoreInformation,myStoreResponse);
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            return true;
        }
        return false;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTrigger(Events.cartBadgeCount cartCount) {
        int count = cartCount.getBadgeCount();
        badge = findViewById(R.id.badge);
        badge.setNumber(count);

        textView_TotalPrice=findViewById(R.id.textView_TotalPrice);
        double cartTotalAmount=Constants.calculateTotalPrice();
        String price=String.format("%.2f", cartTotalAmount);
        textView_TotalPrice.setText(getString(R.string.Currency)+price);
    }
    @Override
    public void onRestart() {
        super.onRestart();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}
