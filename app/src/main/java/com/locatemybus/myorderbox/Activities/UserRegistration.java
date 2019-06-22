package com.locatemybus.myorderbox.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.locatemybus.myorderbox.Network.ApiService;
import com.locatemybus.myorderbox.Network.RequestCode;
import com.locatemybus.myorderbox.Network.RestClient;
import com.locatemybus.myorderbox.Network.RestRequestCallback;
import com.locatemybus.myorderbox.Network.ServerConnectListener;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.Utilities.ProgressDialogHelper;
import com.locatemybus.myorderbox.dto.dtoCustomerSignUp;
import com.locatemybus.myorderbox.dto.dtoUserRegister;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;

import static com.locatemybus.myorderbox.Network.RequestCode.UserRegistration_PostRequest;

public class UserRegistration extends AppCompatActivity implements View.OnClickListener,ServerConnectListener {

    Button button_Register;
    EditText editText_SU_Email,editText_SU_Password,editText_SU_FName,editText_SU_LName,editText_SU_MobileNo;

    ProgressDialogHelper mProgressDialogHelper;
    private ApiService apiService;
    private Context context;
    private Activity activity;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);//To check Email pattern

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        initComponent();
        initListeners();

    }

    private void initComponent(){

        context = this;
        activity = this;
        mProgressDialogHelper = new ProgressDialogHelper(this);
        apiService = RestClient.getInstance(context, false, false);

        editText_SU_Email=findViewById(R.id.editText_SU_Email);
        editText_SU_Password=findViewById(R.id.editText_SU_Password);
        editText_SU_FName=findViewById(R.id.editText_SU_FName);
        editText_SU_LName=findViewById(R.id.editText_SU_LName);
        editText_SU_MobileNo=findViewById(R.id.editText_SU_MobileNo);

        button_Register=findViewById(R.id.button_Register);
    }
    private void initListeners(){
        button_Register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==button_Register){
            getData();
        }
    }
    private void getData(){
        String Email=editText_SU_Email.getText().toString();
        String Password=editText_SU_Password.getText().toString();
        String FName=editText_SU_FName.getText().toString();
        String LName=editText_SU_LName.getText().toString();
        String MobileNo=editText_SU_MobileNo.getText().toString();


        if (checkEmail(Email)){
            if (validateEmail(Email)){
                if (checkPassword(Password)){
                    if (checkFName(FName)){
                        if (checkLName(LName)){
                            if (checkMobileNumber(MobileNo)){
                                dtoCustomerSignUp customer=new dtoCustomerSignUp(114,Email,Password,Password,FName,LName,MobileNo);
                                dtoUserRegister userRegister=new dtoUserRegister(customer);
                                userRegistrationCall(userRegister);
                            }else {
                                Toast.makeText(context, "Please Enter Valid Mobile Number.", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(context, "Please Enter Valid Last Name.", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context, "Please Enter Valid First Name.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, "Please Enter Valid Password.", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(context, "Please Enter Valid Email.", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(context, "Please Enter Valid Email.", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }
    private boolean validateFields(String FName,String LName,String Email,String Password,String MobileNumber){
        if (checkFName(FName)&& checkLName(LName) && checkEmail(Email) && checkPassword(Password) && checkMobileNumber(MobileNumber)){
            return true;
        }
        return false;
    }
    private boolean checkFName(String FName){
        if (FName!=null){
            return true;
        }
        return false;
    }
    private boolean checkLName(String LName){
        if (LName!=null){
            return true;
        }
        return false;
    }
    private boolean checkEmail(String Email){
        if (Email!=null){
            return true;
        }
        return false;
    }
    private boolean checkPassword(String Password){
        if (Password!=null && Password.length()>=5){
            return true;
        }
        return false;
    }
    private boolean checkMobileNumber(String MobileNo){
        if (MobileNo!=null && MobileNo.length()==11){
            return true;
        }else {
            Toast.makeText(context, "Please Enter valid Mobile number", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void userRegistrationCall(dtoUserRegister userRegister){
        Call<dtoCustomerSignUp> registerCall = apiService.userRegistration(userRegister , "application/json");
        RestRequestCallback callbackObject = new RestRequestCallback(UserRegistration.this, (ServerConnectListener) this, UserRegistration_PostRequest);
        registerCall.enqueue(callbackObject);
    }
    @Override
    public void onFailure(String error, RequestCode requestCode) {
        if (requestCode==UserRegistration_PostRequest){
            Toast.makeText(context, "Unsuccessful registration call", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onSuccess(Object object, RequestCode requestCode) {
        if (requestCode==UserRegistration_PostRequest){
            Intent intent=new Intent(this,Login.class);
            startActivity(intent);
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK)
//        {
//            return true;
//        }
//        return false;
//    }

}
