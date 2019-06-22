package com.locatemybus.myorderbox.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.locatemybus.myorderbox.Activities.ui.main.SectionsPagerAdapter;
import com.locatemybus.myorderbox.Helper.Constants;
import com.locatemybus.myorderbox.Helper.Events;
import com.locatemybus.myorderbox.Helper.GlobalBus;
import com.locatemybus.myorderbox.Network.ApiService;
import com.locatemybus.myorderbox.Network.RequestCode;
import com.locatemybus.myorderbox.Network.RestClient;
import com.locatemybus.myorderbox.Network.RestRequestCallback;
import com.locatemybus.myorderbox.Network.ServerConnectListener;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.Utilities.ProgressDialogHelper;
import com.locatemybus.myorderbox.dto.dtoCategories;
import com.locatemybus.myorderbox.dto.dtoCategory;
import com.locatemybus.myorderbox.dto.dtoDealsResponse;
import com.locatemybus.myorderbox.dto.dtoDeliveryPostcode;
import com.locatemybus.myorderbox.dto.dtoListingProductsResponse;
import com.locatemybus.myorderbox.dto.dtoMyStoreResponse;
import com.nex3z.notificationbadge.NotificationBadge;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;

import io.paperdb.Paper;
import retrofit2.Call;

import static com.locatemybus.myorderbox.Network.RequestCode.CategoryProducts_GetRequest;
import static com.locatemybus.myorderbox.Network.RequestCode.Deals_GetRequest;
import static com.locatemybus.myorderbox.Network.RequestCode.StoreCategories_GetRequest;

public class MenuPageTabular extends MenuNavigation implements ServerConnectListener, TabLayout.OnTabSelectedListener, View.OnClickListener {

    int tabsCount=0;
    ViewPager viewPager;
    TabLayout tabLayout;
    SectionsPagerAdapter sectionsPagerAdapter;

    ProgressDialogHelper mProgressDialogHelper;
    private ApiService apiService;
    private Context context;
    private Activity activity;
    dtoCategories categories;

    NotificationBadge badge;

    ImageView imageView_AB_Image,imageView_Cart,menuImage;
    TextView textView_MP_Heading,textView_AB_Heading;
    String imageUrl;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_menu_page_tabular;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

//        GlobalBus.getBus().register(this);

        dtoMyStoreResponse storeResponse=Paper.book().read(Constants.StoreInformation);

        initComponent(storeResponse);
        initListener();

        dtoDeliveryPostcode deliveryPostcode=Paper.book().read(Constants.SelectedStore);
//        Integer selectedStore=deliveryPostcode.getStoreId();
        Integer selectedStore=storeResponse.getStore().getId();
        retrieveCategories(selectedStore);

    }
    private void initComponent(dtoMyStoreResponse storeResponse){

        context = this;
        activity = this;
        mProgressDialogHelper = new ProgressDialogHelper(this);
        apiService = RestClient.getInstance(context, false, false);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);

        textView_AB_Heading=findViewById(R.id.textView_AB_Heading);

        badge = findViewById(R.id.badge);
        int count=0;
        count=Constants.updateCartCount();
        if (count>0){
            badge.setNumber(count);
        }

        imageView_AB_Image=findViewById(R.id.imageView_AB_Image);
        textView_MP_Heading=findViewById(R.id.textView_MP_Heading);
        imageView_Cart=findViewById(R.id.imageView_Cart);
        menuImage=findViewById(R.id.menuImage);

        /*
        * Set Cart image According to order type
        * */
        String orderType=Paper.book().read(Constants.DeliveryType);
        if (orderType.equals("delivery")){
            imageView_Cart.setImageResource(R.drawable.delivery_icon);
        }else if (orderType.equals("collection")){
            imageView_Cart.setImageResource(R.drawable.collection);
        }

        textView_TotalPrice=findViewById(R.id.textView_TotalPrice);
        double cartTotalAmount=Constants.calculateTotalPrice();
        String price=String.format("%.2f", cartTotalAmount);
        textView_TotalPrice.setText(getString(R.string.Currency)+price);

        /*Set Company logo or Heading*/
