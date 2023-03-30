package com.example.cs214project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RUCafeApplication extends Application {

    @Override
    public void start(Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(RUCafeMainController.class.getResource("RUCafeMain.fxml"));
        Scene scene;
        Parent root;
        try {
            root = fxmlLoader.load();
            scene = new Scene(root);
            stage.setTitle("RUCafe Main");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println("fxml file does not exist");
            throw new RuntimeException(e);
        }
    }

        public static void main (String[]args){
            launch(args);
        }
    }