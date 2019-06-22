package com.locatemybus.myorderbox.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.locatemybus.myorderbox.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener {

    Button button_ForgotPassword;
    ImageView imageView_FP_Close;
    EditText editText_FP_Email;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);//To check Email pattern

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initComponent();
        initListeners();

    }
    private void initComponent(){
        editText_FP_Email=findViewById(R.id.editText_FP_Email);
        button_ForgotPassword=findViewById(R.id.button_ForgotPassword);
        imageView_FP_Close=findViewById(R.id.imageView_FP_Close);
    }
    private void initListeners(){
        button_ForgotPassword.setOnClickListener(this);
        imageView_FP_Close.setOnClickListener(this);
        editText_FP_Email.setOnTouchListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v==button_ForgotPassword){

            String Email=editText_FP_Email.getText().toString();
            if (checkEmail(Email)){
                if (validateEmail(Email)){
                    Intent intent=new Intent(this,Login.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "Please Enter Valid Email.", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Please Enter Valid Email.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (v==imageView_FP_Close){
            Intent intent=new Intent(this,Login.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v==editText_FP_Email){
            editText_FP_Email.setBackgroundColor(getResources().getColor(R.color.fontColor));
        }
        return false;
    }

    public static boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }
    private boolean checkEmail(String Email){
        if (Email!=null){
            return true;
        }
        return false;
    }
}
