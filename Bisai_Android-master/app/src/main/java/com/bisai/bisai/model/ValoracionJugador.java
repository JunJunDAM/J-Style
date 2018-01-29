package com.bisai.bisai.model;

import java.util.Date;

/**
 * Created by sergi on 05/04/2017.
 */

public class ValoracionJugador {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Date hora;
    private Boolean meGusta;
    private Jugador jugadorValorado;
    private Jugador jugadorValorador;

    public ValoracionJugador(Long id, Date hora, Jugador jugadorValorador, Jugador jugadorValorado, Boolean meGusta) {
        this.id = id;
        this.hora = hora;
        this.jugadorValorador = jugadorValorador;
        this.jugadorValorado = jugadorValorado;
        this.meGusta = meGusta;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Jugador getJugadorValorador() {
        return jugadorValorador;
    }

    public void setJugadorValorador(Jugador jugadorValorador) {
        this.jugadorValorador = jugadorValorador;
    }

    public Jugador getJugadorValorado() {
        return jugadorValorado;
    }

    public void setJugadorValorado(Jugador jugadorValorado) {
        this.jugadorValorado = jugadorValorado;
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

        ValoracionJugador that = (ValoracionJugador) o;

        if (!id.equals(that.id)) return false;
        if (!hora.equals(that.hora)) return false;
        if (!meGusta.equals(that.meGusta)) return false;
        if (!jugadorValorado.equals(that.jugadorValorado)) return false;
        return jugadorValorador.equals(that.jugadorValorador);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + hora.hashCode();
        result = 31 * result + meGusta.hashCode();
        result = 31 * result + jugadorValorado.hashCode();
        result = 31 * result + jugadorValorador.hashCode();
        return result;
    }
}
