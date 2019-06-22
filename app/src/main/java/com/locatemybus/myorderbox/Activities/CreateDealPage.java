package com.locatemybus.myorderbox.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.locatemybus.myorderbox.Helper.Constants;
import com.locatemybus.myorderbox.Helper.DealPizzaAdapter;
import com.locatemybus.myorderbox.Helper.MenuPageAdapter;
import com.locatemybus.myorderbox.Network.ApiService;
import com.locatemybus.myorderbox.Network.RequestCode;
import com.locatemybus.myorderbox.Network.RestClient;
import com.locatemybus.myorderbox.Network.ServerConnectListener;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.Utilities.ProgressDialogHelper;
import com.locatemybus.myorderbox.dto.dtoDeal;
import com.locatemybus.myorderbox.dto.dtoDealDetail;
import com.locatemybus.myorderbox.dto.dtoGroup;
import com.locatemybus.myorderbox.dto.dtoItem;
import com.locatemybus.myorderbox.dto.dtoItemOrder;
import com.locatemybus.myorderbox.dto.dtoMenuPageItem;
import com.locatemybus.myorderbox.dto.dtoProduct;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class CreateDealPage extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener,ServerConnectListener {

    ListView listView_CD;
    ArrayList<dtoItemOrder> itemOrderArrayList;
    Button button_Deal_Add;
    DealPizzaAdapter dealPizzaAdapter;
    TextView textView_CustomHeading,textView_CD_Heading,textView_CD_Detail,textView_totalAmount;
    ImageView imageView_CustomBack,imageView_CartImage;
    List<dtoGroup> groups;
    View dialogView;

    List<dtoProduct> products=new ArrayList<>();
    String heading;
    List<dtoItem> items;
    dtoDealDetail dealDetail;
    dtoDeal deal;
    dtoProduct product;

    ProgressDialogHelper mProgressDialogHelper;
    private ApiService apiService;
    private Context context;
    private Activity activity;

    Button button_CDP_Amount;
    NotificationBadge badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_deal_page);

        deal=Paper.book().read(Constants.SelectedDeal);

        initComponent();
        initListener();

        setEmptyAdapter();
        if (Constants.AcitvityCheck==2) {
            setItemsAdapter();
            Constants.resetValues();//Resetting all values
        }

    }
    private void initComponent(){

        context = this;
        activity = this;
        mProgressDialogHelper = new ProgressDialogHelper(this);
        apiService = RestClient.getInstance(context, false, false);

        badge = findViewById(R.id.badge);
        int count=Constants.updateCartCount();
        if (count>0){
            badge.setNumber(count);
        }

        button_Deal_Add=findViewById(R.id.button_Deal_Add);

        listView_CD=findViewById(R.id.listView_CD);
        imageView_CustomBack=findViewById(R.id.imageView_CustomBack);

        imageView_CartImage=findViewById(R.id.imageView_CartImage);

        String orderType=Paper.book().read(Constants.DeliveryType);
        if (orderType.equals("delivery")){
            imageView_CartImage.setImageResource(R.drawable.delivery_icon);
        }else if (orderType.equals("collection")){
            imageView_CartImage.setImageResource(R.drawable.collection);
        }

        textView_CD_Heading=findViewById(R.id.textView_CD_Heading);
        textView_CD_Detail=findViewById(R.id.textView_CD_Detail);

        textView_CD_Heading.setText(deal.getName());
        textView_CD_Detail.setText(deal.getDescription());

        textView_CustomHeading=findViewById(R.id.textView_CustomHeading);
        textView_totalAmount=findViewById(R.id.textView_totalAmount);

        String price=String.format("%.2f", Constants.CartTotalAmount);
        textView_totalAmount.setText(getString(R.string.Currency)+price);
        textView_CustomHeading.setText(getString(R.string.CD_CreateDeal));

        button_CDP_Amount=findViewById(R.id.button_CDP_Amount);
        price=String.format("%.2f", Constants.DealTotalAmount);
        button_CDP_Amount.setText(getString(R.string.Currency)+price);

    }
    private void initListener(){
        listView_CD.setOnItemClickListener(this);
        button_Deal_Add.setOnClickListener(this);
        imageView_CustomBack.setOnClickListener(this);
    }
    private void setEmptyAdapter(){
        dealDetail= Paper.book().read(Constants.SelectedDealDetail);
        groups=dealDetail.getGroups();
        itemOrderArrayList=new ArrayList<>();
        for (dtoGroup group:groups){
            itemOrderArrayList.add(new dtoItemOrder(group.getName()));
        }
        dealPizzaAdapter=new DealPizzaAdapter(this,itemOrderArrayList);
        listView_CD.setAdapter(dealPizzaAdapter);
        dealPizzaAdapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
        if (v==button_Deal_Add){
            dtoDealDetail dealDetail=Paper.book().read(Constants.SelectedDealDetail);
            dealDetail.setMinCartValue(String.valueOf(Constants.DealTotalAmount));
            dealDetail.setQuantity(1);

            if (Constants.selectedDeals==null){
                Constants.selectedDeals=new ArrayList<>();
            }
            Constants.selectedDeals.add(dealDetail);

            /*
             * Store Selected Deals into DB
             * */
            Paper.book().write(Constants.SelectedDealsList,Constants.selectedDeals);

            int count=Constants.updateCartCount();
            if (count>0){
                badge.setNumber(count);
            }
            Constants.resetValues();
//            Constants.calculateTotalPrice();

            Intent intent=new Intent(this,MenuPageTabular.class);
            startActivity(intent);
        }
        else if (v==imageView_CustomBack){
            super.onBackPressed();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Constants.selectedPosition=position;
        mProgressDialogHelper.showDialog("Getting Products....",false);
        items=groups.get(position).getItems();
        heading=itemOrderArrayList.get(position).getHeading();

        setItemsDialog(items);
    }
    private void setItemsDialog(final List<dtoItem> items){
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        LayoutInflater inflater =getLayoutInflater();
        if (dialogView != null) {
            ViewGroup parentViewGroup = (ViewGroup) dialogView.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViewsInLayout();
            }
        }
        try {
            dialogView = inflater.inflate(R.layout.dialog_select_item, null);
        } catch (InflateException e) {
        }
        builder.setView(dialogView);
        builder.setCancelable(true);
        final android.app.AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        ListView listView=(ListView)dialogView.findViewById(R.id.listView_SelectItem);
        TextView textView_SI_Heading=(TextView) dialogView.findViewById(R.id.textView_SI_Heading);

        textView_SI_Heading.setText(heading);
        final ArrayList<dtoMenuPageItem> menuPageItems=new ArrayList<>();

        for (dtoItem item:items){
            menuPageItems.add(new dtoMenuPageItem(item.getProduct().getId(),item.getProduct().getName(),item.getProduct().getDescription(),item.getProduct().getImage().getUrl(),item.getProduct().getPrice()));
        }

        MenuPageAdapter menuPageAdapter=new MenuPageAdapter(this,menuPageItems);
        listView.setAdapter(menuPageAdapter);
        menuPageAdapter.notifyDataSetChanged();

        mProgressDialogHelper.hideDialog();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dtoMenuPageItem menuPageItem=menuPageItems.get(position);

                /*Send Data to CreateDeal_MDP Activity*/
                Paper.book().write(Constants.SelectedItem,dealDetail.getGroups().get(Constants.selectedPosition).getItems().get(position));//send item data
                Paper.book().write(Constants.SelectedGroup,dealDetail.getGroups().get(Constants.selectedPosition));
                Paper.book().write(Constants.SelectedDealItems,itemOrderArrayList);

                dialog.dismiss();
                Intent intent=new Intent(CreateDealPage.this,CreateDeal_MDP.class);
                startActivity(intent);

            }
        });
    }
    private void setItemsAdapter(){

        product=Paper.book().read(Constants.SelectedDealItem);
        itemOrderArrayList=Paper.book().read(Constants.SelectedDealItems);

        dealPizzaAdapter=new DealPizzaAdapter(this,itemOrderArrayList);
        listView_CD.setAdapter(dealPizzaAdapter);
        dealPizzaAdapter.notifyDataSetChanged();

        dtoItemOrder itemOrder=new dtoItemOrder(product.getName(),product.getName(),product.getDescription(),product.getPrice(),product.getImage().getUrl());
        itemOrderArrayList.set(Constants.selectedPosition,itemOrder);
        dealPizzaAdapter.notifyDataSetChanged();

    }


    @Override
    public void onFailure(String error, RequestCode requestCode) {

    }
    @Override
    public void onSuccess(Object object, RequestCode requestCode) {

    }

}
