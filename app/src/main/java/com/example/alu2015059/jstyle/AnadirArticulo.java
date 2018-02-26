package com.example.alu2015059.jstyle;

import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by alu2015059 on 05/02/2018.
 */

public class AnadirArticulo extends AppCompatActivity{

    //private ImageView imagen = findViewById(R.id.foto);
    private EditText descripcion = findViewById(R.id.et_descripcion);
    private EditText codigo = findViewById(R.id.et_codigo);
    private EditText cantidad = findViewById(R.id.et_cantidad);
    private EditText sexo = findViewById(R.id.et_sexo);
    private EditText precio = findViewById(R.id.et_precio);

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_articulos);

        //Image imatge = imagen.;
        String descripcio = String.valueOf(descripcion.getText());
        String codi = String.valueOf(codigo.getText());
        Integer cantitat = Integer.parseInt(String.valueOf(cantidad.getText()));
        String sexe = String.valueOf(sexo.getText());
        Double preu = Double.parseDouble(String.valueOf(precio.getText()));

        Database db = new Database(this, "Prendas", null, 1);
        SQLiteDatabase sqldb = db.getWritableDatabase();

        if(db != null){

            db.close();
        }

        /*
        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1);

        SQLiteDatabase db = usdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if(db != null)
        {
            //Insertamos 5 usuarios de ejemplo
            for(int i=1; i<=5; i++)
            {
                //Generamos los datos
                int codigo = i;
                String nombre = "Usuario" + i;

                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO Usuarios (codigo, nombre) " +
                        "VALUES (" + codigo + ", '" + nombre +"')");
            }

            //Cerramos la base de datos
            db.close();
            */
    }
}
