package com.example.cs213project4;

import javafx.fxml.FXML;
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
        update();
    }

    @FXML
    public void update(){
        RUCafeMainController.myOrder.calculateTotal();
        this.textArea.getItems().clear();
        String items = RUCafeMainController.myOrder.toString();
        String splitItems[] = items.split("\\n");
        for(int i = 0; i < splitItems.length; i++){
            this.textArea.getItems().add(splitItems[i]);
        }
        subTotalText.setText(new DecimalFormat("$#,##0.00").format(RUCafeMainController.myOrder.getSubtotal()));
        salesTaxText.setText(new DecimalFormat("$#,##0.00").format(RUCafeMainController.myOrder.getSalesTax()));
        totalText.setText(new DecimalFormat("$#,##0.00").format(RUCafeMainController.myOrder.getTotal()));
    }

    @FXML
    public void placeOrder(){
        String item = this.textArea.getSelectionModel().getSelectedItem();
        for(int i = 0; i < RUCafeMainController.myOrder.getItems().size(); i++){
            if(item.equals(RUCafeMainController.myOrder.getItems().get(i).toString()))
                MenuItem correctItem
                break;
        }
        RUCafeMainController.myOrder.removeMenuItem(RUCafeMainController.myOrder.getItems().get(i));
        update();
    }

    @FXML
    public void removeItem(){
        String item = this.textArea.getSelectionModel().getSelectedItem();
        RUCafeMainController.myOrder.removeMenuItem(item);
        update();
    }
}
