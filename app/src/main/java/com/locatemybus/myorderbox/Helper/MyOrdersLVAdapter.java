//
//package com.locatemybus.myorderbox.Helper;
//    import android.content.Context;
//    import android.view.LayoutInflater;
//    import android.view.View;
//    import android.view.ViewGroup;
//    import android.widget.ArrayAdapter;
//    import android.widget.ExpandableListView;
//    import android.widget.TextView;
//    import com.locatemybus.myorderbox.R;
//    import com.locatemybus.myorderbox.dto.dtoMyOrder;
//
//    import java.util.ArrayList;
//
//public class MyOrdersLVAdapter extends ArrayAdapter<dtoMyOrder> {
//
//    private Context context;
//    private ArrayList<dtoMyOrder> myOrderArrayList;
//    private MyOrdersEXLVAdapter myOrdersEXLVAdapter;
//
//    public MyOrdersLVAdapter(Context context, ArrayList<dtoMyOrder> myOrderArrayList) {
//        super(context, R.layout.custom_myorder_listitem, myOrderArrayList);
//
//        this.context=context;
//        this.myOrderArrayList=myOrderArrayList;
//    }
//
//    @Override
//    public View getView(int position, View view, ViewGroup parent) {
//
//        LayoutInflater inflater=LayoutInflater.from(context);
//        View rowView=inflater.inflate(R.layout.custom_myorder_listitem, null,true);
//
//        TextView textView_MO_OrderNo=(TextView) rowView.findViewById(R.id.textView_MO_OrderNo);
//        TextView textView_MO_Heading=(TextView) rowView.findViewById(R.id.textView_MO_Heading);
//        TextView textView_MO_ItemCount=(TextView) rowView.findViewById(R.id.textView_MO_ItemCount);
//        TextView textView_MO_Price=(TextView) rowView.findViewById(R.id.textView_MO_Price);
//        TextView textView_MO_Delivered=(TextView) rowView.findViewById(R.id.textView_MO_Delivered);
//        TextView textView_MO_Status=(TextView) rowView.findViewById(R.id.textView_MO_Status);
//        TextView textView_MO_Time=(TextView) rowView.findViewById(R.id.textView_MO_Time);
//        TextView textView_MO_Date=(TextView) rowView.findViewById(R.id.textView_MO_Date);
//        ExpandableListView lvExp_MO_Items=(ExpandableListView) rowView.findViewById(R.id.lvExp_MO_Items);
//
////        textView_MO_OrderNo.setText(String.valueOf(myOrderArrayList.get(position).getCount()));
////        textView_MO_Heading.setText(myOrderArrayList.get(position).getItem());
////        textView_MO_ItemCount.setText(String.valueOf(myOrderArrayList.get(position).getAmount()));
////        textView_MO_Price.setText(String.valueOf(myOrderArrayList.get(position).getAmount()));
////        textView_MO_Delivered.setText(String.valueOf(myOrderArrayList.get(position).getAmount()));
////        textView_MO_Status.setText(String.valueOf(myOrderArrayList.get(position).getAmount()));
////        textView_MO_Time.setText(String.valueOf(myOrderArrayList.get(position).getAmount()));
////        textView_MO_Date.setText(String.valueOf(myOrderArrayList.get(position).getAmount()));
//
//        textView_MO_OrderNo.setText(myOrderArrayList.get(position).getOrderNo());
//        textView_MO_Heading.setText(myOrderArrayList.get(position).getHeading());
//        textView_MO_ItemCount.setText(myOrderArrayList.get(position).getCount());
//        textView_MO_Price.setText(String.valueOf(myOrderArrayList.get(position).getPrice()));
//        textView_MO_Delivered.setText(myOrderArrayList.get(position).getDelivery());
//        textView_MO_Status.setText(myOrderArrayList.get(position).getStatus());
//        textView_MO_Time.setText(myOrderArrayList.get(position).getTime());
//        textView_MO_Date.setText(myOrderArrayList.get(position).getDate());
//
////        myOrdersEXLVAdapter=new MyOrdersEXLVAdapter(context,myOrderArrayList.get(position).getExLvHeading(),myOrderArrayList.get(position).getItemsHashMap());
////        lvExp_MO_Items.setAdapter(myOrdersEXLVAdapter);
//
//        return rowView;
//
//    }
//
//}
//
