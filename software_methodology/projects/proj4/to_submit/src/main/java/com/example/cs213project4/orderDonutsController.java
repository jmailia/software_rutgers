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

    /**
     * Initialize the options provided to the user.
     */
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

    /**
     * Update the flavor menu to the correct tab for donut type
     * @return 1 for Yeast tab, 2 for Cake, 3 for Hole
     */
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

    /**
     * Gets the flavor from the selection
     * @param selection A string containing the flavor selected
     * @return the ith listing of the flavor in the donut type.
     * ie: the first flavor for yeast donuts will return '1'.
     */
    private int readFlavor(String selection){
        switch(selection){
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

    /**
     * Add a flavor of donut to your hand
     */
    @FXML
    private void addToList(){
        RUCafeMainController.myOrder.addMenuItem(myDonut);
        String selection = listDonutFlavor.getSelectionModel().getSelectedItem();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        int howManyDonuts = numDonutComboBox.getValue();
        if (selection!=null && howManyDonuts>0) {
            Donut myDonut2 = new Donut(howManyDonuts, updateMenu(), readFlavor(selection));
            updateTotal();
            this.donutOrderTextArea.getItems().add(myDonut2.toString());
            updateMenu();
            confirmation.setContentText("Successfully Added Donut To Order");
            confirmation.show();
        } else {
            confirmation.setContentText("You either did not input a donut type, a flavor, or valid number of donuts. Try again.");
            confirmation.show();
            return;
        }

    }

    /**
     * Add the donuts to the cart
     */
    @FXML
    private void addToOrder(){
        updateMenu();
    }

    /**
     * Remove the donuts from your hand, prior to placing them in the cart
     */
    @FXML
    private void removeFromList(){
        //boolean isInCart = this.donutOrderTextArea.getItems().contains();
        this.numDonutComboBox.setValue(ONE);
        this.donutTypeComboBox.setValue("Yeast Donut");
        updateMenu();
    }

    /**
     *The type of donut is picked.
     */
    @FXML
    private void pickedType(){
        updateMenu();
    }

    /**
     * Update the total cost of the donuts
     */
    @FXML
    private void updateTotal(){
        double total = myDonut.itemPrice();
        runningTotalDonut.setText(myDonut.itemPriceToString(total));
    }
}
