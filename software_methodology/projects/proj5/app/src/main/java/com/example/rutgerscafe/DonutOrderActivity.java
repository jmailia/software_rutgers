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
 * Functionalities associated with the donut order
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
    * Initialization and functions of coffee activity
    * @param savedInstanceState (not used)
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
        addToOrder.setOnClickListener(view -> {addDonuts();});

        deleteDonut = findViewById(R.id.btn_deleteDonut);
        deleteDonut.setOnClickListener(view -> {deleteSelectedDonut();});

        submitOrder = findViewById(R.id.btn_submitDonutOrder);
        submitOrder.setOnClickListener(view -> {submitDonutOrder();});

    }

    /**
     * Puts donuts in cart
     */
    private void submitDonutOrder() {
        if (MainActivity.donutOrder.isEmpty()) {
            Toast toast = Toast.makeText(this, "There is nothing to put in the cart. Add donuts and try again.", Toast.LENGTH_LONG);
            toast.show();
        } else {
            for (Donut myDonut : MainActivity.donutOrder) {MainActivity.myOrder.addMenuItem(myDonut);}
            donutsAdapter.clear();
            donutsAdapter.notifyDataSetChanged();
            updateSubtotal();
            Toast toast = Toast.makeText(this, "The donuts were added successfully.", Toast.LENGTH_LONG);
            toast.show();
            super.onBackPressed();
        }
    }

    /**
     * Get the int from the flavor selected
     * @return the int of the flavor
     */
    private int findFlavorInt(){
        int value = 0;
        switch(String.valueOf(type.getText())){
            case "Donut Hole":
                Donut.holeFlavors holeFlavor = Donut.holeFlavors.valueOf(flavor.getText().toString());
                if(holeFlavor == Donut.holeFlavors.GLAZED){value = 3;}
                if(holeFlavor == Donut.holeFlavors.POWDER){value = 2;}
                if(holeFlavor == Donut.holeFlavors.CHOCOLATE){value = 1;}


                break;
            case "Yeast Donut":
                Donut.yeastFlavors yeastFlavor = Donut.yeastFlavors.valueOf(flavor.getText().toString());
                if(yeastFlavor == Donut.yeastFlavors.KEYLIME){value = 6;}
                if(yeastFlavor == Donut.yeastFlavors.STRAWBERRY){value = 5;}
                if(yeastFlavor == Donut.yeastFlavors.COCONUT){value = 4;}
                if(yeastFlavor == Donut.yeastFlavors.BOSTONCREAM){value = 3;}
                if(yeastFlavor == Donut.yeastFlavors.VANILLA){value = 2;}
                if(yeastFlavor == Donut.yeastFlavors.JELLY){value = 1;}

                break;
            case "Cake Donut":
                Donut.cakeFlavors cakeFlavor = Donut.cakeFlavors.valueOf(flavor.getText().toString());
                if(cakeFlavor == Donut.cakeFlavors.BLUEBERRY){value = 3;}
                if(cakeFlavor == Donut.cakeFlavors.CINNAMON){value = 2;}
                if(cakeFlavor == Donut.cakeFlavors.LEMON){value = 1;}
                break;
        }
        return value;
    }
    /**
     * Delete the selected donut
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
                    .setNegativeButton("No", (dialogInterface, i) -> {})
                    .show();
        }
        catch (NullPointerException e) {
            Toast toast = Toast.makeText(this, "No Donut Selected to Delete", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * Places donuts into the cart
     */
    private void addDonuts() {
        try {
            int flavor = findFlavorInt();
            switch (String.valueOf(type.getText())) {
                case "Donut Hole":
                    donutsAdapter.add(new Donut(Integer.parseInt(quantity.getText().toString()), Donut.HOLE, flavor));
                    donutsAdapter.notifyDataSetChanged();
                    break;
                case "Cake Donut":
                    donutsAdapter.add(new Donut(Integer.parseInt(quantity.getText().toString()), Donut.CAKE, flavor));
                    donutsAdapter.notifyDataSetChanged();
                    break;
                case "Yeast Donut":
                    donutsAdapter.add(new Donut(Integer.parseInt(quantity.getText().toString()), Donut.YEAST, flavor));
                    donutsAdapter.notifyDataSetChanged();
                    break;

            }
            updateSubtotal();
            Toast toast = Toast.makeText(this, "Donut order was added successfully.", Toast.LENGTH_LONG);
            toast.show();
        } catch (NumberFormatException e) {
            Toast toast = Toast.makeText(this, "You did not specify the number of donuts. Try again.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * Updates donut subtotal
     */
    private void updateSubtotal() {
        double st = 0.00;
        for (Donut donut : MainActivity.donutOrder) {st += donut.itemPrice();}
        subtotal.setText("$" + df.format(st));
    }
}