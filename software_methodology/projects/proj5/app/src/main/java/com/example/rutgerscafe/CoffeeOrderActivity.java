package com.example.rutgerscafe;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;


public class CoffeeOrderActivity extends AppCompatActivity {

    CheckBox frenchVanillaCheckBox;
    CheckBox irishCreamCheckBox;
    CheckBox caramelCheckBox;
    CheckBox mochaCheckBox;
    Spinner sizeSpinner;
    EditText quantityText;
    TextView subtotalTextView;
    Button addToOrderButton;
    ArrayAdapter<CharSequence> coffeeSizeAdapter;
    Coffee myCoffee = new Coffee();
    private final DecimalFormat df = new DecimalFormat("#0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffeeorder_activity);
        subtotalTextView = findViewById(R.id.tv_coffeeSubtotal);
        sizeSpinner = findViewById(R.id.sizeSpinner);
        coffeeSizeAdapter = ArrayAdapter.createFromResource(this, R.array.coffeeSizes,
                android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(coffeeSizeAdapter);
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {changeSize();}
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        quantityText = findViewById(R.id.etn_coffeeQuantity);
        quantityText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                changeQuantity();
            }
        });
        frenchVanillaCheckBox = findViewById(R.id.frenchVanillaCheckBox);
        frenchVanillaCheckBox.setOnClickListener(view -> {addAddIn(frenchVanillaCheckBox);});

        irishCreamCheckBox = findViewById(R.id.irishCreamCheckBox);
        irishCreamCheckBox.setOnClickListener(view -> {addAddIn(irishCreamCheckBox);});

        caramelCheckBox = findViewById(R.id.caramelCheckBox);
        caramelCheckBox.setOnClickListener(view -> {addAddIn(caramelCheckBox);});

        mochaCheckBox = findViewById(R.id.mochaCheckBox);
        mochaCheckBox.setOnClickListener(view -> {addAddIn(mochaCheckBox);});

        addToOrderButton = findViewById(R.id.addCoffeeButton);
        addToOrderButton.setOnClickListener(view -> {addCoffee();});
    }

    /**
     * Update the subtotal
     */
    private void updateSubtotal() {
        try {
            Integer.parseInt(String.valueOf(quantityText.getText()));
            double itemPrice = myCoffee.itemPrice();
            subtotalTextView.setText("$" + df.format(itemPrice));
        } catch (NumberFormatException e) {subtotalTextView.setText("$0.00");}

    }

    /**
     * Reset by setting all values to false.
     */
    private void reset() {

        sizeSpinner.setSelection(0);
        quantityText.setText("");
        myCoffee = new Coffee();
        subtotalTextView.setText("$0.00");


        //set checkboxes to false
        mochaCheckBox.setChecked(false);
        frenchVanillaCheckBox.setChecked(false);
        caramelCheckBox.setChecked(false);
        irishCreamCheckBox.setChecked(false);
    }

    /**
     * Change the size and update the subtotal
     */
    private void changeSize() {
        switch (String.valueOf(sizeSpinner.getSelectedItem())) {
            case "Short":
                myCoffee.setSize("Short");
                break;
            case "Tall":
                myCoffee.setSize("Tall");
                break;
            case "Grande":
                myCoffee.setSize("Grande");
                break;
            case "Venti":
                myCoffee.setSize("Venti");
                break;
        }
        updateSubtotal();
    }

    /**
     * Change the Quantity
     */
    private void changeQuantity() {
        try {
            myCoffee.setQuantity(Integer.parseInt(String.valueOf(quantityText.getText())));
            updateSubtotal();
        }
        catch (NumberFormatException e) {subtotalTextView.setText("$0.00");}
    }

    /**
     * add the addIn, otherwise remove it
     * @param myCheckBox the value of the addin checkbox
     */
    private void addAddIn(CheckBox myCheckBox) {
        if (myCheckBox.isChecked()) {myCoffee.setAddin(String.valueOf(myCheckBox.getText()));}
        else {myCoffee.removeAddin(String.valueOf(myCheckBox.getText()));}
        updateSubtotal();
    }

    /**
     * Add a coffee, consider an error where the user does not specify the number of coffees
     */
    private void addCoffee() {
        try {
            Integer.parseInt(String.valueOf(quantityText.getText()));
            MainActivity.myOrder.addMenuItem(myCoffee);
            myCoffee = new Coffee();
            reset();
            Toast toast = Toast.makeText(this, "Coffee added successfully.", Toast.LENGTH_LONG);
            toast.show();
        }
        catch (Exception e) {
            Toast toast = Toast.makeText(this, "You didn't specify a number. Try again.", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}