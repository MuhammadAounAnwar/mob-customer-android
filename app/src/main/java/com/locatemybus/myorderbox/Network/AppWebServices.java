package com.locatemybus.myorderbox.Network;

public interface AppWebServices {
    String BASE_URL = "https://go.myorderboxhq.com";
//    String BASE_URL = "http://staging.myorderboxhq.com";


    String GetCustomers="/new_api_v2/customers";//Get Customers--User Registration
    String LoginCustomer="/new_api_v2/customers/sign_in";//login Customer

    String Getresturants="/new_api_v2/my_companies";//Get Restaurants
    String ReteriveStore="/new_api_v2/my_stores";//Get Stores
    String ReteriveDeliveryPostCodes="/new_api_v2/company_delivery_postcodes";//Get Delivery by PostCode

    String GetCollectionPostCodes="/new_api_v2/stores";//Get Collection by postCode
    String StoreSearchByPostCode="/new_api_v2/stores";//Get Store by postCode

    String Categories="/new_api_v2/categories";
    String ListingProducts="/new_api_v2/product_menus";//Get products list
    String DealDetail="/new_api_v2/deals/{id}";//Get Deal detail
    String ReteriveProduct="/new_api_v2/product_menus/{product_id}";
    String MyOrders="/new_api_v2/reports/{customer_id}/orders";
    String TrackOrder="/new_api_v2/orders/tracking/{order_id}";
    String TrackOrderDetails="/new_api_v2/orders/{order_id}";
    String PlaceOrder="/new_api_v2/orders";
    String ReteriveProductOptions="/new_api_v2/product_menu_options";//Get product options

}
