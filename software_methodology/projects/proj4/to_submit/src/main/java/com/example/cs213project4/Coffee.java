package com.example.cs213project4;

/**
 * Functionality offered when ordering a coffee from the menu
 */
public class Coffee extends MenuItem{

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
     * Constructs the coffee with the correct supplemental add-ins.
     * @param coffeeSize the size of the coffee
     * @param frenchVanilla true for a frenchVanilla option, false if not
     * @param irishCream true for a irishCream option, false if not
     * @param caramel true for a caramel option, false if not
     * @param mocha true for a mocha option, false if not
     * @param quantity the number of times the add-in is added in.
     */
    public Coffee(int coffeeSize, boolean frenchVanilla, boolean irishCream, boolean caramel, boolean mocha, int quantity){
        super();
        super.setQuantity(quantity);
        int amountAddIns = 0;
        this.coffeeSize = coffeeSize;
        this.frenchVanilla = frenchVanilla;
        this.irishCream = irishCream;
        this.caramel = caramel;
        this.mocha = mocha;
        if(frenchVanilla)
            amountAddIns++;
        if(irishCream)
            amountAddIns++;
        if(caramel)
            amountAddIns++;
        if(mocha)
            amountAddIns++;
        this.amountAddIns = amountAddIns;
    }

    /**
     * Determines the cost of the coffee by adding the cup-size cost along with any additional add-in costs
     * @return the calculated cost of the coffee
     */
    public double itemPrice(){
        double itemPrice = 0.00;
        switch(this.coffeeSize){
            case(SHORT):
                itemPrice = SHORTCOST + (amountAddIns * ADDINCOST);
            case(TALL):
                itemPrice = TALLCOST + (amountAddIns * ADDINCOST);
            case(GRANDE):
                itemPrice = GRANDECOST + (amountAddIns * ADDINCOST);
            case(VENTI):
                itemPrice = VENTICOST + (amountAddIns * ADDINCOST);
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
