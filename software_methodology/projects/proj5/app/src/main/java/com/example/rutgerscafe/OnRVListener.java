package com.example.rutgerscafe;

/**
 * This interface defines methods used to detect when an item is clicked on the Recyclerview
 */
public interface OnRVListener {
    /**
     * Detects the Recyclerview is clicked
     * @param position The position of the item clicked
     */
    void onRVClick(int position);
}
