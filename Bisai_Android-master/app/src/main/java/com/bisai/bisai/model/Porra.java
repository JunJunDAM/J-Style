package com.bisai.bisai.model;

/**
 * Created by sergi on 05/04/2017.
 */

public class Porra {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Double cantidad;
    private Integer eleccion;
    private Jugador jugador;
    private Partida partida;


    public Porra(Long id, Partida partida, Double cantidad, Integer eleccion, Jugador jugador) {
        this.id = id;
        this.partida = partida;
        this.cantidad = cantidad;
        this.eleccion = eleccion;
        this.jugador = jugador;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Integer getEleccion() {
        return eleccion;
    }

    public void setEleccion(Integer eleccion) {
        this.eleccion = eleccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
}
