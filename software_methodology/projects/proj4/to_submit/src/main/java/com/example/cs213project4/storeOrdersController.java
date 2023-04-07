package com.example.cs213project4;
/* @author Henry Hecht */
/* @author Aidan Cronin */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class storeOrdersController {

    @FXML
    private TextArea textArea;
    @FXML
    private Button cancelOrderButton;
    @FXML
    private Button exportButton;
    @FXML
    private TextField totalBox;
    @FXML
    private ComboBox<Integer> orderBox;

    /**
     * Initializer method for storeOrdersController.
     */
    @FXML
    private void initialize(){
        for (int x =0; x < RUCafeMainController.storeOrders.size(); x++) {
            this.orderBox.getItems().add(x + 1);
        }
        if (orderBox.getItems().isEmpty()) {
            return;
        }
        this.orderBox.setValue(1);
        this.update();
    }

    /**
     * Method updates the textArea and totalBox depending on which order is selected.
     */
    private void update(){
        if (orderBox.getItems().isEmpty()) {
            return;
        }
        int num = this.orderBox.getValue() - 1;
        this.textArea.setText(RUCafeMainController.storeOrders.get(num).toString());
        RUCafeMainController.storeOrders.get(num).calculateTotal();
        double total = RUCafeMainController.storeOrders.get(num).getTotal();
        this.totalBox.setText(new DecimalFormat("$#,##0.00").format(total));
    }

    /**
     * Method cancels a store order if it is selected.
     * @param event
     */
    @FXML
    private void cancelThisOrder(ActionEvent event) {
            int num = this.orderBox.getValue() - 1;
            this.orderBox.getItems().remove(RUCafeMainController.storeOrders.size() - 1);
            RUCafeMainController.storeOrders.remove(RUCafeMainController.storeOrders.get(num));
            if (this.orderBox.getItems().isEmpty()) {
                this.textArea.clear();
                this.totalBox.clear();
            }
            this.update();
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setContentText("Order has been cancelled");
            confirmation.show();
    }

    /**
     * Method calls an update() for orderBox.
     * @param event
     */
    @FXML
    private void orderUpdate(ActionEvent event) {
        this.update();
    }

    /**
     * Method exports store order selected into a text file
     * @param event
     * @throws IOException
     */
    @FXML
    private void exportOrder(ActionEvent event) throws IOException {
        FileWriter writer = new FileWriter(new String("storeOrder" + this.orderBox.getValue() + ".txt"));
        int num = this.orderBox.getValue() - 1;
        writer.write(RUCafeMainController.storeOrders.get(num).toString());
        writer.close();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setContentText("Order has been exported to: " + new String("storeOrder" + this.orderBox.getValue() + ".txt"));
        confirmation.show();
    }
}
