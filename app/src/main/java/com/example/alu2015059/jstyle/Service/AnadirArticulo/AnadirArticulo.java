package com.example.alu2015059.jstyle.Service.AnadirArticulo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.R;
import com.example.alu2015059.jstyle.Repository.SQLiteDBHelper;
import com.example.alu2015059.jstyle.RepositoryTesting.ArticulosBBDD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alu2015059 on 05/02/2018.
 */

public class AnadirArticulo extends AppCompatActivity {

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
                if(TextUtils.isEmpty(description)){
                    descripcion.setError("Campo requerido");
                    descripcion.requestFocus();
                    return;
                }
                String code = codigo.getText().toString();
                if(TextUtils.isEmpty(code)){
                    codigo.setError("Campo requerido");
                    codigo.requestFocus();
                    return;
                }
                String cantity = cantidad.getText().toString();
                if (String.valueOf(cantity).equals("")){
                    cantidad.setError("Campo requerido");
                    cantidad.requestFocus();
                    return;
                }
                String sex = sexo .getText().toString();
                if (TextUtils.isEmpty(sex)){
                    sexo.setError("Campo requerido");
                    sexo.requestFocus();
                    return;
                }
                String price = precio.getText().toString();
                if (String.valueOf(price).equals("")){
                    precio.setError("Campo requerido");
                    precio.requestFocus();
                    return;
                }

                int cantidad = Integer.valueOf(cantity);
                double precio = Double.valueOf(price);

                List<Articulo> listaArticulos = new ArrayList<>();
                boolean error = false;

                try {
                    //listaArticulos = ArticulosBBDD.getAllArticulos(getContentResolver());
                    listaArticulos = SQLiteDBHelper.getAllArticulos();
                    for (Articulo a : listaArticulos){
                        if(a.getCodigo().equalsIgnoreCase(code)){
                            codigo.setError("!");
                            codigo.requestFocus();
                            error = true;
                            Toast.makeText(AnadirArticulo.this, "Codigo actualmente en uso",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(error == false) {
                    try {
                        //Creo el articulo
                        Articulo articulo = new Articulo(description, code, cantidad, sex, precio);

                        //Cojo el metodo creado en SQLiteDBHelper para guardar el articulo
                        SQLiteDBHelper.insertArticulo(articulo);

                        //ArticulosBBDD.insertArticulo(getContentResolver(), articulo);
                        Toast.makeText(AnadirArticulo.this, "ARTICULO creado correctamente",Toast.LENGTH_SHORT).show();

                        //Despues de que el boton haga su funcionalidad, htesago que vuelva a la pagina de los articulos, finalizando esta
                        AnadirArticulo.this.finish();
                        return;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
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
