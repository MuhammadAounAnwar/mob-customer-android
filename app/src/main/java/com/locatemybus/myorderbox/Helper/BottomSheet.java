package com.locatemybus.myorderbox.Helper;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.dto.dtoAttributeOptions;
import com.locatemybus.myorderbox.dto.dtoMyOrder;

import java.util.ArrayList;

import io.paperdb.Paper;

public class BottomSheet extends BottomSheetDialogFragment  {
    private BottomSheetBehavior mBehavior;
    ExpandableListView lvExp_MO_Options;
    ArrayList<dtoMyOrder> SauceArray;
    MenuPageSauceAdapter menuPageSauceAdapter;
    MenuPageCrustAdapter menuPageCrustAdapter;
    dtoAttributeOptions attributeOptions;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.bottomsheet, null);
        lvExp_MO_Options=view.findViewById(R.id.lvExp_MO_Options);
        lvExp_MO_Options.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                dialog.dismiss();

                return true;
            }
        });

        attributeOptions=Paper.book().read(Constants.OptionsArray);
        setAdapter();
        LinearLayout linearLayout = view.findViewById(R.id.LinearLayout_root);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        params.height = getScreenHeight();
        linearLayout.setLayoutParams(params);
        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        return dialog;
    }
    @Override
    public void onStart() {
        super.onStart();
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
    private void setAdapter(){
        if (Constants.OptionCheck==1){
            SauceArray=Paper.book().read(Constants.SauceArray);
            menuPageSauceAdapter = new MenuPageSauceAdapter(getContext(), attributeOptions.getName(), SauceArray);
            lvExp_MO_Options.setAdapter(menuPageSauceAdapter);
            lvExp_MO_Options.expandGroup(0);
            menuPageSauceAdapter.notifyDataSetChanged();
        }
        else if (Constants.OptionCheck==2){
            SauceArray=Paper.book().read(Constants.ToppingArray);
            menuPageCrustAdapter = new MenuPageCrustAdapter(getContext(), attributeOptions.getName(), SauceArray);
            lvExp_MO_Options.setAdapter(menuPageCrustAdapter);
            lvExp_MO_Options.expandGroup(0);
            menuPageCrustAdapter.notifyDataSetChanged();
        }

    }



}