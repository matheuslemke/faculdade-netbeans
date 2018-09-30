/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desexample;

import java.security.*;
import javax.crypto.*;
//
// Public Key cryptography using the RSA algorithm.

public class RSAExample
{

    public static void main(String[] args) throws Exception
    {

        byte[] plainText = "TESTE do RSA!".getBytes("UTF8");
        //
        // generate an RSA key
        System.out.println("\nStart generating RSA key");
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        //keyGen.initialize(1024);
        keyGen.initialize(4096);
        KeyPair key = keyGen.generateKeyPair();
        System.out.println("Finish generating RSA key");
        //
        // get an RSA cipher object and print the provider   
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        System.out.println("\n" + cipher.getProvider().getInfo());
        //
        // encrypt the plaintext using the public key
        System.out.println("\nStart encryption");
        cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
        byte[] cipherText = cipher.doFinal(plainText);
        System.out.println("Finish encryption: ");
        System.out.println(new String(cipherText, "UTF8"));
        //
        // decrypt the ciphertext using the private key
        System.out.println("\nStart decryption");
        cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
        byte[] newPlainText = cipher.doFinal(cipherText);
        System.out.println("Finish decryption: ");
        System.out.println(new String(newPlainText, "UTF8"));
    }
}
