package com.example.bustrasit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class RouteActivity extends AppCompatActivity {

    Spinner routeSpinner;
    Button btnViewSeats, btnViewMap;
    String[] routes = {"Avadi - VIT College", "Manali - VIT College", "Chennai Central - VIT College"};
    String selectedRoute = routes[0]; // Default selection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        routeSpinner = findViewById(R.id.spinnerRoutes);
        btnViewSeats = findViewById(R.id.btnViewSeats);
        btnViewMap = findViewById(R.id.btnViewMap);

        // Populate the spinner with routes
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, routes);
        routeSpinner.setAdapter(adapter);

        // Capture selected route
        routeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRoute = routes[position]; // Update selected route
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Handle button clicks
        btnViewSeats.setOnClickListener(v -> {
            Intent intent = new Intent(RouteActivity.this, AvailabilityActivity.class);
            startActivity(intent);
        });

        btnViewMap.setOnClickListener(v -> {
            Intent intent = new Intent(RouteActivity.this, MapActivity.class);
            intent.putExtra("SELECTED_ROUTE", selectedRoute); // Pass selected route
            startActivity(intent);
        });
    }
}
