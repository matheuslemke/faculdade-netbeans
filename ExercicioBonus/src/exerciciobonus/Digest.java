/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciobonus;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author matheus
 */
public class Digest
{

    private final String msg;

    public Digest(String msg)
    {
        this.msg = msg;
    }

    public byte[] doIt()
    {
        try
        {
            byte[] plainText = msg.getBytes("UTF8");
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // calculate the digest
            messageDigest.update(plainText);

            //print it out
            System.out.println("\nDigest: ");
            byte[] digest = messageDigest.digest();
            //System.out.println(new String(digest, "UTF8"));
            System.out.println(new BASE64Encoder().encode(digest));
            return digest;

        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException ex)
        {
            Logger.getLogger(Digest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
