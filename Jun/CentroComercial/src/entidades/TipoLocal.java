/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Sergio
 */
public enum TipoLocal {
    
    BAR, RESTAURANTE, MUJERES, HOMBRES, MIXTOS, LIBRERIA, DEPORTE;

    public static TipoLocal getBAR() {
        return BAR;
    }

    public static TipoLocal getRESTAURANTE() {
        return RESTAURANTE;
    }
    
    public static TipoLocal getMUJERES() {
        return MUJERES;
    }

    public static TipoLocal getHOMBRES() {
        return HOMBRES;
    }

    public static TipoLocal getMIXTOS() {
        return MIXTOS;
    }

    public static TipoLocal getLIBRERIA() {
        return LIBRERIA;
    }
    
    
}
