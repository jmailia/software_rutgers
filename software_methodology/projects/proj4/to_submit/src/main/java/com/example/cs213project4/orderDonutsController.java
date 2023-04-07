package com.example.cs213project4;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class orderDonutsController {

    public final static int ZERO = 0;
    public final static int ONE = 1;
    public final static int TWO = 2;
    public final static int THREE = 3;
    public final static int FOUR = 4;
    public final static int FIVE = 5;
    public final static int SIX = 6;
    public final static int SEVEN = 7;
    public final static int EIGHT = 8;
    public final static int NINE = 9;
    public final static int TEN = 10;
    public final static int ELEVEN = 11;
    public final static int TWELVE = 12;
    public final static int THIRTEEN = 13;
    private ArrayList<Donut> myDonuts;

    @FXML
    private ComboBox<String> donutTypeComboBox;
    @FXML
    private ListView<String> listDonutFlavor;
    @FXML
    private ComboBox<Integer> numDonutComboBox;
    @FXML
    private Button addDonutButton;
    @FXML
    private Button removeDonutButton;
    @FXML
    private ListView<String> donutOrderTextArea;
    @FXML
    private TextField runningTotalDonut;
    @FXML
    private Button addToOrderDonutButton;

    private Donut myDonut;
    @FXML
    private void initialize() {
        this.myDonuts = new ArrayList<Donut>();
        this.donutTypeComboBox.getItems().addAll("Yeast Donut" , "Donut Hole", "Cake Donut");
        this.numDonutComboBox.getItems().addAll(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE, THIRTEEN);
        this.numDonutComboBox.setValue(ONE);
        this.donutTypeComboBox.setValue("Yeast Donut");
        myDonut = new Donut(1,1,1);
        int donutType = updateMenu();
    }
    @FXML
    public int updateMenu(){
        if (this.donutTypeComboBox.getValue() == "Yeast Donut") {
            this.listDonutFlavor.getItems().clear();
            this.listDonutFlavor.getItems().addAll("Jelly", "Vanilla", "Boston Cream", "Coconut", "Strawberry", "Key Lime");
            return 1;
        }
        else if (this.donutTypeComboBox.getValue() == "Donut Hole") {
            this.listDonutFlavor.getItems().clear();
            this.listDonutFlavor.getItems().addAll("Chocolate", "Powder", "Glazed");
            return 3;
        }
        else if (this.donutTypeComboBox.getValue() == "Cake Donut") {
            this.listDonutFlavor.getItems().clear();
            this.listDonutFlavor.getItems().addAll("Lemon", "Cinnamon", "Blueberry");
            return 2;
        }
        this.listDonutFlavor.getSelectionModel().select(ZERO);
        return 0;
    }
    private int readFlavor(){
        switch(this.listDonutFlavor.getSelectionModel().getSelectedItem()){
            case("Jelly"):
            case("Lemon"):
            case("Chocolate"):
                return 1;
            case("Vanilla"):
            case("Cinnamon"):
            case("Powder"):
                return 2;
            case("Boston Cream"):
            case("Blueberry"):
            case("Glazed"):
                return 3;
            case("Coconut"):
                return 4;
            case("Strawberry"):
                return 5;
            case("Key Lime"):
                return 6;
            default:
                return 0;
        }
    }
    @FXML
    private void addToList(){
        int flavorSelection = 0;
        RUCafeMainController.myOrder.addMenuItem(myDonut);
        //
        Donut myDonut = new Donut(numDonutComboBox.getValue(),updateMenu(),readFlavor());

        updateTotal();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setContentText("Successfully Added Donut To Order");
        confirmation.show();
        this.numDonutComboBox.setValue(ONE);
        this.donutTypeComboBox.setValue("Yeast Donut");
        updateMenu();
    }
    @FXML
    private void addToOrder(){
        updateMenu();
    }
    @FXML
    private void removeFromList(){
        this.numDonutComboBox.setValue(ONE);
        this.donutTypeComboBox.setValue("Yeast Donut");
        updateMenu();
    }
    @FXML
    private void pickedType(){
        updateMenu();
    }
    @FXML
    private void updateTotal(){
        double total = myDonut.itemPrice();
        //totalTextField.setText(myDonut.itemPriceToString(total));
    }
}
