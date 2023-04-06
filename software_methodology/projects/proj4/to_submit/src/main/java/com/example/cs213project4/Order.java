package com.example.cs213project4;
/* @author Henry Hecht */
/* @author Aidan Cronin */

import java.util.ArrayList;

/**
 * Functionality related to the current order, including the items in the cart and the money owed.
 */
public class Order {

    private ArrayList<MenuItem> items;
    private double subtotal;
    private double salesTax;
    private double total;
    private static final double NJ_SALES_TAX = 0.06626;

    /**
     * Initiates the current order
     */
    public Order(){
        items = new ArrayList<MenuItem>();
    }

    /**
     * Get the subtotal of the order.
     * @return the subtotal of the order.
     */
    public double getSubtotal(){
        return this.subtotal;
    }

    /**
     * Get the sales tax for NJ.
     * @return the sales tax for NJ.
     */
    public double getSalesTax(){
        return this.salesTax;
    }

    /**
     * Get the total cost of goods sold.
     * @return the total cost of goods sold.
     */
    public double getTotal(){
        return this.total;
    }

    /**
     * Get the items in the cart.
     * @return the items in the cart.
     */
    public ArrayList<MenuItem> getItems(){
        return this.items;
    }

    /**
     * Add an item from the menu, in the correct quantity that was ordered
     * @param item the item which you want to order
     * @return true, if the menu item was ordered.
     */
    public boolean addMenuItem(MenuItem item){
        for(int i = 0; i < items.size(); i++){
            if(item.equals(items.get(i))){
                items.get(i).quantityIncrement();
                return true;
            }
        }
        items.add(item);
        return true;
    }

    /**
     * Remove an item from the menu, in the correct quantity that you wanted to order it in.
     * @param obj the item which you want to remove from the menu.
     * @return true, if the menu item was removed, false otherwise.
     */
    public boolean removeMenuItem(Object obj){
        if (obj instanceof String) {
            String item = (String)obj;
            for (int x = 0; x < items.size(); x++) {
                if (items.get(x).toString().equals(item)) {
                    items.remove(x);
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Calculate the total price of goods sold, as calculated by their inherent cost plus NJ sales tax.
     */
    public void calculateTotal(){
        this.subtotal = 0;
        for(int i = 0; i < items.size(); i++){
            this.subtotal = this.subtotal + items.get(i).getItemPrice();
        }
        this.salesTax = this.subtotal * NJ_SALES_TAX;
        this.total = this.salesTax + this.subtotal;
    }

    /**
     * Display the items as a string.
     * @return The string of items to display.
     */
    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < items.size(); i++){
            result = result + items.get(i).toString() + "\n";
        }
        return result;
    }

}