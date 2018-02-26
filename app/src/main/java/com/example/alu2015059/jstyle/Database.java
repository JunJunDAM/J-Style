package com.example.alu2015059.jstyle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alu2015059 on 05/02/2018.
 */

public class Database extends SQLiteOpenHelper{

    String sentencia = "CREATE TABLE Prendas (<imagen> , descripcion TEXT, codigo TEXT, cantidad INTEGER, sexo TEXT, precio )";



    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqldb) {
        sqldb.execSQL(sentencia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Prendas");
        sqLiteDatabase.execSQL(sentencia);
    }
}
