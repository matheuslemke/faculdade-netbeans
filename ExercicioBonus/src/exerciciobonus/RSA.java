/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciobonus;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import sun.misc.BASE64Encoder;

/**
 *
 * @author matheus
 */
public class RSA
{

    private final byte[] msg;
    private KeyPair key;
    private Cipher cipher;
    private byte[] cipherText;

    public RSA(byte[] msg)
    {
        this.msg = msg;
    }

    public byte[] encrypt()
    {

        try
        {
            byte[] plainText = msg;

            // generate an RSA key
            System.out.println("\nStart generating RSA key");
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

            keyGen.initialize(4096);

            key = keyGen.generateKeyPair();

            System.out.println("Finish generating RSA key");

            // set an RSA cipher object and print the provider
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            // encrypt the plaintext using the private key
            System.out.println("\nStart encryption");
            cipher.init(Cipher.ENCRYPT_MODE, key.getPrivate());
            cipherText = cipher.doFinal(plainText);
            System.out.println("Finish encryption: ");
            System.out.println(new String(cipherText, "UTF8"));

            return cipherText;

        }
        catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex)
        {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public byte[] decrypt()
    {
        try
        {
            // decrypt the ciphertext using the public key
            System.out.println("\nStart decryption");
            cipher.init(Cipher.DECRYPT_MODE, key.getPublic());
            byte[] newPlainText = cipher.doFinal(cipherText);
            System.out.println("Finish decryption: ");
            System.out.println(new BASE64Encoder().encode(newPlainText));
            return newPlainText;
        }
        catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex)
        {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
