package com.locatemybus.myorderbox.Helper;

import com.locatemybus.myorderbox.dto.dtoDealDetail;
import com.locatemybus.myorderbox.dto.dtoMenuPageItem;
import com.locatemybus.myorderbox.dto.dtoProduct;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import io.paperdb.Paper;

public class Constants {

    public static int pizzaCount=0;
    public static int SauceCount=0;

    public static int SelectedTab=0;
    public static String SelectedTabText="SelectedTabText";
    public static int Count=1;
    public static int Counter=0;
    public static int ChildPosition=-1;
    public static int GroupPosition=-1;
    public static int StoreId=0;
    public static int AcitvityCheck=0;
    public static int selectedPosition=0;
    public static int BadgeCount=0;
    public static int OptionCheck=0;
    public static double ItemAmount=0;
    public static double ExtraAmount=0;
    public static double TotalAmount=0;
    public static double CartTotalAmount=0;
    public static double DealTotalAmount=0;

    public static String DeliveryType="DeliveryType";
    public static String DeliveryTime="DeliveryTime";

    public static String DeliveryPostalCode="DeliveryPostalCode";
    public static String SelectedStore="SelectedStore";
    public static String StoreInformation="StoreInformation";
    public static String CategoriesNames="CategoriesNames";
    public static String CategoryProducts="CategoryProducts";
    public static String CategoryDeals="CategoryDeals";
    public static String LoginInformation="LoginInformation";

    public static String SelectedItem="SelectedItem";
    public static String SelectedGroup="SelectedGroup";
    public static String SelectedDeal="SelectedDeal";
    public static String SelectedDealDetail="SelectedDealDetail";
    public static String SelectedDealItem="SelectedDealItem";
    public static String SelectedDealItems="SelectedDealItems";
    public static String OrderTracking="OrderTracking";
    public static String TrackingOrderDetails="TrackingOrderDetails";
    public static String HashMap="HashMap";

    public static String SelectedPostalCode="SelectedPostalCode";
    public static String SelectedProductsList="SelectedProductsList";
    public static String SelectedDealsList="SelectedDealsList";
    public static String OptionsArray="OptionsArray";
    public static String NotificationPrefArray="NotificationPrefArray";
    public static String SauceArray="SauceArray";
    public static String ToppingArray="ToppingArray";

    public static List<dtoProduct> selectedProducts;
    public static List<dtoDealDetail> selectedDeals;
    public static List<Boolean> NotifiactionOptions;

    public static List<dtoMenuPageItem> selectedMenuItems;
    public static String DealItems="DealItems";
    public static HashMap<String, List<dtoProduct>> traversedItems;

    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";



    public static void resetValues(){
        Count=1;
        Counter=0;
        ChildPosition=-1;
        GroupPosition=-1;
        AcitvityCheck=0;
//        selectedPosition=0;
        ItemAmount=0;
        ExtraAmount=0;
        TotalAmount=0;

        /*
         * Delete DB
         * */
        Paper.book().delete(Constants.SauceArray);
        Paper.book().delete(Constants.ToppingArray);
        Paper.book().delete(Constants.OptionsArray);

    }
    public static String getRandomString(final int sizeOfRandomString) {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }
    public static double calculateTotalPrice(){
        double cartTotalAmount=0;
        if (Constants.selectedProducts!=null){
            for (dtoProduct product:Constants.selectedProducts){
                cartTotalAmount=cartTotalAmount+(product.getItemAmount()*product.getQuantity());
            }
        }

        if (Constants.selectedDeals!=null){
            for (dtoDealDetail dealDetail:Constants.selectedDeals){
                cartTotalAmount=cartTotalAmount+(Double.parseDouble(dealDetail.getMinCartValue())*dealDetail.getQuantity());
            }
        }
        return cartTotalAmount;
    }

    /*
    * Update Cart Badge Count
    * */
    public static int updateCartCount(){
        int count=0;
        Constants.selectedProducts= Paper.book().read(Constants.SelectedProductsList);
        Constants.selectedDeals=Paper.book().read(Constants.SelectedDealsList);
        if (Constants.selectedProducts!=null){
            count=Constants.selectedProducts.size();
        }
        if (Constants.selectedDeals!=null){
            count+=Constants.selectedDeals.size();
        }
        return count;
    }

}
