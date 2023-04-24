package com.example.rutgerscafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ImageButton orderCoffeeButton;
    private ImageButton orderDonutButton;
    private ImageButton currentOrderButton;
    private ImageButton storeOrdersButton;
    public static Order myOrder = new Order();
    public static ArrayList<Order> storeOrders = new ArrayList<Order>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        orderCoffeeButton = (ImageButton) findViewById(R.id.coffeeImageButton);
        orderDonutButton = (ImageButton) findViewById(R.id.donutImageButton);
        currentOrderButton = (ImageButton) findViewById(R.id.basketViewImageButton);
        storeOrdersButton = (ImageButton) findViewById(R.id.storeOrdersImageButton);

        orderCoffeeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoOrderCoffee = new Intent(MainActivity.this, CoffeeOrderActivity.class);
                startActivity(gotoOrderCoffee);
            }
        });

        orderDonutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoOrderDonut = new Intent(MainActivity.this, DonutActivity.class);
                startActivity(gotoOrderDonut);
            }
        });

        currentOrderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoBasketView = new Intent(MainActivity.this, BasketViewActivity.class);
                startActivity(gotoBasketView);
            }
        });

        storeOrdersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoStoreOrders = new Intent(MainActivity.this, StoreOrdersActivity.class);
                startActivity(gotoStoreOrders);
            }
        });
    }

}