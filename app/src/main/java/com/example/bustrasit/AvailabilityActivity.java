package com.example.bustrasit;

import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

public class AvailabilityActivity extends AppCompatActivity {

    GridView seatGrid;

    // Seat Status Example
    // "A" = Available, "O" = Occupied, "F" = Female reserved
    String[] seatStatus = {
            "A", "O", "F", "A", "O",
            "A", "F", "O", "A", "A",
            "A", "O", "A", "F", "O",
            "A", "A", "O", "A", "F"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);

        seatGrid = findViewById(R.id.seatGrid);
        seatGrid.setAdapter(new SeatAdapter(this, seatStatus));
    }
}
