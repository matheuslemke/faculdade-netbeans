package desexample;

import java.security.*;
import javax.crypto.*;
import sun.misc.BASE64Encoder;
//
// Generate a Message Digest

public class Digest
{

    public static void main(String[] args) throws Exception
    {

        byte[] plainText = "TESTE DE DIGEST - aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaasasasaMD5".getBytes("UTF8");
        //
        // get a message digest object using the MD5 algorithm
        //MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //
        // print out the provider used
        System.out.println("\n" + messageDigest.getProvider().getInfo());
        //
        // calculate the digest and print it out
        messageDigest.update(plainText);
        System.out.println("\nDigest: ");
        System.out.println(new String(messageDigest.digest(), "UTF8"));
        System.out.println(new BASE64Encoder().encode(messageDigest.digest()));
    }
}