//        dtoMyStore myStore=storeResponse.getStore();
//        imageUrl=myStore.getOnlineSettings().getLogo().getUrl();
//        if (imageUrl!=null){
//            Glide.with(this).load(AppWebServices.BASE_URL+imageUrl).into(imageView_AB_Image);
//        }else {
//            textView_AB_Heading.setText(myStore.getName());
//        }
    }
    private void initListener(){
        tabLayout.addOnTabSelectedListener(this);
        menuImage.setOnClickListener(this);
    }
    //Set Tabs Name
    private void setTabsName(dtoCategories categories){
        tabsCount=categories.getCategories().size();
//        for (dtoCategory category : categories.getCategories()){
//            tabLayout.addTab(tabLayout.newTab().setText(category.getNameOnline()));
//            Log.d("Aoun", "setTabsName: "+category.getNameOnline());
//        }
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),tabsCount);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    //For Call response
    @Override
    public void onFailure(String error, RequestCode requestCode) {
        mProgressDialogHelper.hideDialog();
        if (requestCode==StoreCategories_GetRequest){
            Toast.makeText(context, "Internet Slow--Categories Failure", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode==CategoryProducts_GetRequest){
            Toast.makeText(context, "Sorry Unable to get products.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onSuccess(Object object, RequestCode requestCode) {
        if (requestCode==StoreCategories_GetRequest){
            mProgressDialogHelper.hideDialog();
            categories=(dtoCategories)object;
            if (categories!=null){
                Paper.book().write(Constants.CategoriesNames,categories);
                setTabsName(categories);
            }
        }
        else if (requestCode==CategoryProducts_GetRequest){

            mProgressDialogHelper.hideDialog();
            Toast.makeText(context, "successfully get Products from server.", Toast.LENGTH_SHORT).show();

            dtoListingProductsResponse productsResponse=(dtoListingProductsResponse)object;

            /*
            * Store data into Hash Map
            * */
            if (productsResponse!=null){
                HashMap<String, dtoListingProductsResponse> traversedCategories=Paper.book().read(Constants.HashMap);
                if (traversedCategories==null){
                    traversedCategories=new HashMap<>();
                    traversedCategories.put(Constants.SelectedTabText,productsResponse);
                }else {
                    traversedCategories.put(Constants.SelectedTabText,productsResponse);
                }
                Paper.book().write(Constants.HashMap,traversedCategories);
            }

            /*
            * Store Data into Paper DB
            * */
            Paper.book().write(Constants.CategoryProducts,productsResponse);

            /*Sending Data to Tabular Fragment*/
            Events.ActivityToFragment activityFragmentMessage = new Events.ActivityToFragment(productsResponse);
            GlobalBus.getBus().post(activityFragmentMessage);
        }
    }
    private boolean checkCategory(){
        HashMap<String, dtoListingProductsResponse> traversedCategories=Paper.book().read(Constants.HashMap);
        if (traversedCategories!=null){
            for (String key:traversedCategories.keySet()){
                if (Constants.SelectedTabText.equals(key)){
                    dtoListingProductsResponse productsResponse=traversedCategories.get(key);
                    Paper.book().write(Constants.CategoryProducts,productsResponse);
                    return true;
                }
            }
        }
        return false;
    }
    //For tablayout tab selection
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        int tabPosition=tab.getPosition();
        Constants.SelectedTab=tab.getPosition();
        Constants.SelectedTabText=tab.getText().toString();

        /*
        * Store Selected tab text into paper DB
        * */
        Paper.book().write(Constants.SelectedTabText,Constants.SelectedTabText);

        textView_AB_Heading.setText(Constants.SelectedTabText);

        dtoCategories categories=Paper.book().read(Constants.CategoriesNames);
        dtoCategory category=categories.getCategories().get(tabPosition);

        if (checkCategory()){
            /*
             * Store Data into Paper DB
             * */
//            dtoListingProductsResponse productsResponse=Paper.book().read(Constants.CategoryProducts);
            HashMap<String, dtoListingProductsResponse> traversedCategories=Paper.book().read(Constants.HashMap);
            dtoListingProductsResponse productsResponse=traversedCategories.get(Constants.SelectedTabText);

            /*Sending Data to Tabular Fragment*/
            Events.ActivityToFragment activityFragmentMessage = new Events.ActivityToFragment(productsResponse);
            GlobalBus.getBus().post(activityFragmentMessage);
        }else {
            listingProductsCall(category.getId());
        }
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    //Get store Categories
    private void retrieveCategories(Integer store_id){
        mProgressDialogHelper.showDialog("Getting Products....",false);
        Call<dtoCategories> getCategoriesCall = apiService.getCategories(store_id);
        RestRequestCallback callbackObject = new RestRequestCallback(MenuPageTabular.this, (ServerConnectListener) this, StoreCategories_GetRequest);
        getCategoriesCall.enqueue(callbackObject);
    }
    /*Get Selected Category items List on tab selection*/
    private void listingProductsCall(int categoryId){
        mProgressDialogHelper.showDialog("Getting Products....",true);
        Call<dtoListingProductsResponse> getCategoryProducts = apiService.getProductsList(categoryId);
        RestRequestCallback callbackObject = new RestRequestCallback(MenuPageTabular.this, (ServerConnectListener) this, CategoryProducts_GetRequest);
        getCategoryProducts.enqueue(callbackObject);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            return true;
        }
        return false;
    }

    /*
    *Get Deals List on tab selection
    **/
    private void listingDealsCall(){
        mProgressDialogHelper.showDialog("Getting Deals....",true);
        Call<dtoDealsResponse> getDealsCall = apiService.getDealsList();
        RestRequestCallback callbackObject = new RestRequestCallback(MenuPageTabular.this, (ServerConnectListener) this, Deals_GetRequest);
        getDealsCall.enqueue(callbackObject);
    }
    /*
    *get All categories products list
    **/
    private void listingProducts(dtoCategories categories){
        if (categories!=null){
            ArrayList<dtoCategory> categoryArrayList=categories.getCategories();
            for (dtoCategory category:categoryArrayList){
                listingProductsCall(category.getId());
            }
        }
    }
    @Override
    public void onClick(View v) {
        if (v==menuImage){
            drawer.openDrawer(Gravity.START);
        }
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