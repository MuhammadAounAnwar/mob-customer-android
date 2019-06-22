package com.locatemybus.myorderbox.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.adapter.ListViewAdapter;
import com.locatemybus.myorderbox.Helper.MenuPageAdapter;
import com.locatemybus.myorderbox.Helper.RecyclerAdapter;
import com.locatemybus.myorderbox.Helper.RecyclerTouchListener;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.dto.dtoItemOrder;
import com.locatemybus.myorderbox.dto.dtoRecycler;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class MenuPage extends MenuNavigation implements AdapterView.OnItemClickListener,View.OnTouchListener {

    CarouselView carouselView;
    ArrayList<dtoRecycler> recyclerArrayList;
    RecyclerView recyclerView;
    int[] sampleImages = {R.drawable.pizza_banner, R.drawable.pizza_banner, R.drawable.pizza_banner, R.drawable.pizza_banner, R.drawable.pizza_banner};
    ListView listView;
    Intent intent;
    TextView textView_MP_Heading;
    ImageView imageView_AB_Image;
    SwipeToDismissTouchListener<ListViewAdapter> touchListener;
    RecyclerAdapter recyclerAdapter;
    MenuPageAdapter menuPageAdapter;
    ArrayList<dtoItemOrder> notificationData;
    int currentSize=0;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_menu_page;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);


        initComponent();
        initListeners();
        setAdapter();
//        initScrollListener();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                dtoRecycler movie = recyclerArrayList.get(position);

                    for (int i=0;i<recyclerArrayList.size();i++){
                            TextView textView=recyclerView.getChildAt(i).findViewById(R.id.textView_RV_Item);

                            if (i==position){
                                textView.setBackground(getDrawable(R.drawable.green_round));
                            }
                            else {
                                textView.setBackgroundColor(getResources().getColor(R.color.fontColor));
                            }
                    }

                textView_MP_Heading.setText(movie.getItemType());
                imageView_AB_Image.setVisibility(View.GONE);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

//        touchListener = new SwipeToDismissTouchListener<>(
//                new ListViewAdapter(listView),
//                new SwipeToDismissTouchListener.DismissCallbacks<ListViewAdapter>() {
//                    @Override
//                    public boolean canDismiss(int position) {
//                        return true;
//                    }
//
//                    @Override
//                    public void onDismiss(ListViewAdapter view, int position) {
//
//                }
//                });
//
//        listView.setOnTouchListener(touchListener);
//        listView.setOnScrollListener((AbsListView.OnScrollListener) touchListener.makeScrollListener());

    }

//    private void initScrollListener() {
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                if (linearLayoutManager != null ){
////                    && linearLayoutManager.findLastCompletelyVisibleItemPosition() == recyclerArrayList.size() - 1
//                    loadMore();
//                }
//            }
//        });
//    }

//    private void loadMore() {
//        recyclerArrayList.add(null);
//        recyclerAdapter.notifyItemInserted(recyclerArrayList.size() - 1);
//
//        recyclerArrayList.remove(recyclerArrayList.size() - 1);
//        int scrollPosition = recyclerArrayList.size();
//        recyclerAdapter.notifyItemRemoved(scrollPosition);
//        currentSize = scrollPosition;
//        int nextLimit = currentSize + 4;
//
//        while (currentSize - 1 < nextLimit) {
//            recyclerArrayList.add(recyclerArrayList.get(currentSize));
//            currentSize++;
//        }
//        recyclerAdapter.notifyDataSetChanged();
//    }

    private void initComponent(){

        getSupportActionBar().setHomeAsUpIndicator(setBadgeCount(this,R.drawable.hamburger_icon, 3));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        textView_MP_Heading=findViewById(R.id.textView_MP_Heading);
        listView=(ListView)findViewById(R.id.listView_MP);
        imageView_AB_Image=findViewById(R.id.imageView_AB_Image);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

    }
    private void initListeners() {
        listView.setOnItemClickListener(this);
    }
    private void setAdapter(){

        notificationData=new ArrayList<>();
        for (int i=0;i<5;i++){
            notificationData.add(new dtoItemOrder(1,"pizza","21","Caramelized onion nnsdkj nanksdk ajsndnas jansdknak"));
        }

        //Setting Adapter for Recycler

        recyclerArrayList=new ArrayList<>();
        recyclerArrayList.add(new dtoRecycler("DEALS"));
        recyclerArrayList.add(new dtoRecycler("PIZZA"));
        recyclerArrayList.add(new dtoRecycler("PASTA"));
        recyclerArrayList.add(new dtoRecycler("DRINKS"));

        recyclerView=findViewById(R.id.recyclerView);

        recyclerAdapter=new RecyclerAdapter(recyclerArrayList);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MenuPage.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView.setAdapter(recyclerAdapter);

//        //Setting Adapter for Menu Page
//        menuPageAdapter=new MenuPageAdapter(this,notificationData);
//        listView.setAdapter(menuPageAdapter);
//        menuPageAdapter.notifyDataSetChanged();

    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent=new Intent(this,MenuDetailPage.class);
        startActivity(intent);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
