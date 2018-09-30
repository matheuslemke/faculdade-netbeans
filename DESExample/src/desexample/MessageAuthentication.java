/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desexample;

import java.security.*;
import javax.crypto.*;
import sun.misc.BASE64Encoder;
//
// Generate a Message Authentication Code

public class MessageAuthentication
{

    public static void main(String[] args) throws Exception
    {

        byte[] plainText = "TESTE AUTHENTICATE Hmac MD5".getBytes("UTF8");
    //
        // get a key for the HmacMD5 algorithm
        System.out.println("\nStart generating key");
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
        SecretKey MD5key = keyGen.generateKey();
        System.out.println("Finish generating key");
    //
        // get a MAC object and update it with the plaintext
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(MD5key);
        mac.update(plainText);
    //
        // print out the provider used and the MAC
        System.out.println("\n" + mac.getProvider().getInfo());
        System.out.println("\nMAC: ");
        System.out.println(new String(mac.doFinal(), "UTF8"));
        System.out.println(new BASE64Encoder().encode(mac.doFinal()));

    }
}
