package com.example.alu2015059.jstyle.Service.Compra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.alu2015059.jstyle.R;

/**
 * Created by alu2015059 on 28/05/2018.
 */

public class CompraActivity extends AppCompatActivity {

    Button btn_cancelar;
    Button btn_comprar;

    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        btn_cancelar = findViewById(R.id.ac_btn_cancelar);
        btn_comprar = findViewById(R.id.ac_btn_finCompra);

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompraActivity.this.finish();
            }
        });

        btn_comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
