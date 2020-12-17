/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Utilidades.Utilidades;

/**
 *
 * @author 2dam
 */
public class CifradoEjercicios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opc = 0;

        do {
            opc = menu();

            switch (opc) {
                case 1:
                    AlgoritmoDeResumen.cifrarSHA1();
                    break;
                case 2:
                    AlgoritmoDeResumen.cifrarSHA256();
                    break;
                case 3:

                    break;
            }

        } while (opc != 4);
    }

    private static int menu() {
        int resp = 0;

        System.out.println("\n1. Algoritmo de resumen: SHA-1."
                + "\n2. Algoritmo de resumen: SHA-256."
                + "\n3. ."
                + "\n4. Salir.");
        System.out.println("\nElige tu opción: ");
        resp = Utilidades.leerInt();
        return resp;
    }
}
