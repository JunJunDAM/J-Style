package com.example.alu2015059.jstyle.Service.Login_VersionBeta;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alu2015059.jstyle.Service.MostrarArticulos.PaginaArticulos;
import com.example.alu2015059.jstyle.R;
import com.example.alu2015059.jstyle.Repository.SQLiteDBHelper;

/**
 * Created by alu2015059 on 24/04/2018.
 */

public class Login extends AppCompatActivity{

    EditText username;
    EditText password;
    private Cursor cursor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username = findViewById(R.id.et_login_username);
        password = findViewById(R.id.et_login_password);

        Button btn_registrar = findViewById(R.id.btn_login_registrar);
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
            }
        });

        Button btn_login = findViewById(R.id.btn_login_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Abro base de datos con permisos
                SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(Login.this);
                SQLiteDatabase sqLiteDatabase = SQLiteDBHelper.getWritableDatabase();
                //Cojo el valor de los edit text
                String user = username.getText().toString();
                String pass = password.getText().toString();

                Boolean auth = SQLiteDBHelper.authentication(user, pass);

                if(auth == true){
                    Intent intent = new Intent(Login.this, PaginaArticulos.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this, "Usuario o Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                }

                sqLiteDatabase.close();
                //Query para obtener el usuario y la contraseña de los edit text
                cursor = sqLiteDatabase.rawQuery("SELECT USERNAME, PASSWORD FROM USERS WHERE " +
                        "USERNAME = '" + user + "' AND " +
                        "PASSWORD = '" + pass + "'", null);
                //Si el cursos tiene datos los cojo en Strings
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst() == true){
                            String u = cursor.getString(0);
                            String p = cursor.getString(1);
                            Log.d("prova", u);
                            Log.d("prova2", p);
                            //Si los valores del cursor son equivalentes a los que ha escrito el usuario accede a la seguiente pantalla
                            if(user.equals(u) && password.equals(p)){
                                username.setText("");
                                password.setText("");
                                Intent intent = new Intent(Login.this, PaginaArticulos.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Login.this, "Usuario o Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Login.this, "Usuario no existe",Toast.LENGTH_SHORT).show();
                        }
                    } finally {
                        cursor.close();
                    }
                }
            }
        });

        Button btn_cancelar = findViewById(R.id.btn_login_cancelar);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login.this.finish();
            }
        });
    }
}
