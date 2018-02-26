package com.example.alu2015059.jstyle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alu2015059 on 05/02/2018.
 */

public class Database extends SQLiteOpenHelper{

    String sentencia = "CREATE TABLE Prendas (<imagen> , descripcion TEXT, codigo TEXT, cantidad INTEGER, sexo TEXT, precio )";



    public Database(Context context) {
        super(context, PrendasDB.DATABASE_NAME, null, PrendasDB.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PrendasDB.CREATE_PRENDASDB);
    }

    //https://expocodetech.com/guardar-datos-con-sqlite-en-android/

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Prendas");
        sqLiteDatabase.execSQL(sentencia);
    }
}
