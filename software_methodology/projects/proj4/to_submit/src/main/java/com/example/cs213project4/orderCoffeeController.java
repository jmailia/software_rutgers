package com.example.cs213project4;
/* @author Henry Hecht */
/* @author Aidan Cronin */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DecimalFormat;

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

    /**
     * Initializer method for orderCoffeeController.
     */
    @FXML
    public void initialize(){
        coffeeSizeBox.getItems().addAll("Short", "Tall", "Grande", "Venti");
        coffeeQuantityBox.getItems().addAll(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE);
        myCoffee = new Coffee();
        myCoffee.setItemPrice(1.89);
        myCoffee.setQuantity(1);
        totalTextField.setText(new DecimalFormat("$#,##0.00").format(myCoffee.getItemPrice()));
    }

    /**
     * Method updates the total displayed in the TextField.
     */
    @FXML
    private void updateTotal(){
        double total = myCoffee.itemPrice();
        totalTextField.setText(myCoffee.itemPriceToString(total));
    }

    /**
     * Method adds selected coffee to basket order.
     * @param event
     */
    @FXML
    private void addToOrder(ActionEvent event){
        RUCafeMainController.myOrder.addMenuItem(myCoffee);

        irishCreamBox.setSelected(false);
        frenchVanillaBox.setSelected(false);
        caramelBox.setSelected(false);
        mochaBox.setSelected(false);
        myCoffee = new Coffee();
        String size = coffeeSizeBox.getValue();

        if(size == null){myCoffee.setSize("Short");}
        else if (size.equals("Short")){myCoffee.setSize("Short");}
        else if (size.equals("Tall")){myCoffee.setSize("Tall");}
        else if (size.equals("Grande")){myCoffee.setSize("Grande");}
        else if (size.equals("Venti")){myCoffee.setSize("Venti");}
        coffeeQuantityBox.setValue(ONE);
        myCoffee.setQuantity(ONE);
        updateTotal();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setContentText("Successfully Added Coffee To Order");
        confirmation.show();
    }

    /**
     * Method takes value from coffeeSizeBox and sets it to myCoffee.
     * @param event
     */
    @FXML
    private void coffeeSize(ActionEvent event) {
        String size = coffeeSizeBox.getValue();
        switch(size) {
            case ("Short"):
                myCoffee.setSize("Short");
                break;
            case ("Tall"):
                myCoffee.setSize("Tall");
                break;
            case ("Grande"):
                myCoffee.setSize("Grande");
                break;
            case ("Venti"):
                myCoffee.setSize("Venti");
                break;
        }
        myCoffee.setItemPrice();
        updateTotal();
    }

    /**
     * Method gets value from coffeeQuantityBox and sets it to myCoffee.
     * @param event
     */
    @FXML
    private void coffeeQuantity(ActionEvent event) {
        int quantity = coffeeQuantityBox.getValue();
        myCoffee.setQuantity(quantity);
        myCoffee.setItemPrice();
        updateTotal();
    }

    /**
     * Method checks if frenchVanilla is selected as an addIn.
     * @param event
     */
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

    /**
     * Method checks if irishCream is selected as an addIn.
     * @param event
     */
    @FXML
    private void irishCreamAddIn(ActionEvent event){
        if(irishCreamBox.isSelected()) {
            myCoffee.setAddin("Irish Cream");
        }
        else{
            myCoffee.removeAddin("Irish Cream");
        }
        myCoffee.setAmountAddIns();
        myCoffee.setItemPrice();
        updateTotal();
    }

    /**
     * Method checks if caramel is selected as an addIn.
     * @param event
     */
    @FXML
    private void caramelAddIn(ActionEvent event){
        if(caramelBox.isSelected()) {
            myCoffee.setAddin("Caramel");
        }
        else{
            myCoffee.removeAddin("Caramel");
        }
        myCoffee.setAmountAddIns();
        myCoffee.setItemPrice();
        updateTotal();
    }

    /**
     * Method checks if mocha is selected as an addIn.
     * @param event
     */
    @FXML
    private void mochaAddIn(ActionEvent event){
        if(mochaBox.isSelected()) {
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