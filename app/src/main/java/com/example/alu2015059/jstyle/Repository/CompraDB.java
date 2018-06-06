package com.example.alu2015059.jstyle.Repository;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by alu2015059 on 28/05/2018.
 */

public class CompraDB {
    public static class TABLE {
        public static final String TABLE_NAME = "COMPRA";
        public static final String DESCRIPCION = "descripcion";
        public static final String CODIGO = "codigo";
        public static final String CANTIDAD = "cantidad";
        public static final String SEXO = "sexo";
        public static final String PRECIO = "precio";
    }

    public static final String COMPRA_CREATE_TABLE =
            "CREATE TABLE " + TABLE.TABLE_NAME + " (" +
                    TABLE.DESCRIPCION + " TEXT, " +
                    TABLE.CODIGO + " TEXT, " +
                    TABLE.CANTIDAD + " INTEGER, " +
                    TABLE.SEXO + " TEXT, " +
                    TABLE.PRECIO + " DOUBLE);";

    public static final String COMPRA_CREATETABLE_IFNOTEXISTS =
            "CREATE TABLE IF NOT EXISTS " + TABLE.TABLE_NAME  + " (" +
                    TABLE.DESCRIPCION + " TEXT, " +
                    TABLE.CODIGO + " TEXT, " +
                    TABLE.CANTIDAD + " INTEGER, " +
                    TABLE.SEXO + " TEXT, " +
                    TABLE.PRECIO + " DOUBLE);";

    public static final String COMPRA_DROPTABLE = "DROP TABLE IF EXISTS " + TABLE.TABLE_NAME;

    public static class CARRITO implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" + Database.AUTHORITY + "/TABLE");
        public static final String DESCRIPCION = "descripcion";
        public static final String CODIGO = "codigo";
        public static final String CANTIDAD = "cantidad";
        public static final String SEXO = "sexo";
        public static final String PRECIO = "precio";
    }
}
