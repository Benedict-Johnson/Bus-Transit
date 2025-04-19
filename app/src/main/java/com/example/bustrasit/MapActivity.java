package com.example.bustrasit;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Random;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final LatLng VIT_LOCATION = new LatLng(12.8406, 80.1534); // VIT Chennai Campus

    // Start locations for each route
    private final LatLng AVADI = new LatLng(13.1144, 80.1489);
    private final LatLng MANALI = new LatLng(13.1660, 80.2641);
    private final LatLng CHENNAI_CENTRAL = new LatLng(13.0827, 80.2707);

    private LatLng selectedStartLocation; // Stores selected start point

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Get selected route from intent
        Intent intent = getIntent();
        String selectedRoute = intent.getStringExtra("SELECTED_ROUTE");

        // Set selected start location based on chosen route
        if (selectedRoute != null) {
            switch (selectedRoute) {
                case "Avadi - VIT College":
                    selectedStartLocation = AVADI;
                    break;
                case "Manali - VIT College":
                    selectedStartLocation = MANALI;
                    break;
                case "Chennai Central - VIT College":
                    selectedStartLocation = CHENNAI_CENTRAL;
                    break;
            }
        }

        // Initialize the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (selectedStartLocation != null) {
            // Add marker for starting point
            mMap.addMarker(new MarkerOptions().position(selectedStartLocation).title("Start Point")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

            // Add marker for VIT destination
            mMap.addMarker(new MarkerOptions().position(VIT_LOCATION).title("VIT Chennai")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            // Draw the blue polyline for the selected route
            PolylineOptions polylineOptions = new PolylineOptions()
                    .add(selectedStartLocation) // Add starting point
                    .add(VIT_LOCATION) // Add destination
                    .width(8) // Width of the line
                    .color(0xFF0000FF); // Blue color
            mMap.addPolyline(polylineOptions);

            // Add random buses along the route
            addRandomBuses(selectedStartLocation, VIT_LOCATION);
        }

        // Move the camera to show the selected route
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedStartLocation, 10));
    }

    private void addRandomBuses(LatLng start, LatLng end) {
        Random rand = new Random();
        int busesToShow = rand.nextInt(2) + 1; // Show 1 or 2 buses randomly

        for (int i = 0; i < busesToShow; i++) {
            LatLng busLocation = getRandomBusPosition(start, end);
            mMap.addMarker(new MarkerOptions().position(busLocation).title("Bus " + (i + 1))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))); // Bus icon (red)
        }
    }

    private LatLng getRandomBusPosition(LatLng start, LatLng end) {
        Random rand = new Random();
        double lat = start.latitude + (end.latitude - start.latitude) * rand.nextDouble();
        double lng = start.longitude + (end.longitude - start.longitude) * rand.nextDouble();
        return new LatLng(lat, lng);
    }
}
