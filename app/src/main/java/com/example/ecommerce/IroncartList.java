package com.example.ecommerce;

public class IroncartList {

    private String imageview, itemname, itemstock, itemprice, itemquantity,total_price,grand_total;

    public IroncartList() {
    }



    public IroncartList(String imageview, String itemname, String itemstock, String itemprice, String itemquantity, String total_price, String grand_total) {
        this.imageview = imageview;
        this.itemname = itemname;
        this.itemstock = itemstock;
        this.itemprice = itemprice;
        this.itemquantity = itemquantity;
        this.total_price = total_price;
        this.grand_total = grand_total;
    }

    public String getImageview() {
        return imageview;
    }

    public void setImageview(String imageview) {
        this.imageview = imageview;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemstock() {
        return itemstock;
    }

    public void setItemstock(String itemstock) {
        this.itemstock = itemstock;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemquantity() {
        return itemquantity;
    }

    public void setItemquantity(String itemquantity) {
        this.itemquantity = itemquantity;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }
}
