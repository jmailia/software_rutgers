package com.example.cs213project4;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class basketViewController {

    @FXML
    private ListView<String> textArea;
    @FXML
    private TextField subTotalText;
    @FXML
    private TextField salesTaxText;
    @FXML
    private TextField totalText;
    @FXML
    private Button removeSelectedButton;
    @FXML
    private Button placeOrderButton;

    @FXML
    public void initialize(){
        updateTextArea();
    }

    @FXML
    public void updateTextArea(){
        RUCafeMainController.myOrder.calculateTotal();
        this.textArea.getItems().clear();
        String items = RUCafeMainController.myOrder.toString();
        String splitItems[] = items.split("\\n");
        for(int i = 0; i < splitItems.length; i++){
            this.textArea.getItems().add(splitItems[i]);
            System.out.println(splitItems[i]);
        }
        subTotalText.setText(new DecimalFormat("$#,##0.00").format(RUCafeMainController.myOrder.getSubtotal()));
        salesTaxText.setText(new DecimalFormat("$#,##0.00").format(RUCafeMainController.myOrder.getSalesTax()));
        totalText.setText(new DecimalFormat("$#,##0.00").format(RUCafeMainController.myOrder.getTotal()));
    }

    @FXML
    public void placeOrder(){
        if(RUCafeMainController.myOrder != null) {
            Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
            confirmation.setContentText("Order has been placed");
            confirmation.show();
            RUCafeMainController.storeOrders.add(RUCafeMainController.myOrder);
            RUCafeMainController.myOrder = new Order();
        }
        updateTextArea();
    }

    @FXML
    public void removeItem(){
        String item = this.textArea.getSelectionModel().getSelectedItem();
        RUCafeMainController.myOrder.removeMenuItem(item);
        updateTextArea();
    }
}
