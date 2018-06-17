package com.example.alu2015059.jstyle.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.Domain.Imagen;
import com.example.alu2015059.jstyle.Domain.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
        sqLiteDatabase.execSQL(ImagenDB.IMAGEN_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ArticulosDB.ARTICULOS_DROPTABLE);
        sqLiteDatabase.execSQL(LoginDB.USERS_DROPTABLE);
        sqLiteDatabase.execSQL(CompraDB.COMPRA_DROPTABLE);
        sqLiteDatabase.execSQL(ImagenDB.IMAGEN_DROPTABLE);
        //Llamamos al metodo onCreate para que se cree de nuevo la tabla
        this.onCreate(sqLiteDatabase);
    }

    public void createTableIfNotExists(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CompraDB.COMPRA_CREATETABLE_IFNOTEXISTS);
        db.execSQL(ArticulosDB.ARTICULOS_CREATETABLE_IFNOTEXISTS);
        db.execSQL(ImagenDB.IMAGEN_CREATETABLE_IFNOTEXISTS);
    }
    // CRUD

    public void insertCompra(Articulo articulo){
        //obtenemos permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        //Creamos un objeto para agregar las columnas y valores
        ContentValues values = new ContentValues();
        values.put(CompraDB.TABLE.DESCRIPCION, articulo.getDescripcion());
        values.put(CompraDB.TABLE.CODIGO, articulo.getCodigo());
        values.put(CompraDB.TABLE.CANTIDAD, articulo.getCantidad());
        values.put(CompraDB.TABLE.SEXO, articulo.getSexo());
        values.put(CompraDB.TABLE.PRECIO, articulo.getPrecio());

        //Insertamos los datos en la tabla
        db.insert(CompraDB.TABLE.TABLE_NAME, null, values);

        //Cerramos la conexion con la base de datos
        db.close();
    }

    public void insertImagen(String codigo, Bitmap bitmap){
        //obtenemos permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);
        byte[] blob = baos.toByteArray();

        //Insertamos los datos en la tabla
        String query = "INSERT INTO '" + ImagenDB.TABLE.TABLE_NAME + "' (codigo, bitmap) VALUES(?,?)";
        SQLiteStatement insert = db.compileStatement(query);
        insert.clearBindings();
        insert.bindString(1, codigo);
        insert.bindBlob(2, blob);
        insert.executeInsert();

        //Cerramos la conexion con la base de datos
        db.close();
    }

    public Bitmap getImagen(String codigo){
        //obtenemos permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        //db.execSQL("SELECT FROM '" + ImagenDB.TABLE.TABLE_NAME + "' WHERE codigo = '" + codigo + "'");
        //Insertamos los datos en la tabla
        String query = String.format("SELECT * FROM '" + ImagenDB.TABLE.TABLE_NAME + "' WHERE codigo = %d", codigo);
        Cursor cursor = db.rawQuery(query, new String[]{});
        Bitmap bitmap = null;
        if(cursor.moveToFirst()){
            byte[] blob = cursor.getBlob(1);
            ByteArrayInputStream bais = new ByteArrayInputStream(blob);
            bitmap = BitmapFactory.decodeStream(bais);
        }
        if(cursor != null && !cursor.isClosed()){
            cursor.close();
        }

        //Cerramos la conexion con la base de datos
        db.close();
        return bitmap;
    }

    public List<Imagen> getAllImagenes(){
        //Creamos una Array para llenarlo con los articulos que tengamos en la BD
        List imagenes = new ArrayList();
        //Metemos en un String la query que queremos ejecutar
        String query = "SELECT * FROM '" + ImagenDB.TABLE.TABLE_NAME + "'";
        //Obtenemos permisos de escritura y ejecutamos la query
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        //Revisamos lso registros y agregamos al array
        Imagen imagen = null;
        if(cursor.moveToFirst()){
            do{
                imagen = new Imagen();
                imagen.setCodigo(cursor.getString(0));
                byte [] blob = cursor.getBlob(1);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(blob);
                Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
                imagen.setBitmap(bitmap);

                // Añadimos los articulos a la lista de articulos
                imagenes.add(imagen);
            }while (cursor.moveToNext());
        }

        //Cerramos el cursor
        cursor.close();
        db.close();
        //Devolvemos los articulos encontrados
        return imagenes;
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
        //values.put(ArticulosDB.TABLE.IMAGE, articulo.getBitmap());
        values.put(ArticulosDB.TABLE.DESCRIPCION, articulo.getDescripcion());
        values.put(ArticulosDB.TABLE.CODIGO, articulo.getCodigo());
        values.put(ArticulosDB.TABLE.CANTIDAD, articulo.getCantidad());
        values.put(ArticulosDB.TABLE.SEXO, articulo.getSexo());
        values.put(ArticulosDB.TABLE.PRECIO, articulo.getPrecio());

        //Insertamos los datos en la tabla
        db.insert(ArticulosDB.TABLE.TABLE_NAME, null, values);

        //Cerramos la conexion con la base de datos
        db.close();
    }

    public void updateCantCarrito(Articulo articulo, int cant){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE'" + CompraDB.TABLE.TABLE_NAME + "' SET cantidad = '" + cant + "' WHERE codigo = '" + articulo.getCodigo() + "'";
        db.execSQL(query);

        db.close();
    }

    public void updateCantArticulo(Articulo articulo, int cant){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE'" + ArticulosDB.TABLE.TABLE_NAME + "' SET cantidad = '" + cant + "' WHERE codigo = '" + articulo.getCodigo() + "'";
        db.execSQL(query);

        db.close();
    }

    public void compraFinal(List<Articulo> listaCompra){
        //obtenemos permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        for (Articulo carrito : listaCompra){
            db.execSQL("DELETE FROM '" + CompraDB.TABLE.TABLE_NAME + "' WHERE codigo = '" + carrito.getCodigo() + "'");
        }
        //Cerramos la conexion con la base de datos
        db.close();
    }

    public void deleteArticulo(Articulo articulo){
        //obtenemos permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM '" + ArticulosDB.TABLE.TABLE_NAME + "' WHERE codigo = '" + articulo.getCodigo() + "'");

        //Cerramos la conexion con la base de datos
        db.close();
    }

    public void deleteArticuloFromCarrito(Articulo articulo){
        //obtenemos permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM '" + CompraDB.TABLE.TABLE_NAME + "' WHERE codigo = '" + articulo.getCodigo() + "'";
        db.execSQL(query);

        //Cerramos la conexion con la base de datos
        db.close();
    }

    public List<Articulo> getArticuloByCodigo(String codigo){
        //Creamos una Array para llenarlo con los articulos que tengamos en la BD
        List articulos = new ArrayList();
        //Metemos en un String la query que queremos ejecutar
        String query = "SELECT * FROM '" + ArticulosDB.TABLE.TABLE_NAME + "' WHERE codigo LIKE '%" + codigo + "%'";
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
        String query = "SELECT * FROM '" + ArticulosDB.TABLE.TABLE_NAME + "'";
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
        String query = "SELECT * FROM '" + CompraDB.TABLE.TABLE_NAME + "'";
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
