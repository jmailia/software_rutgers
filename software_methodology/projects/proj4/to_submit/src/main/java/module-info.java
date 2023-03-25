module com.example.cs214project4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cs214project4 to javafx.fxml;
    exports com.example.cs214project4;
}