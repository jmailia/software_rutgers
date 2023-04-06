package com.example.cs213project4;
/* @author Henry Hecht */
/* @author Aidan Cronin */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * To set up base functionality for the RU Cafe Application
 */
public class RUCafeApplication extends Application {

    /**
     * Loads the home page for the application.
     * @param stage the stage provided.
     */
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

    /**
     * Launches the application
     * @param args the arguments provided
     */
    public static void main (String[]args){
        launch(args);
    }
}