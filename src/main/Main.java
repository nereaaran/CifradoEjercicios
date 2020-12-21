/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Utilidades.Utilidades;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Clase principal que ejecuta los metodos de las clases de cifrado.
 *
 * @author Nerea
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //Texto original despues de ser hasheado
        String mensajeDigerido = null;
        String mensajeCifrado = null;
        String mensajeDescifrado = null;
        byte[] mensajeCifradoBytes = null;
        byte[] mensajeDescifradoBytes = null;
        byte[] salt = getSalt();

        int opc = 0;
        do {
            opc = menu();
            switch (opc) {
                case 1:
                    AlgoritmoDeResumen.cifrarSHA("Esto es un texto plano");
                    break;
                case 2:
                    mensajeDigerido = AlgoritmoDeResumen.cifrarMD5("Esto es un texto plano");
                    System.out.println("Mensaje resumen MD5: " + mensajeDigerido);
                    break;
                case 3:
                    mensajeDigerido = AlgoritmoDeResumen.cifrarMD5ConSalt("Esto es un texto plano", salt);
                    System.out.println("Mensaje resumen MD5 con salt: " + mensajeDigerido);
                    break;
                case 4:
                    ClavePrivada clavePrivada = new ClavePrivada();
                    mensajeCifrado = clavePrivada.cifrarAES("Clave", "Mensaje super secreto");
                    System.out.println("Cifrado: " + mensajeCifrado);
                    System.out.println("-----------");
                    mensajeDescifrado = clavePrivada.descifrarAES("Clave");
                    System.out.println("Descifrado: " + mensajeDescifrado);
                    System.out.println("-----------");
                    break;

                case 5:
                    KeyGenerator clavePrivada_KeyGenerator = new KeyGenerator();
                    clavePrivada_KeyGenerator.generatePrivateKey();
                    System.out.println("Ficheros de Clave Generados!");

                    ClavePublica clavePublica = new ClavePublica();
                    mensajeCifradoBytes = clavePublica.cifrarRSA("Mensaje super secreto");
                    System.out.println("Cifrado! -> " + new String(mensajeCifradoBytes));
                    System.out.println("Tama�o -> " + mensajeCifradoBytes.length + " bytes");
                    System.out.println("-----------");
                    mensajeDescifradoBytes = clavePublica.descifrarRSA(mensajeCifradoBytes);
                    System.out.println("Descifrado! -> " + new String(mensajeDescifradoBytes));
                    System.out.println("-----------");
                    break;
            }
        } while (opc != 6);
    }

    private static int menu() {
        int resp = 0;

        System.out.println("\n1. Algoritmo resumen: SHA."
                + "\n2. Algoritmo resumen: MD5."
                + "\n3. Algoritmo resumen: MD5 con salt."
                + "\n4. Clave privada: AES."
                + "\n5. Clave publica: RSA."
                + "\n6. Salir."
                + "\nElige tu opción: ");
        resp = Utilidades.leerInt();
        return resp;
    }

    /**
     * Salt es un texto generado aleatoriamente que es adjuntado al mensaje
     * antes de obtener el hash para mayor seguridad.
     *
     * @return Texto generado aleatoriamente.
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSalt() throws NoSuchAlgorithmException {
        //Always use a SecureRandom generator
        //SHA1PRNG es un algoritmo generador de numeros pseudoaleatorio basado en el message digest de SHA-1
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
}
