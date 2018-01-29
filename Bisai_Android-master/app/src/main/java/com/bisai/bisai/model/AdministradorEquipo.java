package com.bisai.bisai.model;

import java.util.Date;

/**
 * Created by sergi on 05/04/2017.
 */

public class AdministradorEquipo {

    private Long id;
    private Date hora;
    private Permiso permiso;
    private Equipo equipo;
    private Jugador jugador;

    public AdministradorEquipo(Long id, Jugador jugador, Equipo equipo, Permiso permiso, Date hora) {
        this.id = id;
        this.jugador = jugador;
        this.equipo = equipo;
        this.permiso = permiso;
        this.hora = hora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdministradorEquipo that = (AdministradorEquipo) o;

        if (!id.equals(that.id)) return false;
        if (!hora.equals(that.hora)) return false;
        if (permiso != that.permiso) return false;
        if (!equipo.equals(that.equipo)) return false;
        return jugador.equals(that.jugador);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + hora.hashCode();
        result = 31 * result + permiso.hashCode();
        result = 31 * result + equipo.hashCode();
        result = 31 * result + jugador.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
