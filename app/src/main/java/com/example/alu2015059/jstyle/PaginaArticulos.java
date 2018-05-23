package com.example.alu2015059.jstyle;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by alu2015059 on 23/01/2018.
 */

//link para hacer fragments : http://www.hermosaprogramacion.com/2014/10/android-listas-adaptadores/

public class PaginaArticulos extends AppCompatActivity{
    private SwipeRefreshLayout srl_Articulos;
    private RecyclerView rv_Articulos;
    private Button btn_buscar;
    private Button btn_volver;
    private Button btn_anadirArticulo;
    private EditText code;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_articulos_adm);

        btn_buscar = findViewById(R.id.pga_btn_buscador);
        btn_anadirArticulo = findViewById(R.id.pga_btn_anadir);
        btn_volver = findViewById(R.id.pga_btn_volver);
        code = findViewById(R.id.pga_et_buscador);
        srl_Articulos = findViewById(R.id.srl_articulos);
        rv_Articulos = findViewById(R.id.rv_articulos);

        //Boton para volver a la pagina principal
        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaginaArticulos.this.finish();
            }
        });

        //Boton para buscar en el buscador
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateArticulos();
            }
        });

        //Boton que llevara a la pagina para añadir un nuevo articulo
        btn_anadirArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaginaArticulos.this, AnadirArticulo.class);
                startActivity(intent);
            }
        });

        srl_Articulos.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateArticulos();
            }
        });
    }

    private void updateArticulos() {

    }
}
