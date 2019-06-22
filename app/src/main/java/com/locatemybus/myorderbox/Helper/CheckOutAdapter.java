package com.locatemybus.myorderbox.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.locatemybus.myorderbox.Network.AppWebServices;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.dto.dtoDealDetail;
import com.locatemybus.myorderbox.dto.dtoItemOrder;
import com.locatemybus.myorderbox.dto.dtoListingProductsResponse;
import com.locatemybus.myorderbox.dto.dtoProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.paperdb.Paper;

public class CheckOutAdapter extends ArrayAdapter<dtoItemOrder> {

    private Context context;
    private ArrayList<dtoItemOrder> itemOrderArrayList;
    String imageUrl;

    public CheckOutAdapter(Context context, ArrayList<dtoItemOrder> itemOrderArrayList) {
        super(context, R.layout.custom_orderitem_list, itemOrderArrayList);

        this.context=context;
        this.itemOrderArrayList=itemOrderArrayList;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        final dtoItemOrder itemOrder=itemOrderArrayList.get(position);

        LayoutInflater inflater=LayoutInflater.from(context);
        View rowView=inflater.inflate(R.layout.custom_checkout_listitem, null,true);

        ImageView imageView_CO_Image = (ImageView) rowView.findViewById(R.id.imageView_CO_Image);

        TextView textView_CO_heading = (TextView) rowView.findViewById(R.id.textView_CO_heading);
        TextView textView_CO_Content = (TextView) rowView.findViewById(R.id.textView_CO_Content);
        TextView textView_CO_Price = (TextView) rowView.findViewById(R.id.textView_CO_Price);
        TextView textView_CO_Count = (TextView) rowView.findViewById(R.id.textView_CO_Count);

        ImageView imageView_CO_Inc=(ImageView)rowView.findViewById(R.id.imageView_CO_Inc);
        ImageView imageView_CO_Dec=(ImageView)rowView.findViewById(R.id.imageView_CO_Dec);

        imageView_CO_Inc.setClickable(true);
        imageView_CO_Dec.setClickable(true);

        imageView_CO_Inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOrder.setCount(itemOrder.getCount()+1);
                notifyDataSetChanged();

                updateHashMap(itemOrder);

                Events.CheckOutIncrement checkOutIncrement = new Events.CheckOutIncrement(position,itemOrder.getId(),itemOrder.getCount());
                GlobalBus.getBus().post(checkOutIncrement);
            }
        });

        imageView_CO_Dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((itemOrder.getCount()-1)>0) {
                    itemOrder.setCount(itemOrder.getCount()-1);
                    updateHashMap(itemOrder);
                }
                else if ((itemOrder.getCount()-1)==0){
                    /*
                    * Delete that item from cart
                    * Also Update Items in Products List
                    * */

                    itemOrderArrayList.remove(position);//Remove from List Adapter
                    int index=-1;
                    index = idExistProducts(itemOrder.getId());
                    if (index!=-1){
                        Constants.selectedProducts.remove(index);
                        Paper.book().write(Constants.SelectedProductsList,Constants.selectedProducts);
                    }
                    index=idExistDeals(itemOrder.getId());
                    if (index!=-1){
                        Constants.selectedDeals.remove(index);
                        Paper.book().write(Constants.SelectedDealsList,Constants.selectedDeals);
                    }

                    itemOrder.setCount(0);

                    updateHashMap(itemOrder);
                }
                notifyDataSetChanged();

                int count=Constants.updateCartCount();
                if (count>=0){
                    /*Sending Cart badge Count to activities*/
                    Events.cartBadgeCount cartCount = new Events.cartBadgeCount(count);
                    GlobalBus.getBus().post(cartCount);
                }

                Events.CheckOutDecrement checkOutDecrement = new Events.CheckOutDecrement(position,itemOrder.getId(),itemOrder.getCount());
                GlobalBus.getBus().post(checkOutDecrement);
            }
        });


        imageUrl=itemOrderArrayList.get(position).getImageUrl();
        if (imageUrl!=null){
            Glide.with(rowView).load(AppWebServices.BASE_URL+imageUrl).into(imageView_CO_Image);
        }

        String countValue=String.valueOf(itemOrderArrayList.get(position).getCount());
        countValue="X"+countValue;
        textView_CO_Count.setText(countValue);

        textView_CO_heading.setText(itemOrderArrayList.get(position).getItem());
        textView_CO_Content.setText(itemOrderArrayList.get(position).getContent());

        String price=String.format("%.2f", Double.parseDouble(itemOrderArrayList.get(position).getAmount()));
        textView_CO_Price.setText(price);

        return rowView;

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
    /*
     * Update HashMap
     * Event Bus to Notify Adapter
     * */
    private void updateHashMap(dtoItemOrder itemOrder){
        HashMap<String, dtoListingProductsResponse> traversedCategories=Paper.book().read(Constants.HashMap);
        if (traversedCategories!=null){
            List<String> keyList = new ArrayList<String>(traversedCategories.keySet());
            for (Map.Entry<String, dtoListingProductsResponse> entry : traversedCategories.entrySet()) {

                String key = entry.getKey();
                dtoListingProductsResponse productsResponse = entry.getValue();
                List<dtoProduct> products=productsResponse.getProducts();

                for (int i=0;i<products.size();i++){
                    dtoProduct product=products.get(i);
                    if (product.getId()==itemOrder.getId()){
                        /*
                        * update Product Response object
                        * update hash map key value pair
                        * Update paper db hash map
                        * */
                        productsResponse.getProducts().get(i).setQuantity(itemOrder.getCount());//update product response object
                        traversedCategories.put(key,productsResponse);
                        Paper.book().write(Constants.HashMap,traversedCategories);
                    }
                }
            }
        }
    }

    /*
    * Remove Selected Item From
    * HashMap
    * Selected Deal or Selected Products List
    * */
    public void removeItem(int position){
        dtoItemOrder itemOrder=itemOrderArrayList.get(position);
        int index=-1;
        index = idExistProducts(itemOrder.getId());
        if (index!=-1){
            Constants.selectedProducts.remove(index);
            Paper.book().write(Constants.SelectedProductsList,Constants.selectedProducts);
        }
        index=idExistDeals(itemOrder.getId());
        if (index!=-1){
            Constants.selectedDeals.remove(index);
            Paper.book().write(Constants.SelectedDealsList,Constants.selectedDeals);
        }
        itemOrder.setCount(0);
        updateHashMap(itemOrder);

        itemOrderArrayList.remove(position);

        notifyDataSetChanged();

        int count=Constants.updateCartCount();
        if (count>=0){
            /*Sending Cart badge Count to activities*/
            Events.cartBadgeCount cartCount = new Events.cartBadgeCount(count);
            GlobalBus.getBus().post(cartCount);
        }
    }

}
