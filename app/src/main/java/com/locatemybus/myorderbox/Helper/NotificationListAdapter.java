package com.locatemybus.myorderbox.Helper;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.TextView;
    import com.locatemybus.myorderbox.R;
    import com.locatemybus.myorderbox.dto.dtoNotificationData;
    import java.util.ArrayList;

public class NotificationListAdapter extends ArrayAdapter<dtoNotificationData> {

    private Context context;
    private ArrayList<dtoNotificationData> itemOrderArrayList;

    public NotificationListAdapter(Context context, ArrayList<dtoNotificationData> itemOrderArrayList) {
        super(context, R.layout.custom_notification_listitem, itemOrderArrayList);

        this.context=context;
        this.itemOrderArrayList=itemOrderArrayList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View rowView=inflater.inflate(R.layout.custom_notification_listitem, null,true);

        TextView textView_Heading = (TextView) rowView.findViewById(R.id.textView_NotificationListHeading);
        TextView textView_Content = (TextView) rowView.findViewById(R.id.textView_NotificationListContent);

        textView_Heading.setText(itemOrderArrayList.get(position).getHeading());
        textView_Content.setText(itemOrderArrayList.get(position).getContent());

        return rowView;

    }

}
