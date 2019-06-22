package com.locatemybus.myorderbox.Activities.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.locatemybus.myorderbox.Helper.Constants;
import com.locatemybus.myorderbox.dto.dtoCategories;
import com.locatemybus.myorderbox.dto.dtoCategory;

import java.util.ArrayList;

import io.paperdb.Paper;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    dtoCategories categories;
    int tabsCount=10;
    ArrayList<String> tabsName;

    public SectionsPagerAdapter(Context context, FragmentManager fm, int tabsCount) {
        super(fm);
        mContext = context;
        this.tabsCount=tabsCount;
        setTabsName();
    }

    private void setTabsName(){
        dtoCategories categories= Paper.book().read(Constants.CategoriesNames);
        tabsName=new ArrayList<>();
        for (dtoCategory category : categories.getCategories()){
            tabsName.add(category.getNameOnline());
        }
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(position+1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabsName.get(position);
//        return null;
    }

    @Override
    public int getCount() {
        return tabsCount;
    }
}