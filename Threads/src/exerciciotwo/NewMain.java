/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciotwo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus
 */
public class NewMain
{

    public static class Somador implements Runnable
    {

        private int Start;
        private int Final;
        private int vetor[];
        private int soma;

        public Somador(int Start, int Final, int[] vetor, int soma)
        {
            this.Start = Start;
            this.Final = Final;
            this.vetor = vetor;
            this.soma = soma;
        }

        public int getSoma()
        {
            return soma;
        }

        @Override
        public void run()
        {
            for (int i = Start; i <= Final; i++)
            {
                soma += vetor[i];
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int vetor[] =
        {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        };

        int soma = 0;

        Somador s1 = new Somador(0, 4, vetor, soma);
        Somador s6 = new Somador(5, 9, vetor, soma);
        Thread somar1 = new Thread(s1);
        Thread somar6 = new Thread(s6);

        somar1.start();
        somar6.start();

        try
        {
            somar1.join();
            somar6.join();
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(s1.getSoma() + s6.getSoma());

    }

}
