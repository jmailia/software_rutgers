package com.example.rutgerscafe;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;


/**
 * Define functionalities associated with the main activity
 */
public class MainActivity extends AppCompatActivity {

    //initialize data structures to hold orders and UI elements
    private ImageButton buttonForCoffee;
    private ImageButton buttonForDonuts;
    private ImageButton buttonForOrder;
    private ImageButton buttonForStore;
    public static Order myOrder = new Order();
    public static ArrayList<Order> storeOrders = new ArrayList<Order>();
    public static List<Donut> donutOrder = new ArrayList<>();

    /**
     * Determine when a button is clicked and start its respective activity
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        buttonForCoffee = (ImageButton) findViewById(R.id.coffeeImageButton);
        buttonForDonuts = (ImageButton) findViewById(R.id.donutImageButton);
        buttonForOrder = (ImageButton) findViewById(R.id.basketViewImageButton);
        buttonForStore = (ImageButton) findViewById(R.id.storeOrdersImageButton);

        buttonForOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent basketViewNavigation = new Intent(
                        MainActivity.this, BasketViewActivity.class);
                startActivity(basketViewNavigation);
            }
        });

        buttonForCoffee.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent coffeeOrderNavigation = new Intent(
                        MainActivity.this, CoffeeOrderActivity.class);
                startActivity(coffeeOrderNavigation);
            }
        });

        buttonForStore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent storeOrderNavigation = new Intent(
                        MainActivity.this, StoreOrdersActivity.class);
                startActivity(storeOrderNavigation);
            }
        });

        buttonForDonuts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent donutOrderNavigation = new Intent(
                        MainActivity.this, DonutActivity.class);
                startActivity(donutOrderNavigation);
            }
        });
    }
}