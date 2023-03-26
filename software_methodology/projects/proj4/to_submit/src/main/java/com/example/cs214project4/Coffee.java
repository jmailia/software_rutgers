package com.example.cs214project4;

public class Coffee {

    private String sizeOfCoffee;
    private String[] addons;

    public Coffee(String sizeOfCoffee, String[] addons){
        this.sizeOfCoffee = sizeOfCoffee;
        this.addons = addons;
    }

    public Coffee(String sizeOfCoffee){
        this.sizeOfCoffee = sizeOfCoffee;
    }

    public String getSizeOfCoffee(){
        return this.sizeOfCoffee;
    }

    public String[] getAddons(){
        return this.addons;
    }

    public double itemPrice(){
        double itemPrice = 0.00;
        if(addons != null){
            switch (sizeOfCoffee){
                case("short"):
                    itemPrice += 1.89 + (.30 * addons.length);
                case("tall"):
                    itemPrice += 2.29 + (.30 * addons.length);
                case("grande"):
                    itemPrice += 2.69 + (.30 * addons.length);
                case("venti"):
                    itemPrice += 3.09 + (.30 * addons.length);
            }
        }
        else{
            switch (sizeOfCoffee){
                case("short"):
                    itemPrice += 1.89;
                case("tall"):
                    itemPrice += 2.29;
                case("grande"):
                    itemPrice += 2.69;
                case("venti"):
                    itemPrice += 3.09;
            }
        }
        return itemPrice;
    }


}
