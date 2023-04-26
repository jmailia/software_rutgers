package com.example.rutgerscafe;

/**
 * An interface for recyclerview click detection
 */
public interface OnRVListener {
    /**
     * Recyclerview click detection
     * @param position Where item was clicked
     */
    void onRVClick(int position);
}
