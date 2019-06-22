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
import com.locatemybus.myorderbox.dto.dtoItemOrder;

import java.util.ArrayList;

public class DealPizzaAdapter extends ArrayAdapter<dtoItemOrder> {

    private Context context;
    private  ArrayList<dtoItemOrder> itemOrderArrayList;
    String imageUrl;

    public DealPizzaAdapter(Context context, ArrayList<dtoItemOrder> itemOrderArrayList) {
        super(context, R.layout.custom_orderitem_list, itemOrderArrayList);

        this.context=context;
        this.itemOrderArrayList=itemOrderArrayList;
    }

    public void removeItem(int position){
        itemOrderArrayList.remove(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View rowView=inflater.inflate(R.layout.custom_pizza_deal, null,true);

        ImageView imageView_DPA_Image = (ImageView) rowView.findViewById(R.id.imageView_DPA_Image);


        TextView textView_DP_Heading = (TextView) rowView.findViewById(R.id.textView_DP_Heading);
        TextView textView_DP_Name = (TextView) rowView.findViewById(R.id.textView_DP_Name);
        TextView textView_DP_Description = (TextView) rowView.findViewById(R.id.textView_DP_Description);
        TextView textView_DP_Size = (TextView) rowView.findViewById(R.id.textView_DP_Size);


        textView_DP_Heading.setText(itemOrderArrayList.get(position).getHeading());

        imageUrl=itemOrderArrayList.get(position).getImageUrl();
        if (itemOrderArrayList.get(position).getImageUrl()!=null){
            Glide.with(rowView).load(AppWebServices.BASE_URL+imageUrl).into(imageView_DPA_Image);
        }
        if (itemOrderArrayList.get(position).getItem()!=null){
            textView_DP_Name.setText(itemOrderArrayList.get(position).getItem());
        }
        if (itemOrderArrayList.get(position).getContent()!=null){
            textView_DP_Description.setText(itemOrderArrayList.get(position).getContent());
        }
        if (itemOrderArrayList.get(position).getAmount()!=null){
            textView_DP_Size.setText(itemOrderArrayList.get(position).getAmount());
        }


        return rowView;

    }

}


