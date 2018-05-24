package com.example.alu2015059.jstyle.Domain;

/**
 * Created by alu2015059 on 26/02/2018.
 */

public class Articulo {
    private String descripcion;
    private String codigo;
    private Integer cantidad;
    private String sexo;
    private Double precio;

    public Articulo() {
    }

    public Articulo(String descripcion, String codigo, Integer cantidad, String sexo, Double precio) {
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.sexo = sexo;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "descripcion='" + descripcion + '\'' +
                ", codigo='" + codigo + '\'' +
                ", cantidad=" + cantidad +
                ", sexo='" + sexo + '\'' +
                ", precio=" + precio +
                '}';
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
