package com.example.cs213project4;
/* @author Henry Hecht */
/* @author Aidan Cronin */

import java.text.DecimalFormat;

/**
 * Functionality related to a menu item as determined by its price and the its quantity ordered
 */
public abstract class MenuItem {

    private double itemPrice;
    private int quantity;

    /**
     * Initiates the menu
     */
    public MenuItem(){}

    /**
     * Constructs a menu item
     * @param itemPrice the price of the item
     * @param quantity the quantity of the item
     */
    public MenuItem(double itemPrice, int quantity){
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    /**
     * get the price of the item
     * @return the price of the item
     */
    public double getItemPrice(){
        return this.itemPrice;
    }

    /**
     * get the quantity of the item
     * @return the quantity of the item
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * set the price of the item
     * @param itemPrice the price which the item is to be set to
     */
    public void setItemPrice(double itemPrice){
        this.itemPrice = itemPrice;
    }

    /**
     * set the quantity of the item
     * @param quantity the quantity of the item which is to be set
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     * Increments the quantity by 1
     */
    public void quantityIncrement(){
        this.quantity++;
    }

    /**
     * Decrements the quantity by 1
     */
    public void quantityDecrement(){
        this.quantity--;
    }

    /**
     * Display the item price as a string
     * @param itemPrice the price of the item
     * @return the price of the item with correct formatting
     */
    public String itemPriceToString(double itemPrice){
        return new DecimalFormat("$#,##0.00").format(itemPrice);
    }

    /**
     * get the price of the item
     * @return the price of the item
     */
    public abstract double itemPrice();

}