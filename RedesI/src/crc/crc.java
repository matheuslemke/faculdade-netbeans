package crc;

import java.io.*;

public class crc
{

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] data;
        int[] div;
        int[] divisor;
        int[] rem;
        int[] crc;
        int data_bits, divisor_bits, tot_length;

        System.out.println("Número de bits dos dados:");
        data_bits = Integer.parseInt(br.readLine());
        data = new int[data_bits];

        System.out.println("Digite os bits de um em um, teclando enter após cada bit:");
        for (int i = 0; i < data_bits; i++)
        {
            data[i] = Integer.parseInt(br.readLine());
        }

        System.out.println("Número de bits do polinômio: ");
        divisor_bits = Integer.parseInt(br.readLine());
        divisor = new int[divisor_bits];

        System.out.println("Digite os bits do polinômio de um em um, teclando enter após cada bit:");
        for (int i = 0; i < divisor_bits; i++)
        {
            divisor[i] = Integer.parseInt(br.readLine());
        }

        tot_length = data_bits + divisor_bits - 1;

        div = new int[tot_length];
        rem = new int[tot_length];
        crc = new int[tot_length];

        /*Geração do CRC*/
        System.arraycopy(data, 0, div, 0, data.length);

        System.out.print("Dados com o gama: ");
        for (int i = 0; i < div.length; i++)
        {
            System.out.print(div[i]);
        }
        System.out.println();

        System.arraycopy(div, 0, rem, 0, div.length);

        rem = dividir(div, divisor, rem);

        for (int i = 0; i < div.length; i++)
        {
            crc[i] = (div[i] ^ rem[i]);
        }

        System.out.println();
        System.out.println("CÓDIGO CRC A SER TRANSMITIDO: ");
        for (int i = 0; i < crc.length; i++)
        {
            System.out.print(crc[i]);
        }

        /*Transmissão*/
        System.out.println("\n\n----Transmitir código CRC----");


        /*        System.out.print("crc bits are : ");
         for(int i=0; i< crc.length; i++)
         System.out.print(crc[i]);
         System.out.println();
         */
        System.arraycopy(crc, 0, rem, 0, crc.length);

        rem = dividir(crc, divisor, rem);

        for (int i = 0; i < rem.length; i++)
        {
            if (rem[i] != 0)
            {
                System.out.println("ERRO NA TRANSMISSÃO!");
                break;
            }
            if (i == rem.length - 1)
            {
                System.out.println("TRANSMISSÃO EFETUADA COM SUCESSO! \n R(x) = 0");
            }
        }
    }

    static int[] dividir(int div[], int divisor[], int rem[])
    {
        int cur = 0;
        while (true)
        {
            for (int i = 0; i < divisor.length; i++)
            {
                rem[cur + i] = (rem[cur + i] ^ divisor[i]);
            }

            while (rem[cur] == 0 && cur != rem.length - 1)
            {
                cur++;
            }

            if ((rem.length - cur) < divisor.length)
            {
                break;
            }
        }
        return rem;
    }
}
