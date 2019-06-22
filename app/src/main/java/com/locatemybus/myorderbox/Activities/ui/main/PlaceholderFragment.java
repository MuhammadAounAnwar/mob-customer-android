package com.locatemybus.myorderbox.Activities.ui.main;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.locatemybus.myorderbox.Activities.CreateDealPage;
import com.locatemybus.myorderbox.Activities.MenuDetailPage;
import com.locatemybus.myorderbox.Helper.Constants;
import com.locatemybus.myorderbox.Helper.Events;
import com.locatemybus.myorderbox.Helper.GlobalBus;
import com.locatemybus.myorderbox.Helper.MenuPageAdapter;
import com.locatemybus.myorderbox.Network.ApiService;
import com.locatemybus.myorderbox.Network.RequestCode;
import com.locatemybus.myorderbox.Network.RestClient;
import com.locatemybus.myorderbox.Network.RestRequestCallback;
import com.locatemybus.myorderbox.Network.ServerConnectListener;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.Utilities.ProgressDialogHelper;
import com.locatemybus.myorderbox.dto.dtoDeal;
import com.locatemybus.myorderbox.dto.dtoDealDetail;
import com.locatemybus.myorderbox.dto.dtoDealDetailResponse;
import com.locatemybus.myorderbox.dto.dtoDeliveryPostcode;
import com.locatemybus.myorderbox.dto.dtoItemOrder;
import com.locatemybus.myorderbox.dto.dtoListingProductsResponse;
import com.locatemybus.myorderbox.dto.dtoMenuPageItem;
import com.locatemybus.myorderbox.dto.dtoMyStore;
import com.locatemybus.myorderbox.dto.dtoMyStoreResponse;
import com.locatemybus.myorderbox.dto.dtoProduct;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.paperdb.Paper;
import retrofit2.Call;

