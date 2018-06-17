package com.example.alu2015059.jstyle.Domain;

import android.graphics.Bitmap;

public class Imagen {
    private String codigo;
    private Bitmap bitmap;

    public Imagen() {
    }

    public Imagen(String codigo, Bitmap bitmap) {
        this.codigo = codigo;
        this.bitmap = bitmap;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
