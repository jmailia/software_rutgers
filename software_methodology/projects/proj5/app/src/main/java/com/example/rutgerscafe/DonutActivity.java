package com.example.rutgerscafe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

/**
 * Functionality related to Donut Activity
 */
public class DonutActivity extends AppCompatActivity implements OnRVListener{
    Spinner donutTypes;
    ArrayAdapter<CharSequence> donutTypeAdapter;
    private RecyclerView flavorRecyclerView;
    private RecyclerView.Adapter flavorRVAdapter;
    private RecyclerView.LayoutManager flavorRVLayoutManager;

    //12 flavors
    private final List<String> flavors = new ArrayList<>(Arrays.asList("Jelly",
            "Vanilla", "Boston Cream", "Coconut", "Strawberry", "Keylime", "Lemon", "Cinnamon", "Blueberry",
            "Chocolate Hole", "Powder Hole", "Glazed Hole"));


    /**
     * Create the default donut activity
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.donut_activity);
        donutTypes = (Spinner) findViewById(R.id.sp_donutType);
        donutTypeAdapter = ArrayAdapter.createFromResource(this, R.array.donutTypes,
                android.R.layout.simple_spinner_dropdown_item);

        donutTypes.setAdapter(donutTypeAdapter);
        flavorRecyclerView = (RecyclerView) findViewById(R.id.rv_donutFlavorList);

        flavorRecyclerView.setHasFixedSize(true); //it will have a fixed size

        flavorRecyclerView.setLayoutManager((new LinearLayoutManager(this)));
        flavorRecyclerView.setAdapter(new MyAdapter(flavors, this, this));
    }


    @Override
    public void onRVClick(int position) {
        Intent intent = new Intent(this, DonutOrderActivity.class);
        intent.putExtra("Flavor", flavors.get(position));
        intent.putExtra("Type", String.valueOf(donutTypes.getSelectedItem()));
        startActivity(intent);
    }
}