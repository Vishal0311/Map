package com.vishal.newmap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    Button bt_loc,start,stop;
    TextView textView1, textView2, textView3, textView4, textView5;
    FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private Location mlocation = null;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MainActivity.this);
        bt_loc = findViewById(R.id.loc_button);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new ExplicitIntent(MainActivity.this, R.string.tracker_action_start));

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new ExplicitIntent(MainActivity.this, R.string.tracker_action_stop));
            }
        });













        bt_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat
                        .checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED && ActivityCompat
                        .checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    // We should set up map here
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
   /* locationCallback = new LocationCallback() {
      @Override
      public void onLocationResult(LocationResult locationResult) {
        if (locationResult == null) {
          return;
        }
        for (Location location : locationResult.getLocations()) {
          // Update UI with location data
          // ...
          textView1.setText(String.valueOf(location.getLatitude()));
          textView2.setText(String.valueOf(location.getLongitude()));
        }
      }
    };
*/
    }

    private void getlocation(LocationResultCallback callback) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationRequest locationRequest = LocationRequest.create()
                .setInterval(5000)
                .setSmallestDisplacement(40F) // In meters
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        fusedLocationProviderClient
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                if (locationResult == null) {
                                    return;
                                }
                                callback.opUpdate(locationResult.getLastLocation());
                                for (Location location : locationResult.getLocations()) {
                                    // Update UI with location data
                                    textView1.setText(String.valueOf(location.getLatitude()));
                                    textView2.setText(String.valueOf(location.getLongitude()));
                                }
                            }
                        },
                        Looper.getMainLooper());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.nightmode));
            if (!success) {
                Log.e("msg: ", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("msg: ", "Can't find style. Error: ", e);
        }

        mMap = googleMap;
        if (mlocation != null) {
            // Not implemented
        }

        PolylineOptions line = new PolylineOptions()
                .width(15)
                .color(Color.WHITE);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(Double.NaN, Double.NaN));
        Marker marker = mMap.addMarker(markerOptions);
        getlocation((location -> {
            // Add the marker options to the map first. This will return a Marker instance
            // which we could use to update the update it's positions later
            Log.d("setOnClickListener", "onClick: location from callback " + location);
            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
            //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            CameraUpdate center = CameraUpdateFactory.newLatLng(sydney);
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(17);
            line.add(sydney);
            marker.setPosition(sydney);
            mMap.addPolyline(line);
            mMap.moveCamera(center);
            mMap.animateCamera(zoom);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16f));
        }));
    }

    interface LocationResultCallback {
        public void opUpdate(Location location);
    }
}