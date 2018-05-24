package com.example.alu2015059.jstyle.Service.Login_VersionBeta;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alu2015059.jstyle.Service.MostrarArticulos.PaginaArticulos;
import com.example.alu2015059.jstyle.R;
import com.example.alu2015059.jstyle.Repository.SQLiteDBHelper;
import com.example.alu2015059.jstyle.Domain.User;

/**
 * Created by alu2015059 on 24/04/2018.
 */

public class Registro extends AppCompatActivity{

    EditText username;
    EditText password1;
    EditText password2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_activity);

        Button btn_aceptar = findViewById(R.id.btn_aceptar2);
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = findViewById(R.id.etr_compañia);
                password1 = findViewById(R.id.etr_pass1);
                password2 = findViewById(R.id.etr_pass2);

                String user = username.getText().toString();
                String pass1 = password1.getText().toString();
                String pass2 = password2.getText().toString();

                if(pass1.equals(pass2)){
                    User usr = new User(user, pass1);
                    SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(Registro.this);
                    SQLiteDatabase sqLiteDatabase = SQLiteDBHelper.getWritableDatabase();
                    SQLiteDBHelper.insertUser(usr);
                    sqLiteDatabase.close();

                    Intent intent = new Intent(Registro.this, PaginaArticulos.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(Registro.this, "Contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btn_cancelar = findViewById(R.id.btn_cancelar2);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registro.this.finish();
            }
        });
    }
}
