package com.example.rutgerscafe;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;


public class BasketViewActivity extends AppCompatActivity {

    ListView itemList;
    TextView subtotal;
    TextView tax;
    TextView grandTotal;
    Button deleteItem;
    Button submitOrder;
    MenuItem selected;
    ArrayAdapter itemsAdapter;
    private final DecimalFormat df = new DecimalFormat("#0.00");

    /**
     * Initializes elements of the cart activity and defines their functionalities
     * @param savedInstanceState Not used
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basketview_activity);
        itemList = findViewById(R.id.lv_itemList);
        itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, MainActivity.myOrder.getItems());
        itemList.setAdapter(itemsAdapter);
        itemList.setOnItemClickListener((adapterView, view, i, l) -> selected = (MenuItem) itemList.getItemAtPosition(i));
        deleteItem = findViewById(R.id.btn_deleteItem);
        deleteItem.setOnClickListener(view -> {
            deleteItem();
        });
        submitOrder = findViewById(R.id.btn_submitOrder);
        submitOrder.setOnClickListener(view -> {
            submit();
        });
        subtotal = findViewById(R.id.tv_orderSubtotal);
        tax = findViewById(R.id.tv_orderTax);
        grandTotal = findViewById(R.id.tv_orderGrandTotal);
        updateTotals();
    }

    /**
     * Submits the cart to store orders
     */
    private void submit() {
        if (!MainActivity.myOrder.getItems().isEmpty()) {
            MainActivity.storeOrders.add(MainActivity.myOrder);
            MainActivity.myOrder = new Order();
            itemsAdapter.notifyDataSetChanged();
            updateTotals();
            Toast toast = Toast.makeText(this, "Order Successfully Placed!", Toast.LENGTH_LONG);
            toast.show();
            super.onBackPressed();
        }
        else {
            Toast toast = Toast.makeText(this, "No Items in Cart to Order", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * Deletes the item from the order list
     */
    private void deleteItem() {
        if (!itemsAdapter.isEmpty()) {
            try {
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("Are you sure you want to delete this item?")
                        .setTitle("Delete Item")
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            itemsAdapter.remove(selected);
                            itemsAdapter.notifyDataSetChanged();
                            updateTotals();
                        })
                        .setNegativeButton("No", (dialogInterface, i) -> {
                        })
                        .show();
            }
            catch (NullPointerException e) {
                Toast toast = Toast.makeText(this, "No Item Selected to Delete", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else {
            Toast toast = Toast.makeText(this, "There are no items to delete", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    /**
     * Updates the monetary values on the cart activity
     */
    private void updateTotals() {
        double st = MainActivity.myOrder.getSubtotal();
        subtotal.setText("$" + df.format(st));
        double tx = MainActivity.myOrder.getSubtotal() * 0.0625;
        tax.setText("$" + df.format(tx));
        double gt = st + tx;
        grandTotal.setText("$" + df.format(gt));
    }
}