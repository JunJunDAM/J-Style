package com.example.alu2015059.jstyle.RepositoryTesting;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.Repository.CompraDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alu2015059 on 01/06/2018.
 */

public class CompraBBDD {
    static public Uri insertCompra(ContentResolver contentResolver, Articulo articulo){
        Uri uri = CompraDB.CARRITO.CONTENT_URI;

        ContentValues values = new ContentValues();
        values.put(CompraDB.CARRITO.DESCRIPCION, articulo.getDescripcion());
        values.put(CompraDB.CARRITO.CODIGO, articulo.getCodigo());
        values.put(CompraDB.CARRITO.CANTIDAD, articulo.getCantidad());
        values.put(CompraDB.CARRITO.SEXO, articulo.getSexo());
        values.put(CompraDB.CARRITO.PRECIO, articulo.getPrecio());

        return contentResolver.insert(uri, values);
    }

    static public void deleteCompra(ContentResolver contentResolver, Articulo articulo){
        Uri uri = Uri.parse(CompraDB.CARRITO.CONTENT_URI + "/" + articulo.getCodigo());
        contentResolver.delete(uri, null, null);
    }

    static public void updateCompra(ContentResolver contentResolver, Articulo articulo){
        Uri uri = Uri.parse(CompraDB.CARRITO.CONTENT_URI + "/" + articulo.getCodigo());

        ContentValues values = new ContentValues();
        values.put(CompraDB.CARRITO.DESCRIPCION, articulo.getDescripcion());
        values.put(CompraDB.CARRITO.CODIGO, articulo.getCodigo());
        values.put(CompraDB.CARRITO.CANTIDAD, articulo.getCantidad());
        values.put(CompraDB.CARRITO.SEXO, articulo.getSexo());
        values.put(CompraDB.CARRITO.PRECIO, articulo.getPrecio());

        contentResolver.update(uri, values, null, null);
    }

    static public List<Articulo> getCarrito(ContentResolver contentResolver){
        Uri uri = CompraDB.CARRITO.CONTENT_URI;

        String[] COLUMNAS = {CompraDB.CARRITO.DESCRIPCION, CompraDB.CARRITO.CODIGO, CompraDB.CARRITO.CANTIDAD
                , CompraDB.CARRITO.SEXO, CompraDB.CARRITO.PRECIO};

        Cursor cursor = contentResolver.query(uri, COLUMNAS, null, null, null);

        List<Articulo> listaArticulos = new ArrayList<>();

        while (cursor.moveToNext()){
            Articulo a = new Articulo();
            a.setDescripcion(cursor.getString(cursor.getColumnIndex(CompraDB.CARRITO.DESCRIPCION)));
            a.setCodigo(cursor.getString(cursor.getColumnIndex(CompraDB.CARRITO.CODIGO)));
            a.setCantidad(cursor.getInt(cursor.getColumnIndex(CompraDB.CARRITO.CANTIDAD)));
            a.setSexo(cursor.getString(cursor.getColumnIndex(CompraDB.CARRITO.SEXO)));
            a.setPrecio(cursor.getDouble(cursor.getColumnIndex(CompraDB.CARRITO.PRECIO)));

            listaArticulos.add(a);
        }
        return listaArticulos;
    }
}
