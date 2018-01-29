package com.bisai.bisai.model;

import java.util.Arrays;

/**
 * Created by sergi on 05/04/2017.
 */

public class Clasificacion {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String url;
    private Integer resultado;
    private byte[] foto;
    private String fotoContentType;
    private Integer ranking;
    private Torneo torneo;
    private Equipo equipo;

    public Clasificacion(String url, Integer resultado, byte[] foto, String fotoContentType, Integer ranking, Torneo torneo, Equipo equipo, Long id) {
        this.url = url;
        this.resultado = resultado;
        this.foto = foto;
        this.fotoContentType = fotoContentType;
        this.ranking = ranking;
        this.torneo = torneo;
        this.equipo = equipo;
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

        Clasificacion that = (Clasificacion) o;

        if (!id.equals(that.id)) return false;
        if (!url.equals(that.url)) return false;
        if (!resultado.equals(that.resultado)) return false;
        if (!Arrays.equals(foto, that.foto)) return false;
        if (!fotoContentType.equals(that.fotoContentType)) return false;
        if (!ranking.equals(that.ranking)) return false;
        if (!torneo.equals(that.torneo)) return false;
        return equipo.equals(that.equipo);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + resultado.hashCode();
        result = 31 * result + Arrays.hashCode(foto);
        result = 31 * result + fotoContentType.hashCode();
        result = 31 * result + ranking.hashCode();
        result = 31 * result + torneo.hashCode();
        result = 31 * result + equipo.hashCode();
        return result;
    }
}
