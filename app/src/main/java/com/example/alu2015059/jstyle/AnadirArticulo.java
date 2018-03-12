package com.example.alu2015059.jstyle;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by alu2015059 on 05/02/2018.
 */

public class AnadirArticulo extends AppCompatActivity{

    //private ImageView imagen = findViewById(R.id.foto);
    private EditText descripcion;
    private EditText codigo;
    private EditText cantidad;
    private EditText sexo;
    private EditText precio;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_articulos);

        descripcion = findViewById(R.id.et_descripcion);
        codigo = findViewById(R.id.et_codigo);
        cantidad = findViewById(R.id.et_cantidad);
        sexo = findViewById(R.id.et_sexo);
        precio = findViewById(R.id.et_precio);
    }

    public void guardar(View view){
        ArticulosDBHelper articulosDBHelper = new ArticulosDBHelper(this);
        SQLiteDatabase sqLiteDatabase = articulosDBHelper.getWritableDatabase();

        String description = descripcion.getText().toString();
        String code = codigo.getText().toString();
        int cantity = Integer.valueOf(String.valueOf(cantidad.getText()));
        String sex = sexo .getText().toString();
        double price = Double.valueOf(String.valueOf(precio.getText()));

        ContentValues values = new ContentValues();
        values.put(ArticulosDB.ARTICULOS.DESCRIPCION, description);
        values.put(ArticulosDB.ARTICULOS.CODIGO, code);
        values.put(ArticulosDB.ARTICULOS.CANTIDAD, cantity);
        values.put(ArticulosDB.ARTICULOS.SEXO, sex);
        values.put(ArticulosDB.ARTICULOS.PRECIO, price);

        sqLiteDatabase.insert(ArticulosDB.ARTICULOS.TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }

    public void cancelar(View view){
        descripcion.setText("");
        codigo.setText("");
        cantidad.setText("");
        sexo.setText("");
        precio.setText("");
        setContentView(R.layout.activity_main);
    }
}
