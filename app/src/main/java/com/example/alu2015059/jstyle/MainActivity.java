package com.example.alu2015059.jstyle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.alu2015059.jstyle.Repository.ArticulosDB;
import com.example.alu2015059.jstyle.Repository.CompraDB;
import com.example.alu2015059.jstyle.Repository.LoginDB;
import com.example.alu2015059.jstyle.Repository.SQLiteDBHelper;
import com.example.alu2015059.jstyle.Service.Login_VersionBeta.Login;
import com.example.alu2015059.jstyle.Service.Mapa.MapsActivity;
import com.example.alu2015059.jstyle.Service.MostrarArticulos.PaginaArticulos;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private Button button;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(MainActivity.this);

        sqLiteDBHelper.createTableIfNotExists();

        Button pagPrincipal_btn = findViewById(R.id.iniciarapp_btn);
        pagPrincipal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PaginaArticulos.class);
                startActivity(intent);
            }
        });

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setVisibility(View.INVISIBLE);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        
        ImageButton imageViewMap = findViewById(R.id.imageViewMap);
        imageViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Image Maps clicked."); // Checkear el logcat
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }

        });

    }
}
