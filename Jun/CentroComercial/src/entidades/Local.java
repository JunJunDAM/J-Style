package entidades;

/**
 *
 * @author Sergio
 */
public class Local {

    //Variables que van a tener en común todas las clases
    private TipoLocal tipoLocal;
    private int centroID;
    private String nombreLocal;
    private String horario;//cambiar string a formato de fecha
    private int numEmpleados;
    private double metrosCuadrados;

    public Local() {
    }

    public Local(TipoLocal tipoLocal, int centroID, String nombreLocal, String horario, int numEmpleados, double metrosCuadrados) {
        this.tipoLocal = tipoLocal;
        this.centroID = centroID;
        this.nombreLocal = nombreLocal;
        this.horario = horario;
        this.numEmpleados = numEmpleados;
        this.metrosCuadrados = metrosCuadrados;
    }

    public TipoLocal getTipoLocal() {
        return tipoLocal;
    }

    public void setTipoLocal(TipoLocal tipoLocal) {
        this.tipoLocal = tipoLocal;
    }

    public int getCentroID() {
        return centroID;
    }

    public void setCentroID(int centroID) {
        this.centroID = centroID;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getNumEmpleados() {
        return numEmpleados;
    }

    public void setNumEmpleados(int numEmpleados) {
        this.numEmpleados = numEmpleados;
    }

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

}
