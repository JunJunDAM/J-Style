package com.example.alu2015059.jstyle.Domain;

public class ArticuloSYNC {
    private String codigo;
    private int operacion;

    public ArticuloSYNC(){}

    public ArticuloSYNC(String codigo, int operacion) {
        this.codigo = codigo;
        this.operacion = operacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }
}
