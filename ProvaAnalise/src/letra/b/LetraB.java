/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letra.b;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import util.prova.HeapSort;

/**
 *
 * @author matheus
 */
public class LetraB
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int n = 20;
        int maxValorX = 50;
        int ax, i = 0, soma = 0;
        int[] vetorA = new int[n];
        ArrayList<Integer> listaA = new ArrayList<>(n);

        for (; i < n;)
        {
            ax = ThreadLocalRandom.current().nextInt(1, maxValorX);
            if (!listaA.contains(ax))
            {
                listaA.add(ax);
                i++;
            }
        }

        System.out.println("Vetor gerado aleatoriamente");
        for (int j = 0; j < n; j++)
        {
            System.out.print(listaA.get(j) + " ");
        }
        System.out.println("");

        i = 0;
        for (Integer a : listaA)
        {
            vetorA[i] = a;
            i++;
        }

        HeapSort.sort(vetorA);

        System.out.println("Vetor ORDENADO");
        for (int j = 0; j < n; j++)
        {
            System.out.print(vetorA[j] + " ");
        }
        System.out.println("");

        for (int p = n, j = 0; p >= 1; p--, j++)
        {
            soma += vetorA[j] * p;
        }

        System.out.println("Menor soma: " + soma);

    }

}
