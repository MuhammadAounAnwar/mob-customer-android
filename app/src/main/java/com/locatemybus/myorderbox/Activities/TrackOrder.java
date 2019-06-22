package com.locatemybus.myorderbox.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.locatemybus.myorderbox.Helper.Constants;
import com.locatemybus.myorderbox.Helper.OrderItemAdapter;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.dto.dtoAddress;
import com.locatemybus.myorderbox.dto.dtoItemOrder;
import com.locatemybus.myorderbox.dto.dtoMyOrderProduct;
import com.locatemybus.myorderbox.dto.dtoOrder;
import com.locatemybus.myorderbox.dto.dtoOrderTracking;
import com.locatemybus.myorderbox.dto.dtoTrackOrderDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.paperdb.Paper;

public class TrackOrder extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    ListView listView_TrackOrder;
    OrderItemAdapter orderItemAdapter;
    ArrayList<dtoItemOrder> itemOrder;
    TextView textView_CustomHeading,textView_TO_Name,textView_TO_Time,textView_TO_Address,textView_TO_ItemName,textView_TO_ItemContent,textView_TO_Size,textView_TO_Preparing;
    ImageView imageView_CustomBack;

    LinearLayout linearLayout_OrderStatus;
    dtoOrder order;
    dtoOrderTracking orderTracking;
    dtoTrackOrderDetails trackOrderDetails;
    SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
    String time,customerAddress;

    private static final String MAP_VIEW_BUNDLE_KEY = "asd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);

        order= Paper.book().read(Constants.SelectedItem);
        orderTracking=Paper.book().read(Constants.OrderTracking);
        trackOrderDetails=Paper.book().read(Constants.TrackingOrderDetails);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(getString(R.string.google_maps_key));
        }
        MapView mMapView = findViewById(R.id.TrackOrder_Map);
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);

        initComponent(order);
        initListener();
        setValues();
        setAdapter(order);
    }

    private void initComponent(dtoOrder order){
        listView_TrackOrder=findViewById(R.id.listView_TrackOrder);
        imageView_CustomBack=findViewById(R.id.imageView_CustomBack);
        linearLayout_OrderStatus=findViewById(R.id.linearLayout_OrderStatus);

        textView_TO_Name=findViewById(R.id.textView_TO_Name);
        textView_TO_Time=findViewById(R.id.textView_TO_Time);
        textView_TO_Address=findViewById(R.id.textView_TO_Address);
        textView_TO_ItemName=findViewById(R.id.textView_TO_ItemName);
        textView_TO_ItemContent=findViewById(R.id.textView_TO_ItemContent);
        textView_TO_Size=findViewById(R.id.textView_TO_Size);
        textView_TO_Preparing=findViewById(R.id.textView_TO_Preparing);
        textView_CustomHeading=findViewById(R.id.textView_CustomHeading);
        textView_CustomHeading.setText(getString(R.string.TO_Heading));





        if (order.getStatus().equals("sent")) {
            linearLayout_OrderStatus.setVisibility(View.GONE);
            textView_TO_Preparing.setVisibility(View.VISIBLE);
        }
        else {
            linearLayout_OrderStatus.setVisibility(View.VISIBLE);
            textView_TO_Preparing.setVisibility(View.GONE);
        }
        if (order.getTypeCode().equals("collection")){

        }
    }
    private void initListener(){
        imageView_CustomBack.setOnClickListener(this);
    }

    private void setValues(){
        textView_TO_Name.setText(orderTracking.getTracking().getDriver().getFullName());
        if (order.getDueTime()!=null){
            time=timeFormat.format(order.getDueTime());

        }else {
            time="NA";
        }
        textView_TO_Time.setText(time);

        dtoAddress address = trackOrderDetails.getOrder().getAddress();
        customerAddress=address.getLine1()+" , "+address.getCity();
    }

    private void setAdapter(dtoOrder order){
        itemOrder=new ArrayList<>();
        for (dtoMyOrderProduct myOrderProduct:order.getProducts()){
            itemOrder.add(new dtoItemOrder(myOrderProduct.getQuantity(),myOrderProduct.getName(),myOrderProduct.getPrice()));
        }

        orderItemAdapter=new OrderItemAdapter(TrackOrder.this,itemOrder);
        listView_TrackOrder.setAdapter(orderItemAdapter);
        orderItemAdapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
        if (v==imageView_CustomBack){
            super.onBackPressed();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double lat= orderTracking.getTracking().getStore().getLat();
        double lng= orderTracking.getTracking().getStore().getLng();
        LatLng ResLocation = new LatLng(lat,lng);
        googleMap.addMarker(new MarkerOptions().position(ResLocation));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ResLocation));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ResLocation,14));
    }
}
