
package com.locatemybus.myorderbox.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.dto.dtoMyOrder;

import java.util.ArrayList;

public class MenuPagePizzaAdapter extends BaseExpandableListAdapter {

    private Context context;
    private String heading;
    private ArrayList<dtoMyOrder> myOrderArrayList;

    public MenuPagePizzaAdapter(Context context,String heading, ArrayList<dtoMyOrder> myOrderArrayList) {

        this.context=context;
        this.heading=heading;
        this.myOrderArrayList=myOrderArrayList;
    }


    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Constants.pizzaCount=myOrderArrayList.size();
        return Constants.pizzaCount;
    }

    @Override
    public String getGroup(int groupPosition) {
        return heading;
    }

    @Override
    public dtoMyOrder getChild(int groupPosition, int childPosition) {
        return myOrderArrayList.get(childPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String pizzaHeading=heading;
        View view=convertView;
        if (view==null){
            LayoutInflater inflater =LayoutInflater.from(context);
            view = inflater.inflate(R.layout.pizza_header,null);
        }

        TextView textView_pizza=(TextView) view.findViewById(R.id.textView_PizzaHeading);
        textView_pizza.setText(pizzaHeading);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view=convertView;
        if (myOrderArrayList!=null){
            dtoMyOrder myOrder=myOrderArrayList.get(childPosition);

            if (view==null){
                LayoutInflater inflater =LayoutInflater.from(context);
                view = inflater.inflate(R.layout.custom_pizzasize_listitem,null);
            }

            TextView textView_PizzaSize=(TextView) view.findViewById(R.id.textView_PizzaSize);
            TextView textView_PizzaPrice=(TextView) view.findViewById(R.id.textView_PizzaPrice);

            textView_PizzaSize.setText(myOrder.getHeading());
            textView_PizzaPrice.setText(myOrder.getPrice());
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

