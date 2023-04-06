package com.example.cs213project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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

    @FXML
    private void initialize() {
        this.myDonuts = new ArrayList<Donut>();
        this.donutTypeComboBox.getItems().addAll("Yeast Donut" , "Donut Hole", "Cake Donut");
        this.numDonutComboBox.getItems().addAll(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE, THIRTEEN);
        this.numDonutComboBox.setValue(ONE);
        this.donutTypeComboBox.setValue("Yeast Donut");
        if (donutTypeComboBox.getValue() == "Yeast Donut") {
            listDonutFlavor.getItems().clear();
            listDonutFlavor.getItems().addAll("Jelly", "Vanilla", "Boston Cream");
        }
        else if (donutTypeComboBox.getValue() == "Donut Hole") {
            listDonutFlavor.getItems().clear();
            listDonutFlavor.getItems().addAll("Chocolate", "Powder", "Glazed");
        }
        else if (donutTypeComboBox.getValue() == "Cake Donut") {
            listDonutFlavor.getItems().clear();
            listDonutFlavor.getItems().addAll("Lemon", "Cinnamon", "Blueberry");
        }
        this.listDonutFlavor.getSelectionModel().select(ZERO);
    }
}
