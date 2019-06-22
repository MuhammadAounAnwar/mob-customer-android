package com.locatemybus.myorderbox.dto;

public class dtoItemOrder {

    private int id;
    private int count;
    private String item;
    private String amount;
    private String content;
    private String size;
    private String imageUrl;
    private String heading;
    private boolean check=false;


    public dtoItemOrder(String heading) {
        this.heading = heading;
    }
    public dtoItemOrder(String heading, String item, String content, String amount) {
        this.heading = heading;
        this.item = item;
        this.content = content;
        this.amount = amount;
    }
    public dtoItemOrder(String heading, String item, String content, String amount,String imageUrl) {
        this.heading = heading;
        this.item = item;
        this.content = content;
        this.amount = amount;
        this.imageUrl=imageUrl;
    }
    public dtoItemOrder(String heading, String item, String content, String amount,String imageUrl,boolean check) {
        this.heading = heading;
        this.item = item;
        this.content = content;
        this.amount = amount;
        this.imageUrl=imageUrl;
        this.check=check;
    }

    /*constructors*/

    public dtoItemOrder(String item, String content) {
        this.item = item;
        this.content = content;
    }
    public dtoItemOrder(String item, String content, String size) {
        this.item = item;
        this.content = content;
        this.size = size;
    }
    public dtoItemOrder(int count, String item, String amount, String content) {
        this.count = count;
        this.item = item;
        this.amount = amount;
        this.content = content;
    }
    public dtoItemOrder(int id,int count, String item, String amount, String content,String imageUrl) {
        this.id=id;
        this.count = count;
        this.item = item;
        this.amount = amount;
        this.content = content;
        this.imageUrl=imageUrl;
    }


    /*Getter and Setter*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public dtoItemOrder(int count, String item, String amount) {
        this.count = count;
        this.item = item;
        this.amount = amount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

}
