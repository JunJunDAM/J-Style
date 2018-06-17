package com.example.alu2015059.jstyle.Repository;

import android.net.Uri;
import android.provider.BaseColumns;

public class ImagenDB {
    public static class TABLE {
        public static final String TABLE_NAME = "IMAGENES";
        public static final String CODIGO = "codigo";
        public static final String BITMAP = "bitmap";
    }

    public static final String IMAGEN_CREATE_TABLE =
            "CREATE TABLE " + TABLE.TABLE_NAME + " (" +
                    TABLE.CODIGO + " TEXT, " +
                    TABLE.BITMAP + " BLOB);";

    public static final String IMAGEN_CREATETABLE_IFNOTEXISTS =
            "CREATE TABLE IF NOT EXISTS " + TABLE.TABLE_NAME + " (" +
                    TABLE.CODIGO + " TEXT, " +
                    TABLE.BITMAP + " BLOB);";

    public static final String IMAGEN_DROPTABLE = "DROP TABLE IF EXISTS " + ArticulosDB.TABLE.TABLE_NAME;

    public static class IMAGEN implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" + Database.AUTHORITY + "/ARTICULO");
        public static final String CODIGO = "codigo";
        public static final String BITMAP = "bitmap";
    }
}
