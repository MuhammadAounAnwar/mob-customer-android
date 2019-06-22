package com.locatemybus.myorderbox.Helper;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
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
import com.locatemybus.myorderbox.dto.dtoListingProductsResponse;
import com.locatemybus.myorderbox.dto.dtoMenuPageItem;
import com.locatemybus.myorderbox.dto.dtoProduct;

import java.util.ArrayList;
import java.util.HashMap;

import io.paperdb.Paper;

public class MenuPageAdapter extends ArrayAdapter<dtoMenuPageItem> {

    private Context context;
    private ArrayList<dtoMenuPageItem> itemOrderArrayList;
    String imageUrl;
    ImageView imageView_MP_Plus,imageView_MP_Minus;
    TextView textView_MP_Count;
    ConstraintLayout constraintLayout_MP_Counter;

    public MenuPageAdapter(Context context, ArrayList<dtoMenuPageItem> itemOrderArrayList) {
        super(context, R.layout.custom_orderitem_list, itemOrderArrayList);

        this.context=context;
        this.itemOrderArrayList=itemOrderArrayList;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View rowView=inflater.inflate(R.layout.custom_menupage_listitem, null,true);

        ImageView imageView_MP_Image = (ImageView)rowView.findViewById(R.id.imageView_MP_Image);
        TextView textView_MP_Heading = (TextView) rowView.findViewById(R.id.textView_MP_Heading);
        TextView textView_MP_Content = (TextView) rowView.findViewById(R.id.textView_MP_Content);
        TextView textView_MP_Price = (TextView) rowView.findViewById(R.id.textView_MP_Price);

        constraintLayout_MP_Counter=rowView.findViewById(R.id.constraintLayout_MP_Counter);
        imageView_MP_Plus=rowView.findViewById(R.id.imageView_MP_Plus);
        imageView_MP_Minus=rowView.findViewById(R.id.imageView_MP_Minus);
        textView_MP_Count=rowView.findViewById(R.id.textView_MP_Count);

        if (itemOrderArrayList.get(position).getQuantity()>0){
            constraintLayout_MP_Counter.setVisibility(View.VISIBLE);
            textView_MP_Count.setText("X"+itemOrderArrayList.get(position).getQuantity());
        }
        else {
            constraintLayout_MP_Counter.setVisibility(View.GONE);
        }

        imageView_MP_Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int quantity=itemOrderArrayList.get(position).getQuantity();
                quantity+=1;
                itemOrderArrayList.get(position).setQuantity(quantity);
                notifyDataSetChanged();
                setQuantity(position,quantity);
                updateList(position,quantity);

                int count=Constants.updateCartCount();
                if (count>=0){
                    /*Sending Cart badge Count to activities*/
                    Events.cartBadgeCount cartCount = new Events.cartBadgeCount(count);
                    GlobalBus.getBus().post(cartCount);
                }
            }
        });
        imageView_MP_Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity=itemOrderArrayList.get(position).getQuantity();
                if (quantity>0){
                    quantity-=1;
                }
                itemOrderArrayList.get(position).setQuantity(quantity);
                notifyDataSetChanged();
                setQuantity(position,quantity);
                updateList(position,quantity);

                int count=Constants.updateCartCount();
                if (count>=0){
                    /*Sending Cart badge Count to activities*/
                    Events.cartBadgeCount cartCount = new Events.cartBadgeCount(count);
                    GlobalBus.getBus().post(cartCount);
                }
            }
        });


        imageUrl=itemOrderArrayList.get(position).getImageUrl();
        if (imageUrl!=null){
            Glide.with(rowView).load(AppWebServices.BASE_URL+imageUrl).into(imageView_MP_Image);
        }


        textView_MP_Heading.setText(itemOrderArrayList.get(position).getName());
        textView_MP_Content.setText(itemOrderArrayList.get(position).getDescription());
        if (itemOrderArrayList.get(position).getPrice()!=null){
            textView_MP_Price.setText("from  "+itemOrderArrayList.get(position).getPrice());
            textView_MP_Price.setVisibility(View.VISIBLE);
        }
        return rowView;
    }

    /*
    * Update hash map
    * */
    private void setQuantity(int position ,int quantity){
        HashMap<String, dtoListingProductsResponse> traversedCategories=Paper.book().read(Constants.HashMap);
        dtoListingProductsResponse productsResponse=traversedCategories.get(Constants.SelectedTabText);

        productsResponse.getProducts().get(position).setQuantity(quantity);
        traversedCategories.put(Constants.SelectedTabText,productsResponse);
        Paper.book().write(Constants.HashMap,traversedCategories);
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
    * Update Selected Products List
    * */
    private void updateList(int position,int quantity){
        int index;
        index=idExist(itemOrderArrayList.get(position).getId());
        if (index!=-1){
            if (quantity>0){
                dtoProduct product=Constants.selectedProducts.get(index);
                product.setQuantity(quantity);
                Constants.selectedProducts.set(index,product);
            }
            else {
                Constants.selectedProducts.remove(index);
            }
            /*
             * Store Selected Products into DB
             * */
            Paper.book().write(Constants.SelectedProductsList,Constants.selectedProducts);

        }
    }

}
