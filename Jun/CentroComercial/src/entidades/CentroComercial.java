package entidades;

/**
 *
 * @author Sergio
 */
public class CentroComercial {
    
    private int centroID;
    private String nombreCentro;
    private int numLocales;

    //constructor vacio
    public CentroComercial() {
    }

    public CentroComercial(int centroID, String nombreCentro) {
        this.centroID = centroID;
        this.nombreCentro = nombreCentro;
        
    }

    public int getCentroID() {
        return centroID;
    }

    public void setCentroID(int centroID) {
        this.centroID = centroID;
    }

    

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public int getNumLocales() {
        return numLocales;
    }

    public void setNumLocales(int numLocales) {
        this.numLocales = numLocales;
    }
    
    
    
    

}
