package com.example.cs214project4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public abstract class RUCafeController extends MenuItem{
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}