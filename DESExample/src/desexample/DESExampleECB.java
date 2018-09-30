/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desexample;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.*;
import sun.misc.BASE64Encoder;

/**
 *
 * @author matheus
 */
public class DESExampleECB
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        KeyGenerator keygen;
        try
        {
            //PARA ALTERAR O TIPO DO ALGORITMO É SÓ ALTERAR NO NOME; SIMPLES ASSIM

            keygen = KeyGenerator.getInstance("DES");
            Key key = keygen.generateKey();
            //keygen.init(3); não precisa no caso do DES; porém em outros algoritmos é necessário especificar o tamanho da chave
            String message = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

            byte[] plaintext = message.getBytes("UTF8");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cypherText = cipher.doFinal(plaintext);

            //System.out.println(new String(cypherText, "UTF8"));
            System.out.println(new BASE64Encoder().encode(cypherText));

            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] newPlainText = cipher.doFinal(cypherText);

            System.out.println(new String(newPlainText, "UTF8"));

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex)
        {
            Logger.getLogger(DESExampleECB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
