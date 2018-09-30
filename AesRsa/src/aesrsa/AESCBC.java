/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aesrsa;

/**
 *
 * @author matheus
 */
import javax.crypto.*;
import javax.crypto.spec.*;

public class AESCBC
{

    public static byte[] encrypt(String key1, String key2, String value)
    {
        try
        {
            IvParameterSpec iv = new IvParameterSpec(key2.getBytes());

            SecretKeySpec skeySpec = new SecretKeySpec(key1.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return encrypted;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(String key1, String key2, byte[] encrypted)
    {
        try
        {
            IvParameterSpec iv = new IvParameterSpec(key2.getBytes("UTF-8"));

            SecretKeySpec skeySpec = new SecretKeySpec(key1.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(encrypted);

            return original;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
    {

        String key1 = "CHAVEONECHAVEONE"; // 128 bit key
        String key2 = "CHAVETWOCHAVETWO";

        String mensagem = "Testando o AES!";

        byte[] mensagemEncriptada = encrypt(key1, key2, mensagem);

        byte[] mensagemDesencriptada = decrypt(key1, key2, mensagemEncriptada);

        System.out.println("Mensagem: \n" + mensagem);
        System.out.println("Mensagem Hexadecimal: \n" + toHex(mensagem));
        System.out.println("");
        System.out.println("Chave1: \n" + key1);
        System.out.println("Chave1 Hexadecimal: \n" + toHex(key1));
        System.out.println("Chave2: \n" + key2);
        System.out.println("Chave2 Hexadecimal: \n" + toHex(key2));
        System.out.println("");
        System.out.println("Mensagem Encriptada: \n" + new String(mensagemEncriptada));
        System.out.println("Mensagem Encriptada Hexadecimal: \n" + toHex(new String(mensagemEncriptada)));
        System.out.println("Mensagem Desencriptada: \n" + new String(mensagemDesencriptada));
        System.out.println("Mensagem Desciptada Hex: \n" + toHex(new String(mensagemDesencriptada)));
    }

    static String toHex(String string)
    {
        StringBuilder buf = new StringBuilder(200);
        for (char ch : string.toCharArray())
        {
            if (buf.length() > 0)
            {
                buf.append(' ');
            }
            buf.append(String.format("%04x", (int) ch));
        }
        return buf.toString();
    }
}
