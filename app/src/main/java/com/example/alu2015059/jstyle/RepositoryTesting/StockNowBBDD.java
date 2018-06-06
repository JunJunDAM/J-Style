package com.example.alu2015059.jstyle.RepositoryTesting;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseArray;

import com.example.alu2015059.jstyle.Repository.ArticulosDB;
import com.example.alu2015059.jstyle.Repository.CompraDB;
import com.example.alu2015059.jstyle.Repository.Database;

/**
 * Created by alu2015059 on 01/06/2018.
 */

public class StockNowBBDD extends ContentProvider{

    private static final int ARTICULO = 1;
    private static final int ALL_ARTICULOS = 2;

    private static final int COMPRA = 3;
    private static final int CARRITO = 4;

    private SQLiteDatabase sqLiteDatabase;
    public DBHelper dbHelper;

    private static final UriMatcher URI_MATCHER;
    private  static final SparseArray<String> MIME_TYPES;

    static {
        URI_MATCHER = new UriMatcher(0);
        MIME_TYPES = new SparseArray<String>();

        URI_MATCHER.addURI(
                Database.AUTHORITY,
                ArticulosDB.TABLE.TABLE_NAME,
                ALL_ARTICULOS);

        URI_MATCHER.addURI(
                Database.AUTHORITY,
                ArticulosDB.TABLE.TABLE_NAME + "/#",
                ARTICULO);

        URI_MATCHER.addURI(
                Database.AUTHORITY,
                CompraDB.TABLE.TABLE_NAME,
                CARRITO);

        URI_MATCHER.addURI(
                Database.AUTHORITY,
                CompraDB.TABLE.TABLE_NAME + "/#",
                COMPRA);

        MIME_TYPES.put(
                ALL_ARTICULOS, "vnd.android.cursor.dir/vnd." +
                        Database.AUTHORITY + "." + ArticulosDB.TABLE.TABLE_NAME);

        MIME_TYPES.put(
                ARTICULO, "vnd.android.cursor.item/vnd." +
                        Database.AUTHORITY + "." + ArticulosDB.TABLE.TABLE_NAME);

        MIME_TYPES.put(
                CARRITO, "vnd.android.cursor.dir/vnd." +
                        Database.AUTHORITY + "." + CompraDB.TABLE.TABLE_NAME);

        MIME_TYPES.put(
                COMPRA, "vnd.android.cursor.item/vnd." +
                        Database.AUTHORITY + "." + CompraDB.TABLE.TABLE_NAME);
    }

