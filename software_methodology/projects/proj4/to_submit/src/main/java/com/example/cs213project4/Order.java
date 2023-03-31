package com.example.cs213project4;

import java.util.ArrayList;

public class Order {

    private ArrayList<MenuItem> items;
    private double subtotal;
    private double salesTax;
    private double total;
    private static final double NJ_SALES_TAX = 0.06626;

    public Order(){
        items = new ArrayList<MenuItem>();
    }

    public double getSubtotal(){
        return this.subtotal;
    }

    public double getSalesTax(){
        return this.salesTax;
    }

    public double getTotal(){
        return this.total;
    }

    public ArrayList<MenuItem> getItems(){
        return this.items;
    }

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

    public boolean removeMenuItem(MenuItem item){
        for(int i = 0; i < items.size(); i++){
            if(item.equals(items.get(i))){
                if(items.get(i).getQuantity() > 1){
                    items.get(i).quantityDecrement();
                    return true;
                }
                else{
                    items.remove(items.get(i));
                }
                return true;
            }
        }
        return false;
    }

    public void calculateTotal(){
        this.subtotal = 0;
        for(int i = 0; i < items.size(); i++){
            this.subtotal += items.get(i).getItemPrice();
        }
        this.salesTax = this.subtotal * NJ_SALES_TAX;
        this.total = this.salesTax + this.subtotal;
    }

    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < items.size(); i++){
            result = result + items.get(i).toString() + "\n";
        }
        return result;
    }


}
