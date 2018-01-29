/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.academica;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alu2015059
 */
public class GestionAcademica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Secretaria secretaria = new Secretaria();

        List listaAlumnos = new ArrayList();
        List listaAsignaturas = new ArrayList();

        Alumno alumno1 = new Alumno("12345678C", "Paco");
        Alumno alumno2 = new Alumno("32654987Z", "Juan");
        Alumno alumno3 = new Alumno("45678912D", "Miguel");

        listaAlumnos.add(alumno1);
        listaAlumnos.add(alumno2);
        listaAlumnos.add(alumno3);

        Asignaturas asignatura1 = new Asignaturas("Programacion", "123D456", alumno1);
        Asignaturas asignatura2 = new Asignaturas("Mates", "123d456", alumno2);
        Asignaturas asignatura3 = new Asignaturas("Base de datos", "789a456", alumno3);

        listaAsignaturas.add(asignatura1);
        listaAsignaturas.add(asignatura2);
        listaAsignaturas.add(asignatura3);

        Departamento departamentoAlumnos = new Departamento(listaAlumnos, listaAsignaturas);

        System.out.println("Alumnos: " + secretaria.muestaAlumnos(listaAlumnos));

        System.out.println("Asignaturas: " + secretaria.muestaAsignaturas(listaAsignaturas));

    }

    private static String pedirCadena(String mensaje) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String respuesta = "";
        boolean error;
        do {
            try {
                System.out.print(mensaje);
                respuesta = br.readLine();
                error = false;
            } catch (IOException ex) {
                System.out.println("Error de entrada/salida");
                error = true;
            }
        } while (error);
        return respuesta;
    }

    private static double pedirDouble(String mensaje) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double respuesta = 0;
        boolean error;
        do {
            try {
                System.out.print(mensaje);
                respuesta = Double.parseDouble(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error de entrada/salida");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("No has introducido un numero decimal");
                error = true;
            }
        } while (error);
        return respuesta;
    }

    private static int pedirEntero() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numero = 0;
        boolean error = false;
        do {
            try {
                numero = Integer.parseInt(br.readLine());
            } catch (IOException ex) {
                System.out.println("Error de entrada y salida");
            } catch (NumberFormatException ex) {
                System.out.println("No has introducido un nº entero.");
            }
        } while (error);
        return numero;
    }
}
