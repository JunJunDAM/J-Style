package com.bisai.bisai.model;

import java.util.Arrays;

/**
 * Created by sergi on 27/03/2017.
 */

public class Juego {

    private Long id;
    private String nombre;
    private String url;
    private byte[] foto;
    private String fotoContentType;
    private byte[] qr;
    private String qrContentType;


    public Juego(Long id, String qrContentType, byte[] qr, String fotoContentType, byte[] foto, String url, String nombre) {
        this.id = id;
        this.qrContentType = qrContentType;
        this.qr = qr;
        this.fotoContentType = fotoContentType;
        this.foto = foto;
        this.url = url;
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Juego juego = (Juego) o;

        if (!id.equals(juego.id)) return false;
        if (!nombre.equals(juego.nombre)) return false;
        if (!url.equals(juego.url)) return false;
        if (!Arrays.equals(foto, juego.foto)) return false;
        if (!fotoContentType.equals(juego.fotoContentType)) return false;
        if (!Arrays.equals(qr, juego.qr)) return false;
        return qrContentType.equals(juego.qrContentType);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nombre.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + Arrays.hashCode(foto);
        result = 31 * result + fotoContentType.hashCode();
        result = 31 * result + Arrays.hashCode(qr);
        result = 31 * result + qrContentType.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public String getQrContentType() {
        return qrContentType;
    }

    public void setQrContentType(String qrContentType) {
        this.qrContentType = qrContentType;
    }

    public byte[] getQr() {
        return qr;
    }

    public void setQr(byte[] qr) {
        this.qr = qr;
    }
}
