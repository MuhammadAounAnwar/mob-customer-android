package com.locatemybus.myorderbox.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.locatemybus.myorderbox.Helper.Constants;
import com.locatemybus.myorderbox.R;

import java.util.ArrayList;

import io.paperdb.Paper;


public class NotificationPreferences extends AppCompatActivity implements View.OnClickListener {

    TextView textView_NPEmail1,textView_NP_Email2,textView_NPEmail3,textView_NP_TM1,textView_NPTM2,textView_NPTM3,textView_NPAN,textView_NPAN2;
    TextView customheading;
    ImageView imageView_CustomBack;
    SwitchCompat[] switchCompats=new SwitchCompat[6];
    TextView[] textViews=new TextView[8];


    private void initComponent(){
        switchCompats[0]=findViewById(R.id.switch_UpdateEmail);
        switchCompats[1]=findViewById(R.id.switch_UpdateMsg);
        switchCompats[2]=findViewById(R.id.switch_UpdateNotification);
        switchCompats[3]=findViewById(R.id.switch_OfferEmail);
        switchCompats[4]=findViewById(R.id.switch_OfferMsg);
        switchCompats[5]=findViewById(R.id.switch_OfferNotification);

        textViews[0]=findViewById(R.id.textView_NPEmail1);
        textViews[1]=findViewById(R.id.textView_NP_Email2);
        textViews[2]=findViewById(R.id.textView_NP_TM1);
        textViews[3]=findViewById(R.id.textView_NPTM2);
        textViews[4]=findViewById(R.id.textView_NPAN);
        textViews[5]=findViewById(R.id.textView_NPEmail3);
        textViews[6]=findViewById(R.id.textView_NPTM3);
        textViews[7]=findViewById(R.id.textView_NPAN2);

        setSwitches();
        switchCompats[2].setChecked(true);
        updateSwitchArray(2,true);
        switchCompats[5].setChecked(true);
        updateSwitchArray(5,true);
        setSwitches();

        imageView_CustomBack=findViewById(R.id.imageView_CustomBack);
        customheading=findViewById(R.id.textView_CustomHeading);
        customheading.setText(getString(R.string.NP_Heading));
    }

    private void resetTextColor(){
        textView_NPEmail1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        textView_NP_Email2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        textView_NPEmail3.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        textView_NP_TM1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        textView_NPTM2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        textView_NPTM3.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        textView_NPAN.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        textView_NPAN2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }


    private void setSwitches(){
        Constants.NotifiactionOptions=Paper.book().read(Constants.NotificationPrefArray);
        if (Constants.NotifiactionOptions==null){
            initializeSwitchArray();
        }else {
            for (int i=0;i<Constants.NotifiactionOptions.size();i++){
                if (Constants.NotifiactionOptions.get(i)){
                    switchCompats[i].setChecked(true);
                    updateTextView(i);
                }else {
                    switchCompats[i].setChecked(false);
                }
            }
        }
    }
    private void initializeSwitchArray(){
        Constants.NotifiactionOptions=new ArrayList<>();
        for (int i=0;i<switchCompats.length;i++){
            Constants.NotifiactionOptions.add(false);
        }
        Paper.book().write(Constants.NotificationPrefArray,Constants.NotifiactionOptions);
    }
    private void updateSwitchArray(int index,boolean value) {
        Constants.NotifiactionOptions=Paper.book().read(Constants.NotificationPrefArray);
        Constants.NotifiactionOptions.set(index,value);
        Paper.book().write(Constants.NotificationPrefArray,Constants.NotifiactionOptions);
    }
    private void updateTextView(int index){
        if (index==0){
            textViews[0].setTextColor(getResources().getColor(R.color.colorBlack));
            textViews[1].setTextColor(getResources().getColor(R.color.colorBlack));
        }
        else if (index==1){
            textViews[2].setTextColor(getResources().getColor(R.color.colorBlack));
            textViews[3].setTextColor(getResources().getColor(R.color.colorBlack));
        }
        else if (index==2){
            textViews[4].setTextColor(getResources().getColor(R.color.colorBlack));
        }
        else if (index==3){
            textViews[5].setTextColor(getResources().getColor(R.color.colorBlack));
        }
        else if (index==4){
            textViews[6].setTextColor(getResources().getColor(R.color.colorBlack));
        }
        else if (index==5){
            textViews[7].setTextColor(getResources().getColor(R.color.colorBlack));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_preferences);

        initComponent();
        initListener();

        switchCompats[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    textViews[0].setTextColor(getResources().getColor(R.color.colorBlack));
                    textViews[1].setTextColor(getResources().getColor(R.color.colorBlack));

                    updateSwitchArray(0,true);
                }
                else {
                    textViews[0].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    textViews[1].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    updateSwitchArray(0,false);
                }
            }
        });

        switchCompats[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    textViews[2].setTextColor(getResources().getColor(R.color.colorBlack));
                    textViews[3].setTextColor(getResources().getColor(R.color.colorBlack));
                    updateSwitchArray(1,true);
                }
                else {
                    textViews[2].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    textViews[3].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    updateSwitchArray(1,false);
                }
            }
        });

        switchCompats[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    textViews[4].setTextColor(getResources().getColor(R.color.colorBlack));
                    updateSwitchArray(2,true);
                }
                else {
                    textViews[4].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    updateSwitchArray(2,false);
                }
            }
        });

        switchCompats[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    textViews[5].setTextColor(getResources().getColor(R.color.colorBlack));
                    updateSwitchArray(3,true);
                }
                else {
                    textViews[5].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    updateSwitchArray(3,false);
                }
            }
        });

        switchCompats[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    textViews[6].setTextColor(getResources().getColor(R.color.colorBlack));
                    updateSwitchArray(4,true);
                }
                else {
                    textViews[6].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    updateSwitchArray(4,false);
                }
            }
        });

        switchCompats[5].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    textViews[7].setTextColor(getResources().getColor(R.color.colorBlack));
                    updateSwitchArray(5,true);
                }
                else {
                    textViews[7].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    updateSwitchArray(5,false);
                }
            }
        });



    }



    private void initListener(){
        imageView_CustomBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v==imageView_CustomBack){
            super.onBackPressed();
        }
    }
}
