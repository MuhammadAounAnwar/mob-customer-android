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
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.locatemybus.myorderbox.Helper.BottomSheet;
import com.locatemybus.myorderbox.Helper.Constants;
import com.locatemybus.myorderbox.Helper.Events;
import com.locatemybus.myorderbox.Helper.GlobalBus;
import com.locatemybus.myorderbox.Helper.MenuPageCrustAdapter;
import com.locatemybus.myorderbox.Helper.MenuPagePizzaAdapter;
import com.locatemybus.myorderbox.Helper.MenuPageSauceAdapter;
import com.locatemybus.myorderbox.Network.ApiService;
import com.locatemybus.myorderbox.Network.AppWebServices;
import com.locatemybus.myorderbox.Network.RequestCode;
import com.locatemybus.myorderbox.Network.RestClient;
import com.locatemybus.myorderbox.Network.RestRequestCallback;
import com.locatemybus.myorderbox.Network.ServerConnectListener;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.Utilities.ProgressDialogHelper;
import com.locatemybus.myorderbox.dto.dtoAttributeOptionResponse;
import com.locatemybus.myorderbox.dto.dtoAttributeOptionValue;
import com.locatemybus.myorderbox.dto.dtoAttributeOptions;
import com.locatemybus.myorderbox.dto.dtoDealDetail;
import com.locatemybus.myorderbox.dto.dtoGroup;
import com.locatemybus.myorderbox.dto.dtoItem;
import com.locatemybus.myorderbox.dto.dtoMyOrder;
import com.locatemybus.myorderbox.dto.dtoProduct;
import com.locatemybus.myorderbox.dto.dtoProductAttribute;
import com.nex3z.notificationbadge.NotificationBadge;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;

import static com.locatemybus.myorderbox.Network.RequestCode.AttributeOptions_GetRequest;

public class CreateDeal_MDP extends AppCompatActivity implements View.OnClickListener, ServerConnectListener {
    ExpandableListView expandableListView_pizzaCrust, expandableListView_pizzaSauce, expandableListView_lvExp_MO_Items;

    Button button_add,button_MDP_Price;
    MenuPagePizzaAdapter menuPagePizzaAdapter;
//    LinearLayout linearLayout,linearLayout_MD_Topping,linearLayout_MD_Crust,linearLayout_MD_Item;
    TextView textView_CustomHeading,textView_MD_Price,textView_MD_Count,textView_MDP_Heading,textView_MDP_Content,textView_totalAmount;
    ImageView imageView_CustomBack,imageView_MD_Image,imageView_MD_Dec,imageView_MD_Inc,imageView_CartImage;

    int PizzatotalHeight=0,PizzadefaultHeight=0,sauceTotalHeight=0,sauceDefaultHeight=0,crsutTotalHeight=0,crustDefaultHeight=0,childPos=0;
    View view_Pizza,view_Sauce,view_Crust;
    dtoProduct product;
    dtoItem item;
    dtoGroup group;
    List<dtoProductAttribute> productAttributes;
    ArrayList<dtoMyOrder> SauceArray,ToppingArray;
    List<dtoMyOrder> myOrders;

    String imageUrl;
    ProgressDialogHelper mProgressDialogHelper;
    private ApiService apiService;
    private Context context;
    private Activity activity;
    MenuPageSauceAdapter menuPageSauceAdapter;
    MenuPageCrustAdapter menuPageCrustAdapter;

    ConstraintLayout constraintLayout_Count;

