package com.example.alu2015059.jstyle.Repository;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.alu2015059.jstyle.Domain.Articulo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alu2015059 on 01/06/2018.
 */

public class ArticulosBBDD {

    static public void insertArticulo(ContentResolver contentResolver, Articulo articulo){
        Uri uri = ArticulosDB.ARTICULO.CONTENT_URI;

        ContentValues values = new ContentValues();
        values.put(ArticulosDB.ARTICULO.DESCRIPCION, articulo.getDescripcion());
        values.put(ArticulosDB.ARTICULO.CODIGO, articulo.getCodigo());
        values.put(ArticulosDB.ARTICULO.CANTIDAD, articulo.getCantidad());
        values.put(ArticulosDB.ARTICULO.SEXO, articulo.getSexo());
        values.put(ArticulosDB.ARTICULO.PRECIO, articulo.getPrecio());

        contentResolver.insert(uri, values);
    }

    static public void deleteArticulo(ContentResolver contentResolver, Articulo articulo){
        Uri uri = Uri.parse(ArticulosDB.ARTICULO.CONTENT_URI + "/" + articulo.getCodigo());
        contentResolver.delete(uri, null, null);
    }

    static public void updateCompra(ContentResolver contentResolver, Articulo articulo){
        Uri uri = Uri.parse(ArticulosDB.ARTICULO.CONTENT_URI + "/" + articulo.getCodigo());

        ContentValues values = new ContentValues();
        values.put(ArticulosDB.ARTICULO.DESCRIPCION, articulo.getDescripcion());
        values.put(ArticulosDB.ARTICULO.CODIGO, articulo.getCodigo());
        values.put(ArticulosDB.ARTICULO.CANTIDAD, articulo.getCantidad());
        values.put(ArticulosDB.ARTICULO.SEXO, articulo.getSexo());
        values.put(ArticulosDB.ARTICULO.PRECIO, articulo.getPrecio());

        contentResolver.update(uri, values, null, null);
    }

    static public Articulo getArticuloById(ContentResolver contentResolver, Articulo articulo){
        Uri uri = Uri.parse(ArticulosDB.ARTICULO.CONTENT_URI + "/" + articulo.getCodigo());

        String[] COLUMNAS = {ArticulosDB.ARTICULO.DESCRIPCION, ArticulosDB.ARTICULO.CODIGO, ArticulosDB.ARTICULO.CANTIDAD
        , ArticulosDB.ARTICULO.SEXO, ArticulosDB.ARTICULO.PRECIO};

        Cursor cursor = contentResolver.query(uri, COLUMNAS, null, null, null);

        if(cursor.moveToFirst()){
            Articulo a = new Articulo();
            a.setDescripcion(cursor.getString(cursor.getColumnIndex(ArticulosDB.ARTICULO.DESCRIPCION)));
            a.setCodigo(cursor.getString(cursor.getColumnIndex(ArticulosDB.ARTICULO.CODIGO)));
            a.setCantidad(cursor.getInt(cursor.getColumnIndex(ArticulosDB.ARTICULO.CANTIDAD)));
            a.setSexo(cursor.getString(cursor.getColumnIndex(ArticulosDB.ARTICULO.SEXO)));
            a.setPrecio(cursor.getDouble(cursor.getColumnIndex(ArticulosDB.ARTICULO.PRECIO)));
            return a;
        }
        return null;
    }

    static public List<Articulo> getAllArticulos(ContentResolver contentResolver){
        Uri uri = ArticulosDB.ARTICULO.CONTENT_URI;

        String[] COLUMNAS = {ArticulosDB.ARTICULO.DESCRIPCION, ArticulosDB.ARTICULO.CODIGO, ArticulosDB.ARTICULO.CANTIDAD
                , ArticulosDB.ARTICULO.SEXO, ArticulosDB.ARTICULO.PRECIO};

        Cursor cursor = contentResolver.query(uri, COLUMNAS, null, null, null);

        List<Articulo> listaArticulos = new ArrayList<>();

        while (cursor.moveToNext()){
            Articulo a = new Articulo();
            a.setDescripcion(cursor.getString(cursor.getColumnIndex(ArticulosDB.ARTICULO.DESCRIPCION)));
            a.setCodigo(cursor.getString(cursor.getColumnIndex(ArticulosDB.ARTICULO.CODIGO)));
            a.setCantidad(cursor.getInt(cursor.getColumnIndex(ArticulosDB.ARTICULO.CANTIDAD)));
            a.setSexo(cursor.getString(cursor.getColumnIndex(ArticulosDB.ARTICULO.SEXO)));
            a.setPrecio(cursor.getDouble(cursor.getColumnIndex(ArticulosDB.ARTICULO.PRECIO)));

            listaArticulos.add(a);
        }
        return listaArticulos;
    }
}
