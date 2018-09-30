package crc;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus
 */
public class CRC
{

    public String xor(String membro, String polinomio)
    {
        String retorno = null;
        for (int i = 0; i < polinomio.length(); i++)
        {
            if(membro.charAt(i) == polinomio.charAt(i))
            {
                retorno+=0;
            }
            else retorno +=1;
        }
        return retorno;
    }

    public String gerarFrame(String dados, String polinomio, String gama)
    {
        String dadoGama = dados + gama;
        String membro;
        for (int i = 0; i < dadoGama.length(); i++)
        {
            if ((Integer.parseInt(String.valueOf(dados.charAt(i)))) == 1)
            {
                membro = dados.substring(i, i + polinomio.length());
                dados.replaceAll(membro, xor(membro, polinomio));
            }
        }
        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {

        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String dados;
            System.out.println("Digite o dado em números binários:");
            dados = in.readLine();

            for (int i = 0; i < dados.length(); i++)
            {
                if ((Integer.parseInt(String.valueOf(dados.charAt(i)))) != 1
                        && (Integer.parseInt(String.valueOf(dados.charAt(i)))) != 0)
                {
                    throw new NumberFormatException();
                }
            }

            System.out.println("Digite o polinômio gerador:");
            String polinomio = in.readLine();

            if (polinomio.length() > dados.length())
            {
                throw new PolinomioMaiorException();
            }

            int lenghtGama = polinomio.length() - 1;
            StringBuilder gamaBuilder = new StringBuilder();
            for (int i = 0; i < lenghtGama; i++)
            {
                gamaBuilder.append(0);
            }
            String gama = gamaBuilder.toString();
        }
        catch (IOException io)
        {
            Logger.getLogger(CRC.class.getName()).log(Level.SEVERE, null, io);
        }
        catch (NumberFormatException nf)
        {
            System.out.println("Só são permitidos números binários!");
        }
        catch (PolinomioMaiorException pm)
        {
            System.out.println("O polinômio deve possuir um número de bits menor ou igual ao número de bits do dado!");
        }

    }

}
