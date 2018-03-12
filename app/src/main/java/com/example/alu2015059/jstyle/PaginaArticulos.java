package com.example.alu2015059.jstyle;

import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alu2015059 on 23/01/2018.
 */

public class PaginaArticulos extends ListActivity{
    private ListView listView;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_articulos);

        listView = findViewById(R.id.list_articles);

        ArticulosDBHelper articulosDBHelper = new ArticulosDBHelper(this);
        SQLiteDatabase sqLiteDatabase = articulosDBHelper.getWritableDatabase();

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(, menu);
        return true;
    }*/
}
