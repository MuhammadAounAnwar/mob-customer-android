package com.locatemybus.myorderbox.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.adapter.ListViewAdapter;
import com.locatemybus.myorderbox.Helper.Constants;
import com.locatemybus.myorderbox.Helper.Events;
import com.locatemybus.myorderbox.Helper.GlobalBus;
import com.locatemybus.myorderbox.Helper.MyOrdersEXLVAdapter;
import com.locatemybus.myorderbox.Network.ApiService;
import com.locatemybus.myorderbox.Network.RequestCode;
import com.locatemybus.myorderbox.Network.RestClient;
import com.locatemybus.myorderbox.Network.RestRequestCallback;
import com.locatemybus.myorderbox.Network.ServerConnectListener;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.Utilities.ProgressDialogHelper;
import com.locatemybus.myorderbox.dto.dtoItemOrder;
import com.locatemybus.myorderbox.dto.dtoMyOrder;
import com.locatemybus.myorderbox.dto.dtoMyOrderProduct;
import com.locatemybus.myorderbox.dto.dtoMyOrderResponse;
import com.locatemybus.myorderbox.dto.dtoOrder;
import com.locatemybus.myorderbox.dto.dtoOrderTracking;
import com.locatemybus.myorderbox.dto.dtoSignInResponse;
import com.locatemybus.myorderbox.dto.dtoTrackOrderDetails;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;

import static com.locatemybus.myorderbox.Network.RequestCode.MyOrders_PostCall;
import static com.locatemybus.myorderbox.Network.RequestCode.TrackOrderDetails_GetCall;
import static com.locatemybus.myorderbox.Network.RequestCode.TrackOrder_GetCall;

public class MyOrders extends AppCompatActivity implements View.OnClickListener, ServerConnectListener {

    MyOrdersEXLVAdapter myOrdersEXLVAdapter;
    ExpandableListView listView_MyOrder;
    ArrayList<dtoMyOrder> myOrderArrayList;
    ArrayList<dtoItemOrder> itemOrder;
    ConstraintLayout constraintLayout;
    ImageView imageView_CustomBack;
    SwipeToDismissTouchListener<ListViewAdapter> touchListener;
    TextView textView_CustomHeading;
    ConstraintLayout constraintLayout_CartLayout;

    ProgressDialogHelper mProgressDialogHelper;
    private ApiService apiService;
    private Context context;
    private Activity activity;
    Intent intent;

    HashMap<String, ArrayList<dtoMyOrder>> myOrdersHashMap = new HashMap<>();
    SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String time,date;

    dtoMyOrderResponse orderResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        GlobalBus.getBus().register(this);
        setContentView(R.layout.activity_my_orders);

        initComponent();
        initListener();


        dtoSignInResponse signInResponse=Paper.book().read(Constants.LoginInformation);
        if (signInResponse != null) {
            myOrders(signInResponse.getId());
        }else {
            intent=new Intent(this,Login.class);
            startActivity(intent);
        }



