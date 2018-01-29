package aplicacion;

import entidades.CentroComercial;
import entidades.Local;
import entidades.TipoLocal;
import java.util.ArrayList;
/**
 *
 * @author Sergio
 */
public class Principal {

    private static ArrayList<CentroComercial> centros = new ArrayList<>();
    private static ArrayList<Local> locales = new ArrayList<>();
    //inicializamos los locales con el constructor por defecto
    private static Local bar = new Local();
    private static Local ropa = new Local();
    private static Local libreria = new Local();
    private static Local restaurante1 = new Local();
    private static Local restaurante2 = new Local();
    private static Local sport = new Local();
    private static int numLocales;
    private static double impuesto;

    public static void main(String[] args) {

        //creamos centro comercial
        CentroComercial centro = new CentroComercial(1, "La maquinista");

        centros.add(centro);

        
        //creamos los 5 locales, utilizando downcasting
        bar = new Local(TipoLocal.BAR, 1, "Sureña", "De 10:00 a 23:00", 50, 520);

        locales.add(bar);

        ropa = new Local(TipoLocal.MIXTOS, 1, "Bershka", "10:00 a 23:00", 150, 650);

        locales.add(ropa);

        libreria = new Local(TipoLocal.LIBRERIA, 1, "Abacus", "10:00 a 23:00", 20, 1000);

        locales.add(libreria);

        restaurante1 = new Local(TipoLocal.RESTAURANTE, 1, "Brasa y Leña", "De 10:00 a 23:00", 90, 760);

        restaurante2 = new Local(TipoLocal.RESTAURANTE, 1, "McDonalds", "10:00 a 23:00", 60, 840);

        locales.add(restaurante1);
        locales.add(restaurante2);

        sport = new Local(TipoLocal.DEPORTE, 1, "Decimas", "10:00 a 23:00", 50, 1500);

        locales.add(sport);

        mostrarCentro();
        mostrarLocales();

    }

    public static void mostrarCentro() {

        int idCentro = 0;

        for (CentroComercial centroAux : centros) {
            idCentro = centroAux.getCentroID();

            System.out.println("Centro: " + centroAux.getNombreCentro());

            for (Local localAux : locales) {
                //si el id del local coincide con el id del centro
                //mostramos los datos del local
                if (localAux.getCentroID() == idCentro) {

                    numLocales++;

                }
            }

            System.out.println("Cantidad Locales: " + numLocales);

        }
    }

    public static void mostrarLocales() {

        int idCentro = 0;

        for (CentroComercial centroAux : centros) {
            idCentro = centroAux.getCentroID();
            System.out.println("Locales: ");
            for (Local localAux : locales) {
                //si el id del local coincide con el id del centro
                //mostramos los datos del local

                if (localAux.getCentroID() == idCentro) {

                    impuesto = calcularImpuestos(localAux.getTipoLocal(), localAux);

                    System.out.println("        " + localAux.getNombreLocal() + " - " + localAux.getTipoLocal() + " -> Impuestos: " + impuesto);

                }
            }

        }

    }

    public static double calcularImpuestos(TipoLocal local, Local localAux) {

        switch (local) {
            case BAR:
                impuesto = 1.2 * localAux.getMetrosCuadrados();

                return impuesto;

            case RESTAURANTE:
                impuesto = 1.3 * localAux.getMetrosCuadrados();

                return impuesto;
            case LIBRERIA:

                impuesto = 1.0 * localAux.getMetrosCuadrados();

                return impuesto;
            case MUJERES:
                impuesto = 0.9 * localAux.getMetrosCuadrados();

                return impuesto;
            case HOMBRES:
                impuesto = 1.1 * localAux.getMetrosCuadrados();

                return impuesto;
            case MIXTOS:
                impuesto = 1.0 * localAux.getMetrosCuadrados();

                return impuesto;
            case DEPORTE:
                impuesto = 1.4 * localAux.getMetrosCuadrados();

                return impuesto;
            default:

                break;
        }

        //devolvemos 0 en caso de fallo
        return 0;
    }

}
