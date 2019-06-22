
package com.locatemybus.myorderbox.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.dto.dtoMyOrder;

import java.util.ArrayList;

public class MenuPageCrustAdapter extends BaseExpandableListAdapter {


    private Context context;
    private ArrayList<dtoMyOrder> myOrderArrayList;
    private String heading;
    int ChildPosition;
    int itemCount=0;

    public MenuPageCrustAdapter(Context context,String heading, ArrayList<dtoMyOrder> myOrderArrayList) {

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
        Constants.SauceCount=myOrderArrayList.size();
        return Constants.SauceCount;
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
            view = inflater.inflate(R.layout.sauce_header,null);
        }

        TextView textView_Sauceheading=(TextView) view.findViewById(R.id.textView_Sauceheading);
        textView_Sauceheading.setText(pizzaHeading);

        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, final View convertView, ViewGroup parent) {

        dtoMyOrder myOrder=myOrderArrayList.get(childPosition);
        View view=convertView;
        if (view==null){
            LayoutInflater inflater =LayoutInflater.from(context);
            view = inflater.inflate(R.layout.custom_sauce_listitem,null);
        }

        TextView textView_Saucename=(TextView) view.findViewById(R.id.textView_Saucename);
        TextView textView_saucePrice=(TextView) view.findViewById(R.id.textView_saucePrice);
        final TextView textView_Counter=(TextView) view.findViewById(R.id.textView_Counter);

        ImageView imageView_Plus=(ImageView) view.findViewById(R.id.imageView_Plus);
        ImageView imageView_Minus=(ImageView) view.findViewById(R.id.imageView_Minus);

        textView_Saucename.setText(myOrder.getHeading());
        textView_saucePrice.setText(String.valueOf(myOrder.getPrice()));
        if (myOrder.getItemCount()==1){
            textView_Counter.setText("X"+String.valueOf(myOrder.getItemCount()));
        }

        imageView_Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if (Constants.GroupPosition==groupPosition && Constants.ChildPosition==childPosition){
                Constants.GroupPosition=groupPosition;
                Constants.ChildPosition=childPosition;

                textView_Counter.setVisibility(View.VISIBLE);
                if (Constants.Counter==0){
                    Constants.Counter+=1;
                    Events.ToppingIncrement toppingIncrement = new Events.ToppingIncrement(childPosition);
                    GlobalBus.getBus().post(toppingIncrement);
                }
                textView_Counter.setText("X"+String.valueOf(Constants.Counter));
                myOrderArrayList.get(childPosition).setItemCount(Constants.Counter);
            }
            else {
                Constants.GroupPosition=-1;
                Constants.ChildPosition=-1;
                Constants.Counter=0;

                Constants.GroupPosition=groupPosition;
                Constants.ChildPosition=childPosition;

                textView_Counter.setVisibility(View.VISIBLE);
                if (Constants.Counter==0){
                    Constants.Counter+=1;
                }
                textView_Counter.setText("X"+String.valueOf(Constants.Counter));
                myOrderArrayList.get(childPosition).setItemCount(Constants.Counter);

                Events.ToppingIncrement toppingIncrement = new Events.ToppingIncrement(childPosition);
                GlobalBus.getBus().post(toppingIncrement);
            }
            }
        });
        imageView_Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            itemCount= myOrderArrayList.get(childPosition).getItemCount();
            if (itemCount==1){
                myOrderArrayList.get(childPosition).setItemCount(0);
                textView_Counter.setText("");

                Events.ToppingDecrement toppingDecrement = new Events.ToppingDecrement(childPosition);
                GlobalBus.getBus().post(toppingDecrement);
            }

            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}


