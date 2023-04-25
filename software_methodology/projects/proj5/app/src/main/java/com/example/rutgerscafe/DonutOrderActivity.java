package com.example.rutgerscafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

/**
 * This class controls the functionality of the donut order activity
 * @author Rory Xu, Hassan Alfareed
 */
public class DonutOrderActivity extends AppCompatActivity{

    ListView donutOrder;
    TextView flavor;
    TextView type;
    EditText quantity;
    TextView subtotal;
    Button addToOrder;
    Button deleteDonut;
    Button submitOrder;
    Donut selected;
    Intent intent;
    ArrayAdapter<Donut> donutsAdapter;
    private final DecimalFormat df = new DecimalFormat("#0.00");

    /**
    * Initializes elements of the coffee activity and defines their functionalities
    * @param savedInstanceState Not used
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donutorder_activity);
        intent = getIntent();
        donutOrder = findViewById(R.id.lv_currDonutOrder);
        donutsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, MainActivity.donutOrder);
        donutOrder.setAdapter(donutsAdapter);
        donutOrder.setOnItemClickListener((adapterView, view, i, l) -> selected = (Donut) donutOrder.getItemAtPosition(i));
        flavor = findViewById(R.id.tv_selectedFlavor);
        type = findViewById(R.id.tv_selectedType);
        quantity = findViewById(R.id.etn_donutQuantity);
        subtotal = findViewById(R.id.tv_donutSubtotal);
        updateSubtotal();

        if (intent != null) {
            flavor.setText(intent.getExtras().getString("Flavor"));
            type.setText(intent.getExtras().getString("Type"));
        }
        addToOrder = findViewById(R.id.btn_addDonutOrder);
        addToOrder.setOnClickListener(view -> {
            addDonuts();
        });

        deleteDonut = findViewById(R.id.btn_deleteDonut);
        deleteDonut.setOnClickListener(view -> {
            deleteSelectedDonut();
        });

        submitOrder = findViewById(R.id.btn_submitDonutOrder);
        submitOrder.setOnClickListener(view -> {
            submitDonutOrder();
        });

    }

    /**
     * Submits the order of donuts to cart
     */
    private void submitDonutOrder() {
        if (!MainActivity.donutOrder.isEmpty()) {
            for (Donut myDonut : MainActivity.donutOrder) {
                MainActivity.myOrder.addMenuItem(myDonut);
            }
            donutsAdapter.clear();
            donutsAdapter.notifyDataSetChanged();
            updateSubtotal();
            Toast toast = Toast.makeText(this, "Donut Order Successfully Placed!", Toast.LENGTH_LONG);
            toast.show();
            super.onBackPressed();
        }
        else {
            Toast toast = Toast.makeText(this, "No Donuts in Cart to Order", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private int findFlavorInt(){
        int value = 0;
        switch(String.valueOf(type.getText())){
            case "Yeast Donut":
                Donut.yeastFlavors temp = Donut.yeastFlavors.valueOf(flavor.getText().toString());
                if(temp == Donut.yeastFlavors.JELLY){value = 1;}
                if(temp == Donut.yeastFlavors.VANILLA){value = 2;}
                if(temp == Donut.yeastFlavors.BOSTONCREAM){value = 3;}
                if(temp == Donut.yeastFlavors.COCONUT){value = 4;}
                if(temp == Donut.yeastFlavors.STRAWBERRY){value = 5;}
                if(temp == Donut.yeastFlavors.KEYLIME){value = 6;}
                break;
            case "Cake Donut":
                Donut.cakeFlavors temp2 = Donut.cakeFlavors.valueOf(flavor.getText().toString());
                if(temp2 == Donut.cakeFlavors.LEMON){value = 1;}
                if(temp2 == Donut.cakeFlavors.CINNAMON){value = 2;}
                if(temp2 == Donut.cakeFlavors.BLUEBERRY){value = 3;}
                break;
            case "Donut Hole":
                Donut.holeFlavors temp3 = Donut.holeFlavors.valueOf(flavor.getText().toString());
                if(temp3 == Donut.holeFlavors.CHOCOLATE){value = 1;}
                if(temp3 == Donut.holeFlavors.POWDER){value = 2;}
                if(temp3 == Donut.holeFlavors.GLAZED){value = 3;}
                break;
        }
        return value;
    }
    /**
     * Deletes the selected donut
     */
    private void deleteSelectedDonut() {
        try {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage("Are you sure you want to delete this Donut?")
                    .setTitle("Delete Donut")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        donutsAdapter.remove(selected);
                        donutsAdapter.notifyDataSetChanged();
                        updateSubtotal();
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                    })
                    .show();
        }
        catch (NullPointerException e) {
            Toast toast = Toast.makeText(this, "No Donut Selected to Delete", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * Adds the donuts in the list to the cart
     */
    private void addDonuts() {
        try {
            int flavor = findFlavorInt();
            switch (String.valueOf(type.getText())) {
                case "Yeast Donut":
                    donutsAdapter.add(new Donut(Integer.parseInt(quantity.getText().toString()), Donut.YEAST, flavor));
                    donutsAdapter.notifyDataSetChanged();
                    updateSubtotal();
                    break;
                case "Cake Donut":
                    donutsAdapter.add(new Donut(Integer.parseInt(quantity.getText().toString()), Donut.CAKE, flavor));
                    donutsAdapter.notifyDataSetChanged();
                    updateSubtotal();
                    break;
                case "Donut Hole":
                    donutsAdapter.add(new Donut(Integer.parseInt(quantity.getText().toString()), Donut.HOLE, flavor));
                    donutsAdapter.notifyDataSetChanged();
                    updateSubtotal();
                    break;
            }
            Toast toast = Toast.makeText(this, "Donut Successfully Added!", Toast.LENGTH_LONG);
            toast.show();
        } catch (NumberFormatException e) {
            Toast toast = Toast.makeText(this, "Please specify the number of donuts!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * Updates the monetary values on the donut order activity
     */
    private void updateSubtotal() {
        double st = 0.00;
        for (Donut donut : MainActivity.donutOrder) {
            st += donut.itemPrice();
        }
        subtotal.setText("$" + df.format(st));
    }
}