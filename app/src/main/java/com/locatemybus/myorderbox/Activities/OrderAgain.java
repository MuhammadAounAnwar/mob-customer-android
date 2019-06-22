package com.locatemybus.myorderbox.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.locatemybus.myorderbox.Helper.Constants;
import com.locatemybus.myorderbox.Helper.OrderItemAdapter;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.dto.dtoItemOrder;
import com.locatemybus.myorderbox.dto.dtoMyOrderProduct;
import com.locatemybus.myorderbox.dto.dtoOrder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.paperdb.Paper;

public class OrderAgain extends AppCompatActivity implements View.OnClickListener {

    ListView listView_TrackOrder;
    OrderItemAdapter orderItemAdapter;
    ArrayList<dtoItemOrder> itemOrder;
    Button button_OrderAgain;
    TextView textView_CustomHeading;
    ImageView imageView_CustomBack;
    String time,date;

    SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");



    TextView textView_OA_Date,textView_OA_Time,textView_OA_Item,textView_OA_Content,textView_OA_Price,textView_OA_Quantity,textView_OA_Tax,textView_OA_Size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_order_again);
        initComponent();
        initListener();

        dtoOrder order=Paper.book().read(Constants.SelectedItem);
        setValues(order);
        setAdapter(order);

    }

    private void initComponent(){
        listView_TrackOrder=findViewById(R.id.listView_OrderAgain);
        button_OrderAgain=findViewById(R.id.button_OrderAgain);
        imageView_CustomBack=findViewById(R.id.imageView_CustomBack);

        textView_CustomHeading=findViewById(R.id.textView_CustomHeading);
        textView_CustomHeading.setText(getString(R.string.OA_Heading));

        textView_OA_Date=findViewById(R.id.textView_OA_Date);
        textView_OA_Time=findViewById(R.id.textView_OA_Time);
        textView_OA_Item=findViewById(R.id.textView_OA_Item);
        textView_OA_Content=findViewById(R.id.textView_OA_Content);
        textView_OA_Price=findViewById(R.id.textView_OA_Price);
        textView_OA_Quantity=findViewById(R.id.textView_OA_Quantity);
        textView_OA_Tax=findViewById(R.id.textView_OA_Tax);
        textView_OA_Size=findViewById(R.id.textView_OA_Size);
    }
    private void initListener(){
        button_OrderAgain.setOnClickListener(this);
        imageView_CustomBack.setOnClickListener(this);
    }

    private void setValues(dtoOrder order){
        textView_OA_Item.setText(order.getSourceName());
        textView_OA_Price.setText(String.valueOf(order.getSubTotal()));
//        textView_OA_Quantity.setText(String.valueOf(order.get));
        textView_OA_Tax.setText(String.valueOf(order.getOrderTax()));

        time=timeFormat.format(order.getDueTime());
        date=dateFormat.format(order.getDueTime());

        textView_OA_Time.setText(time);
        textView_OA_Date.setText(date);


    }
    private void setAdapter(dtoOrder order){

        itemOrder=new ArrayList<>();
        for (dtoMyOrderProduct product:order.getProducts()){
            itemOrder.add(new dtoItemOrder(product.getQuantity(),product.getName(),product.getPrice()));
        }

        orderItemAdapter=new OrderItemAdapter(OrderAgain.this,itemOrder);
        listView_TrackOrder.setAdapter(orderItemAdapter);
        orderItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (v==button_OrderAgain){
            Intent intent=new Intent(this,TrackOrder.class);
            startActivity(intent);
        }else if (v==imageView_CustomBack){
            super.onBackPressed();
        }
    }

}
