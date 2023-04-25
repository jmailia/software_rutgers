package com.example.rutgerscafe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This class controls the functionality of the store orders activity
 * @author Rory Xu, Hassan Alfareed
 */
public class StoreOrdersActivity extends AppCompatActivity {

    ListView storeOrdersList;
    Button viewOrder;
    Button deleteOrder;
    ArrayAdapter<Order> ordersAdapter;
    int selected;

    /**
     * Initializes elements of the store orders activity and defines their functionalities
     * @param savedInstanceState Not used
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storeorder_activity);
        storeOrdersList = findViewById(R.id.lv_storeOrders);
        ordersAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, MainActivity.storeOrders.getOrders());
        storeOrdersList.setAdapter(ordersAdapter);
        storeOrdersList.setOnItemClickListener((adapterView, view, i, l) -> selected = i);
        viewOrder = findViewById(R.id.btn_viewOrder);
        viewOrder.setOnClickListener(view -> {
            viewSelectedOrder();
        });
        deleteOrder = findViewById(R.id.btn_deleteOrder);
        deleteOrder.setOnClickListener(view -> {
            deleteSelectedOrder();
        });
    }

    /**
     * Deletes the selected order from the store orders list
     */
    private void deleteSelectedOrder() {
        if (!ordersAdapter.isEmpty()) {
            try {
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("Are you sure you want to delete " + MainActivity.storeOrders.getOrders().get(selected) + "?")
                        .setTitle("Delete Order")
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            ordersAdapter.remove(MainActivity.storeOrders.getOrders().get(selected));
                            ordersAdapter.notifyDataSetChanged();
                        })
                        .setNegativeButton("No", (dialogInterface, i) -> {
                        })
                        .show();
            }
            catch (NullPointerException e) {
                Toast toast = Toast.makeText(this, "No Order Selected to Delete", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else {
            Toast toast = Toast.makeText(this, "No Order Selected to Delete", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * Opens a new activity that displays the selected order details
     */
    private void viewSelectedOrder() {
        if (!ordersAdapter.isEmpty()) {
            try {
                Intent intent = new Intent(this, ViewOrderActivity.class);
                intent.putExtra("Selected Order", selected);
                startActivity(intent);
            } catch (Exception e) {
                Toast toast = Toast.makeText(this, "No Order Selected to View", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else {
            Toast toast = Toast.makeText(this, "No Order Selected to View", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}