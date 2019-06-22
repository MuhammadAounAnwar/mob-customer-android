package com.locatemybus.myorderbox.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class MenuDetailPage extends AppCompatActivity implements View.OnClickListener,ServerConnectListener {


    ExpandableListView expandableListView_pizzaCrust, expandableListView_pizzaSauce, expandableListView_lvExp_MO_Items;

    Button button_add,button_MDP_Price;
    ImageView imageView_MD_Dec,imageView_MD_Inc;
    MenuPagePizzaAdapter menuPagePizzaAdapter;
    TextView textView_CustomHeading,textView_MDP_Heading,textView_MDP_Content,textView_MD_Count,textView_totalAmount;
    ImageView imageView_CustomBack,imageView_MD_Image,imageView_CartImage;

    dtoProduct product;
    List<dtoProductAttribute> productAttributes;
    ArrayList<dtoMyOrder> SauceArray,ToppingArray;
    List<dtoAttributeOptions> attributeOptions;
    List<dtoMyOrder> myOrders;

    String imageUrl;
    int childPos=-1;

    ProgressDialogHelper mProgressDialogHelper;
    private ApiService apiService;
    private Context context;
    private Activity activity;

    MenuPageSauceAdapter menuPageSauceAdapter;
    MenuPageCrustAdapter menuPageCrustAdapter;

    NotificationBadge badge;
    double amount=0;
    BottomSheet bottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail_page);

        GlobalBus.getBus().register(this);

        product=Paper.book().read(Constants.SelectedItem);
        String heading=Constants.SelectedTabText+"size";
        initComponent();
        initListener();

        Constants.resetValues();
        Constants.TotalAmount=0;
        Constants.ItemAmount=0;
        Constants.ExtraAmount=0;
        Constants.ChildPosition=0;

        setItemImage(product);
        setItemAdapter(product,heading);


        /*Main Item*/
        expandableListView_lvExp_MO_Items.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });
        expandableListView_lvExp_MO_Items.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                if (childPos!=-1){
                    String heading=productAttributes.get(childPos).getName()+"   "+getString(R.string.Currency)+productAttributes.get(childPos).getPrices().get(childPos).getAmount();
                    setItemAdapter(product,heading);
                }
            }
        });
        expandableListView_lvExp_MO_Items.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                childPos=childPosition;
                if (checkOptions(productAttributes.get(childPos))){
                    button_MDP_Price.setText(getString(R.string.Currency)+productAttributes.get(childPosition).getPrices().get(childPosition).getAmount());

                    product.setAttribute(productAttributes.get(childPos));//Set Product Attribute

                    Constants.ItemAmount= Double.parseDouble(productAttributes.get(childPos).getPrices().get(childPos).getAmount());
                    amount=Constants.ItemAmount*Constants.Count;
                    ItemOptionsCall(String.valueOf(productAttributes.get(childPos).getId()));
                } else {
                    button_MDP_Price.setText(getString(R.string.Currency)+productAttributes.get(childPosition).getPrices().get(childPosition).getAmount());
                    product.setAttribute(productAttributes.get(childPos));//Set Product Attribute
                    Constants.ItemAmount= Double.parseDouble(productAttributes.get(childPos).getPrices().get(childPos).getAmount());
                    amount=Constants.ItemAmount*Constants.Count;
                }

                expandableListView_lvExp_MO_Items.collapseGroup(0);

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

        badge = findViewById(R.id.badge);
        int count=Constants.updateCartCount();
        if (count>0){
            badge.setNumber(Constants.BadgeCount);
        }

        button_add = findViewById(R.id.button_MDP_Add);
        expandableListView_pizzaCrust = findViewById(R.id.lvExp_PizzaCrust);
        expandableListView_pizzaSauce = findViewById(R.id.lvExp_PizzaSauce);
        expandableListView_lvExp_MO_Items = findViewById(R.id.lvExp_MO_Options);

        textView_CustomHeading=findViewById(R.id.textView_CustomHeading);
        textView_MD_Count=findViewById(R.id.textView_MD_Count);
        textView_MD_Count.setText("X"+ Constants.Count);
        textView_MDP_Heading=findViewById(R.id.textView_MDP_Heading);
        textView_MDP_Content=findViewById(R.id.textView_MDP_Content);

        imageView_CustomBack=findViewById(R.id.imageView_CustomBack);
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

        button_MDP_Price=findViewById(R.id.button_MDP_Price);

        textView_CustomHeading.setText(Constants.SelectedTabText);

        textView_totalAmount=findViewById(R.id.textView_totalAmount);
        String price=String.format("%.2f", Constants.CartTotalAmount);
        textView_totalAmount.setText(getString(R.string.Currency)+price);

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
    private boolean checkAttributes(dtoProduct product){
        if (product.getAttributes().size()>0){
            return true;
        }
        return false;
    }
    private void setItemAdapter(dtoProduct product,String heading){
        if (checkAttributes(product)){
            productAttributes=product.getAttributes();

            ArrayList<dtoMyOrder> myOrders = new ArrayList<>();

            for (dtoProductAttribute productAttribute:productAttributes){
                myOrders.add(new dtoMyOrder(productAttribute.getId(),productAttribute.getName(), productAttribute.getPrice(),0));
            }

            menuPagePizzaAdapter = new MenuPagePizzaAdapter(this, heading, myOrders);
            expandableListView_lvExp_MO_Items.setAdapter(menuPagePizzaAdapter);
            menuPagePizzaAdapter.notifyDataSetChanged();
        }
        else {
            textView_MDP_Heading.setText(product.getNameOnline());
            textView_MDP_Content.setText(product.getDescription());

            String price=String.format("%.2f", Double.parseDouble(product.getPrice()));
            button_MDP_Price.setText(getString(R.string.Currency)+price);

            Constants.ItemAmount=Double.parseDouble(product.getPrice());
        }

        if (childPos==-1){
            expandableListView_lvExp_MO_Items.expandGroup(0);
        }
    }
    /*
     * Get index if id exist in Selected Products
     **/
    private int idExistProducts(int id){
        int index=-1;
        if (Constants.selectedProducts!=null){
            for (int i=0;i<Constants.selectedProducts.size();i++){
                dtoProduct product=Constants.selectedProducts.get(i);
                if (product.getId()==id){
                    index=i;
                }
            }
        }

        return index;
    }
    /*
     * Get index if id exist in Selected Deals
     **/
    private int idExistDeals(int id){
        int index=-1;
        if (Constants.selectedDeals!=null){
            for (int i=0;i<Constants.selectedDeals.size();i++){
                dtoDealDetail dealDetail=Constants.selectedDeals.get(i);
                if (dealDetail.getId()==id){
                    index=i;
                }
            }
        }

        return index;
    }

    @Override
    public void onClick(View v) {
        if (v == button_add) {

            if (childPos!=-1){
                product.setItemAmount(Constants.ItemAmount+Constants.ExtraAmount);
                product.setExtraAmount(Constants.ExtraAmount);
                product.setDiscountAmount(product.getDiscountAmount());
                product.setQuantity(Constants.Count);

                Constants.TotalAmount=(Constants.ItemAmount+Constants.ExtraAmount)*Constants.Count;
                product.setTotalAmount(Constants.TotalAmount);

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
                 * Saving Item to SelectedProducts List
                 * */
                if (Constants.selectedProducts==null){
                    Constants.selectedProducts=new ArrayList<>();
                }

                int index=-1;
                index=idExistProducts(product.getId());
                if (index!=-1){
                    int quantity=Constants.selectedProducts.get(index).getQuantity();
                    Constants.selectedProducts.get(index).setQuantity(quantity+1);
                }
                else {
                    Constants.selectedProducts.add(product);
                }

                /*
                 * Store Selected Products into DB
                 * */
                Paper.book().write(Constants.SelectedProductsList,Constants.selectedProducts);

                Constants.resetValues();
                Constants.calculateTotalPrice();

                Intent intent = new Intent(this, MenuPageTabular.class);
                startActivity(intent);
            }else {
                Toast.makeText(context, "Please Select Item Size", Toast.LENGTH_SHORT).show();
            }
        }
        else if (v==imageView_CustomBack){
            super.onBackPressed();
            Constants.resetValues();
        }
        else if (v==imageView_MD_Inc){
            Constants.Count+=1;
        }
        else if (v==imageView_MD_Dec){
            if (Constants.Count>1) {
                Constants.Count-=1;
            }
        }
        textView_MD_Count.setText("X"+ Constants.Count);
        amount=(Constants.ItemAmount+Constants.ExtraAmount)*Constants.Count;
        String price=String.format("%.2f", amount);
        button_MDP_Price.setText(getString(R.string.Currency)+ price);
    }
    private boolean checkOptions(dtoProductAttribute productAttribute){
        if (productAttribute.getOption()){
            return true;
        }
            return false;
    }
    private void ItemOptionsCall(String attribute_id){
        mProgressDialogHelper.showDialog("Getting Options....",true);
        Call<dtoAttributeOptionResponse> getAttributeOptions = apiService.getProductOptions(attribute_id);
        RestRequestCallback callbackObject = new RestRequestCallback(MenuDetailPage.this, (ServerConnectListener) this, AttributeOptions_GetRequest);
        getAttributeOptions.enqueue(callbackObject);
    }
    @Override
    public void onFailure(String error, RequestCode requestCode) {
        mProgressDialogHelper.hideDialog();
        if (requestCode==AttributeOptions_GetRequest){
//            Toast.makeText(context, "Sorry..Unable to get options...", Toast.LENGTH_SHORT).show();
        }
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

        Paper.book().write(Constants.OptionsArray,attributeOptions);

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

        Paper.book().write(Constants.OptionsArray,attributeOptions);

        menuPageCrustAdapter = new MenuPageCrustAdapter(this, attributeOptions.getName(), ToppingArray);
        expandableListView_pizzaCrust.setAdapter(menuPageCrustAdapter);
        menuPageCrustAdapter.notifyDataSetChanged();
    }


    /*
     * Listeners for Crust Adapter
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTrigger(Events.AdapterToActivity adapterToActivity) {

        int childPosition=adapterToActivity.getChildPosition();
        Constants.ExtraAmount=Double.parseDouble(SauceArray.get(childPosition).getPrice())+Constants.ExtraAmount;
        Constants.TotalAmount=Constants.ExtraAmount+Constants.ItemAmount;
        Constants.TotalAmount=Constants.TotalAmount*Constants.Count;
        String price=String.format("%.2f", Constants.TotalAmount);
        button_MDP_Price.setText(getString(R.string.Currency)+ price);

        SauceArray.get(childPosition).setItemCount(1);
        Paper.book().write(Constants.SauceArray,SauceArray);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTrigger(Events.counterDecrement counterDecrement) {

        int childPosition=counterDecrement.getChildPosition();
        if (Constants.ExtraAmount!=0){
            Constants.ExtraAmount=Constants.ExtraAmount-Double.parseDouble(SauceArray.get(childPosition).getPrice());
        }
        Constants.TotalAmount=Constants.ExtraAmount+Constants.ItemAmount;
        Constants.TotalAmount=Constants.TotalAmount*Constants.Count;
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
        Constants.TotalAmount=Constants.TotalAmount*Constants.Count;
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
        Constants.TotalAmount=Constants.TotalAmount*Constants.Count;
        String price=String.format("%.2f", Constants.TotalAmount);
        button_MDP_Price.setText(getString(R.string.Currency)+ price);

        ToppingArray.get(childPosition).setItemCount(0);
        Paper.book().write(Constants.ToppingArray,ToppingArray);

    }

}
