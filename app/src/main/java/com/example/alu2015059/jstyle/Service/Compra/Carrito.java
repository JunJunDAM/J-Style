package com.example.alu2015059.jstyle.Service.Compra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.R;
import com.example.alu2015059.jstyle.Repository.SQLiteDBHelper;

import java.util.List;

/**
 * Created by alu2015059 on 28/05/2018.
 */

public class Carrito extends AppCompatActivity {

    private Button btn_cancelar;
    private Button btn_finalizarCarro;
    private SwipeRefreshLayout srl_Articulos;
    private RecyclerView rv_Articulos;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrito_activity);

        btn_cancelar = findViewById(R.id.ca_btn_cancelar);
        btn_finalizarCarro = findViewById(R.id.ca_btn_finalizarCarrito);
        srl_Articulos = findViewById(R.id.ca_srl_articulos);
        rv_Articulos = findViewById(R.id.ca_rv_articulos);

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Carrito.this.finish();
            }
        });

        btn_finalizarCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Carrito.this, CompraActivity.class);
                startActivity(intent);
            }
        });

        srl_Articulos.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateArticulos();
            }
        });

        updateArticulos();
    }

    private void updateArticulos() {
        SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(Carrito.this);

        update(SQLiteDBHelper.getCarrito());
    }

    private void update(List<Articulo> listaArticulos) {
        srl_Articulos.setRefreshing(false);

        CarritoAdapter adapter = new CarritoAdapter(listaArticulos, this);
        rv_Articulos.setLayoutManager(new LinearLayoutManager(Carrito.this));
        rv_Articulos.setItemAnimator(new DefaultItemAnimator());
        rv_Articulos.setAdapter(adapter);
    }
}
