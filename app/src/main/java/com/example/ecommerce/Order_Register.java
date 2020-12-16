package com.example.ecommerce;

import java.util.ArrayList;

public class Order_Register {
    private String orderId;
    private String orderTime;
    private String orderDate;
    private float orderTotal;
    ArrayList<OrderDeatils> mOrderDetails = null;

    public ArrayList<OrderDeatils> getOrderDetails() {
        return mOrderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public float getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(float orderTotal) {
        this.orderTotal = orderTotal;
    }


    public void setOrderDetails(ArrayList<OrderDeatils> orderDetails) {
        mOrderDetails = orderDetails;
    }


    public Order_Register() {


    }


}
    class OrderDeatils {
        String itemName = null;

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String item_name, String itemName) {
            this.itemName = itemName;
        }

        public String getItemPrice() {
            return itemPrice;
        }

        public void setItemPrice(String item_name, String itemPrice) {
            this.itemPrice = itemPrice;
        }

        public String getItemQuantity() {
            return itemQuantity;
        }

        public void setItemQuantity(String itemQuantity) {
            this.itemQuantity = itemQuantity;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        String itemPrice = null;
        String itemQuantity = null;
        int totalPrice = 0;

}


