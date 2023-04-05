package com.example.cs213project4;

public class Donut extends MenuItem {

    enum donutTypes{YEASTDONUT, CAKEDONUT, HOLES}
    enum yeastFlavors{JELLY, VANILLA, BOSTONCREAM}
    enum cakeFlavors{LEMON, CINNAMON, BLUEBERRY}
    enum holeFlavors{CHOCOLATE, POWDER, GLAZED}

    public static final int YEAST = 1;
    public static final int CAKE = 2;
    public static final int HOLE = 3;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final double YEASTCOST = 1.59;
    public static final double CAKECOST = 1.79;
    public static final double HOLECOST = 0.39;

    private donutTypes type;
    private yeastFlavors yeastFlavor;
    private cakeFlavors cakeFlavor;
    private holeFlavors holeFlavor;

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

    public donutTypes getType(){
        return this.type;
    }

    public yeastFlavors getYeastFlavor(){
        return this.yeastFlavor;
    }

    public cakeFlavors getCakeFlavor(){
        return this.cakeFlavor;
    }

    public holeFlavors getHoleFlavor(){
        return this.holeFlavor;
    }

    public double itemPrice(){
        return super.getItemPrice() * super.getQuantity();
    }

    @Override
    public String toString() {
        String donutFlavor = "";
        String donut = "";

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
        donut = new String(donutFlavor + "(" + super.getQuantity() + ")");
        return donut;
    }

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


