package com.example.cs214project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RUCafeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader RUCafeMain = new FXMLLoader(RUCafeApplication.class.getResource("RUCafeMain.fxml"));
        FXMLLoader orderDonuts = new FXMLLoader(RUCafeApplication.class.getResource("orderDonuts.fxml"));
        FXMLLoader orderCoffee = new FXMLLoader(RUCafeApplication.class.getResource("orderCoffee.fxml"));
        FXMLLoader orderBasket = new FXMLLoader(RUCafeApplication.class.getResource("orderBasket.fxml"));
        FXMLLoader storeOrders = new FXMLLoader(RUCafeApplication.class.getResource("storeOrders.fxml"));
        Scene RUCafeMainScene = new Scene(RUCafeMain.load(), 320, 240);
        Scene orderDonutsScene = new Scene(orderDonuts.load(), 320, 240);
        Scene orderCoffeeScene = new Scene(orderCoffee.load(), 320, 240);
        Scene orderBasketScene = new Scene(orderBasket.load(), 320, 240);
        Scene storeOrdersScene = new Scene(storeOrders.load(), 320, 240);

        stage.setScene(RUCafeMainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}