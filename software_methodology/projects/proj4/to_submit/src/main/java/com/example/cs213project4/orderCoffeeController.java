package com.example.cs213project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class orderCoffeeController {

    private final static int ONE = 1;
    private final static int TWO = 2;
    private final static int THREE = 3;
    private final static int FOUR = 4;
    private final static int FIVE = 5;
    private final static int SIX = 6;
    private final static int SEVEN = 7;
    private final static int EIGHT = 8;
    private final static int NINE = 9;

    private Coffee myCoffee;

    @FXML
    private CheckBox frenchVanillaBox;
    @FXML
    private CheckBox irishCreamBox;
    @FXML
    private CheckBox caramelBox;
    @FXML
    private CheckBox mochaBox;
    @FXML
    private TextField totalTextField;
    @FXML
    private ComboBox<String> coffeeSizeBox;
    @FXML
    private ComboBox<Integer> coffeeQuantityBox;
    @FXML
    private Button addToOrderButton;

    @FXML
    public void initialize(){
        coffeeSizeBox.getItems().addAll("Short", "Tall", "Grande", "Venti");
        coffeeQuantityBox.getItems().addAll(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE);
        myCoffee = new Coffee();
    }

    @FXML
    private void updateTotal(){
        double total = myCoffee.itemPrice();
        totalTextField.setText(myCoffee.itemPriceToString(total));
    }

    @FXML
    private void addToOrder(){
        RUCafeMainController.myOrder.addMenuItem(myCoffee);
        irishCreamBox.setSelected(false);
        frenchVanillaBox.setSelected(false);
        caramelBox.setSelected(false);
        mochaBox.setSelected(false);
        myCoffee = new Coffee();
        String size = coffeeSizeBox.getValue();
        if(size == null) {
            myCoffee.setSize("Short");
        }
        else if (size.equals("Short")) {
            myCoffee.setSize("Short");
        }
        else if (size.equals("Tall")) {
            myCoffee.setSize("Tall");
        }
        else if (size.equals("Grande")) {
            myCoffee.setSize("Grande");
        }
        else if (size.equals("Venti")) {
            myCoffee.setSize("Venti");
        }
        coffeeQuantityBox.setValue(ONE);
        myCoffee.setQuantity(ONE);
        updateTotal();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Confirmation");
        confirmation.setContentText("Successfully Added Coffee To Order");
        confirmation.show();
    }

    @FXML
    private void coffeeSize(ActionEvent e) {
        String size = coffeeSizeBox.getValue();
        switch(size) {
            case ("Short"):
                myCoffee.setSize("Short");
            case ("Tall"):
                myCoffee.setSize("Tall");
            case ("Grande"):
                myCoffee.setSize("Grande");
            case ("Venti"):
                myCoffee.setSize("Venti");
        }
        myCoffee.setItemPrice();
        updateTotal();
    }

    @FXML
    private void coffeeQuantity(ActionEvent e) {
        int quantity = coffeeQuantityBox.getValue();
        myCoffee.setQuantity(quantity);
        myCoffee.setItemPrice();
        updateTotal();
    }

    @FXML
    private void frenchVanillaAddIn(ActionEvent event){
        if(frenchVanillaBox.isSelected()) {
            myCoffee.setAddin("French Vanilla");
        }
        else{
            myCoffee.removeAddin("French Vanilla");
        }
        myCoffee.setAmountAddIns();
        myCoffee.setItemPrice();
        updateTotal();
    }

    @FXML
    private void irishCreamAddIn(ActionEvent event){
        if(frenchVanillaBox.isSelected()) {
            myCoffee.setAddin("Irish Cream");
        }
        else{
            myCoffee.removeAddin("Irish Cream");
        }
        myCoffee.setAmountAddIns();
        myCoffee.setItemPrice();
        updateTotal();
    }

    @FXML
    private void caramelAddIn(ActionEvent event){
        if(frenchVanillaBox.isSelected()) {
            myCoffee.setAddin("Caramel");
        }
        else{
            myCoffee.removeAddin("Caramel");
        }
        myCoffee.setAmountAddIns();
        myCoffee.setItemPrice();
        updateTotal();
    }

    @FXML
    private void mochaAddIn(ActionEvent event){
        if(frenchVanillaBox.isSelected()) {
            myCoffee.setAddin("Mocha");
        }
        else{
            myCoffee.removeAddin("Mocha");
        }
        myCoffee.setAmountAddIns();
        myCoffee.setItemPrice();
        updateTotal();
    }
}


