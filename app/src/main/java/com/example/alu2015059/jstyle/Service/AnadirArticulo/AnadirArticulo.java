package com.example.alu2015059.jstyle.Service.AnadirArticulo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.R;
import com.example.alu2015059.jstyle.Repository.SQLiteDBHelper;

import java.io.File;

/**
 * Created by alu2015059 on 05/02/2018.
 */

public class AnadirArticulo extends AppCompatActivity{

    private static int TAKE_PICTURE = 1;

    //Creo variables
    private ImageView image;
    private Button btn_image;
    private Button btn_galeria;
    private EditText descripcion;
    private EditText codigo;
    private EditText cantidad;
    private EditText sexo;
    private EditText precio;
    int code = TAKE_PICTURE;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_articulos);
        //Asigno a las variables su edit text correspondido
        image = findViewById(R.id.ai_foto);
        btn_image = findViewById(R.id.ai_foto_btn);
        btn_galeria = findViewById(R.id.aa_btn_galeria);
        descripcion = findViewById(R.id.et_descripcion);
        codigo = findViewById(R.id.et_codigo);
        cantidad = findViewById(R.id.et_cantidad);
        sexo = findViewById(R.id.et_sexo);
        precio = findViewById(R.id.et_precio);

        Button btn_guardar = findViewById(R.id.btn_guardar);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Abrimos base de datos con permisos de escritura
                SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(AnadirArticulo.this);

                //Cojo los valores de los articulos
                ImageView imagen = image;
                String description = descripcion.getText().toString();
                String code = codigo.getText().toString();
                int cantity = Integer.valueOf(String.valueOf(cantidad.getText()));
                String sex = sexo .getText().toString();
                double price = Double.valueOf(String.valueOf(precio.getText()));

                //Creo el articulo
                Articulo articulo = new Articulo(description, code, cantity, sex, price);
                //Cojo el metodo creado en SQLiteDBHelper para guardar el articulo
                SQLiteDBHelper.insertArticulo(articulo);

                Toast.makeText(AnadirArticulo.this, "Articulo creado correctamente",Toast.LENGTH_SHORT).show();

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

        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent, TAKE_PICTURE);
                }
            }
        });

        btn_galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                String name = codigo.toString();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode == TAKE_PICTURE && resultCode == RESULT_OK){
            Bundle bundle = intent.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            image.setImageBitmap(bitmap);
        }
    }
}
