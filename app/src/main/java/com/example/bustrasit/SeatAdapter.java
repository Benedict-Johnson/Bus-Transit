package com.example.bustrasit;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class SeatAdapter extends BaseAdapter {
    private final Context context;
    private final String[] seats;

    public SeatAdapter(Context context, String[] seats) {
        this.context = context;
        this.seats = seats;
    }

    @Override
    public int getCount() {
        return seats.length + 6; // Extra spaces for the aisle
    }

    @Override
    public Object getItem(int position) {
        return (position % 4 == 2) ? null : seats[position - (position / 4)];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position % 4 == 2) {
            // Space for aisle
            View emptySpace = new View(context);
            emptySpace.setLayoutParams(new ViewGroup.LayoutParams(30, 100));
            return emptySpace;
        }

        TextView seatView;
        if (convertView == null || convertView instanceof View) {
            seatView = new TextView(context);
            seatView.setLayoutParams(new ViewGroup.LayoutParams(120, 100));
            seatView.setGravity(Gravity.CENTER);
            seatView.setTextSize(16);
            seatView.setPadding(15, 15, 15, 15);
        } else {
            seatView = (TextView) convertView;
        }

        int seatIndex = position - (position / 4);
        if (seatIndex < seats.length) {
            seatView.setText(String.valueOf(seatIndex + 1));
            seatView.setBackgroundColor(getSeatColor(seats[seatIndex]));
            seatView.setTextColor(Color.WHITE);
        }

        return seatView;
    }

    private int getSeatColor(String status) {
        switch (status) {
            case "O": return ContextCompat.getColor(context, R.color.occupied);
            case "F": return ContextCompat.getColor(context, R.color.female_reserved);
            default: return ContextCompat.getColor(context, R.color.available);
        }
    }
}
