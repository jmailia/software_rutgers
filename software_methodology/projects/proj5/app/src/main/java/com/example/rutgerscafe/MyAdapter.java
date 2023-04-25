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
 * This class creates an adapter for a Recyclerview in order to connect data with the activity
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
     * This method returns the view holder for the Recyclerview
     * @param parent The parent of the view holder
     * @param viewType Not used
     * @return A new view holder
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donut_flavor_layout, parent, false);
        return new MyViewHolder(view, mOnRVListener);
    }

    /**
     * This methods binds the view holder elements to the data and begins displaying the data in string form
     * @param holder The designated view holder
     * @param position The position where the flavor is to be placed
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.donut_flavorName.setText(flavors.get(position));
    }

    /**
     * Gets the number of flavors in the Recyclerview
     * @return The number of flavors in the Recyclerview
     */
    @Override
    public int getItemCount() {
        return flavors.size();
    }

    /**
     * This inner class creates a view holder for the Recyclerview
     * @author Rory Xu, Hassan Alfareed
     */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView donut_flavorName;
        ConstraintLayout parentLayout;
        OnRVListener onRVListener;

        /**
         * Constructs a view holder for the Recyclerview
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
         * Gets the position of the clicked item
         * @param view The current view
         */
        @Override
        public void onClick(View view) {
            onRVListener.onRVClick(getAdapterPosition());
        }
    }
}