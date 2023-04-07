package com.example.cs213project4;

import javafx.scene.control.Alert;

/**
 * Functionality offered when ordering a donut from the menu
 */
public class Donut extends MenuItem {

    enum donutTypes{YEASTDONUT, CAKEDONUT, HOLES}
    enum yeastFlavors{JELLY, VANILLA, BOSTONCREAM, COCONUT, STRAWBERRY, KEYLIME}
    enum cakeFlavors{LEMON, CINNAMON, BLUEBERRY}
    enum holeFlavors{CHOCOLATE, POWDER, GLAZED}

    /**
     * The first option, a yeast donut, is selected
     */
    public static final int YEAST = 1;
    /**
     * The second option, a cake donut, is selected
     */
    public static final int CAKE = 2;
    /**
     * The third option, a hole donut, is selected
     */
    public static final int HOLE = 3;
    /**
     * The first flavor is selected
     */
    public static final int ONE = 1;
    /**
     * The second flavor is selected
     */
    public static final int TWO = 2;
    /**
     * The third flavor is selected
     */
    public static final int THREE = 3;
    /**
     * The first flavor is selected
     */
    public static final int FOUR = 4;
    /**
     * The second flavor is selected
     */
    public static final int FIVE = 5;
    /**
     * The third flavor is selected
     */
    public static final int SIX = 6;
    /**
     * The default cost of a yeast donut
     */
    public static final double YEASTCOST = 1.59;
    /**
     * The default cost of a cake donut
     */
    public static final double CAKECOST = 1.79;
    /**
     * The default cost of a hole donut
     */
    public static final double HOLECOST = 0.39;

    private donutTypes type;
    private yeastFlavors yeastFlavor;
    private cakeFlavors cakeFlavor;
    private holeFlavors holeFlavor;

    /**
     * Constructs a donut given its amount ordered, its type, and flavor
     * @param amount the number of donuts ordered
     * @param donutType the type of donut ordered
     * @param flavor the flavor of the donut ordered
     */
    public Donut(int amount, int donutType, int flavor){
        super();
        super.setQuantity(amount);
        switch(donutType){
            case(YEAST):
                super.setItemPrice(YEASTCOST);
                this.type = donutTypes.YEASTDONUT;
                if(flavor == 1){this.yeastFlavor = yeastFlavors.JELLY;}
                if(flavor == 2){this.yeastFlavor = yeastFlavors.VANILLA;}
                if(flavor == 3){this.yeastFlavor = yeastFlavors.BOSTONCREAM;}
                if(flavor == 4){this.yeastFlavor = yeastFlavors.COCONUT;}
                if(flavor == 5){this.yeastFlavor = yeastFlavors.STRAWBERRY;}
                if(flavor == 6){this.yeastFlavor = yeastFlavors.KEYLIME;}
                break;
            case(CAKE):
                super.setItemPrice(CAKECOST);
                this.type = donutTypes.CAKEDONUT;
                if(flavor == 1){this.cakeFlavor = cakeFlavors.LEMON;}
                if(flavor == 2){this.cakeFlavor = cakeFlavors.CINNAMON;}
                if(flavor == 3){this.cakeFlavor = cakeFlavors.BLUEBERRY;}
                break;
            case(HOLE):
                super.setItemPrice(HOLECOST);
                this.type = donutTypes.HOLES;
                if(flavor == 1){this.holeFlavor = holeFlavors.CHOCOLATE;}
                if(flavor == 2){this.holeFlavor = holeFlavors.POWDER;}
                if(flavor == 3){this.holeFlavor = holeFlavors.GLAZED;}
                break;
        }
    }

    /**
     * Setter for the type of donut
     */
    public void setType(String type){
        switch(type){
            case("Yeast"):
                this.type = donutTypes.YEASTDONUT;
                break;
            case("Cake"):
                this.type = donutTypes.CAKEDONUT;
                break;
            case("Hole"):
                this.type = donutTypes.HOLES;
                break;
        }
    }

    /**
     * Setter for the flavor of the yeast donut
     */
    public void setYeastFlavor(String flavor){
        switch(flavor){
            case("Jelly"):
                this.yeastFlavor = yeastFlavors.JELLY;
                break;
            case("Vanilla"):
                this.yeastFlavor = yeastFlavors.VANILLA;
                break;
            case("Boston Cream"):
                this.yeastFlavor = yeastFlavors.BOSTONCREAM;
                break;
            case("Coconut"):
                this.yeastFlavor = yeastFlavors.COCONUT;
                break;
            case("Strawberry"):
                this.yeastFlavor = yeastFlavors.STRAWBERRY;
                break;
            case("Key Lime"):
                this.yeastFlavor = yeastFlavors.KEYLIME;
                break;
        }
    }

