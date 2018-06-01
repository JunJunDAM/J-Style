package com.example.alu2015059.jstyle.Repository;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.BaseColumns;
import android.widget.ImageView;

/**
 * Created by alu2015059 on 26/02/2018.
 */

public class ArticulosDB {

    public static class ARTICULOS {
        public static final String TABLE_NAME = "ARTICULOS";
        public static final String DESCRIPCION = "descripcion";
        public static final String CODIGO = "codigo";
        public static final String CANTIDAD = "cantidad";
        public static final String SEXO = "sexo";
        public static final String PRECIO = "precio";
    }

    public static final String ARTICULOS_CREATE_TABLE =
            "CREATE TABLE " + ARTICULOS.TABLE_NAME + " (" +
                    ARTICULOS.DESCRIPCION + " TEXT, " +
                    ARTICULOS.CODIGO + " TEXT, " +
                    ARTICULOS.CANTIDAD + " INTEGER, " +
                    ARTICULOS.SEXO + " TEXT, " +
                    ARTICULOS.PRECIO + " DOUBLE);";

    public static final String ARTICULOS_CREATETABLE_IFNOTEXISTS =
            "CREATE TABLE IF NOT EXISTS " + ARTICULOS.TABLE_NAME + " (" +
                    ARTICULOS.DESCRIPCION + " TEXT, " +
                    ARTICULOS.CODIGO + " TEXT, " +
                    ARTICULOS.CANTIDAD + " INTEGER, " +
                    ARTICULOS.SEXO + " TEXT, " +
                    ARTICULOS.PRECIO + " DOUBLE);";

    public static final String ARTICULOS_DROPTABLE = "DROP TABLE IF EXISTS " + ARTICULOS.TABLE_NAME;

    public static class ARTICULO implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" + Database.AUTHORITY + "/ARTICULOS");
        public static final String DESCRIPCION = "descripcion";
        public static final String CODIGO = "codigo";
        public static final String CANTIDAD = "cantidad";
        public static final String SEXO = "sexo";
        public static final String PRECIO = "precio";
    }
}
