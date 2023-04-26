package com.example.rutgerscafe;
/* @author Henry Hecht */
/* @author Aidan Cronin */

import java.util.ArrayList;

/**
 * Functionalities of the current order as determined by the cart items and the running subtotal.
 */
public class Order {

    private ArrayList<MenuItem> items;
    private double subtotal;
    private double salesTax;
    private double total;
    private static final double NJ_SALES_TAX = 0.06626;

    /**
     * Initiate order
     */
    public Order(){
        items = new ArrayList<MenuItem>();
    }

    /**
     * Get subtotal of  order
     * @return the subtotal of order
     */
    public double getSubtotal(){
        return this.subtotal;
    }

    /**
     * Get NJ sales tax
     * @return the NJ sales tax
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
     * Get cart items
     * @return the cart items
     */
    public ArrayList<MenuItem> getItems(){
        return this.items;
    }

    /**
     * add a menu item, in its correct quantity
     * @param item what you want to order
     * @return true, if order was successful
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
                if (items.get(x).toString().equals(item)) {items.remove(x); return true;}
            }
        }
        return false;
    }
    /**
     * calculate total price of goods sold, incorporating NJ sales tax.
     */
    public void calculateTotal(){
        this.subtotal = 0;
        for(int j = 0; j < items.size(); j++){this.subtotal += items.get(j).getItemPrice();}
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
        for(int i = 0; i < items.size(); i++){result = result + items.get(i).toString() + "\n";}
        return result;
    }

}