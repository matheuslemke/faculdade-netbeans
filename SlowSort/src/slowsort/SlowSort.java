/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slowsort;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author matheus
 */
public class SlowSort
{

    public static void sort(int[] vetor, int i, int j)
    {
        if (i >= j)
            return;
        int m = (i + j) / 2;
        int aux;
        sort(vetor, i, m);
        sort(vetor, m + 1, j);

        if (vetor[j] < vetor[m])
        {
            aux = vetor[m];
            vetor[m] = vetor[j];
            vetor[j] = aux;
        }
        sort(vetor, i, j - 1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int n = 300;
        int[] vetor = new int[n];

        for (int i = 0; i < n; i++)
        {
            vetor[i] = ThreadLocalRandom.current().nextInt(0, 500);
        }

        sort(vetor, 0, vetor.length - 1);

        for (int i = 0; i < vetor.length; i++)
        {
            System.out.println(vetor[i] + " ");
        }
    }
}
