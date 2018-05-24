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

        SQLiteDatabase db = this.getWritableDatabase();

        //Creo un usuario de prueba
        ContentValues user = new ContentValues();
        user.put(LoginDB.USERS.USERNAME, "admin");
        user.put(LoginDB.USERS.PASSWORD, "admin");
        db.insert(LoginDB.USERS.TABLE_NAME, null, user);

        //Creo un articulo de pueba
        ContentValues articulo = new ContentValues();
        articulo.put(ArticulosDB.ARTICULOS.DESCRIPCION, "Camiseta");
        articulo.put(ArticulosDB.ARTICULOS.CODIGO, 1);
        articulo.put(ArticulosDB.ARTICULOS.CANTIDAD, 20);
        articulo.put(ArticulosDB.ARTICULOS.SEXO, "Hombre");
        articulo.put(ArticulosDB.ARTICULOS.PRECIO, 12);

        db.insert(ArticulosDB.ARTICULOS.TABLE_NAME, null, articulo);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ArticulosDB.ARTICULOS_DROPTABLE);
        sqLiteDatabase.execSQL(LoginDB.USERS_DROPTABLE);
        //Llamamos al metodo onCreate para que se cree de nuevo la tabla
        this.onCreate(sqLiteDatabase);
    }

    // CRUD

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

    public Articulo getArticuloByCodigo(String codigo){
        //Declaro un objeto articulo
        Articulo articulo = null;
        //Obtenemos los permisod de lectura
        SQLiteDatabase db = this.getReadableDatabase();
        //Definimos un array con los nombres de columnas que queremos
        String[] COLUMNAS = {ArticulosDB.ARTICULOS.DESCRIPCION, ArticulosDB.ARTICULOS.CODIGO, ArticulosDB.ARTICULOS.CANTIDAD, ArticulosDB.ARTICULOS.SEXO, ArticulosDB.ARTICULOS.PRECIO};
        //Construimos la QUERY

        Cursor cursor = db.query(ArticulosDB.ARTICULOS.TABLE_NAME, //Nombre de la tabla
                COLUMNAS, //Nombre de las columnas
                " codigo = ?", // Columnas de la clausula WHERE
                new String[]{codigo}, // Valores de las columnas de la clausula WHere
                null, //Clausula GroupBy
                null, //Clausula Having
                null, //Clausula OrderBy
                null); //Limite de registros
        //Sacamos el resultado obtenido por el codigo proporcionado
        if(cursor != null){
            cursor.moveToFirst();
            // Construyo el objeto Prenda
            articulo = new Articulo();
            articulo.setDescripcion(cursor.getString(0));
            articulo.setCodigo(cursor.getString(1));
            articulo.setCantidad(Integer.parseInt(cursor.getString(2)));
            articulo.setSexo(cursor.getString(3));
            articulo.setPrecio(Double.parseDouble(cursor.getString(4)));
        }
        cursor.close();
        db.close();
        //Devolvemos el objeto Prenda
        return  articulo;
    }

    //Obtener todos los articulos
    public List getAllArticulos(){
        //Creamos una Array para llenarlo con los articulos que tengamos en la BD
        List articulos = new ArrayList();
        //Metemos en un String la query que queremos ejecutar
        String query = "SELECT * FROM " + ArticulosDB.ARTICULOS.TABLE_NAME;
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
