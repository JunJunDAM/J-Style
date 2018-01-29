package com.example.alu2015059.jstyle;

import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by alu2015059 on 23/01/2018.
 */

public class MarcadorMapa extends AppCompatActivity implements OnMapReadyCallback{
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng ubicacion = new LatLng(41.385524, 2.162439);
        googleMap.addMarker(new MarkerOptions().position(ubicacion)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
    }
}