    /**
     * Setter for the flavor of the cake donut
     */
    public void setCakeFlavor(String flavor){
        switch(flavor){
            case("Lemon"):
                this.cakeFlavor = cakeFlavors.LEMON;
                break;
            case("Cinnamon"):
                this.cakeFlavor = cakeFlavors.CINNAMON;
                break;
            case("Blueberry"):
                this.cakeFlavor = cakeFlavors.BLUEBERRY;
                break;
        }
    }

    /**
     * Setter for the flavor of the hole donut
     */
    public void setHoleFlavor(String flavor){
        switch(flavor){
            case("Chocolate"):
                this.holeFlavor = holeFlavors.CHOCOLATE;
                break;
            case("Powder"):
                this.holeFlavor = holeFlavors.POWDER;
                break;
            case("Glazed"):
                this.holeFlavor = holeFlavors.GLAZED;
                break;
        }
    }

    /**
     * Get the type of donut
     * @return the type of donut
     */
    public donutTypes getType(){
        return this.type;
    }

    /**
     * Get the flavor of the yeast donut
     * @return the flavor of the yeast donut
     */
    public yeastFlavors getYeastFlavor(){
        return this.yeastFlavor;
    }

    /**
     * Get the flavor of the cake donut
     * @return the flavor of the cake donut
     */
    public cakeFlavors getCakeFlavor(){
        return this.cakeFlavor;
    }

    /**
     * Get the flavor of the hole donut
     * @return the flavor of the hole donut
     */
    public holeFlavors getHoleFlavor(){
        return this.holeFlavor;
    }

    /**
     * Get the price of the items
     * @return the price of the items
     */
    public double itemPrice(){
        return super.getItemPrice() * super.getQuantity();
    }

    /**
     * Display the flavor of the donut along with its quantity as a string
     * @return a string containing the flavor of the donut along with its quantity
     */
    @Override
    public String toString() {
        return new String(toStringForEquals() + "( Quantity: " + super.getQuantity() + " )");
    }

    /**
     * Whether the current object equals the donut
     * @param obj the object to compare with
     * @return true, if the two are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Donut) {
            Donut donut = (Donut)obj;
            String parameter = donut.toStringForEquals();
            String thisDonut = this.toStringForEquals();

            return parameter.equals(thisDonut);
        }
        return false;
    }
    /**
     * Display the flavor of the donut as a string
     * @return a string containing the flavor of the donut
     */
    public String toStringForEquals(){
        String donutFlavor = "";
        if(this.type == donutTypes.YEASTDONUT){
            if(this.yeastFlavor == yeastFlavors.JELLY){donutFlavor = "Jelly ";}
            else if(this.yeastFlavor == yeastFlavors.VANILLA){donutFlavor = "Vanilla ";}
            else if(this.yeastFlavor == yeastFlavors.BOSTONCREAM){donutFlavor = "Boston Cream ";}
            else if(this.yeastFlavor == yeastFlavors.COCONUT){donutFlavor = "Coconut ";}
            else if(this.yeastFlavor == yeastFlavors.STRAWBERRY){donutFlavor = "Strawberry ";}
            else if(this.yeastFlavor == yeastFlavors.KEYLIME){donutFlavor = "Key Lime ";}
        }
        else if(this.type == donutTypes.CAKEDONUT){
            if(this.cakeFlavor == cakeFlavors.LEMON){donutFlavor = "Lemon Cake ";}
            else if(this.cakeFlavor == cakeFlavors.CINNAMON){donutFlavor = "Cinnamon Cake ";}
            else if(this.cakeFlavor == cakeFlavors.BLUEBERRY){donutFlavor = "Blueberry Cake ";}
        }
        else if(this.type == donutTypes.HOLES){
            if(this.holeFlavor == holeFlavors.CHOCOLATE){donutFlavor = "Chocolate Hole ";}
            else if(this.holeFlavor == holeFlavors.POWDER){donutFlavor = "Powder Hole ";}
            else if(this.holeFlavor == holeFlavors.GLAZED){donutFlavor = "Glazed Hole ";}
        }
        return donutFlavor;
    }

}