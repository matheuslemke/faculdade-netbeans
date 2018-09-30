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
public class DESExamplePCBC
{

    public static void main(String[] args)
    {
        KeyGenerator keygen;
        try
        {
            //PARA ALTERAR O TIPO DO ALGORITMO É SÓ ALTERAR NO NOME; SIMPLES ASSIM

            byte[] iv =
            {
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08
            };
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            keygen = KeyGenerator.getInstance("DES");
            Key key = keygen.generateKey();

            //keygen.init(3); não precisa no caso do DES; porém em outros algoritmos é necessário especificar o tamanho da chave
            String message = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

            byte[] plaintext = message.getBytes("UTF8");
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            byte[] cypherText = cipher.doFinal(plaintext);

            //System.out.println(new String(cypherText, "UTF8"));
            System.out.println(new BASE64Encoder().encode(cypherText));

            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            byte[] newPlainText = cipher.doFinal(cypherText);

            System.out.println(new String(newPlainText, "UTF8"));

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex)
        {
            Logger.getLogger(DESExampleECB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex)
        {
            Logger.getLogger(DESExamplePCBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
