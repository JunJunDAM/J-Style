package com.example.alu2015059.jstyle;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alu2015059.jstyle.SQLite.SQLiteDBHelper;

/**
 * Created by alu2015059 on 23/01/2018.
 */

public class PaginaArticulos extends ListActivity{
    private ListView listView;
    private Button btn_buscar;
    private EditText code;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_articulos_adm);

        //Boton para volver a la pagina principal
        Button btn_volver = findViewById(R.id.btn_volver);
        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaginaArticulos.this.finish();
            }
        });

        btn_buscar = findViewById(R.id.btn_buscador);
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code = findViewById(R.id.et_buscador);
                String codigo = String.valueOf(code);

                if(codigo.equalsIgnoreCase("")){
                    Toast.makeText(PaginaArticulos.this, "Buscador vacio",Toast.LENGTH_SHORT).show();
                }else{
                    SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(PaginaArticulos.this);
                    SQLiteDatabase sqLiteDatabase = SQLiteDBHelper.getWritableDatabase();

                    String query = "SELECT * FORM ARTICULOS WHERE codigo = '" + codigo + "'";



                    SQLiteDBHelper.getArticuloByCodigo(codigo);
                    sqLiteDatabase.close();
                }
            }
        });

        //Boton que llevara a la pagina para añadir un nuevo articulo
        Button btn_anadirArticulo = findViewById(R.id.btn_anadir);
        btn_anadirArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaginaArticulos.this, AnadirArticulo.class);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(View view){
        getMenuInflater().inflate(R.id.lista_articulos, view);
        return true;
    }

}
