package com.example.alu2015059.jstyle.RepositoryTesting;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.alu2015059.jstyle.Domain.ArticuloSYNC;
import com.example.alu2015059.jstyle.Repository.Database;

import java.util.ArrayList;

public class ArticulosSync {
    static public void insert(ContentResolver resolvedor, ArticuloSYNC sync){
        Uri uri = Database.ARTICULO.CONTENT_URI;

        ContentValues values = new ContentValues();
        values.put(Database.ARTICULO_SYNC.CODIGO, sync.getCodigo());
        values.put(Database.ARTICULO_SYNC.OPERACION, sync.getOperacion());

        resolvedor.insert(uri, values);
    }

    static public void delete(ContentResolver resolver, String codigo){
        Uri uri = Uri.parse(Database.ARTICULO_SYNC.CONTENT_URI + "/" + codigo);
        resolver.delete(uri, null, null);
    }

    static public void update(ContentResolver resolver, ArticuloSYNC sync){
        Uri uri = Uri.parse(Database.ARTICULO_SYNC.CONTENT_URI + "/" + sync.getCodigo());

        ContentValues values = new ContentValues();
        values.put(Database.ARTICULO_SYNC.CODIGO, sync.getCodigo());
        values.put(Database.ARTICULO_SYNC.OPERACION, sync.getOperacion());

        resolver.update(uri, values, null, null);
    }


    static public ArticuloSYNC read(ContentResolver resolver, String codigo) {
        Uri uri = Uri.parse(Database.ARTICULO_SYNC.CONTENT_URI + "/" + codigo);

        String[] projection = {
                Database.ARTICULO_SYNC.CODIGO,
                Database.ARTICULO_SYNC.OPERACION};

        Cursor cursor = resolver.query(uri, projection, null, null, null);

        if (cursor.moveToFirst()){
            ArticuloSYNC sync = new ArticuloSYNC();
            sync.setCodigo(cursor.getString(cursor.getColumnIndex(Database.ARTICULO_SYNC.CODIGO)));
            sync.setOperacion(cursor.getInt(cursor.getColumnIndex(Database.ARTICULO_SYNC.OPERACION)));
            return sync;
        }

        return null;

    }

    static public ArrayList<ArticuloSYNC> readAll(ContentResolver resolver) {
        Uri uri = Database.ARTICULO_SYNC.CONTENT_URI;

        String[] projection = {
                Database.ARTICULO_SYNC.CODIGO,
                Database.ARTICULO_SYNC.OPERACION};

        Cursor cursor = resolver.query(uri, projection, null, null, null);

        ArrayList<ArticuloSYNC> syncs = new ArrayList<>();

        while (cursor.moveToNext()){
            ArticuloSYNC sync = new ArticuloSYNC();
            sync.setCodigo(cursor.getString(cursor.getColumnIndex(Database.ARTICULO_SYNC.CODIGO)));
            sync.setOperacion(cursor.getInt(cursor.getColumnIndex(Database.ARTICULO_SYNC.OPERACION)));
            syncs.add(sync);
        }

        return syncs;

    }
}