    dtoDealDetail dealDetail;
    NotificationBadge badge;
    BottomSheet bottomSheet;
    List<dtoAttributeOptions> attributeOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail_page);


        GlobalBus.getBus().register(this);
        dealDetail= Paper.book().read(Constants.SelectedDealDetail);

        item= Paper.book().read(Constants.SelectedItem);
        group= Paper.book().read(Constants.SelectedGroup);
        product=item.getProduct();

        initComponent();
        initListener();
        setItemImage(product);
        if (product.getHasAttributes()){
            setItemAdapter(product,item.getProductAttribute().getName());
            textView_MD_Count.setText(getString(R.string.Currency)+item.getProduct().getPrice());
            mProgressDialogHelper.hideDialog();
        }else {
            setItemAdapter(product,"");
        }

        expandableListView_lvExp_MO_Items.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });


        expandableListView_pizzaSauce.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                Paper.book().write(Constants.OptionsArray,attributeOptions.get(0));
                Paper.book().write(Constants.SauceArray,SauceArray);
                Constants.OptionCheck=1;

                //TODO open bottom sheet
                bottomSheet=new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());

                return true;
            }
        });
        expandableListView_pizzaCrust.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                Paper.book().write(Constants.OptionsArray,attributeOptions.get(1));
                Paper.book().write(Constants.ToppingArray,ToppingArray);

                Constants.OptionCheck=2;
                //TODO open bottom sheet
                bottomSheet=new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());

                return true;
            }
        });
    }

    private void initComponent(){

        context = this;
        activity = this;
        mProgressDialogHelper = new ProgressDialogHelper(this);
        apiService = RestClient.getInstance(context, false, false);

        button_add = findViewById(R.id.button_MDP_Add);
        expandableListView_pizzaCrust = findViewById(R.id.lvExp_PizzaCrust);
        expandableListView_pizzaSauce = findViewById(R.id.lvExp_PizzaSauce);
        expandableListView_lvExp_MO_Items = findViewById(R.id.lvExp_MO_Options);
        textView_CustomHeading=findViewById(R.id.textView_CustomHeading);
        imageView_CustomBack=findViewById(R.id.imageView_CustomBack);
        textView_CustomHeading.setText("DEAL");

//        textView_MD_Price=findViewById(R.id.textView_MD_Price);
        textView_MD_Count=findViewById(R.id.textView_MD_Count);
        textView_MDP_Heading=findViewById(R.id.textView_MDP_Heading);
        textView_MDP_Content=findViewById(R.id.textView_MDP_Content);

        imageView_MD_Inc=findViewById(R.id.imageView_MD_Inc);
        imageView_MD_Dec=findViewById(R.id.imageView_MD_Dec);
        imageView_MD_Image=findViewById(R.id.imageView_MD_Image);

        imageView_CartImage=findViewById(R.id.imageView_CartImage);

        String orderType=Paper.book().read(Constants.DeliveryType);
        if (orderType.equals("delivery")){
            imageView_CartImage.setImageResource(R.drawable.delivery_icon);
        }else if (orderType.equals("collection")){
            imageView_CartImage.setImageResource(R.drawable.collection);
        }

        constraintLayout_Count=findViewById(R.id.constraintLayout_Count);

        constraintLayout_Count.setVisibility(View.GONE);

        textView_MDP_Heading.setVisibility(View.GONE);
        textView_MDP_Content.setVisibility(View.GONE);

        button_MDP_Price=findViewById(R.id.button_MDP_Price);

        textView_totalAmount=findViewById(R.id.textView_totalAmount);
        String price=String.format("%.2f", Constants.TotalAmount);
        textView_totalAmount.setText(getString(R.string.Currency)+price);


        badge = findViewById(R.id.badge);
        int count=Constants.updateCartCount();
        if (count>0){
            badge.setNumber(count);
        }
    }

    private void initListener(){
        button_add.setOnClickListener(this);
        imageView_CustomBack.setOnClickListener(this);

        imageView_MD_Inc.setClickable(true);
        imageView_MD_Dec.setClickable(true);

        imageView_MD_Inc.setOnClickListener(this);
        imageView_MD_Dec.setOnClickListener(this);
    }
    private void setItemImage(dtoProduct product){
        if (product.getImage().getUrl()!=null){
            imageUrl=product.getImage().getUrl();
            Glide.with(this).load(AppWebServices.BASE_URL+imageUrl).into(imageView_MD_Image);
        }
    }
    private void setItemAdapter(dtoProduct product,String heading){

        if (item.getProductAttributeId()!=null){
            textView_MDP_Heading.setText(product.getNameOnline());
            textView_MDP_Content.setText(item.getProductAttribute().getName());

            if (group.getPricingType()!=null){
                if (group.getPricingType().equals("fixed_price")){
                    button_MDP_Price.setText(getString(R.string.Currency)+ group.getPricingValue());
                }
                else if (group.getPricingType().equals("unchanged")){
                    if (product.getHasAttributes()){
                        button_MDP_Price.setText(getString(R.string.Currency)+ item.getProductAttribute().getPrice());
                    }
                    else {
                        button_MDP_Price.setText(getString(R.string.Currency)+ product.getPrice());
                    }
                }
            }

            if (group.getPricingValue()!=null){
                Constants.ItemAmount= Double.parseDouble( group.getPricingValue());
            }

            /*Set Adapters for item options*/
            if (checkOptions(item.getProductAttribute().getId())){
                ItemOptionsCall(String.valueOf(item.getProductAttribute().getId()));
            }
        }
        else {
            textView_MDP_Heading.setText(product.getNameOnline());
            textView_MDP_Content.setText(product.getDescription());

            if (group.getPricingType()!=null){
                if (group.getPricingType().equals("fixed_price")){
                    button_MDP_Price.setText(getString(R.string.Currency)+ group.getPricingValue());
                    Constants.ItemAmount= Double.parseDouble( group.getPricingValue());
                }
                else if (group.getPricingType().equals("unchanged")){
                    if (product.getHasAttributes()){
                        button_MDP_Price.setText(getString(R.string.Currency)+ item.getProductAttribute().getPrice());
                        Constants.ItemAmount= Double.parseDouble( group.getPricingValue());
                    }
                    else {
                        button_MDP_Price.setText(getString(R.string.Currency)+ product.getPrice());
                        Constants.ItemAmount= Double.parseDouble( product.getPrice());
                    }
                }
            }

//            if (group.getPricingValue()!=null){
//                button_MDP_Price.setText("$"+ group.getPricingValue());
//                Constants.ItemAmount= Double.parseDouble( group.getPricingValue());
//            }
        }

        textView_MDP_Heading.setVisibility(View.VISIBLE);
        textView_MDP_Content.setVisibility(View.VISIBLE);

    }
    private boolean checkOptions(int id){

        for (dtoProductAttribute productAttribute:product.getAttributes()){
            if (productAttribute.getId()==id){
                return  true;
            }
        }
        return false;
    }
    private void ItemOptionsCall(String attribute_id){
        mProgressDialogHelper.showDialog("Getting Options....",true);
        Call<dtoAttributeOptionResponse> getAttributeOptions = apiService.getProductOptions(attribute_id);
        RestRequestCallback callbackObject = new RestRequestCallback(CreateDeal_MDP.this, (ServerConnectListener) this, AttributeOptions_GetRequest);
        getAttributeOptions.enqueue(callbackObject);
    }
    @Override
    public void onSuccess(Object object, RequestCode requestCode) {
        if (requestCode==AttributeOptions_GetRequest){

            dtoAttributeOptionResponse attributeOptionResponse=(dtoAttributeOptionResponse)object;
            attributeOptions=attributeOptionResponse.getProduct().getOptions();

            if (attributeOptions.size()==1){
                setCrustAdapter(attributeOptions.get(0));

                mProgressDialogHelper.hideDialog();
            }
            else if (attributeOptions.size()==2){
                setCrustAdapter(attributeOptions.get(0));
                setToppingsAdapter(attributeOptions.get(1));

                mProgressDialogHelper.hideDialog();
            }
        }
    }
    @Override
    public void onFailure(String error, RequestCode requestCode) {
        mProgressDialogHelper.hideDialog();
        if (requestCode==AttributeOptions_GetRequest){
            Toast.makeText(context, "Sorry..Unable to get options...", Toast.LENGTH_SHORT).show();
        }
    }
    private void setCrustAdapter(dtoAttributeOptions attributeOptions){

        List<dtoAttributeOptionValue> optionValues=attributeOptions.getOptionValues();

        SauceArray = new ArrayList<>();
        for (dtoAttributeOptionValue optionValue:optionValues){
            if (optionValue.getDefault()){
                SauceArray.add(new dtoMyOrder(optionValue.getId(),optionValue.getName(), optionValue.getPrice(),1));
            }else {
                SauceArray.add(new dtoMyOrder(optionValue.getId(),optionValue.getName(), optionValue.getPrice(),0));
            }
        }

        menuPageSauceAdapter = new MenuPageSauceAdapter(this, attributeOptions.getName(), SauceArray);
        expandableListView_pizzaSauce.setAdapter(menuPageSauceAdapter);
        menuPageSauceAdapter.notifyDataSetChanged();

    }
    private void setToppingsAdapter(dtoAttributeOptions attributeOptions){
        List<dtoAttributeOptionValue> optionValues=attributeOptions.getOptionValues();
        ToppingArray = new ArrayList<>();
        for (dtoAttributeOptionValue optionValue:optionValues){
            if (optionValue.getDefault()){
                ToppingArray.add(new dtoMyOrder(optionValue.getId(),optionValue.getName(), optionValue.getPrice(),1));
            }
            else {
                ToppingArray.add(new dtoMyOrder(optionValue.getId(),optionValue.getName(), optionValue.getPrice(),0));
            }
        }

        menuPageCrustAdapter = new MenuPageCrustAdapter(this, attributeOptions.getName(), ToppingArray);
        expandableListView_pizzaCrust.setAdapter(menuPageCrustAdapter);
        menuPageCrustAdapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
        if (v == button_add) {

            Paper.book().write(Constants.SelectedDealItem,product);
            Constants.AcitvityCheck=2;

            /*
            * Product object ready
            * */

            Constants.TotalAmount=Constants.ItemAmount+Constants.ExtraAmount;
            Constants.DealTotalAmount=Constants.TotalAmount+Constants.DealTotalAmount;//Deal Total Amount
            /*
             * Set attribute and attribute options into product
             * */

            SauceArray=Paper.book().read(Constants.SauceArray);
            if (SauceArray!=null){
                myOrders=new ArrayList<>();
                for (dtoMyOrder myOrder:SauceArray){
                    if (myOrder.getItemCount()==1){
                        myOrders.add(myOrder);
                    }
                }
            }

            ToppingArray=Paper.book().read(Constants.ToppingArray);
            if (ToppingArray!=null){
                for (dtoMyOrder myOrder:ToppingArray){
                    if (myOrder.getItemCount()==1){
                        myOrders.add(myOrder);
                    }
                }
            }
            product.setAttributeOptions(myOrders);

            /*
            * DealDetail ready
            * */
            dtoItem item=Paper.book().read(Constants.SelectedItem);
            dtoGroup group=Paper.book().read(Constants.SelectedGroup);

            item.setProduct(product);
            List<dtoItem> items=new ArrayList<>();
            items.add(item);
            group.setItems(items);

            List<dtoGroup> groups=dealDetail.getGroups();
            groups.set(Constants.selectedPosition,group);
            dealDetail.setGroups(groups);
            Paper.book().write(Constants.SelectedDealDetail,dealDetail);

            Constants.resetValues();

            Intent intent = new Intent(this, CreateDealPage.class);
            startActivity(intent);
        }
        else if (v==imageView_CustomBack){
            super.onBackPressed();
            Constants.resetValues();
        }
        else if (v==imageView_MD_Inc){
            Constants.Count+=1;
            textView_MD_Count.setText("X"+ Constants.Count);
        }
        else if (v==imageView_MD_Dec){
            if (Constants.Count>1) {
                Constants.Count-=1;
                textView_MD_Count.setText("X"+ Constants.Count);
            }
        }
    }

    /*
     * Listeners for Crust Adapter
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTrigger(Events.AdapterToActivity adapterToActivity) {

        int childPosition=adapterToActivity.getChildPosition();
        Constants.ExtraAmount=Double.parseDouble(SauceArray.get(childPosition).getPrice())+Constants.ExtraAmount;
        Constants.TotalAmount=Constants.ExtraAmount+Constants.ItemAmount;
        String price=String.format("%.2f", Constants.TotalAmount);
        button_MDP_Price.setText(getString(R.string.Currency)+ price);

        SauceArray.get(childPosition).setItemCount(1);
        Paper.book().write(Constants.SauceArray,SauceArray);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTrigger(Events.counterDecrement counterDecrement) {

        int childPosition=counterDecrement.getChildPosition();
        Constants.ExtraAmount=Constants.ExtraAmount-Double.parseDouble(SauceArray.get(childPosition).getPrice());
        Constants.TotalAmount=Constants.ExtraAmount+Constants.ItemAmount;
        String price=String.format("%.2f", Constants.TotalAmount);
        button_MDP_Price.setText(getString(R.string.Currency)+ price);

        SauceArray.get(childPosition).setItemCount(0);
        Paper.book().write(Constants.SauceArray,SauceArray);
    }

    /*
     * Listeners for Topping Adapter
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTrigger(Events.ToppingIncrement toppingIncrement) {

        int childPosition=toppingIncrement.getChildPosition();
        Constants.ExtraAmount=Double.parseDouble(ToppingArray.get(childPosition).getPrice())+Constants.ExtraAmount;
        Constants.TotalAmount=Constants.ExtraAmount+Constants.ItemAmount;
        String price=String.format("%.2f", Constants.TotalAmount);
        button_MDP_Price.setText(getString(R.string.Currency)+ price);

        ToppingArray.get(childPosition).setItemCount(1);
        Paper.book().write(Constants.ToppingArray,ToppingArray);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTrigger(Events.ToppingDecrement toppingDecrement) {

        int childPosition=toppingDecrement.getChildPosition();
        Constants.ExtraAmount=Constants.ExtraAmount-Double.parseDouble(ToppingArray.get(childPosition).getPrice());
        Constants.TotalAmount=Constants.ExtraAmount+Constants.ItemAmount;
        String price=String.format("%.2f", Constants.TotalAmount);
        button_MDP_Price.setText(getString(R.string.Currency)+ price);

        ToppingArray.get(childPosition).setItemCount(0);
        Paper.book().write(Constants.ToppingArray,ToppingArray);
    }

}
