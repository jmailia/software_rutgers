package com.example.cs213project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class RUCafeMainController{

    public static Order myOrder = new Order();

    @FXML
    private Button orderDonutsButton;
    @FXML
    private Button orderCoffeeButton;
    @FXML
    private Button orderBasketButton;
    @FXML
    private Button storeOrderButton;

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

    @FXML
    public void goToOrderCoffee(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(RUCafeMainController.class.getResource("coffeeOrder.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Order Coffee");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
        }
    }

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