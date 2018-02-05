package com.example.alu2015059.jstyle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mapView.findViewById(R.id.mapView);
        //mapView.getMapAsync((OnMapReadyCallback) this);
        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        //mapFragment.getMapAsync((OnMapReadyCallback) this);

        Button pagPrincipal_btn = findViewById(R.id.iniciarapp_btn);
        pagPrincipal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),PaginaArticulos.class);
                startActivityForResult(intent,0);
            }
        });
    }

}
