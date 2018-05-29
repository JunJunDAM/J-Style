package com.example.alu2015059.jstyle.Service.Mapa;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.alu2015059.jstyle.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ubicacion = new LatLng(41.385524, 2.162439);

        googleMap.addMarker(new MarkerOptions().position(ubicacion).title("J-S Barcelona"));

        // El valor del zoomLevel ajusta el zoom del mapa (hasta un máximo de 21).
        float zoomLevel = 10;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, zoomLevel));

    }
}
