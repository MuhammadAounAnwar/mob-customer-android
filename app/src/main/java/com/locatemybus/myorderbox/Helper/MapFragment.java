package com.locatemybus.myorderbox.Helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.locatemybus.myorderbox.R;

public class MapFragment  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_map, container, false);
        return  view;
    }//OnCreateView End

}
