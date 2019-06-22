package com.locatemybus.myorderbox.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.locatemybus.myorderbox.Helper.Constants;
import com.locatemybus.myorderbox.Network.ApiService;
import com.locatemybus.myorderbox.Network.RequestCode;
import com.locatemybus.myorderbox.Network.RestClient;
import com.locatemybus.myorderbox.Network.RestRequestCallback;
import com.locatemybus.myorderbox.Network.ServerConnectListener;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.Utilities.ProgressDialogHelper;
import com.locatemybus.myorderbox.dto.dtoCustomerSignUp;
import com.locatemybus.myorderbox.dto.dtoSignIn;
import com.locatemybus.myorderbox.dto.dtoSignInResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.paperdb.Paper;
import retrofit2.Call;

import static com.locatemybus.myorderbox.Network.RequestCode.UserLogin_PostRequest;

public class Login extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, ServerConnectListener {

    Button button_Login;
    EditText editText_email,editText_password;
    TextView textView_ForgotPassword,textView_SignUp;
    ImageView btnback;

    ProgressDialogHelper mProgressDialogHelper;
    private ApiService apiService;
    private Context context;
    private Activity activity;

    String Email,Password;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);//To check Email pattern

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponent();
        initListeners();

    }

    private void initComponent(){

        context = this;
        activity = this;
        mProgressDialogHelper = new ProgressDialogHelper(this);
        apiService = RestClient.getInstance(context, false, false);

        button_Login=findViewById(R.id.button_Login);
        editText_email=findViewById(R.id.editText_LoginEmail);
        editText_password=findViewById(R.id.editText_LoginPassword);
        btnback=findViewById(R.id.btnback);

        textView_ForgotPassword=findViewById(R.id.textView_ForgotPassword);
        textView_SignUp=findViewById(R.id.textView_SignUp);
        textView_SignUp.setPaintFlags(textView_SignUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //TODO remove in production
        editText_email.setText("awais.iqbal@wizlinx.com");
        editText_password.setText("12345");
    }
    private void initListeners(){
        button_Login.setOnClickListener(this);
        editText_email.setOnTouchListener(this);
        editText_password.setOnTouchListener(this);
        textView_ForgotPassword.setOnClickListener(this);
        textView_SignUp.setOnClickListener(this);
        btnback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==button_Login){
            getData();
        }
        else if (v==textView_ForgotPassword){
            Intent intent=new Intent(this,ForgotPassword.class);
            startActivity(intent);
        }
        else if (v==textView_SignUp){
            Intent intent=new Intent(this,UserRegistration.class);
            startActivity(intent);
        }
        else if (v==btnback){
            finish();
            super.onBackPressed();
        }
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v==editText_email){
            editText_email.setBackgroundColor(getResources().getColor(R.color.fontColor));

            editText_password.setBackgroundColor(getResources().getColor(R.color.transparentColor));
            editText_password.setBackgroundResource(R.drawable.cornered_edittext);
        }
        else if (v==editText_password){
            editText_email.setBackgroundColor(getResources().getColor(R.color.transparentColor));
            editText_email.setBackgroundResource(R.drawable.cornered_edittext);
            editText_password.setBackgroundColor(getResources().getColor(R.color.fontColor));
        }
        return false;
    }
    private void getData(){

        Email=editText_email.getText().toString();
        Password=editText_password.getText().toString();

        if (checkEmail(Email)){
            if (validateEmail(Email)){
                if (checkPassword(Password)){
                    dtoCustomerSignUp customer=new dtoCustomerSignUp(Constants.StoreId,Email,Password);
                    dtoSignIn signIn=new dtoSignIn(customer);
                    userLoginCall(signIn);
                } else {
                    Toast.makeText(context, "Please enter valid Password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Please enter valid email.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean validateFields(String Email,String Password){
        if (checkEmail(Email) && checkPassword(Password)){
            return true;
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
    private boolean checkPassword(String Password){
        if (Password!=null && Password.length()>=5){
            return true;
        }
        return false;
    }
    private void userLoginCall(dtoSignIn signIn){
        mProgressDialogHelper.showDialog("Signing In....",true);
        Call<dtoSignInResponse> registerCall = apiService.userLogin(signIn,"application/json");
        RestRequestCallback callbackObject = new RestRequestCallback(Login.this, (ServerConnectListener) this, UserLogin_PostRequest);
        registerCall.enqueue(callbackObject);
    }
    @Override
    public void onFailure(String error, RequestCode requestCode) {
        mProgressDialogHelper.hideDialog();
        if (requestCode==UserLogin_PostRequest){
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onSuccess(Object object, RequestCode requestCode) {
        if (requestCode==UserLogin_PostRequest){

            dtoSignInResponse signInResponse=(dtoSignInResponse)object;
            //RestClient.setToken(signInResponse.getAuthenticationToken());//Setting Token

            Paper.book().write(Constants.LoginInformation,signInResponse);

            mProgressDialogHelper.hideDialog();
            Intent intent=new Intent(this,HomePage.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            finish();
            return true;
        }
        return false;
    }


}
