package com.example.alu2015059.jstyle.Repository;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by alu2015059 on 24/04/2018.
 */

public class Database {
    public static final String DATABASE_NAME = "StockNow";
    public static final int DATABASE_VERSION = 1;
    public static final String AUTHORITY = "com.example.alu2015059.jstyle.RepositoryTesting.StockNowBBDD";
    public static final String WEB_SERVER_PATH = "";

    public static class ARTICULO implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" + Database.AUTHORITY + "/ARTICULO");
        public static final String DESCRIPCION = "descripcion";
        public static final String CODIGO = "codigo";
        public static final String CANTIDAD = "cantidad";
        public static final String SEXO = "sexo";
        public static final String PRECIO = "precio";
    }

    public static class ARTICULO_SYNC implements  BaseColumns{
        public static final Uri CONTENT_URI = Uri.parse("content://" + Database.AUTHORITY + "/ARTICULOSYNC");

        public static final String CODIGO = "codigo";
        public static final String OPERACION = "operacion";
    }

    public static class CARRITO implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" + Database.AUTHORITY + "/COMPRA");
        public static final String DESCRIPCION = "descripcion";
        public static final String CODIGO = "codigo";
        public static final String CANTIDAD = "cantidad";
        public static final String SEXO = "sexo";
        public static final String PRECIO = "precio";
    }
}
