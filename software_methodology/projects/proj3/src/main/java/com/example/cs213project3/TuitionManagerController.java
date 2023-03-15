package com.example.cs213project3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TuitionManagerController {

    Enrollment myEnrollment = new Enrollment();
    Roster myRoster = new Roster();

    @FXML
    private TextField fnameRosterTextField;
    @FXML
    private TextField lnameRosterTextField;
    @FXML
    private DatePicker dobRoster;
    @FXML
    private TextField locationTextField;
    @FXML
    private RadioButton BAITRadioButton;
    @FXML
    private RadioButton CSRadioButton;
    @FXML
    private RadioButton ECERadioButton;
    @FXML
    private RadioButton ITIRadioButton;
    @FXML
    private RadioButton MATHRadioButton;
    @FXML
    private TextField creditsCompletedTextField;
    @FXML
    private RadioButton residentRadioButton;
    @FXML
    private RadioButton nonResidentRadioButton;
    @FXML
    private RadioButton tristateRadioButton;
    @FXML
    private RadioButton internationalRadioButton;
    @FXML
    private RadioButton nyRadioButton;
    @FXML
    private RadioButton ctRadioButton;
    @FXML
    private CheckBox studyAbroadCheckButton;
    @FXML
    private TextField fnameEnrollmentTextField;
    @FXML
    private TextField lnameEnrollmentTextField;
    @FXML
    private DatePicker dobEnrollment;
    @FXML
    private TextField creditsEnrolledTextField;
    @FXML
    private TextField fnameScholarshipTextField;
    @FXML
    private TextField lnameScholarshipTextField;
    @FXML
    private DatePicker dobScholarship;
    @FXML
    private TextField creditsScholarshipTextField;
    @FXML
    private TextArea outputText;

    @FXML
    void clickAdd(ActionEvent event) {

    }

    @FXML
    void clickRemove() {

    }

    @FXML
    void clickChangeMajor(ActionEvent event) {

    }

    @FXML
    void loadFromFile(ActionEvent event) {
        
    }

    @FXML
    void clickEnroll(ActionEvent event) {

    }

    @FXML
    void clickDrop() {

    }

    @FXML
    void updateScholarshipAmount(ActionEvent event) {

    }

}
