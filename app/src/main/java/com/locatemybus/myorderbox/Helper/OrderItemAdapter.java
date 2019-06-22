package com.locatemybus.myorderbox.Helper;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.dto.dtoItemOrder;
import java.util.ArrayList;

public class OrderItemAdapter extends ArrayAdapter<dtoItemOrder> {

    private Context context;
    private  ArrayList<dtoItemOrder> itemOrderArrayList;

    public OrderItemAdapter(Context context, ArrayList<dtoItemOrder> itemOrderArrayList) {
        super(context, R.layout.custom_orderitem_list, itemOrderArrayList);

        this.context=context;
        this.itemOrderArrayList=itemOrderArrayList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View rowView=inflater.inflate(R.layout.custom_orderitem_list, null,true);

        TextView textView_Count = (TextView) rowView.findViewById(R.id.textView_Count);
        TextView textView_Item = (TextView) rowView.findViewById(R.id.textView_Item);
        TextView textView_Amount = (TextView) rowView.findViewById(R.id.textView_Amount);

        String countValue=String.valueOf(itemOrderArrayList.get(position).getCount());
        countValue="X"+countValue;

        textView_Count.setText(countValue);
        textView_Item.setText(itemOrderArrayList.get(position).getItem());
        textView_Amount.setText(String.valueOf(itemOrderArrayList.get(position).getAmount()));

        return rowView;

    }

}