        listView_MyOrder.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                constraintLayout=findViewById(R.id.header_Panel);
                constraintLayout.setVisibility(View.GONE);
            }
        });
        listView_MyOrder.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                constraintLayout=findViewById(R.id.header_Panel);
                constraintLayout.setVisibility(View.VISIBLE);
            }
        });
        listView_MyOrder.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });

    }
    private void initComponent(){

        context = this;
        activity = this;
        mProgressDialogHelper = new ProgressDialogHelper(this);
        apiService = RestClient.getInstance(context, false, false);

        textView_CustomHeading=findViewById(R.id.textView_CustomHeading);
        textView_CustomHeading.setText(getString(R.string.MO_Heading));
        imageView_CustomBack=findViewById(R.id.imageView_CustomBack);
        listView_MyOrder=(ExpandableListView) findViewById(R.id.ExLv_MyOrder);

        constraintLayout_CartLayout=findViewById(R.id.constraintLayout_CartLayout);
        constraintLayout_CartLayout.setVisibility(View.GONE);

    }
    private void initListener(){
        imageView_CustomBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v==imageView_CustomBack){
            super.onBackPressed();
        }
    }

    /*Get all my Orders*/
    private void myOrders(int customer_Id){
        Call<dtoMyOrderResponse> getMyOrderCall = apiService.getMyOrders(customer_Id);
        RestRequestCallback callbackObject = new RestRequestCallback(MyOrders.this, (ServerConnectListener) this, MyOrders_PostCall);
        getMyOrderCall.enqueue(callbackObject);
    }
    private void setAdapter(List<dtoOrder> orders){
        myOrderArrayList=new ArrayList<>();
        for (dtoOrder order:orders){

            if (order.getDueTime()!=null){
                time=timeFormat.format(order.getDueTime());
                date=dateFormat.format(order.getDueTime());
            }else {
                time="NA";
                date="NA";
            }


            itemOrder=new ArrayList<>();
            for (dtoMyOrderProduct product:order.getProducts()){
                itemOrder.add(new dtoItemOrder(product.getQuantity(),product.getName(),product.getPrice()));
            }
            if (order.getStatus().equals("delivered")){
                myOrderArrayList.add(new dtoMyOrder(order.getId(),order.getSourceName(),order.getProducts().size(),String.valueOf(order.getSubTotal()),"Order Again",order.getStatus(),time,date,itemOrder));
            }
            else {
                myOrderArrayList.add(new dtoMyOrder(order.getId(),order.getSourceName(),order.getProducts().size(),String.valueOf(order.getSubTotal()),order.getTypeName(),order.getStatus(),time,date,itemOrder));
            }
            myOrdersHashMap.put(String.valueOf(order.getId()),myOrderArrayList);
        }
        List<String> expandableListTitle=new ArrayList<>(myOrdersHashMap.keySet());

        myOrdersEXLVAdapter=new MyOrdersEXLVAdapter(getApplicationContext(),expandableListTitle,myOrdersHashMap);
        listView_MyOrder.setAdapter(myOrdersEXLVAdapter);
        myOrdersEXLVAdapter.notifyDataSetChanged();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTrigger(Events.ordersToOrderAgain orderAgainEvent) {
        int groupPosition=orderAgainEvent.getGroupPostion();

        dtoOrder order=orderResponse.getOrders().get(groupPosition);
        Paper.book().write(Constants.SelectedItem,order);

        if (order.getStatus().equals("delivered")){
            trackOrder(order);
            getOrderDetails(order.getId());
        }
        else {
            intent=new Intent(this,OrderAgain.class);
            startActivity(intent);
        }
    }
    private void trackOrder(dtoOrder order){
        mProgressDialogHelper.showDialog("Order Tracking....",true);
        Call<dtoOrderTracking> trackOrderCall = apiService.trackOrder(order.getId());
        RestRequestCallback callbackObject = new RestRequestCallback(MyOrders.this, (ServerConnectListener) this, TrackOrder_GetCall);
        trackOrderCall.enqueue(callbackObject);
    }
    private void getOrderDetails(int orderId){
        mProgressDialogHelper.showDialog("Order Tracking....",true);
        Call<dtoTrackOrderDetails> trackOrderDetailsCallCall = apiService.trackOrderDetails(orderId);
        RestRequestCallback callbackObject = new RestRequestCallback(MyOrders.this, (ServerConnectListener) this, TrackOrderDetails_GetCall);
        trackOrderDetailsCallCall.enqueue(callbackObject);
    }

    @Override
    public void onFailure(String error, RequestCode requestCode) {
        mProgressDialogHelper.hideDialog();
        if (requestCode==MyOrders_PostCall){
            Toast.makeText(context, "Unable to get your orders...", Toast.LENGTH_LONG).show();
        }
        else if (requestCode==TrackOrder_GetCall){
            Toast.makeText(context, "Unable to track the order...", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode==TrackOrderDetails_GetCall){
            Toast.makeText(context, "Unable to get order details...", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onSuccess(Object object, RequestCode requestCode) {
        mProgressDialogHelper.hideDialog();
        if (requestCode==MyOrders_PostCall){
            orderResponse=(dtoMyOrderResponse)object;
            setAdapter(orderResponse.getOrders());
        }
        else if (requestCode==TrackOrder_GetCall){
            dtoOrderTracking orderTracking=(dtoOrderTracking)object;
            Paper.book().write(Constants.OrderTracking,orderTracking);
        }
        else if (requestCode==TrackOrderDetails_GetCall){
            dtoTrackOrderDetails trackOrderDetails=(dtoTrackOrderDetails)object;
            Paper.book().write(Constants.TrackingOrderDetails,trackOrderDetails);

            intent=new Intent(this,TrackOrder.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(this);
    }
}