    public static class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context){
            super(context, Database.DATABASE_NAME, null, Database.DATABASE_VERSION);
        }

        @Override
        public void onOpen(SQLiteDatabase sqLiteDatabase){
            super.onOpen(sqLiteDatabase);
            //sqLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(ArticulosDB.ARTICULOS_CREATE_TABLE);
            sqLiteDatabase.execSQL(CompraDB.COMPRA_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(ArticulosDB.ARTICULOS_DROPTABLE);
            sqLiteDatabase.execSQL(CompraDB.COMPRA_DROPTABLE);
            onCreate(sqLiteDatabase);
        }
    }

    public StockNowBBDD(){

    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return (dbHelper == null) ? false : true;
    }

    public void resetDataBase(){
        dbHelper.close();
        dbHelper = new DBHelper(getContext());
    }

    @Override
    public synchronized Cursor query( Uri uri, String[] columnas, String selecion, String[] strings, String order) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "";

        switch (URI_MATCHER.match(uri)){
            case ARTICULO:
                if(null == selecion) selecion = "";
                selecion += ArticulosDB.ARTICULO.CODIGO + " = " + uri.getLastPathSegment();
                queryBuilder.setTables(ArticulosDB.TABLE.TABLE_NAME);
                break;
            case ALL_ARTICULOS:
                if(TextUtils.isEmpty(order)) order = ArticulosDB.ARTICULO.CODIGO + " ASC";
                queryBuilder.setTables(ArticulosDB.TABLE.TABLE_NAME);
                break;
            case COMPRA:
                if(null == selecion) selecion = "";
                selecion += CompraDB.CARRITO.CODIGO + " = " + uri.getLastPathSegment();
                queryBuilder.setTables(CompraDB.TABLE.TABLE_NAME);
                break;
            case CARRITO:
                if(TextUtils.isEmpty(order)) order = CompraDB.CARRITO.CODIGO + " ASC";
                queryBuilder.setTables(CompraDB.TABLE.TABLE_NAME);
                break;
        }

        Cursor cursor;
        cursor = queryBuilder.query(db, columnas, selecion, strings, null, null, order);
        return cursor;
    }

    @Override
    public String getType( Uri uri) {
        return null;
    }

    @Override
    public synchronized Uri insert( Uri uri, ContentValues contentValues) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        String table = "";

        switch (URI_MATCHER.match(uri)){
            case ALL_ARTICULOS:
                table = ArticulosDB.TABLE.TABLE_NAME;
                break;
            case CARRITO:
                table = CompraDB.TABLE.TABLE_NAME;
                break;
        }

        long rowID = sqLiteDatabase.insert(table, "", contentValues);

        if(rowID > 0){
            Uri rowURI = ContentUris.appendId(uri.buildUpon(), rowID).build();
            if (table != ArticulosDB.TABLE.TABLE_NAME) getContext().getContentResolver().notifyChange(rowURI, null);
            return rowURI;
        }throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public synchronized int delete( Uri uri, String s, String[] strings) {
       sqLiteDatabase = dbHelper.getWritableDatabase();

       String table = "";
       switch (URI_MATCHER.match(uri)){
           case ARTICULO:
               if(null == s) s = "";
               s += ArticulosDB.ARTICULO.CODIGO + " = " + uri.getLastPathSegment();
               table = ArticulosDB.TABLE.TABLE_NAME;
               break;
           case ALL_ARTICULOS:
               table = ArticulosDB.TABLE.TABLE_NAME;
               break;
           case COMPRA:
               if(null == s) s = "";
               s += CompraDB.CARRITO.CODIGO + " = " + uri.getLastPathSegment();
               table = CompraDB.TABLE.TABLE_NAME;
               break;
           case CARRITO:
               table = CompraDB.TABLE.TABLE_NAME;
               break;
       }
       int rows = sqLiteDatabase.delete(table, s, strings);
       if(rows > 0){
           if(table != ArticulosDB.TABLE.TABLE_NAME) getContext().getContentResolver().notifyChange(uri, null);
           return rows;
       }throw new SQLException("Failed to delete row into " + uri);
    }

    @Override
    public synchronized int update( Uri uri, ContentValues contentValues, String s, String[] strings) {
        sqLiteDatabase = dbHelper.getWritableDatabase();

        String table = "";
        switch (URI_MATCHER.match(uri)){
            case ARTICULO:
                if(null == s) s = "";
                s += ArticulosDB.ARTICULO.CODIGO + " = " + uri.getLastPathSegment();
                table = ArticulosDB.TABLE.TABLE_NAME;
                break;
            case ALL_ARTICULOS:
                table = ArticulosDB.TABLE.TABLE_NAME;
                break;
            case COMPRA:
                if(null == s) s = "";
                s += CompraDB.CARRITO.CODIGO + " = " + uri.getLastPathSegment();
                table = CompraDB.TABLE.TABLE_NAME;
                break;
            case CARRITO:
                table = CompraDB.TABLE.TABLE_NAME;
                break;
        }

        int rows = sqLiteDatabase.update(table, contentValues, s, strings);
        if(rows > 0){
            if(table != ArticulosDB.TABLE.TABLE_NAME) getContext().getContentResolver().notifyChange(uri, null);
            return rows;
        }throw new SQLException("Failed to update Row into " + uri);
    }
}
