package com.example.cs213project4;

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

    public Coffee(){
        super(SHORTCOST, ONE);
        amountAddIns = 0;
    }

    public void setSize(String size){
        switch(size){
            case("Short"):
                this.coffeeSize = SHORT;
                super.setItemPrice(this.itemPrice());
            case("Tall"):
                this.coffeeSize = TALL;
                super.setItemPrice(this.itemPrice());
            case("Grande"):
                this.coffeeSize = GRANDE;
                super.setItemPrice(this.itemPrice());
            case("Venti"):
                this.coffeeSize = VENTI;
                super.setItemPrice(this.itemPrice());
        }
    }

    public void setAmountAddIns(){
        Boolean[] addIns = {frenchVanilla, irishCream, caramel, mocha};
        this.amountAddIns = 0;
        for(int i = 0; i< addIns.length; i++){
            if(addIns[i])
                amountAddIns++;
        }
    }

    public void setItemPrice(){
        super.setItemPrice(this.itemPrice());
    }

    public void setAddin(String addin){
        switch(addin){
            case("French Vanilla"):
                this.frenchVanilla = true;
            case("Irish Cream"):
                this.irishCream = true;
            case("Caramel"):
                this.caramel = true;
            case("Mocha"):
                this.mocha = true;
        }
        this.itemPrice();
    }

    public void removeAddin(String addin){
        switch(addin){
            case("French Vanilla"):
                this.frenchVanilla = false;
            case("Irish Cream"):
                this.irishCream = false;
            case("Caramel"):
                this.caramel = false;
            case("Mocha"):
                this.mocha = false;
        }
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
            itemPrice = SHORTCOST + (amountAddIns * ADDINCOST) * super.getQuantity();
        }
        else if(this.coffeeSize == TALL){
            itemPrice = TALLCOST + (amountAddIns * ADDINCOST) * super.getQuantity();
        }
        else if(this.coffeeSize == GRANDE){
            itemPrice = GRANDECOST + (amountAddIns * ADDINCOST) * super.getQuantity();
        }
        else if(this.coffeeSize == VENTI){
            itemPrice = VENTICOST + (amountAddIns * ADDINCOST) * super.getQuantity();
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
        String coffeeSize = "";
        String addIns = "";
        switch(this.coffeeSize){
            case(SHORT):
                coffeeSize = "Short Coffee ";
            case(TALL):
                coffeeSize = "Tall Coffee ";
            case(GRANDE):
                coffeeSize = "Grande Coffee ";
            case(VENTI):
                coffeeSize = "Venti Coffee ";
        }
        if(amountAddIns > 0){
            if(frenchVanilla)
                addIns = new String(addIns + "French Vanilla, ");
            if(irishCream)
                addIns = new String(addIns + "Irish Cream, ");
            if(caramel)
                addIns = new String(addIns + "Caramel, ");
            if(mocha)
                addIns = new String(addIns + "Mocha, ");
            return coffeeSize + "With: " + addIns;
        }
        else
            return coffeeSize + "With No Add-Ins";
    }


}
