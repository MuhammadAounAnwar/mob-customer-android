package com.locatemybus.myorderbox.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.locatemybus.myorderbox.Helper.CheckOutAdapter;
import com.locatemybus.myorderbox.Helper.Constants;
import com.locatemybus.myorderbox.Network.ApiService;
import com.locatemybus.myorderbox.Network.RequestCode;
import com.locatemybus.myorderbox.Network.RestClient;
import com.locatemybus.myorderbox.Network.RestRequestCallback;
import com.locatemybus.myorderbox.Network.ServerConnectListener;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.Utilities.ProgressDialogHelper;
import com.locatemybus.myorderbox.dto.dtoAddress;
import com.locatemybus.myorderbox.dto.dtoDealDetail;
import com.locatemybus.myorderbox.dto.dtoDeliveryPostcode;
import com.locatemybus.myorderbox.dto.dtoGroup;
import com.locatemybus.myorderbox.dto.dtoItem;
import com.locatemybus.myorderbox.dto.dtoItemOrder;
import com.locatemybus.myorderbox.dto.dtoMyStoreResponse;
import com.locatemybus.myorderbox.dto.dtoOR_Item;
import com.locatemybus.myorderbox.dto.dtoOR_Order;
import com.locatemybus.myorderbox.dto.dtoOR_Product;
import com.locatemybus.myorderbox.dto.dtoOrderRequest;
import com.locatemybus.myorderbox.dto.dtoOrderResponse;
import com.locatemybus.myorderbox.dto.dtoProduct;
import com.locatemybus.myorderbox.dto.dtoSignInResponse;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;

import static com.locatemybus.myorderbox.Network.RequestCode.PlaceOrder_PostCall;

public class CheckOut extends AppCompatActivity implements View.OnClickListener,ServerConnectListener {

    View dialogView;
    Button button_CO_Cash,button_CO_Card,button_CO_Pay;
    TextView textView_CustomHeading,textView_CO_TotalPrice,editText_CO_Notes;
    ImageView imageView_CustomBack;
    ConstraintLayout constraintLayout_CardFields;
    String[] month = { "Expiry Month", "Jan", "Feb", "Mar", "April"};
    String[] year = { "Expiry Year", "2019", "2020", "2021", "2022"};
    Spinner spinner_Year,spinner_Month;

    ProgressDialogHelper mProgressDialogHelper;
    private ApiService apiService;
    private Context context;
    private Activity activity;

    dtoOrderRequest orderRequest;
    dtoOR_Order order;
    String house,street,city,postalCode,notes;

    ConstraintLayout constraintLayout_CartLayout,constraintLayout_CO_Address;

    EditText editText_CO_House,editText_CO_Street,editText_CO_City,editText_CO_PostalCode;
    double cartTotalAmount;
    dtoMyStoreResponse myStoreResponse;
    dtoSignInResponse signInResponse;
    dtoDeliveryPostcode postcode;
    String orderType,orderTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        myStoreResponse=Paper.book().read(Constants.StoreInformation);
        signInResponse= Paper.book().read(Constants.LoginInformation);
        postcode=Paper.book().read(Constants.SelectedPostalCode);
        orderType=Paper.book().read(Constants.DeliveryType);
        orderTime=Paper.book().read(Constants.DeliveryTime);

//        CheckOutDialog();

        initComponent();
        initListeners();
        populateSpinner();

    }

    private void CheckOutDialog(){

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
        } catch (InflateException e) {
        }

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


        Button button_COCheckOut=(Button)dialogView.findViewById(R.id.button_COCheckOut);
        Button button_COPrice=(Button)dialogView.findViewById(R.id.button_COPrice);

        button_COCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        button_COPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        button_CO_Collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_CO_Collection.setTextColor(getResources().getColor(R.color.colorBlack));
                button_CO_Collection.setBackgroundColor(getResources().getColor(R.color.fontColor));

                button_CO_Delivery.setTextColor(getResources().getColor(R.color.fontColor));
                button_CO_Delivery.setBackgroundColor(getResources().getColor(R.color.Button_BG));

                button_CO_DineIn.setTextColor(getResources().getColor(R.color.fontColor));
                button_CO_DineIn.setBackgroundColor(getResources().getColor(R.color.Button_BG));
            }
        });

        button_CO_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_CO_Delivery.setTextColor(getResources().getColor(R.color.colorBlack));
                button_CO_Delivery.setBackgroundColor(getResources().getColor(R.color.fontColor));

                button_CO_Collection.setTextColor(getResources().getColor(R.color.fontColor));
                button_CO_Collection.setBackgroundColor(getResources().getColor(R.color.Button_BG));

                button_CO_DineIn.setTextColor(getResources().getColor(R.color.fontColor));
                button_CO_DineIn.setBackgroundColor(getResources().getColor(R.color.Button_BG));
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
            }
        });


        ArrayList<dtoItemOrder> notificationData=new ArrayList<>();
        for (int i=0;i<5;i++){
            notificationData.add(new dtoItemOrder(1,"pizza","21","Caramelized onion"));
        }

        CheckOutAdapter orderItemAdapter=new CheckOutAdapter(this,notificationData);
        listView.setAdapter(orderItemAdapter);
        orderItemAdapter.notifyDataSetChanged();

