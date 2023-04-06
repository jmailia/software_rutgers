package com.example.cs213project4;
/* @author Henry Hecht */
/* @author Aidan Cronin */

/**
 * Functionality offered when ordering a coffee from the menu
 */
public class Coffee extends MenuItem{

    private static final int ONE = 1;
    private static final int SHORT = 1;
    private static final int TALL = 2;
    private static final int GRANDE = 3;
    private static final int VENTI = 4;
    private static final double SHORTCOST = 1.89;
    private static final double TALLCOST = 2.29;
    private static final double GRANDECOST = 2.69;
    private static final double VENTICOST = 3.09;
    private static final double ADDINCOST = 0.30;

    private int coffeeSize;
    private boolean frenchVanilla;
    private boolean irishCream;
    private boolean caramel;
    private boolean mocha;
    private int amountAddIns;

    /**
     * Constructor method for Coffee. Sets default item cost to SHORTCOST
     * and sets the quantity to ONE.
     */
    public Coffee(){
        super(SHORTCOST, ONE);
        amountAddIns = 0;
    }

    /**
     * Setter method to change the size of this coffee.
     * @param size The Size we want to set in String form
     */
    public void setSize(String size){
        this.coffeeSize = SHORT;
        switch(size){
            case("Short"):
                this.coffeeSize = SHORT;
                break;
            case("Tall"):
                this.coffeeSize = TALL;
                break;
            case("Grande"):
                this.coffeeSize = GRANDE;
                break;
            case("Venti"):
                this.coffeeSize = VENTI;
                break;
        }
        super.setItemPrice(this.itemPrice());
    }

    /**
     * Setter method to change the amount of addIns in this coffee.
     */
    public void setAmountAddIns(){
        Boolean[] addIns = {frenchVanilla, irishCream, caramel, mocha};
        this.amountAddIns = 0;
        for(int i = 0; i< addIns.length; i++){
            if(addIns[i] == true)
                amountAddIns++;
        }
    }

    /**
     * Setter method to change the itemPrice using super().
     */
    public void setItemPrice(){
        super.setItemPrice(this.itemPrice());
    }

    /**
     * Setter method for one of the addIns, only adds.
     * @param addin The addin we want to turn true
     */
    public void setAddin(String addin){
        switch(addin){
            case("French Vanilla"):
                this.frenchVanilla = true;
                break;
            case("Irish Cream"):
                this.irishCream = true;
                break;
            case("Caramel"):
                this.caramel = true;
                break;
            case("Mocha"):
                this.mocha = true;
                break;
        }
        this.setAmountAddIns();
        this.itemPrice();
    }

    /**
     * Setter method for one of the addIns, only removes.
     * @param addin The addin we want to turn false
     */
    public void removeAddin(String addin){
        switch(addin){
            case("French Vanilla"):
                this.frenchVanilla = false;
                break;
            case("Irish Cream"):
                this.irishCream = false;
                break;
            case("Caramel"):
                this.caramel = false;
                break;
            case("Mocha"):
                this.mocha = false;
                break;
        }
        this.setAmountAddIns();
        this.itemPrice();
    }

    /**
     * Determines the cost of the coffee by adding the cup-size cost along with any additional add-in costs
     * @return the calculated cost of the coffee
     */
    public double itemPrice(){
        double itemPrice = 0.00;
        this.setAmountAddIns();
        if(this.coffeeSize == SHORT){
            itemPrice = SHORTCOST * super.getQuantity() + (amountAddIns * ADDINCOST) ;
        }
        else if(this.coffeeSize == TALL){
            itemPrice = TALLCOST * super.getQuantity() + (amountAddIns * ADDINCOST);
        }
        else if(this.coffeeSize == GRANDE){
            itemPrice = GRANDECOST * super.getQuantity() + (amountAddIns * ADDINCOST);
        }
        else if(this.coffeeSize == VENTI){
            itemPrice = VENTICOST * super.getQuantity() + (amountAddIns * ADDINCOST);
        }
        super.setItemPrice(itemPrice);
        return itemPrice;
    }

    /**
     * Displays the user's coffee cup-size and any add-ins as a string
     * @return a string containing the user's coffee cup-size and any add-ins
     */
    @Override
    public String toString(){
        String coffeeSize = "Short Coffee ";
        String addIns = "";
        switch(this.coffeeSize){
            case(SHORT):
                coffeeSize = "Short Coffee ";
                break;
            case(TALL):
                coffeeSize = "Tall Coffee ";
                break;
            case(GRANDE):
                coffeeSize = "Grande Coffee ";
                break;
            case(VENTI):
                coffeeSize = "Venti Coffee ";
                break;
        }
        this.setAmountAddIns();
        if(amountAddIns > 0){
            if(frenchVanilla == true){
                addIns = new String(addIns + "French Vanilla ");}
            if(irishCream == true){
                addIns = new String(addIns + "Irish Cream ");}
            if(caramel == true){
                addIns = new String(addIns + "Caramel ");}
            if(mocha == true){
                addIns = new String(addIns + "Mocha");}
            return coffeeSize + "With: " + addIns + " ( Quantity: " + super.getQuantity() + " )";
        }
        else
            return coffeeSize + "With No Add-Ins ( Quantity: " + super.getQuantity() + " )";
    }

}
