package com.example.cs213project4;

/**
 * Functionality offered when ordering a donut from the menu
 */
public class Donut extends MenuItem {

    enum donutTypes{YEASTDONUT, CAKEDONUT, HOLES}
    enum yeastFlavors{JELLY, VANILLA, BOSTONCREAM}
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
                if(flavor == ONE)
                    this.yeastFlavor = yeastFlavors.JELLY;
                if(flavor == TWO)
                    this.yeastFlavor = yeastFlavors.VANILLA;
                if(flavor == THREE)
                    this.yeastFlavor = yeastFlavors.BOSTONCREAM;
                break;
            case(CAKE):
                super.setItemPrice(CAKECOST);
                this.type = donutTypes.CAKEDONUT;
                if(flavor == ONE)
                    this.cakeFlavor = cakeFlavors.LEMON;
                if(flavor == TWO)
                    this.cakeFlavor = cakeFlavors.CINNAMON;
                if(flavor == THREE)
                    this.cakeFlavor = cakeFlavors.BLUEBERRY;
                break;
            case(HOLE):
                super.setItemPrice(HOLECOST);
                this.type = donutTypes.HOLES;
                if(flavor == ONE)
                    this.holeFlavor = holeFlavors.CHOCOLATE;
                if(flavor == TWO)
                    this.holeFlavor = holeFlavors.POWDER;
                if(flavor == THREE)
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
        return new String(toStringForEquals() + "(" + super.getQuantity() + ")");
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
            if(this.yeastFlavor == yeastFlavors.JELLY){
                donutFlavor = "Jelly ";
            }
            if(this.yeastFlavor == yeastFlavors.VANILLA){
                donutFlavor = "Vanilla ";
            }
            if(this.yeastFlavor == yeastFlavors.BOSTONCREAM){
                donutFlavor = "Boston Cream ";
            }
        }
        if(this.type == donutTypes.CAKEDONUT){
            if(this.cakeFlavor == cakeFlavors.LEMON){
                donutFlavor = "Lemon Cake ";
            }
            if(this.cakeFlavor == cakeFlavors.CINNAMON){
                donutFlavor = "Cinnamon Cake ";
            }
            if(this.cakeFlavor == cakeFlavors.BLUEBERRY){
                donutFlavor = "Blueberry Cake ";
            }
        }
        if(this.type == donutTypes.HOLES){
            if(this.holeFlavor == holeFlavors.CHOCOLATE){
                donutFlavor = "Chocolate Hole ";
            }
            if(this.holeFlavor == holeFlavors.POWDER){
                donutFlavor = "Powder Hole ";
            }
            if(this.holeFlavor == holeFlavors.GLAZED){
                donutFlavor = "Glazed Hole ";
            }
        }
        return donutFlavor;
    }

}


