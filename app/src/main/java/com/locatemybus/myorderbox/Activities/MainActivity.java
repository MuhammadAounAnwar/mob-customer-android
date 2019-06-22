package com.locatemybus.myorderbox.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.locatemybus.myorderbox.Helper.Constants;
import com.locatemybus.myorderbox.Network.ApiService;
import com.locatemybus.myorderbox.Network.RequestCode;
import com.locatemybus.myorderbox.Network.RestClient;
import com.locatemybus.myorderbox.Network.RestRequestCallback;
import com.locatemybus.myorderbox.Network.ServerConnectListener;
import com.locatemybus.myorderbox.R;
import com.locatemybus.myorderbox.Utilities.ProgressDialogHelper;
import com.locatemybus.myorderbox.dto.dtoCompanyRetriveResponse;
import com.locatemybus.myorderbox.dto.dtoMyStoreResponse;

import io.paperdb.Paper;
import retrofit2.Call;

import static com.locatemybus.myorderbox.Network.RequestCode.ResturantRetrieve_GetRequest;
import static com.locatemybus.myorderbox.Network.RequestCode.StoreInformation_PostRequest;

public class MainActivity extends AppCompatActivity implements ServerConnectListener {

    Intent intent;
    ProgressDialogHelper mProgressDialogHelper;
    private ApiService apiService;
    private Context context;
    private Activity activity;

    private Handler mWaitHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        activity = this;
        mProgressDialogHelper = new ProgressDialogHelper(this);
        apiService = RestClient.getInstance(context, false, false);

//        intent=new Intent(MainActivity.this, HomePage.class);
//        startActivity(intent);
        getStoreInformation();
//        retrieveRestaurant();


        mWaitHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                try {
                    Intent intent = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }, 4000);  // Give a 4 seconds delay.

    }

    @Override
    public void onFailure(String error, RequestCode requestCode) {
        if (requestCode==ResturantRetrieve_GetRequest){
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        }
        else if (requestCode==StoreInformation_PostRequest){
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onSuccess(Object object, RequestCode requestCode) {
        if (requestCode==ResturantRetrieve_GetRequest){
            intent=new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
        else if (requestCode==StoreInformation_PostRequest){
            dtoMyStoreResponse  myStoreResponse = (dtoMyStoreResponse) object;
            if (myStoreResponse!=null){
                Paper.book().write(Constants.StoreInformation,myStoreResponse);
                Constants.StoreId=myStoreResponse.getStore().getId();

//                intent=new Intent(MainActivity.this, HomePage.class);
//                startActivity(intent);
            }
        }
    }
    /*Get all data for store*/
    private void getStoreInformation(){
        Call<dtoMyStoreResponse> storeInformationCall = apiService.getStoreInformation();
        RestRequestCallback callbackObject = new RestRequestCallback(MainActivity.this, (ServerConnectListener) this, StoreInformation_PostRequest);
        storeInformationCall.enqueue(callbackObject);
    }

    /*Get All restaurants*/
    private void retrieveRestaurant(){
        Call<dtoCompanyRetriveResponse> getCompaniesCall = apiService.getRestaurants();
        RestRequestCallback callbackObject = new RestRequestCallback(MainActivity.this, (ServerConnectListener) this, RequestCode.ResturantRetrieve_GetRequest);
        getCompaniesCall.enqueue(callbackObject);
    }


}
