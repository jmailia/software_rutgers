module com.example.cs214project4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cs213project4 to javafx.fxml;
    exports com.example.cs213project4;
}