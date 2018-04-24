package com.example.alu2015059.jstyle;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.SQLite.SQLiteDBHelper;

/**
 * Created by alu2015059 on 05/02/2018.
 */

public class AnadirArticulo extends AppCompatActivity{

    //Creo variables
    //private ImageView imagen = findViewById(R.id.foto);
    private EditText descripcion;
    private EditText codigo;
    private EditText cantidad;
    private EditText sexo;
    private EditText precio;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_articulos);
        //Asigno a las variables su edit text correspondido
        descripcion = findViewById(R.id.et_descripcion);
        codigo = findViewById(R.id.et_codigo);
        cantidad = findViewById(R.id.et_cantidad);
        sexo = findViewById(R.id.et_sexo);
        precio = findViewById(R.id.et_precio);

        Button btn_guardar = findViewById(R.id.btn_guardar);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("prova", "Aqui");

                //Abrimos base de datos con permisos de escritura
                SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(AnadirArticulo.this);
                SQLiteDatabase sqLiteDatabase = SQLiteDBHelper.getWritableDatabase();

                //Cojo los valores de los articulos
                String description = descripcion.getText().toString();
                String code = codigo.getText().toString();
                int cantity = Integer.valueOf(String.valueOf(cantidad.getText()));
                String sex = sexo .getText().toString();
                double price = Double.valueOf(String.valueOf(precio.getText()));

                System.out.print(description + code + cantity + sex + price);

                //Creo el articulo
                Articulo articulo = new Articulo(description, code, cantity, sex, price);
                //Cojo el metodo creado en SQLiteDBHelper para guardar el articulo
                SQLiteDBHelper.insertNode(articulo);

                Log.d("prova", "Articulo Guardado");

                //Cierro la base de datos
                sqLiteDatabase.close();
                //Despues de que el boton haga su funcionalidad, hago que vuelva a la pagina de los articulos, finalizando esta
                AnadirArticulo.this.finish();
            }
        });

        Button btn_cancelar = findViewById(R.id.btn_cancelar);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descripcion.setText("");
                codigo.setText("");
                cantidad.setText("");
                sexo.setText("");
                precio.setText("");
                AnadirArticulo.this.finish();
            }
        });

        Button btn_foto = findViewById(R.id.foto_btn);
        btn_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

            }
        });
    }



    //Metodo que guardara los articulos
    public void guardar(View view){
        //Abrimos base de datos con permisos de escritura
        SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(this);
        SQLiteDatabase sqLiteDatabase = SQLiteDBHelper.getWritableDatabase();

        //Cojo los valores de los articulos
        String description = descripcion.getText().toString();
        String code = codigo.getText().toString();
        int cantity = Integer.valueOf(String.valueOf(cantidad.getText()));
        String sex = sexo .getText().toString();
        double price = Double.valueOf(String.valueOf(precio.getText()));

        System.out.print(description + code + cantity + sex + price);

        //Creo el articulo
        Articulo articulo = new Articulo(description, code, cantity, sex, price);
        //Cojo el metodo creado en SQLiteDBHelper para guardar el articulo
        SQLiteDBHelper.insertNode(articulo);

        System.out.println("Articulo añadido");

        /*ContentValues values = new ContentValues();
        values.put(ArticulosDB.ARTICULOS.DESCRIPCION, description);
        values.put(ArticulosDB.ARTICULOS.CODIGO, code);
        values.put(ArticulosDB.ARTICULOS.CANTIDAD, cantity);
        values.put(ArticulosDB.ARTICULOS.SEXO, sex);
        values.put(ArticulosDB.ARTICULOS.PRECIO, price);*/

        /*sqLiteDatabase.insert(ArticulosDB.ARTICULOS.TABLE_NAME, null, articulo);*/
        //Cierro la base de datos
        sqLiteDatabase.close();
    }

    //Metodo para cancelar y volver a la pagina principal
    public void cancelar(View view){
        //Vacio los campos
        descripcion.setText("");
        codigo.setText("");
        cantidad.setText("");
        sexo.setText("");
        precio.setText("");
        //Vuelvo a la pagina principal
        setContentView(R.layout.activity_main);
    }
}
