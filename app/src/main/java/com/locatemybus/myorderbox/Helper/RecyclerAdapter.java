package com.locatemybus.myorderbox.Helper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.dto.dtoRecycler;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<dtoRecycler> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_RV_Item;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView_RV_Item=(TextView) itemView.findViewById(R.id.textView_RV_Item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                }
            });
        }
    }

    public RecyclerAdapter(ArrayList<dtoRecycler> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_horizontalrecycler_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

        @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textView_RV_Item = holder.textView_RV_Item;
        textView_RV_Item.setText(dataSet.get(listPosition).getItemType());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }}