import static com.locatemybus.myorderbox.Network.RequestCode.DealDetail_GetRequest;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements AdapterView.OnItemClickListener, ServerConnectListener {

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.pizza_banner, R.drawable.pizza_banner, R.drawable.pizza_banner, R.drawable.pizza_banner, R.drawable.pizza_banner};
    ListView listView;
    Intent intent;
    TextView textView_MP_Heading,textView_MTP_Phone,textView_MTP_Min,textView_MTP_DTime;
    ImageView imageView_AB_Image,imageView_MP_Plus,imageView_MP_Minus;
    MenuPageAdapter menuPageAdapter;
    ArrayList<dtoItemOrder> notificationData;
    ArrayList<dtoMenuPageItem> menuPageItems;
    dtoListingProductsResponse listingProductsResponse;

    List<dtoDeal> deals;
    List<dtoProduct> products;
    dtoDeal deal;
    dtoProduct product;

    ProgressDialogHelper mProgressDialogHelper;
    private ApiService apiService;
    private Context context;
    private Activity activity;

    private static final String ARG_SECTION_NUMBER = "section_number";
    private PageViewModel pageViewModel;

    ConstraintLayout constraintLayout_MP_Counter;


    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu_page_tabular, container, false);

        dtoMyStoreResponse storeResponse=Paper.book().read(Constants.StoreInformation);
        dtoDeliveryPostcode deliveryPostcode=Paper.book().read(Constants.SelectedPostalCode);
        dtoMyStore myStore=storeResponse.getStore();
        GlobalBus.getBus().register(this);

        initComponent(root,myStore,deliveryPostcode);
        initListeners();

        return root;
    }
    private void initComponent(View view,dtoMyStore myStore,dtoDeliveryPostcode deliveryPostcode){

        context = getContext();
        activity = getActivity();
        mProgressDialogHelper = new ProgressDialogHelper(context);
        apiService = RestClient.getInstance(context, false, false);

        textView_MP_Heading=view.findViewById(R.id.textView_MP_Heading);
        listView=(ListView)view.findViewById(R.id.listView_MP);
        imageView_AB_Image=view.findViewById(R.id.imageView_AB_Image);

        textView_MTP_Phone=view.findViewById(R.id.textView_MTP_Phone);
        textView_MTP_Min=view.findViewById(R.id.textView_MTP_Min);
        textView_MTP_DTime=view.findViewById(R.id.textView_MTP_DTime);

        textView_MTP_Phone.setText(myStore.getOnlineSettings().getPhone_text());

        if (deliveryPostcode!=null){
            textView_MTP_DTime.setText(getString(R.string.Currency)+deliveryPostcode.getCharge());
            textView_MTP_Min.setText(getString(R.string.Currency)+deliveryPostcode.getMinimum());
        }

        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
    }
    private void initListeners() {
        listView.setOnItemClickListener(this);
    }
    /*
    *For Products List
    **/
    private void setProductsAdapter(List<dtoProduct> products){
        menuPageItems=new ArrayList<>();
        for (dtoProduct product : products) {
            if (product.getHasAttributes()){
                if (product.getImage().getUrl()!=null){
                    menuPageItems.add(new dtoMenuPageItem(product.getId(),product.getName(),product.getDescription(),product.getImage().getUrl(),product.getAttributes().get(0).getPrice(),product.getQuantity()));
                }
                else {
                    menuPageItems.add(new dtoMenuPageItem(product.getId(),product.getName(),product.getDescription(),null,product.getAttributes().get(0).getPrice(),product.getQuantity()));
                }
            }else {
                if (product.getImage().getUrl()!=null){
                    menuPageItems.add(new dtoMenuPageItem(product.getId(),product.getName(),product.getDescription(),product.getImage().getUrl(),product.getPrice(),product.getQuantity()));
                }
                else {
                    menuPageItems.add(new dtoMenuPageItem(product.getId(),product.getName(),product.getDescription(),null,product.getPrice(),product.getQuantity()));
                }
            }
        }

        menuPageAdapter=new MenuPageAdapter(getContext(),menuPageItems);
        listView.setAdapter(menuPageAdapter);
        menuPageAdapter.notifyDataSetChanged();

    }
    /*
    *For Deals List
    **/
    private void setDealsAdapter(List<dtoDeal> deals){
        menuPageItems=new ArrayList<>();
        for (dtoDeal deal:deals){
           if (deal.getImage()==null){
               menuPageItems.add(new dtoMenuPageItem(deal.getId(),deal.getName(),deal.getDescription(),null));
           }
           else {
               menuPageItems.add(new dtoMenuPageItem(deal.getId(),deal.getName(),deal.getDescription(),deal.getImage().getUrl()));
           }
        }
        menuPageAdapter=new MenuPageAdapter(getContext(),menuPageItems);
        listView.setAdapter(menuPageAdapter);
        menuPageAdapter.notifyDataSetChanged();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTrigger(Events.ActivityToFragment activityToFragment) {
//            listingProductsResponse =activityToFragment.getProducts();
            listingProductsResponse =Paper.book().read(Constants.CategoryProducts);

            deals=listingProductsResponse.getDeals();
            products=listingProductsResponse.getProducts();

            if (deals.size()>0){
                setDealsAdapter(deals);
            }
            else if (products.size()>0){
                setProductsAdapter(products);
            }
    }
    /*
    * Get index if id exist in Selected Products
    **/
    private int idExist(int id){
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
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (deals.size()>0){
            Paper.book().delete(Constants.SelectedDeal);
            deal=deals.get(position);
            Paper.book().write(Constants.SelectedDeal,deal);
            getDealDetail(deal.getId());

        }
        else if (products.size()>0){
            Paper.book().delete(Constants.SelectedItem);
            product=products.get(position);
            Paper.book().write(Constants.SelectedItem,product);

            constraintLayout_MP_Counter=view.findViewById(R.id.constraintLayout_MP_Counter);
            if (!product.getHasAttributes()){
                constraintLayout_MP_Counter.setVisibility(View.VISIBLE);

                int quantity;
                quantity=menuPageItems.get(position).getQuantity();
                quantity+=1;
                menuPageItems.get(position).setQuantity(quantity);
                menuPageAdapter.notifyDataSetChanged();
                HashMap<String, dtoListingProductsResponse> traversedCategories=Paper.book().read(Constants.HashMap);
                listingProductsResponse=traversedCategories.get(Constants.SelectedTabText);
                listingProductsResponse.getProducts().get(position).setQuantity(quantity);
                traversedCategories.put(Constants.SelectedTabText,listingProductsResponse);
                Paper.book().write(Constants.HashMap,traversedCategories);

                /*
                * If Id does not exist
                * Add that product into list
                * */
                int index=0,count=0;
                index=idExist(listingProductsResponse.getProducts().get(position).getId());
                if (index==-1){

                    if (Constants.selectedProducts==null){
                        Constants.selectedProducts=new ArrayList<>();
                    }
                    dtoProduct product=listingProductsResponse.getProducts().get(position);
                    double amount=Double.parseDouble(listingProductsResponse.getProducts().get(position).getPrice());
                    product.setItemAmount(amount);

                    Constants.selectedProducts.add(product);
                    /*
                     * Store Selected Products into DB
                     * */
                    Paper.book().write(Constants.SelectedProductsList,Constants.selectedProducts);

                    count=Constants.updateCartCount();
                    if (count>0){
                        /*Sending Cart badge Count to activities*/
                        Events.cartBadgeCount cartCount = new Events.cartBadgeCount(count);
                        GlobalBus.getBus().post(cartCount);
                    }
                }
                /*
                * If Id does exists
                * Update quantity of that index
                * */
                else {
                    dtoProduct product=Constants.selectedProducts.get(index);
                    product.setQuantity(quantity);
                    Constants.selectedProducts.set(index,product);
                    /*
                     * Store Selected Products into DB
                     * */
                    Paper.book().write(Constants.SelectedProductsList,Constants.selectedProducts);
                }
            }
            else {
                constraintLayout_MP_Counter.setVisibility(View.GONE);
                intent=new Intent(getContext(), MenuDetailPage.class);
                startActivity(intent);
            }
        }
    }
    /*
    *Get Deal Detail
    **/
    private void getDealDetail(int dealId){
        mProgressDialogHelper.showDialog("Getting Products....",true);
        Call<dtoDealDetailResponse> getDealDetailCall = apiService.getDealDetail(dealId);
        RestRequestCallback callbackObject = new RestRequestCallback(activity, (ServerConnectListener) this, DealDetail_GetRequest);
        getDealDetailCall.enqueue(callbackObject);
    }
    @Override
    public void onFailure(String error, RequestCode requestCode) {
        mProgressDialogHelper.hideDialog();
        if (requestCode==DealDetail_GetRequest){
            Toast.makeText(context, "unable to process request.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onSuccess(Object object, RequestCode requestCode) {
        if (requestCode==DealDetail_GetRequest){

            dtoDealDetailResponse dealDetailResponse=(dtoDealDetailResponse)object;
            dtoDealDetail dealDetail= dealDetailResponse.getDeal();

            Paper.book().write(Constants.SelectedDealDetail,dealDetail);

            mProgressDialogHelper.hideDialog();
            intent=new Intent(getContext(), CreateDealPage.class);
            startActivity(intent);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        GlobalBus.getBus().unregister(this);
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTrigger(Events.UpdateMPT updateMPT) {
        Constants.SelectedTabText=Paper.book().read(Constants.SelectedTabText);
        HashMap<String, dtoListingProductsResponse> traversedCategories=Paper.book().read(Constants.HashMap);
        if (traversedCategories!=null){
            for (Map.Entry<String, dtoListingProductsResponse> entry : traversedCategories.entrySet()) {
                String key=entry.getKey();
                if (key.equals(Constants.SelectedTabText)){
                    dtoListingProductsResponse productsResponse = entry.getValue();
                    List<dtoProduct> products=productsResponse.getProducts();
                    updateAdapter(products);
                }
            }
        }


    }

    private void updateAdapter(List<dtoProduct> products){
        menuPageItems=new ArrayList<>();
        for (dtoProduct product : products) {
            if (product.getHasAttributes()){
                if (product.getImage().getUrl()!=null){
                    menuPageItems.add(new dtoMenuPageItem(product.getId(),product.getName(),product.getDescription(),product.getImage().getUrl(),product.getAttributes().get(0).getPrice(),product.getQuantity()));
                }
                else {
                    menuPageItems.add(new dtoMenuPageItem(product.getId(),product.getName(),product.getDescription(),null,product.getAttributes().get(0).getPrice(),product.getQuantity()));
                }
            }else {
                if (product.getImage().getUrl()!=null){
                    menuPageItems.add(new dtoMenuPageItem(product.getId(),product.getName(),product.getDescription(),product.getImage().getUrl(),product.getPrice(),product.getQuantity()));
                }
                else {
                    menuPageItems.add(new dtoMenuPageItem(product.getId(),product.getName(),product.getDescription(),null,product.getPrice(),product.getQuantity()));
                }
            }
        }
        menuPageAdapter.clear();
        menuPageAdapter=new MenuPageAdapter(getContext(),menuPageItems);
        listView.setAdapter(menuPageAdapter);
        menuPageAdapter.notifyDataSetChanged();
    }

}