/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dam
 */
public class AlgoritmoDeResumen {

    /**
     * Método que genera hashes más fuertes que MD5, aunque no son siempre
     * únicos,ya que hay posibilidad de colision (que dos entradas distintas den
     * un mismo hash como resultado) aunque es menor que con MD5. Para
     * implementar los distintos SHA basta con poner
     * SHA-1/SHA-256/SHA-384/SHA-512 en getInstance(). A mayor numero mayor
     * generacion de byte en el hash y mayor seguridad.
     *
     * <b>Función de una sola vía (o función hash)</b> <br/>
     * <br/>
     *
     * Dado que es <u>imposible</u> obtener el texto original a partir de un
     * hash, SHA es útil para guardar passwords en BBDD. Guardamos el hash, no
     * el password, de forma que cada vez que tengamos que autenticar a un
     * usuario primero calculamos el hash del password que nos llega con el hash
     * de BBDD. Si coinciden, genial. Si no, es que la clave es incorrecta.
     */
    public static void cifrarSHA(String mensaje) {
        MessageDigest md;
        try {
            // Obtén una instancia de MessageDigest que usa SHA
            md = MessageDigest.getInstance("SHA");
            // Convierte el texto en un array de bytes 
            byte dataBytes[] = mensaje.getBytes();
            // Actualiza el MessageDigest con el array de bytes
            md.update(dataBytes);
            // Calcula el resumen (función digest)
            byte resumen[] = md.digest();

            System.out.println("Mensaje original: " + mensaje);
            System.out.println("Número de Bytes: " + md.getDigestLength());
            System.out.println("Algoritmo usado: " + md.getAlgorithm());
            System.out.println("Resumen del Mensaje: " + new String(resumen));
            System.out.println("Mensaje en Hexadecimal: " + Hexadecimal(resumen));
            System.out.println("Proveedor: " + md.getProvider().toString());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    // Convierte Array de Bytes en hexadecimal
    static String Hexadecimal(byte[] resumen) {
        String HEX = "";
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1) {
                HEX += "0";
            }
            HEX += h;
        }
        return HEX.toUpperCase();
    }

    /**
     * El algoritmo MD5 es rapido y facil de implementar pero no es muy seguro.
     * "Is not collision resistant", es decir, diferentes mensajes pueden
     * resultar en un mismo hash.
     *
     * @param mensaje
     * @return
     */
    //https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
    public static String cifrarMD5(String mensaje) {
        String mensajeGenerado = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add message bytes to digest
            md.update(mensaje.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed message in hex format
            mensajeGenerado = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return mensajeGenerado;
    }

    /**
     * Es el mismo ejemplo que "cifrarMD5" pero incluye salt para mayor
     * seguridad. Habria que guardar el valor de salt por cada mensaje que se
     * cifre, porque su valor va cambiando
     */
    public static String cifrarMD5ConSalt(String mensaje, byte[] salt) {
        String mensajeGenerado = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add message bytes to digest
            md.update(salt);
            //Get the hash's bytes 
            byte[] bytes = md.digest(mensaje.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            mensajeGenerado = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return mensajeGenerado;
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
