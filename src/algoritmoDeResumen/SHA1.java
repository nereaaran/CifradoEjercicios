/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmoDeResumen;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author 2dam
 */
public class SHA1 {

    public void cifrarSHA1() {
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
            while (hashtext.length() < 32){
                hashtext = "0" + hashtext;
            }
            
            System.out.println("Mensaje resumen: " + hashtext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
