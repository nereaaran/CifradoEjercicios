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
 * Criptografia Asimetrica (Clave publica) - Generador Claves
 *
 * En un Cifrado asimetrico hay dos participantes: el emisor y el receptor. Los
 * pasos a seguir son:
 *
 * -Generar una clave publica y otra privada. La clave publica se envia al
 * emisor -El emisor cifra los datos con clave publica y se envian al receptor
 * -El receptor descifra los datos con clave privada
 *
 * En este caso, el algoritmo utilizado es el RSA. Para guardar una clave en un
 * archivo, se debe crear un objeto de especificacion de clave. La clase para
 * crear la especificacion de clave privada es PKCS8EncodedKeySpec, y para la
 * publica es X509EncodedKeySpec.
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
