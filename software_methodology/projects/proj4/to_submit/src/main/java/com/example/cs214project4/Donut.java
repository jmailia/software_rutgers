package com.example.cs214project4;

public class Donut extends MenuItem {

    private String typeOfDonut;

    public Donut(String typeOfDonut){
        this.typeOfDonut = typeOfDonut;
    }

    public String getTypeOfDonut(){
        return this.typeOfDonut;
    }

    public double itemPrice(){
        double itemPrice = 0.00;
        switch(typeOfDonut){
            case("yeast"):
                itemPrice = 1.59;
            case("cake"):
                itemPrice = 1.79;
            case("hole"):
                itemPrice = 0.39;
        }
        return itemPrice;
    }

}
