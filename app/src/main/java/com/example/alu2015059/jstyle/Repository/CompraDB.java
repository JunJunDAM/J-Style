package com.example.alu2015059.jstyle.Repository;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by alu2015059 on 28/05/2018.
 */

public class CompraDB {
    public static class COMPRA {
        public static final String TABLE_NAME = "COMPRA";
        public static final String DESCRIPCION = "descripcion";
        public static final String CODIGO = "codigo";
        public static final String CANTIDAD = "cantidad";
        public static final String SEXO = "sexo";
        public static final String PRECIO = "precio";
    }

    public static final String COMPRA_CREATE_TABLE =
            "CREATE TABLE " + CompraDB.COMPRA.TABLE_NAME + " (" +
                    CompraDB.COMPRA.DESCRIPCION + " TEXT, " +
                    CompraDB.COMPRA.CODIGO + " TEXT, " +
                    CompraDB.COMPRA.CANTIDAD + " INTEGER, " +
                    CompraDB.COMPRA.SEXO + " TEXT, " +
                    CompraDB.COMPRA.PRECIO + " DOUBLE);";

    public static final String COMPRA_CREATETABLE_IFNOTEXISTS =
            "CREATE TABLE IF NOT EXISTS " + COMPRA.TABLE_NAME  + " (" +
                    CompraDB.COMPRA.DESCRIPCION + " TEXT, " +
                    CompraDB.COMPRA.CODIGO + " TEXT, " +
                    CompraDB.COMPRA.CANTIDAD + " INTEGER, " +
                    CompraDB.COMPRA.SEXO + " TEXT, " +
                    CompraDB.COMPRA.PRECIO + " DOUBLE);";

    public static final String COMPRA_DROPTABLE = "DROP TABLE IF EXISTS " + CompraDB.COMPRA.TABLE_NAME;

    public static class CARRITO implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" + Database.AUTHORITY + "/COMPRA");
        public static final String DESCRIPCION = "descripcion";
        public static final String CODIGO = "codigo";
        public static final String CANTIDAD = "cantidad";
        public static final String SEXO = "sexo";
        public static final String PRECIO = "precio";
    }
}
