package com.example.cs214project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RUCafeMainController{



    @FXML
    private Button orderDonutsButton;
    @FXML
    private Button orderCoffeeButton;
    @FXML
    private Button orderBasketButton;
    @FXML
    private Button storeOrderButton;

    @FXML
    void goToOrderDonuts(ActionEvent event){
        orderDonutsButton.setOnAction();
    }

    @FXML
    void goToOrderCoffee(ActionEvent event){
        orderCoffeeButton.setOnAction();
    }

    @FXML
    void goToOrderBasket(ActionEvent event){
        orderBasketButton.setOnAction();
    }

    @FXML
    void goToStoreOrders(ActionEvent event) {
        storeOrderButton.setOnAction();
    }

}