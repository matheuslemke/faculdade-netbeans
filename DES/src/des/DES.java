/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus
 */
public class DES
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String mensagem = "Teste1231dsfssd";

        try
        {
            byte[] text = mensagem.getBytes("UTF8");

            System.out.println(toBinary(text));

            byte[][] v = divideArray(text, 8);

            for (byte[] v1 : v)
            {
                System.out.println(toBinary(v1));
            }

            //System.out.println(v[0][0] >> 2 );
            int numeroBytes = text.length;
            numeroBytes--;

            for (int i = 0; i < ((numeroBytes * 8) / 64); i++)
            {
                aplicarIP(text);
            }

        } catch (UnsupportedEncodingException ex)
        {
            Logger.getLogger(DES.class.getName()).log(Level.SEVERE, null, ex);
        }

        long n = 8;

        long b = (byte) n;

        /*
         OBS: O primeiro bit é o mais a esquerda
         1. Geração das chaves
         2. Pré-processamento: msg aplica IP e divide em L0 e R0
         3. Aplicar função f 16 vezes
         4. Pós processamento
         */
    }

    public static int numeroDeBytes(byte[] vetor)
    {
        int i = 0;
        for (byte w : vetor)
        {
            i++;
        }
        return i;
    }

    public static String toBinary(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        int separator = 8;
        int j = 0;
        for (int i = 0; i < Byte.SIZE * bytes.length; i++)
        {
            if (j == separator)
            {
                sb.append(" ");
                j = 0;
            }
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');

            j++;
        }
        return sb.toString();
    }

    public static byte[] fromBinary(String s)
    {
        int sLen = s.length();
        byte[] toReturn = new byte[(sLen + Byte.SIZE - 1) / Byte.SIZE];
        char c;
        for (int i = 0; i < sLen; i++)
        {
            if ((c = s.charAt(i)) == '1')
            {
                toReturn[i / Byte.SIZE] = (byte) (toReturn[i / Byte.SIZE] | (0x80 >>> (i % Byte.SIZE)));
            } else if (c != '0')
            {
                throw new IllegalArgumentException();
            }
        }
        return toReturn;
    }

    public static byte[][] divideArray(byte[] source, int chunksize)
    {

        byte[][] ret = new byte[(int) Math.ceil(source.length / (double) chunksize)][chunksize];

        int start = 0;

        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = Arrays.copyOfRange(source, start, start + chunksize);
            start += chunksize;
        }

        return ret;
    }

    public static void aplicarIP(byte[] msg)
    {

    }

}
