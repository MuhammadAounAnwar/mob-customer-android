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

public class DealPastaAdapter extends ArrayAdapter<dtoItemOrder> {

    private Context context;
    private  ArrayList<dtoItemOrder> itemOrderArrayList;

    public DealPastaAdapter(Context context, ArrayList<dtoItemOrder> itemOrderArrayList) {
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
        View rowView=inflater.inflate(R.layout.custom_pasta_deal, null,true);

        TextView textView_DPasta_heading = (TextView) rowView.findViewById(R.id.textView_DPasta_heading);
        TextView textView_DPasta_Conent = (TextView) rowView.findViewById(R.id.textView_DPasta_Conent);

        textView_DPasta_heading.setText(itemOrderArrayList.get(position).getItem());
        textView_DPasta_Conent.setText(itemOrderArrayList.get(position).getContent());

        return rowView;

    }

}
