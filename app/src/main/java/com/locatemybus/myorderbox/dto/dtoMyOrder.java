package com.locatemybus.myorderbox.dto;

import java.util.ArrayList;
import java.util.HashMap;

public class dtoMyOrder {

    int id;
    private int orderNo;
    private int count;
    private String heading;
    private String price;
    private String status;
    private String delivery;
    private String time;
    private String date;
    private int itemCount;



    private ArrayList<dtoItemOrder> itemOrderArrayList=new ArrayList<>();
    private HashMap<String, ArrayList<dtoItemOrder>> itemsHashMap=new HashMap<>();
    private ArrayList<String> exLvHeading=new ArrayList<>();

//    Constructors

    /*
    * Constructor for Menu Detail Page and CreateDeal_MDP
    * */
    public dtoMyOrder(int id,String heading, String price,int itemCount) {
        this.id=id;
        this.heading = heading;
        this.price = price;
        this.itemCount=itemCount;
    }


    /*
    * Constructor for My Orders
    * */
    public dtoMyOrder(int orderNo, String heading, int count, String price, String status, String delivery, String time, String date, ArrayList<dtoItemOrder> itemOrderArrayList) {
        this.orderNo = orderNo;
        this.heading = heading;
        this.count = count;
        this.price = price;
        this.status = status;
        this.delivery = delivery;
        this.time = time;
        this.date = date;
        this.itemOrderArrayList = itemOrderArrayList;
    }

    public dtoMyOrder(int orderNo, String heading, int count, String price, String status, String delivery, String time, String date, HashMap<String, ArrayList<dtoItemOrder>> itemsHashMap, ArrayList<String> exLvHeading) {
        this.orderNo = orderNo;
        this.heading = heading;
        this.count = count;
        this.price = price;
        this.status = status;
        this.delivery = delivery;
        this.time = time;
        this.date = date;
        this.itemsHashMap = itemsHashMap;
        this.exLvHeading = exLvHeading;
    }


    /*Getter and Setter*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<dtoItemOrder> getItemOrderArrayList() {
        return itemOrderArrayList;
    }

    public void setItemOrderArrayList(ArrayList<dtoItemOrder> itemOrderArrayList) {
        this.itemOrderArrayList = itemOrderArrayList;
    }

    public HashMap<String, ArrayList<dtoItemOrder>> getItemsHashMap() {
        return itemsHashMap;
    }

    public void setItemsHashMap(HashMap<String, ArrayList<dtoItemOrder>> itemsHashMap) {
        this.itemsHashMap = itemsHashMap;
    }

    public ArrayList<String> getExLvHeading() {
        return exLvHeading;
    }

    public void setExLvHeading(ArrayList<String> exLvHeading) {
        this.exLvHeading = exLvHeading;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

}
