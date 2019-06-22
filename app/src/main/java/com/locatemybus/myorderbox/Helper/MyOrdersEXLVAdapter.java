package com.locatemybus.myorderbox.Helper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.dto.dtoItemOrder;
import com.locatemybus.myorderbox.dto.dtoMyOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyOrdersEXLVAdapter extends BaseExpandableListAdapter {


    private Context context;
    List<String> expandableListTitle;
    private HashMap<String, ArrayList<dtoMyOrder>> myOrderArrayList;
    Intent intent;

    public MyOrdersEXLVAdapter(Context context, List<String> expandableListTitle, HashMap<String, ArrayList<dtoMyOrder>> myOrderArrayList) {

        this.context=context;
        this.expandableListTitle=expandableListTitle;
        this.myOrderArrayList=myOrderArrayList;
    }

    @Override
    public int getGroupCount() {
        return myOrderArrayList.size();
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return myOrderArrayList.get(this.expandableListTitle.get(groupPosition)).get(groupPosition).getItemOrderArrayList().size()+1;
    }
    @Override
    public dtoMyOrder getGroup(int groupPosition) {
        return myOrderArrayList.get(this.expandableListTitle.get(groupPosition)).get(groupPosition);
    }
    @Override
    public ArrayList<dtoItemOrder> getChild(int groupPosition, int childPosition) {
        return myOrderArrayList.get(this.expandableListTitle.get(groupPosition)).get(groupPosition).getItemOrderArrayList();
    }
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final dtoMyOrder myOrder=myOrderArrayList.get(this.expandableListTitle.get(groupPosition)).get(groupPosition);
        View view=convertView;
        if (view==null){
            LayoutInflater inflater =LayoutInflater.from(context);
            view = inflater.inflate(R.layout.custom_expandablelist_header,null);
        }

        TextView textView_MO_OrderNo=(TextView) view.findViewById(R.id.textView_MO_OrderNo);
        TextView textView_MO_Heading=(TextView) view.findViewById(R.id.textView_MO_Heading);
        TextView textView_MO_ItemCount=(TextView) view.findViewById(R.id.textView_MO_ItemCount);
        TextView textView_MO_Price=(TextView) view.findViewById(R.id.textView_MO_Price);
        TextView textView_MO_Delivered=(TextView) view.findViewById(R.id.textView_MO_Delivered);
        TextView textView_MO_Status=(TextView) view.findViewById(R.id.textView_MO_Status);
        TextView textView_MO_Time=(TextView) view.findViewById(R.id.textView_MO_Time);
        TextView textView_MO_Date=(TextView) view.findViewById(R.id.textView_MO_Date);

        String countValue=String.valueOf(myOrder.getCount());
        countValue=countValue+"  Items";

        String priceValue=String.valueOf(myOrder.getPrice());
        priceValue="X"+priceValue;

        textView_MO_OrderNo.setText(String.valueOf(myOrder.getOrderNo()));
        textView_MO_Heading.setText(myOrder.getHeading());
        textView_MO_ItemCount.setText(countValue);
        textView_MO_Price.setText(priceValue);
        textView_MO_Delivered.setText(myOrder.getDelivery());
        textView_MO_Status.setText(myOrder.getStatus());
        textView_MO_Time.setText(myOrder.getTime());
        textView_MO_Date.setText(myOrder.getDate());

        textView_MO_Delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Events.ordersToOrderAgain orderAgainEvent = new Events.ordersToOrderAgain(groupPosition);
                GlobalBus.getBus().post(orderAgainEvent);
            }
        });

        return view;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, final View convertView, ViewGroup parent) {

        final dtoMyOrder myOrder=myOrderArrayList.get(this.expandableListTitle.get(groupPosition)).get(groupPosition);
        View view=convertView;
        LayoutInflater inflater =LayoutInflater.from(context);

        if(childPosition>=0 && childPosition<getChildrenCount(groupPosition)-1){
//        if(childPosition>0){
            view = inflater.inflate(R.layout.custom_orderitem_list,null);

            ArrayList<dtoItemOrder> itemOrder = getChild(groupPosition,childPosition-1);

            TextView textView_Count = (TextView) view.findViewById(R.id.textView_Count);
            TextView textView_Item = (TextView) view.findViewById(R.id.textView_Item);
            TextView textView_Amount = (TextView) view.findViewById(R.id.textView_Amount);

            String countValue=String.valueOf(itemOrder.get(childPosition).getCount());
            countValue="X"+countValue;

            textView_Count.setText(countValue);
            textView_Item.setText(itemOrder.get(childPosition).getItem());
            textView_Amount.setText(String.valueOf(itemOrder.get(childPosition).getAmount()));
        }


        if(childPosition == getChildrenCount(groupPosition)-1)
        {
            view = inflater.inflate(R.layout.custom_expandablelist_footer,null);

            TextView textView_MO_Price=(TextView) view.findViewById(R.id.textView_MO_Price);
            TextView textView_MO_Delivered=(TextView) view.findViewById(R.id.textView_MO_Delivered);
            TextView textView_MO_Status=(TextView) view.findViewById(R.id.textView_MO_Status);
            TextView textView_MO_Time=(TextView) view.findViewById(R.id.textView_MO_Time);
            TextView textView_MO_Date=(TextView) view.findViewById(R.id.textView_MO_Date);

            String priceValue=String.valueOf(myOrder.getPrice());
            priceValue="X"+priceValue;

            textView_MO_Price.setText(priceValue);
            textView_MO_Delivered.setText(myOrder.getDelivery());
            textView_MO_Status.setText(myOrder.getStatus());
            textView_MO_Time.setText(myOrder.getTime());
            textView_MO_Date.setText(myOrder.getDate());


            textView_MO_Delivered.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Events.ordersToOrderAgain orderAgainEvent = new Events.ordersToOrderAgain(groupPosition);
                    GlobalBus.getBus().post(orderAgainEvent);
                }
            });
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }



}
