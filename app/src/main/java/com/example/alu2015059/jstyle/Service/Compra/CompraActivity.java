package com.example.alu2015059.jstyle.Service.Compra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.MainActivity;
import com.example.alu2015059.jstyle.R;
import com.example.alu2015059.jstyle.Repository.SQLiteDBHelper;
import com.example.alu2015059.jstyle.RepositoryTesting.ArticulosBBDD;

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

                List<Articulo> listaArticulos = new ArrayList<>();
                listaArticulos = SQLiteDBHelper.getAllArticulos();
                List<Articulo> listaCarrito = new ArrayList<>();
                listaCarrito = SQLiteDBHelper.getCarrito();

                int cantidadArticulos = 0;

                for (Articulo articulo : listaArticulos){
                    for (Articulo carrito : listaCarrito){
                        if(articulo.getCodigo().equals(carrito.getCodigo())){
                            cantidadArticulos = articulo.getCantidad() - carrito.getCantidad();
                            SQLiteDBHelper.updateCantArticulo(articulo, cantidadArticulos);
                            try {
                                ArticulosBBDD.update(getContentResolver(), articulo);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                SQLiteDBHelper.compraFinal(listaCarrito);

                Toast.makeText(CompraActivity.this, "Gracias por utilizar este servicio",Toast.LENGTH_SHORT).show();

                tv_articulos.setText("");
                tv_precio.setText("");
                tv_total.setText("");

                Intent intent = new Intent(CompraActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void rellenar(){
        int cantidad = 0;
        double precio = 0;
        int totalCantidad = 0;
        double totalPrecio = 0;
        int iva = 21;

        SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(CompraActivity.this);

        listaArticulos = SQLiteDBHelper.getCarrito();

        for (Articulo a : listaArticulos){
            precio = a.getPrecio();
            cantidad = a.getCantidad();
            totalCantidad += a.getCantidad();
            totalPrecio += precio * cantidad;
        }

        double total = Math.round(totalPrecio + (totalPrecio * iva / 100))* 100 / 100;

        tv_articulos.setText(String.valueOf(totalCantidad));
        tv_precio.setText(String.valueOf(totalPrecio));
        tv_total.setText(String.valueOf(total));
    }

}
