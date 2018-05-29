package com.example.alu2015059.jstyle.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.Domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alu2015059 on 05/03/2018.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper{
    //Definimos constructor
    public SQLiteDBHelper(Context context){
        super(context, Database.DATABASE_NAME,null, Database.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creamos las tablas en la base de datos
        sqLiteDatabase.execSQL(ArticulosDB.ARTICULOS_CREATE_TABLE);
        sqLiteDatabase.execSQL(LoginDB.USERS_CREATE_TABLE);
        sqLiteDatabase.execSQL(CompraDB.COMPRA_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ArticulosDB.ARTICULOS_DROPTABLE);
        sqLiteDatabase.execSQL(LoginDB.USERS_DROPTABLE);
        sqLiteDatabase.execSQL(CompraDB.COMPRA_DROPTABLE);
        //Llamamos al metodo onCreate para que se cree de nuevo la tabla
        this.onCreate(sqLiteDatabase);
    }

    // CRUD

    public void restartBBDD(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query1 = "DELETE * FROM ARTICULOS";
        db.execSQL(query1);
        String query2 = "DELETE * FROM USERS";
        db.execSQL(query2);
        String query3 = "DELETE * FROM COMPRA";
        db.execSQL(query3);
        db.close();
    }

    public void insertCompra(Articulo articulo){
        //obtenemos permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        //Creamos un objeto para agregar las columnas y valores
        ContentValues values = new ContentValues();
        values.put(CompraDB.COMPRA.DESCRIPCION, articulo.getDescripcion());
        values.put(CompraDB.COMPRA.CODIGO, articulo.getCodigo());
        values.put(CompraDB.COMPRA.CANTIDAD, articulo.getCantidad());
        values.put(CompraDB.COMPRA.SEXO, articulo.getSexo());
        values.put(CompraDB.COMPRA.PRECIO, articulo.getPrecio());

        //Insertamos los datos en la tabla
        db.insert(CompraDB.COMPRA.TABLE_NAME, null, values);

        //Cerramos la conexion con la base de datos
        db.close();
    }

    public void insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LoginDB.USERS.USERNAME, user.getUsername());
        values.put(LoginDB.USERS.PASSWORD, user.getPassword());

        db.insert(LoginDB.USERS.TABLE_NAME, null, values);

        db.close();
    }

    public boolean authentication(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        //String query = "Select USERNAME, PASSWORD from USERS where USERNAME = '" + username + "' and PASSWORD = '" + password + "'";
        String[] COLUMNAS = {LoginDB.USERS.USERNAME, LoginDB.USERS.PASSWORD};

        Cursor cursor = db.query(LoginDB.USERS.TABLE_NAME, //Nombre de la tabla
                COLUMNAS, //Nombre de las columnas
                " USERNAME = ?, PASSWORD = ? ", // Columnas de la clausula WHERE
                new String[]{username, password}, // Valores de las columnas de la clausula WHere
                null, //Clausula GroupBy
                null, //Clausula Having
                null, //Clausula OrderBy
                null); //Limite de registros

        if(cursor != null){
            cursor.moveToFirst();
            return true;
        }else {
            return false;
        }
    }

    public void insertArticulo(Articulo articulo){
        //obtenemos permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        //Creamos un objeto para agregar las columnas y valores
        ContentValues values = new ContentValues();
        values.put(ArticulosDB.ARTICULOS.DESCRIPCION, articulo.getDescripcion());
        values.put(ArticulosDB.ARTICULOS.CODIGO, articulo.getCodigo());
        values.put(ArticulosDB.ARTICULOS.CANTIDAD, articulo.getCantidad());
        values.put(ArticulosDB.ARTICULOS.SEXO, articulo.getSexo());
        values.put(ArticulosDB.ARTICULOS.PRECIO, articulo.getPrecio());

        //Insertamos los datos en la tabla
        db.insert(ArticulosDB.ARTICULOS.TABLE_NAME, null, values);

        //Cerramos la conexion con la base de datos
        db.close();
    }

    public void compraFinal(List<Articulo> listaCompra){
        //obtenemos permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();



        int cant = 0;

        for(Articulo a : listaCompra){
            db.execSQL("UPDATE '" + ArticulosDB.ARTICULOS.TABLE_NAME
                    + " SET cantidad = cantidad -= '" + a.getCantidad()
                    + "' WHERE codigo = '" + a.getCodigo() + "'");
        }

        //Cerramos la conexion con la base de datos
        db.close();
    }

    public void deleteArticulo(Articulo articulo){
        //obtenemos permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM '" + ArticulosDB.ARTICULOS.TABLE_NAME + "' WHERE codigo = '" + articulo.getCodigo() + "'");

        //Cerramos la conexion con la base de datos
        db.close();
    }

    public void deleteArticuloFromCarrito(Articulo articulo){
        //obtenemos permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM COMPRA WHERE codigo = '" + articulo.getCodigo() + "'";
        db.execSQL(query);

        //Cerramos la conexion con la base de datos
        db.close();
    }

    public List<Articulo> getArticuloByCodigo(String codigo){
        //Creamos una Array para llenarlo con los articulos que tengamos en la BD
        List articulos = new ArrayList();
        //Metemos en un String la query que queremos ejecutar
        String query = "SELECT * FROM '" + ArticulosDB.ARTICULOS.TABLE_NAME + "' WHERE codigo LIKE '%" + codigo + "%'";
        //Obtenemos permisos de escritura y ejecutamos la query
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        //Revisamos lso registros y agregamos al array
        Articulo articulo = null;
        if(cursor.moveToFirst()){
            do{
                articulo = new Articulo();
                articulo.setDescripcion(cursor.getString(0));
                articulo.setCodigo(cursor.getString(1));
                articulo.setCantidad(Integer.parseInt(cursor.getString(2)));
                articulo.setSexo(cursor.getString(3));
                articulo.setPrecio(Double.parseDouble(cursor.getString(4)));
                // Añadimos los articulos a la lista de articulos
                articulos.add(articulo);
            }while (cursor.moveToNext());
        }

        //Cerramos el cursor
        cursor.close();
        db.close();
        //Devolvemos los articulos encontrados
        return articulos;
    }

    //Obtener todos los articulos
    public List getAllArticulos(){
        //Creamos una Array para llenarlo con los articulos que tengamos en la BD
        List articulos = new ArrayList();
        //Metemos en un String la query que queremos ejecutar
        String query = "SELECT * FROM '" + ArticulosDB.ARTICULOS.TABLE_NAME + "'";
        //Obtenemos permisos de escritura y ejecutamos la query
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        //Revisamos lso registros y agregamos al array
        Articulo articulo = null;
        if(cursor.moveToFirst()){
            do{
                articulo = new Articulo();
                articulo.setDescripcion(cursor.getString(0));
                articulo.setCodigo(cursor.getString(1));
                articulo.setCantidad(Integer.parseInt(cursor.getString(2)));
                articulo.setSexo(cursor.getString(3));
                articulo.setPrecio(Double.parseDouble(cursor.getString(4)));
                // Añadimos los articulos a la lista de articulos
                articulos.add(articulo);
            }while (cursor.moveToNext());
        }

        //Cerramos el cursor
        cursor.close();
        db.close();
        //Devolvemos los articulos encontrados
        return articulos;
    }

    public List getCarrito(){
        //Creamos una Array para llenarlo con los articulos que tengamos en la BD
        List articulos = new ArrayList();
        //Metemos en un String la query que queremos ejecutar
        String query = "SELECT * FROM '" + CompraDB.COMPRA.TABLE_NAME + "'";
        //Obtenemos permisos de escritura y ejecutamos la query
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        //Revisamos lso registros y agregamos al array
        Articulo articulo = null;
        if(cursor.moveToFirst()){
            do{
                articulo = new Articulo();
                articulo.setDescripcion(cursor.getString(0));
                articulo.setCodigo(cursor.getString(1));
                articulo.setCantidad(Integer.parseInt(cursor.getString(2)));
                articulo.setSexo(cursor.getString(3));
                articulo.setPrecio(Double.parseDouble(cursor.getString(4)));
                // Añadimos los articulos a la lista de articulos
                articulos.add(articulo);
            }while (cursor.moveToNext());
        }

        //Cerramos el cursor
        cursor.close();
        db.close();
        //Devolvemos los articulos encontrados
        return articulos;
    }
}
