package com.locatemybus.myorderbox.Helper;

import com.locatemybus.myorderbox.dto.dtoListingProductsResponse;

public class Events {

    public static class ActivityToFragment{
        dtoListingProductsResponse listingProductsResponse;
        public ActivityToFragment(dtoListingProductsResponse listingProductsResponse){
            this.listingProductsResponse=listingProductsResponse;
        }
        public dtoListingProductsResponse getProducts(){
            return listingProductsResponse;
        }
    }

    /*
    * For Crust Adapter
    * */
    public static class AdapterToActivity{
        int childPosition;

        public int getChildPosition() {
            return childPosition;
        }
        public void setChildPosition(int childPosition) {
            this.childPosition = childPosition;
        }

        public AdapterToActivity(int childPosition) {
            this.childPosition = childPosition;
        }
    }
    public static class counterDecrement{

        int childPosition;

        public counterDecrement(int childPosition) {
            this.childPosition = childPosition;
        }

        public int getChildPosition() {
            return childPosition;
        }

        public void setChildPosition(int childPosition) {
            this.childPosition = childPosition;
        }
    }

    /*
    * For Topping Adapter
    * */
    public static class ToppingIncrement{

        int childPosition;

        public ToppingIncrement(int childPosition) {
            this.childPosition = childPosition;
        }

        public int getChildPosition() {
            return childPosition;
        }

        public void setChildPosition(int childPosition) {
            this.childPosition = childPosition;
        }
    }
    public static class ToppingDecrement{

        int childPosition;

        public ToppingDecrement(int childPosition) {
            this.childPosition = childPosition;
        }

        public int getChildPosition() {
            return childPosition;
        }

        public void setChildPosition(int childPosition) {
            this.childPosition = childPosition;
        }
    }


    /*
     * For CheckOut Adapter
     * */
    public static class CheckOutIncrement{

        int childPosition;
        int id;
        int count;



        public CheckOutIncrement(int childPosition) {
            this.childPosition = childPosition;
        }

        public CheckOutIncrement(int childPosition, int id) {
            this.childPosition = childPosition;
            this.id = id;
        }

        public CheckOutIncrement(int childPosition, int id, int count) {
            this.childPosition = childPosition;
            this.id = id;
            this.count = count;
        }

        public int getChildPosition() {
            return childPosition;
        }

        public void setChildPosition(int childPosition) {
            this.childPosition = childPosition;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
    public static class CheckOutDecrement{

        int childPosition;
        int id;
        int count;

        public CheckOutDecrement(int childPosition) {
            this.childPosition = childPosition;
        }

        public CheckOutDecrement(int childPosition, int id) {
            this.childPosition = childPosition;
            this.id = id;
        }

        public CheckOutDecrement(int childPosition, int id, int count) {
            this.childPosition = childPosition;
            this.id = id;
            this.count = count;
        }

        public int getChildPosition() {
            return childPosition;
        }

        public void setChildPosition(int childPosition) {
            this.childPosition = childPosition;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }


    /*
    * For MyOrders to Order Again
    * */
    public static class ordersToOrderAgain{
        int groupPostion;

        public int getGroupPostion() {
            return groupPostion;
        }

        public void setGroupPostion(int groupPostion) {
            this.groupPostion = groupPostion;
        }

        public ordersToOrderAgain(int groupPostion) {
            this.groupPostion = groupPostion;
        }
    }

    /*
    * For Cart Badge Count
    * */
    public static class cartBadgeCount{
        int badgeCount;

        public int getBadgeCount() {
            return badgeCount;
        }

        public void setBadgeCount(int badgeCount) {
            this.badgeCount = badgeCount;
        }

        public cartBadgeCount(int badgeCount) {
            this.badgeCount = badgeCount;
        }
    }

    /*
    * Update MenuPageTabular Data
    *
    * Send data from cart to MenuPageTabular activity
    * */
    public static class UpdateMPT{
        public UpdateMPT() {}
    }

}
