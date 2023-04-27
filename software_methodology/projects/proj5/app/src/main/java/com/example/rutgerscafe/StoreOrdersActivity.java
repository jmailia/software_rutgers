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
 * Functionalities of the store orders activity
 */
public class StoreOrdersActivity extends AppCompatActivity {

    ListView storeOrdersList;
    Button viewOrder;
    Button deleteOrder;
    ArrayAdapter<Order> ordersAdapter;
    int selected;


    /**
     * Initialization and functions of store orders activity
     * @param savedInstanceState (not used)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storeorder_activity);
        storeOrdersList = findViewById(R.id.lv_storeOrders);
        //ordersAdapter = new ArrayAdapter<>(this,
              //  android.R.layout.simple_list_item_1, MainActivity.storeOrders.getOrders());
        storeOrdersList.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, MainActivity.storeOrders));
        storeOrdersList.setOnItemClickListener((adapterView, view, i, l) -> selected = i);
        viewOrder = findViewById(R.id.btn_viewOrder);
        viewOrder.setOnClickListener(view -> {viewSelectedOrder();});
        deleteOrder = findViewById(R.id.btn_deleteOrder);
        deleteOrder.setOnClickListener(view -> {deleteSelectedOrder();});
    }

    /**
     * Delete an order from the store orders list, there must be one selected
     */
    private void deleteSelectedOrder() {
        //System.out.println(ordersAdapter.toString());

        if (!ordersAdapter.isEmpty()) {
            try {
                new AlertDialog.Builder(this)
                       .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("Are you sure you want to delete "
                                //+ MainActivity.storeOrders.getOrders().get(selected) + "?"
                        )
                        .setTitle("Delete Order")
                        //.setPositiveButton("Yes", (dialogInterface, i) -> {
                        //    ordersAdapter.remove(MainActivity.storeOrders.getOrders().get(selected));
                        //    ordersAdapter.notifyDataSetChanged();
                       // }
                        //)
                        .setNegativeButton("No", (dialogInterface, i) -> {
                        })
                        .show();
            }
            catch (NullPointerException e) {Toast.makeText(this,
                    "You need to select an order to delete it.", Toast.LENGTH_LONG).show();}
        }
        else {Toast.makeText(this, "You need to select an order to delete it.", Toast.LENGTH_LONG).show();}
    }

    /**
     * Display the order details, there must be one selected.
     */
    private void viewSelectedOrder() {
        if (!ordersAdapter.isEmpty()) {
            try {
                //Intent intent = new Intent(this, ViewOrderActivity.class);
                //intent.putExtra("Selected Order", selected);
               // startActivity(intent);
            } catch (Exception e) { Toast.makeText(this,
                    "No order was selected to view. Try again.", Toast.LENGTH_LONG).show();}
        }
        else {Toast.makeText(this,
                "No order was selected to view. Try again.", Toast.LENGTH_LONG).show();}
    }
}