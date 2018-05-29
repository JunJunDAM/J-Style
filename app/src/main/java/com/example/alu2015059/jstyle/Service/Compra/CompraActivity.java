package com.example.alu2015059.jstyle.Service.Compra;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.R;
import com.example.alu2015059.jstyle.Repository.SQLiteDBHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alu2015059 on 28/05/2018.
 */

public class CompraActivity extends AppCompatActivity {

    Button btn_cancelar;
    Button btn_comprar;
    TextView tv_articulos;
    TextView tv_precio;
    TextView tv_iva;
    TextView tv_total;

    List<Articulo> listaArticulos = new ArrayList<>();

    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compra_activity);

        tv_articulos = findViewById(R.id.ca_tv_articulos);
        tv_precio = findViewById(R.id.ca_tv_precio);
        tv_iva = findViewById(R.id.ca_tv_iva);
        tv_total = findViewById(R.id.ca_tv_total);
        btn_cancelar = findViewById(R.id.ac_btn_cancelar);
        btn_comprar = findViewById(R.id.ac_btn_finCompra);

        rellenar();

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompraActivity.this.finish();
            }
        });

        btn_comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(CompraActivity.this);
                SQLiteDatabase sqLiteDatabase = SQLiteDBHelper.getWritableDatabase();

                SQLiteDBHelper.compraFinal(SQLiteDBHelper.getCarrito());

                sqLiteDatabase.close();

                tv_articulos.setText("");
                tv_precio.setText("");
                tv_total.setText("");
            }
        });
    }

    public void rellenar(){
        int cantidad = 0;
        double precio = 0;
        int iva = 21;

        SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(CompraActivity.this);
        SQLiteDatabase sqLiteDatabase = SQLiteDBHelper.getWritableDatabase();

        listaArticulos = SQLiteDBHelper.getCarrito();

        for (Articulo a : listaArticulos){
            precio += a.getPrecio();
            cantidad += a.getCantidad();
        }

        precio = Math.round(precio * cantidad) * 100 / 100;

        double total = Math.round(precio + (precio * iva / 100))* 100 / 100;

        tv_articulos.setText(String.valueOf(cantidad));
        tv_precio.setText(String.valueOf(precio));
        tv_total.setText(String.valueOf(total));

        sqLiteDatabase.close();
    }

}
