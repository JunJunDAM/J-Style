package com.bisai.bisai.model;

import java.util.Date;

/**
 * Created by sergi on 05/04/2017.
 */

public class ValoracionEquipo {


    private static final long serialVersionUID = 1L;
    private Long id;
    private Date hora;
    private Boolean meGusta;
    private Equipo equipo;
    private Jugador jugador;

    public ValoracionEquipo(Long id, Jugador jugador, Equipo equipo, Date hora, Boolean meGusta) {
        this.id = id;
        this.jugador = jugador;
        this.equipo = equipo;
        this.hora = hora;
        this.meGusta = meGusta;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Boolean getMeGusta() {
        return meGusta;
    }

    public void setMeGusta(Boolean meGusta) {
        this.meGusta = meGusta;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValoracionEquipo that = (ValoracionEquipo) o;

        if (!id.equals(that.id)) return false;
        if (!hora.equals(that.hora)) return false;
        if (!meGusta.equals(that.meGusta)) return false;
        if (!equipo.equals(that.equipo)) return false;
        return jugador.equals(that.jugador);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + hora.hashCode();
        result = 31 * result + meGusta.hashCode();
        result = 31 * result + equipo.hashCode();
        result = 31 * result + jugador.hashCode();
        return result;
    }
}
