package com.bisai.bisai.model;

/**
 * Created by sergi on 27/03/2017.
 */

public class Local {

    private Long id;
    private String nombre;
    private String urlLocalizacion;

    public Local(Long id, String urlLocalizacion, String nombre) {
        this.id = id;
        this.urlLocalizacion = urlLocalizacion;
        this.nombre = nombre;
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

    public String getUrlLocalizacion() {
        return urlLocalizacion;
    }

    public void setUrlLocalizacion(String urlLocalizacion) {
        this.urlLocalizacion = urlLocalizacion;
    }






    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Local local = (Local) o;

        if (!id.equals(local.id)) return false;
        if (!nombre.equals(local.nombre)) return false;
        return urlLocalizacion.equals(local.urlLocalizacion);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nombre.hashCode();
        result = 31 * result + urlLocalizacion.hashCode();
        return result;
    }
}
