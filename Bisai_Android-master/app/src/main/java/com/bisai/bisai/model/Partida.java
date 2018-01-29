package com.bisai.bisai.model;

import java.util.Date;

/**
 * Created by sergi on 05/04/2017.
 */

public class Partida {


    private Long id;

    private Date fechaInicio;
    private Date fechaFinal;
    private Integer resultadoEquipo1;
    private Integer resultadoEquipo2;
    private Integer numRonda;
    private Integer numPartidaRonda;
    private Equipo equipo1;
    private Equipo equipo2;
    private Torneo torneo;
    private Partida siguientePartida;
    private Equipo equipoGanador;

    public Partida(Long id, Equipo equipoGanador, Partida siguientePartida, Torneo torneo, Equipo equipo2, Equipo equipo1, Integer numPartidaRonda, Integer numRonda, Integer resultadoEquipo2, Integer resultadoEquipo1, Date fechaFinal, Date fechaInicio) {
        this.id = id;
        this.equipoGanador = equipoGanador;
        this.siguientePartida = siguientePartida;
        this.torneo = torneo;
        this.equipo2 = equipo2;
        this.equipo1 = equipo1;
        this.numPartidaRonda = numPartidaRonda;
        this.numRonda = numRonda;
        this.resultadoEquipo2 = resultadoEquipo2;
        this.resultadoEquipo1 = resultadoEquipo1;
        this.fechaFinal = fechaFinal;
        this.fechaInicio = fechaInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipo getEquipoGanador() {
        return equipoGanador;
    }

    public void setEquipoGanador(Equipo equipoGanador) {
        this.equipoGanador = equipoGanador;
    }

    public Partida getSiguientePartida() {
        return siguientePartida;
    }

    public void setSiguientePartida(Partida siguientePartida) {
        this.siguientePartida = siguientePartida;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Integer getNumPartidaRonda() {
        return numPartidaRonda;
    }

    public void setNumPartidaRonda(Integer numPartidaRonda) {
        this.numPartidaRonda = numPartidaRonda;
    }

    public Integer getResultadoEquipo2() {
        return resultadoEquipo2;
    }

    public void setResultadoEquipo2(Integer resultadoEquipo2) {
        this.resultadoEquipo2 = resultadoEquipo2;
    }

    public Integer getNumRonda() {
        return numRonda;
    }

    public void setNumRonda(Integer numRonda) {
        this.numRonda = numRonda;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Integer getResultadoEquipo1() {
        return resultadoEquipo1;
    }

    public void setResultadoEquipo1(Integer resultadoEquipo1) {
        this.resultadoEquipo1 = resultadoEquipo1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Partida partida = (Partida) o;

        if (!id.equals(partida.id)) return false;
        if (!fechaInicio.equals(partida.fechaInicio)) return false;
        if (!fechaFinal.equals(partida.fechaFinal)) return false;
        if (!resultadoEquipo1.equals(partida.resultadoEquipo1)) return false;
        if (!resultadoEquipo2.equals(partida.resultadoEquipo2)) return false;
        if (!numRonda.equals(partida.numRonda)) return false;
        if (!numPartidaRonda.equals(partida.numPartidaRonda)) return false;
        if (!equipo1.equals(partida.equipo1)) return false;
        if (!equipo2.equals(partida.equipo2)) return false;
        if (!torneo.equals(partida.torneo)) return false;
        if (!siguientePartida.equals(partida.siguientePartida)) return false;
        return equipoGanador.equals(partida.equipoGanador);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + fechaInicio.hashCode();
        result = 31 * result + fechaFinal.hashCode();
        result = 31 * result + resultadoEquipo1.hashCode();
        result = 31 * result + resultadoEquipo2.hashCode();
        result = 31 * result + numRonda.hashCode();
        result = 31 * result + numPartidaRonda.hashCode();
        result = 31 * result + equipo1.hashCode();
        result = 31 * result + equipo2.hashCode();
        result = 31 * result + torneo.hashCode();
        result = 31 * result + siguientePartida.hashCode();
        result = 31 * result + equipoGanador.hashCode();
        return result;
    }
}
