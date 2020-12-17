/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author 2dam
 */
public class AlgoritmoDeResumen {

    
    //https://www.geeksforgeeks.org/sha-1-hash-in-java/
    public static void cifrarSHA1() {
        //Texto que se va a "digerir"
        String mensajeOriginal = "Esto es un texto plano";

        try {
            //Se instancia
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            //Se llama al metodo digest() para calcular el message digest de
            //"mensaje original" y lo devuelve como un array de bytes.
            byte[] messageDigest = md.digest(mensajeOriginal.getBytes());
            //Convierte el array de bytes en una reprentacion signum
            BigInteger no = new BigInteger(1, messageDigest);
            //Convierte el message digest en un valor hex
            String hashtext = no.toString(16);
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            System.out.println("Mensaje resumen: " + hashtext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*https://unipython.com/encriptar-en-md5-y-sha1-con-java/
    Este es otro modo de hacerlo y el resultado es exactamente el mismo
    public static void cifrarSHA1() {
        //Texto que se va a "digerir"
        String mensajeOriginal = "Esto es un texto plano";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] array = md.digest(mensajeOriginal.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            System.out.println("Mensaje resumen: " + sb.toString());
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    
    } */
    
    //https://www.baeldung.com/sha-256-hashing-java
    public static void cifrarSHA256() {
        //Texto que se va a "digerir"
        String mensajeOriginal = "Esto es un texto plano";

        try {
            //Se instancia
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            //Se llama al metodo digest() para calcular el message digest de
            //"mensaje original" y lo devuelve como un array de bytes.
            byte[] messageDigest = md.digest(mensajeOriginal.getBytes());
            //Convierte el array de bytes en una reprentacion signum
            BigInteger no = new BigInteger(1, messageDigest);
            //Convierte el message digest en un valor hex
            String hashtext = no.toString(16);
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            System.out.println("Mensaje resumen: " + hashtext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
