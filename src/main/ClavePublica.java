/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * <b>Criptograf�a Asim�trica (Clave p�blica) - Generador Clave P�blica</b>
 * <br/>
 * <br/>
 *
 * En un <b>Cifrado asim�trico</b> hay dos participantes: el emisor y el
 * receptor. Los pasos a seguir son:
 *
 * <ul>
 * <li>Generar una <b>clave p�blica</b> y otra <b>privada</b>. La clave p�blica
 * se env�a al emisor</li>
 * <li>El emisor <u>cifra</u> los datos con <b>clave p�blica</b> y se env�an al
 * receptor</li>
 * <li>El receptor <u>descifra</u> los datos con <b>clave privada</b></li>
 * </ul>
 *
 * Esta clase genera primero cifra un mensaje con la <b>clave p�blica</b>. A
 * continuaci�n, lo descifra mediante la <b>clave privada</b>. En este caso
 * vamos a utilizar:
 *
 * <ul>
 * <li>El algoritmo RSA</li>
 * <li>El modo ECB: Existen dos, el ECB que es sencillo, y el CBC que necesita
 * un vector de inicializaci�n(IV)</li>
 * <li>El padding PKCS1Padding: Si el mensaje no es m�ltiplo de la longitud del
 * algoritmo se indica un relleno.</li>
 * </ul>
 */
public class ClavePublica {

    /**
     * Cifra un texto con RSA, modo ECB y padding PKCS1Padding (asim�trica) y lo
     * retorna
     *
     * @param mensaje El mensaje a cifrar
     * @return El mensaje cifrado
     */
    public byte[] cifrarRSA(String mensaje) {
        byte[] encodedMessage = null;
        try {
            // Cargamos la clave pública
            byte fileKey[] = fileReader("c:\\trastero\\EjemploRSA_Public.key");
            System.out.println("Tama�o -> " + fileKey.length + " bytes");

            // Obtenemos una instancia de KeyFactory, algoritmo RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // Creamos un nuevo X509EncodedKeySpec del fileKey
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(fileKey);
            // Generamos la public key con el keyFactory
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            // Obtenemos una instancia del Cipher "RSA/ECB/PKCS1Padding"
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            // Iniciamos el cipher (ENCRYPT_MODE)
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // El método doFinal nos cifra el mensaje
            encodedMessage = cipher.doFinal(mensaje.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedMessage;
    }

    /**
     * Descifra un texto con RSA, modo ECB y padding PKCS1Padding (asim�trica) y
     * lo retorna
     *
     * @param mensaje El mensaje a descifrar
     * @return El mensaje descifrado
     */
    private byte[] descifrarTexto(byte[] mensaje) {
        byte[] decodedMessage = null;
        try {
            // Cargamos la clave privada
            byte fileKey[] = fileReader("c:\\trastero\\EjemploRSA_Private.key");
            System.out.println("Tama�o -> " + fileKey.length + " bytes");

            // Obtenemos una instancia de KeyFactory, algoritmo RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // Creamos un nuevo PKCS8EncodedKeySpec del fileKey
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(fileKey);
            // Generamos la public key con el keyFactory
            PrivateKey privateKey = keyFactory.generatePrivate(pKCS8EncodedKeySpec);
            // Obtenemos una instancia del Cipher "RSA/ECB/PKCS1Padding"
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            // Iniciamos el cipher (DECRYPT_MODE)
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            // El método doFinal nos descifra el mensaje
            decodedMessage = cipher.doFinal(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedMessage;
    }

    /**
     * Retorna el contenido de un fichero
     *
     * @param path Path del fichero
     * @return El texto del fichero
     */
    private byte[] fileReader(String path) {
        byte ret[] = null;
        File file = new File(path);
        try {
            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void main(String[] args) {
        EjemploRSA ejemploRSA = new EjemploRSA();
        byte[] mensajeCifrado = ejemploRSA.cifrarTexto("Mensaje super secreto");
        System.out.println("Cifrado! -> " + new String(mensajeCifrado));
        System.out.println("Tama�o -> " + mensajeCifrado.length + " bytes");
        System.out.println("-----------");
        byte[] mensajeDescifrado = ejemploRSA.descifrarTexto(mensajeCifrado);
        System.out.println("Descifrado! -> " + new String(mensajeDescifrado));
        System.out.println("-----------");
    }
}
