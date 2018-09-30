/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aesrsa;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;

/**
 *
 * @author matheus
 */
public class RSAPCBC
{

    public static byte[] encrypt(String mensagem, Key pubKey)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);

            byte[] encriptado = cipher.doFinal(mensagem.getBytes());

            return encriptado;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] encriptado, Key privKey)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.DECRYPT_MODE, privKey);
            byte[] desencriptado = cipher.doFinal(encriptado);

            return desencriptado;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception
    {
        String mensagem = "Testando o RSA!";

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        KeyPair key = keyGen.generateKeyPair();

        RSAPublicKey pubKey = (RSAPublicKey) key.getPublic();
        RSAPrivateKey privKey = (RSAPrivateKey) key.getPrivate();

        byte[] mensagemEncriptada = encrypt(mensagem, pubKey);

        byte[] mensagemDesencriptada = decrypt(mensagemEncriptada, privKey);

        System.out.println("Mensagem: \n" + mensagem);
        System.out.println("Mensagem Hexadecimal: \n" + toHex(mensagem));
        System.out.println("");
        System.out.println("Chave Publica: \n" + new String(pubKey.getEncoded()));
        System.out.println("Chave Publica Hexadecimal: \n" + toHex(new String(pubKey.getEncoded())));
        System.out.println("");
        System.out.println("Chave Privada: \n" + new String(privKey.getEncoded()));
        System.out.println("Chave Privada Hexadecimal: \n" + toHex(new String(privKey.getEncoded())));
        System.out.println("");
        System.out.println("Mensagem Encriptada: \n" + new String(mensagemEncriptada));
        System.out.println("Mensagem Encriptada Hexadecimal: \n" + toHex(new String(mensagemEncriptada)));
        System.out.println("");
        System.out.println("Mensagem Desencriptada: \n" + new String(mensagemDesencriptada));
        System.out.println("Mensagem Desencriptada Hex: \n" + toHex(new String(mensagemDesencriptada)));
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
