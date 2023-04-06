package com.example.cs213project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;

/**
 * The controller for all functionalities related to seeing the store's orders.
 */
public class storeOrdersController {
    /**
     * Go to the page which displays the orders received by the store
     * @param event the event
     */
    public void listDetails (ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("storeOrders.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Store Orders");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception var5) {

        }
    }
}
