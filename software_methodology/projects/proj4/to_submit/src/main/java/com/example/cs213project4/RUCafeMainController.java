package com.example.cs213project4;
/* @author Henry Hecht */
/* @author Aidan Cronin */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.ArrayList;

/**
 * Controller for the main page of the RU Cafe application
 */
public class RUCafeMainController{

    public static Order myOrder = new Order();
    public static ArrayList<Order> storeOrders = new ArrayList<Order>();

    @FXML
    private Button orderDonutsButton;
    @FXML
    private Button orderCoffeeButton;
    @FXML
    private Button orderBasketButton;
    @FXML
    private Button storeOrderButton;

    /**
     * Go to the page for ordering donuts.
     * @param event the event which would result in this page being displayed.
     */
    @FXML
    public void goToOrderDonuts(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("donutOrder.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Order Donuts");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
        }
    }

    /**
     * Go to the page for ordering coffee.
     * @param event the event which would result in this page being displayed.
     */
    @FXML
    public void goToOrderCoffee(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("coffeeOrder.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Order Coffee");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
        }
    }

    /**
     * Go to the page which displays the basket.
     * @param event the event which would result in this page being displayed.
     */
    @FXML
    public void openYourOrdersPage(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("basketView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Basket View");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
        }
    }

    /**
     * Go to the page displaying the store orders.
     * @param event the event which would result in this page being displayed.
     */
    @FXML
    public void openStoreOrdersPage(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("storeOrders.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Store Orders");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
        }
    }

}