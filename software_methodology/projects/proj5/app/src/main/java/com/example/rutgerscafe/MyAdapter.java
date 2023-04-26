package com.example.rutgerscafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * Recyclerview adapter for connecting data with activity
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> flavors;
    private Context context;
    private OnRVListener mOnRVListener;

    public MyAdapter(List<String> flavors, Context context, OnRVListener onRVListener) {
        this.flavors = flavors;
        this.context = context;
        this.mOnRVListener = onRVListener;
    }

    /**
     * Get the view holder for the Recyclerview
     * @param parent The parent of the view holder
     * @param viewType Not used
     * @return A new view holder
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.donut_flavor_layout, parent, false), mOnRVListener);
    }

    /**
     * Bind the data with the view holder elements, display the data as a string
     * @param viewHolder the view holder to use
     * @param flavorPosition the flavor is placed here
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int flavorPosition) {
        viewHolder.donut_flavorName.setText(flavors.get(flavorPosition));
    }

    /**
     * Get the number of flavors in the Recyclerview
     * @return The number of flavors in the Recyclerview
     */
    @Override
    public int getItemCount() {
        return flavors.size();
    }

    /**
     * Create a view holder for Recyclerview
     */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView donut_flavorName;
        ConstraintLayout parentLayout;
        OnRVListener onRVListener;

        /**
         * construct a view holder for the Recyclerview
         * @param itemView The view holder to be used
         * @param onRVListener The listener to be binded
         */
        public MyViewHolder(@NonNull View itemView, OnRVListener onRVListener) {

            super(itemView);

            donut_flavorName = itemView.findViewById(R.id.donut_flavorName);
            parentLayout = itemView.findViewById(R.id.donutFlavorLayout);
            this.onRVListener = onRVListener;
            itemView.setOnClickListener(this);
        }

        /**
         * get position of the clicked item
         * @param currentView (not used))
         */
        @Override
        public void onClick(View currentView) {onRVListener.onRVClick(getAdapterPosition());
        }
    }
}