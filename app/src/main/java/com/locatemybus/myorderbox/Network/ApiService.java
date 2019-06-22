package com.locatemybus.myorderbox.Network;

import com.locatemybus.myorderbox.dto.dtoAttributeOptionResponse;
import com.locatemybus.myorderbox.dto.dtoCategories;
import com.locatemybus.myorderbox.dto.dtoCompanyRetriveResponse;
import com.locatemybus.myorderbox.dto.dtoCustomerSignUp;
import com.locatemybus.myorderbox.dto.dtoDealDetailResponse;
import com.locatemybus.myorderbox.dto.dtoDealsResponse;
import com.locatemybus.myorderbox.dto.dtoDeliveryPostCodeResponse;
import com.locatemybus.myorderbox.dto.dtoItemDetail;
import com.locatemybus.myorderbox.dto.dtoListingProductsResponse;
import com.locatemybus.myorderbox.dto.dtoMyOrderResponse;
import com.locatemybus.myorderbox.dto.dtoMyStoreResponse;
import com.locatemybus.myorderbox.dto.dtoOrderRequest;
import com.locatemybus.myorderbox.dto.dtoOrderResponse;
import com.locatemybus.myorderbox.dto.dtoOrderTracking;
import com.locatemybus.myorderbox.dto.dtoSignIn;
import com.locatemybus.myorderbox.dto.dtoSignInResponse;
import com.locatemybus.myorderbox.dto.dtoTrackOrderDetails;
import com.locatemybus.myorderbox.dto.dtoUserRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.locatemybus.myorderbox.Network.AppWebServices.Categories;
import static com.locatemybus.myorderbox.Network.AppWebServices.DealDetail;
import static com.locatemybus.myorderbox.Network.AppWebServices.GetCustomers;
import static com.locatemybus.myorderbox.Network.AppWebServices.Getresturants;
import static com.locatemybus.myorderbox.Network.AppWebServices.ListingProducts;
import static com.locatemybus.myorderbox.Network.AppWebServices.LoginCustomer;
import static com.locatemybus.myorderbox.Network.AppWebServices.MyOrders;
import static com.locatemybus.myorderbox.Network.AppWebServices.PlaceOrder;
import static com.locatemybus.myorderbox.Network.AppWebServices.ReteriveDeliveryPostCodes;
import static com.locatemybus.myorderbox.Network.AppWebServices.ReteriveProduct;
import static com.locatemybus.myorderbox.Network.AppWebServices.ReteriveProductOptions;
import static com.locatemybus.myorderbox.Network.AppWebServices.ReteriveStore;
import static com.locatemybus.myorderbox.Network.AppWebServices.TrackOrder;
import static com.locatemybus.myorderbox.Network.AppWebServices.TrackOrderDetails;

public interface ApiService {

    //Register User
    @POST(GetCustomers)
    Call<dtoCustomerSignUp> userRegistration(@Body dtoUserRegister userRegister, @Header("Accept") String accept);//Done--23/05/19

    //User login
    @POST(LoginCustomer)
    Call<dtoSignInResponse> userLogin(@Body dtoSignIn signIn, @Header("Accept") String accept);//Done--29/05/19

    //Get Restaurant information
    @GET(Getresturants)
    Call<dtoCompanyRetriveResponse> getRestaurants();//Processing--13/05/19--Retrieving NULL

    //Get Store Information
    @GET(ReteriveStore)
    Call<dtoMyStoreResponse> getStoreInformation();//Done--29/05/19

    //Get Delivery PostCodes
    @GET(ReteriveDeliveryPostCodes)
    Call<dtoDeliveryPostCodeResponse> getDeliveryPostCode();//Done--14/05/19

    //Get Categories
    @GET(Categories)
    Call<dtoCategories> getCategories(@Query("store_id") Integer store_id);//Done--14/05/19

    //Get Categories
    @GET(ListingProducts)
    Call<dtoListingProductsResponse> getProductsList(@Query("category_id") Integer category_id);//Done--21/05/19

    //Get Product Options
    @GET(ReteriveProductOptions)
    Call<dtoAttributeOptionResponse> getProductOptions(@Query("attribute_id") String attribute_id);//Done--21/05/19

    //Get Item Details
    @GET(ReteriveProduct)
    Call<dtoItemDetail> getItemDetail(@Path("product_id") Integer product_id);//Done--22/05/19

    //Get Deals
    @GET(DealDetail)
    Call<dtoDealDetailResponse> getDealDetail(@Path("id") Integer id);//Done--28/05/19

    //Get MyOrders
    @POST(MyOrders)
    Call<dtoMyOrderResponse> getMyOrders(@Path("customer_id") Integer customer_id);//Done--29/05/19

    //Track Order
    @GET(TrackOrder)
    Call<dtoOrderTracking> trackOrder(@Path("order_id") int order_id);//Processing--20/06/19

    // Track Order Details
    @GET(TrackOrderDetails)
    Call<dtoTrackOrderDetails> trackOrderDetails(@Path("order_id") int order_id);//Processing--20/06/19

    //Place Order
    @POST(PlaceOrder)
    Call<dtoOrderResponse> placeOrder(@Body dtoOrderRequest orderRequest);//Processing--30/05/19


    //Get Deals List
    @GET(DealDetail)
    Call<dtoDealsResponse> getDealsList();//Processing--25/05/19--Extra


}
