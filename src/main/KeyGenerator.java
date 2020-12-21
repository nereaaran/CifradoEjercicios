/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * <b>Criptograf�a Asim�trica (Clave p�blica) - Generador Claves</b> <br/>
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
 * En este caso, el algoritmo utilizado es el <b>RSA</b>. Para guardar una clave
 * en un archivo, se debe crear un <u>objeto de especificaci�n de clave</u>. La
 * clase para crear la especificaci�n de clave privada es
 * <u>PKCS8EncodedKeySpec</u>, y para la p�blica es <u>X509EncodedKeySpec</u>.
 */
public class KeyGenerator {

    /**
     * Genera el fichero con la clave privada
     */
    public void generatePrivateKey() {

        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024); // Inicializamos el tama�o a 1024 bits
            KeyPair keypair = generator.generateKeyPair();
            PublicKey publicKey = keypair.getPublic(); // Clave P�blica
            PrivateKey privateKey = keypair.getPrivate(); // Clave Privada

            // Publica
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\DAM\\PSP\\CifradoEjercicios\\src\\archivos\\EjemploRSA_Public.key");
            fileOutputStream.write(x509EncodedKeySpec.getEncoded());
            fileOutputStream.close();

            // Privada
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            fileOutputStream = new FileOutputStream("D:\\DAM\\PSP\\CifradoEjercicios\\src\\archivos\\EjemploRSA_Private.key");
            fileOutputStream.write(pKCS8EncodedKeySpec.getEncoded());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
