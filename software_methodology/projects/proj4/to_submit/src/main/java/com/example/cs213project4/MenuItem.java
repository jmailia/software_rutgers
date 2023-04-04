package com.example.cs213project4;

import java.text.DecimalFormat;

public abstract class MenuItem {

    private double itemPrice;
    private int quantity;

    public MenuItem(){
        this.itemPrice = 0;
        this.quantity = 1;
    }

    public MenuItem(double itemPrice, int quantity){
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public double getItemPrice(){
        return this.itemPrice;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setItemPrice(double itemPrice){
        this.itemPrice = itemPrice;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void quantityIncrement(){
        this.quantity++;
    }

    public void quantityDecrement(){
        this.quantity--;
    }

    public String itemPriceToString(double itemPrice){
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        String formatItemPrice = df.format(itemPrice);
        return formatItemPrice;
    }



    public abstract double itemPrice();

}
