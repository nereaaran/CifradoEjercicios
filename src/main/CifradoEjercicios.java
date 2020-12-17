/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Utilidades.Utilidades;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author 2dam
 */
public class CifradoEjercicios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //Texto que se va a "digerir"
        String mensajeOriginal = "Esto es un texto plano";
        //Texto original despues de ser hasheado
        String mensajeDigerido = null;
        byte[] salt = AlgoritmoDeResumen.getSalt();

        int opc = 0;
        do {
            opc = menu();
            switch (opc) {
                case 1:
                    AlgoritmoDeResumen.cifrarSHA(mensajeOriginal, salt);
                    System.out.println("Mensaje resumen SHA: " + mensajeDigerido);
                    break;
                case 2:
                    mensajeDigerido = AlgoritmoDeResumen.cifrarMD5(mensajeOriginal);
                    System.out.println("Mensaje resumen MD5: " + mensajeDigerido);
                    break;
                case 3:
                    mensajeDigerido = AlgoritmoDeResumen.cifrarMD5ConSalt(mensajeOriginal, salt);
                    System.out.println("Mensaje resumen MD5 con salt: " + mensajeDigerido);
                    break;
            }
        } while (opc != 15);
    }

    private static int menu() {
        int resp = 0;

        System.out.println("\n1. Algoritmo resumen: SHA."
                + "\n2. Algoritmo resumen: MD5."
                + "\n3. Algoritmo resumen: MD5 con salt.");
        System.out.println("\nElige tu opción: ");
        resp = Utilidades.leerInt();
        return resp;
    }
}