//        SwipeToDismissTouchListener<ListViewAdapter> touchListener = new SwipeToDismissTouchListener<>(
//                new ListViewAdapter(listView),
//                new SwipeToDismissTouchListener.DismissCallbacks<ListViewAdapter>() {
//                    @Override
//                    public boolean canDismiss(int position) {
//                        return true;
//                    }
//
//                    @Override
//                    public void onDismiss(ListViewAdapter view, int position) {
//                    }
//                });

//        orderItemAdapter.setOnTouchListener(touchListener);
//        orderItemAdapter.setOnScrollListener((AbsListView.OnScrollListener) touchListener.makeScrollListener());

    }

    private void initComponent(){

        context = this;
        activity = this;
        mProgressDialogHelper = new ProgressDialogHelper(this);
        apiService = RestClient.getInstance(context, false, false);

        button_CO_Card=findViewById(R.id.button_CO_Card);
        button_CO_Cash=findViewById(R.id.button_CO_Cash);
        button_CO_Pay=findViewById(R.id.button_CO_Pay);

        imageView_CustomBack=findViewById(R.id.imageView_CustomBack);
        constraintLayout_CardFields=findViewById(R.id.constraintLayout_CardFields);

        spinner_Month=findViewById(R.id.spinner_Month);
        spinner_Year=findViewById(R.id.spinner_Year);

        editText_CO_Notes=findViewById(R.id.editText_CO_Notes);
        textView_CustomHeading=findViewById(R.id.textView_CustomHeading);
        textView_CO_TotalPrice=findViewById(R.id.textView_CO_TotalPrice);
        textView_CustomHeading.setText(getString(R.string.CO_Heading));



        constraintLayout_CartLayout=findViewById(R.id.constraintLayout_CartLayout);
        constraintLayout_CartLayout.setVisibility(View.GONE);

        editText_CO_House=findViewById(R.id.editText_CO_House);
        editText_CO_Street=findViewById(R.id.editText_CO_Street);
        editText_CO_City=findViewById(R.id.editText_CO_City);
        editText_CO_PostalCode=findViewById(R.id.editText_CO_PostalCode);

        constraintLayout_CO_Address=findViewById(R.id.constraintLayout_CO_Address);
        if (orderType.equals("delivery")){
            constraintLayout_CO_Address.setVisibility(View.VISIBLE);
        }
        if (orderType.equals("collection")){
            constraintLayout_CO_Address.setVisibility(View.GONE);
        }

        cartTotalAmount=Constants.calculateTotalPrice();
        cartTotalAmount=cartTotalAmount+Double.parseDouble(myStoreResponse.getStore().getOnlineSettings().getService_charge_fee());
        if (orderType.equals("delivery")){
            cartTotalAmount=cartTotalAmount+Double.parseDouble(postcode.getCharge());
        }

        String price=String.format("%.2f", cartTotalAmount);
        textView_CO_TotalPrice.setText(getString(R.string.Currency)+price);
    }
    private void initListeners(){
        button_CO_Card.setOnClickListener(this);
        button_CO_Cash.setOnClickListener(this);
        button_CO_Pay.setOnClickListener(this);

        imageView_CustomBack.setOnClickListener(this);
    }
    private void populateSpinner(){
        ArrayAdapter arrayAdapter_Month = new ArrayAdapter(this,android.R.layout.simple_spinner_item,month);
        arrayAdapter_Month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Month.setAdapter(arrayAdapter_Month);

        ArrayAdapter arrayAdapter_Year = new ArrayAdapter(this,android.R.layout.simple_spinner_item,year);
        arrayAdapter_Year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Year.setAdapter(arrayAdapter_Year);
    }
    @Override
    public void onClick(View v) {
        if (v==button_CO_Card){

            button_CO_Card.setBackgroundColor(getResources().getColor(R.color.Button_BG));
            button_CO_Cash.setBackgroundColor(getResources().getColor(R.color.colorBlack));


            constraintLayout_CardFields.setVisibility(View.VISIBLE);
        }
        else if (v==button_CO_Cash){

            button_CO_Card.setBackgroundColor(getResources().getColor(R.color.colorBlack));
            button_CO_Cash.setBackgroundColor(getResources().getColor(R.color.Button_BG));

            constraintLayout_CardFields.setVisibility(View.GONE);

        }
        else if (v==button_CO_Pay){


            house=editText_CO_House.getText().toString();
            city=editText_CO_City.getText().toString();
            street=editText_CO_Street.getText().toString();
            postalCode=editText_CO_PostalCode.getText().toString();
            notes=editText_CO_Notes.getText().toString();

            Double min=Double.parseDouble(myStoreResponse.getStore().getMinOrder());

            if (Constants.CartTotalAmount >= min){
                if (!house.equals("") && !street.equals("") && !city.equals("") && !postalCode.equals("") && !notes.equals("")){
                    List<dtoOR_Item> items=new ArrayList<>();

                    /*
                     * Add Product items
                     * */

                    if (Constants.selectedProducts!=null){
                        for (int i=0;i<Constants.selectedProducts.size();i++){
                            dtoProduct product=Constants.selectedProducts.get(i);

                            List<dtoOR_Product> products=new ArrayList<>();

                            dtoOR_Product or_product=new dtoOR_Product(product.getName(),null,String.valueOf(product.getItemAmount()),true,"0",product.getQuantity(),null,false,"p_"+product.getId(),String.valueOf(product.getId()),null);
                            if (product.getHasAttributes()){
                                or_product.setAttributeId(String.valueOf(product.getAttribute().getId()));
                            }
                            if (product.getAttributeOptions()!=null){
                                or_product.setOptionValues(product.getAttributeOptions());
                            }
                            products.add(or_product);
                            items.add(new dtoOR_Item(String.valueOf(product.getQuantity()),products,String.valueOf(product.getTotalAmount())));
                        }
                    }

                    /*
                     * Add Deal Items
                     * */

                    if (Constants.selectedDeals!=null){
                        for (int i=0;i<Constants.selectedDeals.size();i++){
                            dtoDealDetail dealDetail=Constants.selectedDeals.get(i);
                            List<dtoOR_Product> products=new ArrayList<>();
                            for (dtoGroup group:dealDetail.getGroups()){
                                for (dtoItem item:group.getItems()){
                                    dtoProduct product=item.getProduct();
                                    dtoOR_Product or_product=new dtoOR_Product(product.getName(),null,String.valueOf(product.getItemAmount()),true,"0",product.getQuantity(),null,false,"p_"+product.getId(),String.valueOf(product.getId()),null);
//                            if (product.getHasAttributes()){
//                                or_product.setAttributeId(String.valueOf(product.getAttribute().getId()));
//                            }
                                    if (product.getAttributeOptions()!=null){
                                        or_product.setOptionValues(product.getAttributeOptions());
                                    }
                                    products.add(or_product);
                                }
                            }
                            items.add(new dtoOR_Item(String.valueOf(dealDetail.getQuantity()),"0.0",dealDetail.getId(),dealDetail.getName(),products));
                        }
                    }


                    dtoAddress address=new dtoAddress();

                    if (orderType.equals("delivery")){
                        address.setAddrType("home");
                        address.setLine1(house+" "+street);
                        address.setLine2(house+" "+street);
                        address.setPostCode(postalCode);
                        address.setCity(city);
                    }

                    order=new dtoOR_Order();
                    order.setCustomerId(String.valueOf(signInResponse.getId()));

                    order.setSubTotal(String.valueOf(cartTotalAmount));
                    order.setTotalPrice(String.valueOf(Constants.CartTotalAmount));
                    order.setOrderType(orderType);
                    order.setNotes(notes);
                    order.setCart_string(Constants.getRandomString(10));
                    order.setStore_id(String.valueOf(myStoreResponse.getStore().getId()));
                    order.setAddress(address);
                    order.setItems(items);
                    order.setDueTime(orderTime);

                    orderRequest=new dtoOrderRequest();
                    orderRequest.setOrder(order);

                    placeOrder(orderRequest);
                }
                else if (house.equals("") || street.equals("") || city.equals("") || postalCode.equals("")){
                    Toast.makeText(context, "Some address field is missing.", Toast.LENGTH_SHORT).show();
                }
                else if (notes.equals("")){
                    Toast.makeText(context, "Notes Field is Empty.", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(context, "Your Order amount doest not meet min order amount.", Toast.LENGTH_SHORT).show();
            }


        }
        else if (v==imageView_CustomBack){
            super.onBackPressed();
        }
    }
    private void placeOrder(dtoOrderRequest orderRequest){
        mProgressDialogHelper.showDialog("Placing Order....",true);
        Call<dtoOrderResponse> registerCall = apiService.placeOrder(orderRequest);
        RestRequestCallback callbackObject = new RestRequestCallback(CheckOut.this, (ServerConnectListener) this, PlaceOrder_PostCall);
        registerCall.enqueue(callbackObject);
    }

    @Override
    public void onFailure(String error, RequestCode requestCode) {
        if (requestCode==PlaceOrder_PostCall){
            Toast.makeText(context, "Unable to place your order...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(Object object, RequestCode requestCode) {
        if (requestCode==PlaceOrder_PostCall){
            dtoOrderResponse orderRequest=(dtoOrderResponse)object;

            Constants.resetValues();
            Constants.selectedProducts.clear();
            Constants.BadgeCount=0;
            Constants.CartTotalAmount=0;

            Paper.book().delete(Constants.HashMap);
            Paper.book().delete(Constants.SelectedProductsList);
            Paper.book().delete(Constants.SelectedDealsList);

            Intent intent=new Intent(this,MenuPageTabular.class);
            startActivity(intent);
        }
    }


}
