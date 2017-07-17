package com.gojek.dev.gowithmedriver;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import android.widget.Toast;

public class BookingActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Context context;
    public static final String EXTRA_MESSAGE = "";
    LatLng latLngA = null, latLngB = null;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marke"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        context = this.getApplicationContext();

        //buttonWithME
        Button WithMe = (Button) findViewById(R.id.buttonWithMe);
        WithMe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //something
            }
        });

        //button jarak
        Button btnJarak = (Button) findViewById(R.id.buttonJarak);
        btnJarak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //something

            }
        });

        //TODO : clear the previus marker(if nesesssary)

        //pick place
        PlaceAutocompleteFragment placePick =
                (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.fragment_pick);
        placePick.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place)
            {
                System.out.println(place.getLatLng());
                latLngA = place.getLatLng();
                mMap.addMarker(new MarkerOptions().position(latLngA).title("Pick Place"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngA, 15));
            }

            @Override
            public void onError(Status status)
            {
                System.out.println(status);
            }
        });

        //Destination Place
        PlaceAutocompleteFragment destinationPick =
                (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.fragment_destination);
        destinationPick.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place)
            {
                System.out.println(place.getLatLng());
                latLngB = place.getLatLng();
                mMap.addMarker(new MarkerOptions().position(latLngB).title("Pick Place"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngB, 15));
            }

            @Override
            public void onError(Status status)
            {
                System.out.println(status);
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

}
