/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distanciadeedicao4;

/**
 *
 * @author matheus
 */
public class DistanciaDeEdicao4
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String string1 = "canela", string2 = "cravo";
        String menor = string1, maior = string2;

        int n = Math.min(string1.length(), string2.length());
        int vetor[] = new int[n], aux1 = -1, aux2;
        int custo;

        if (string2.length() == n)
        {
            menor = string2;
            maior = string1;
        }

        if (string1.charAt(0) == string2.charAt(0))
            vetor[0] = 0;
        else
            vetor[0] = 1;

        for (int j = 1; j < n; j++)
            vetor[j] = vetor[j - 1] + 1;

        for (int i = 1; i < maior.length(); i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (j == 0)
                {
                    aux1 = vetor[j] + 1;
                }
                else
                {
                    if (maior.charAt(i) == menor.charAt(j))
                        custo = 0;//matching
                    else
                        custo = 1;//troca
                    aux2 = Math.min(Math.min(vetor[j] + 1, vetor[j - 1] + custo), aux1 + 1);
                    vetor[j - 1] = aux1;
                    aux1 = aux2;
                }
            }
            vetor[n - 1] = aux1;
        }

        for (int i = 0; i < n; i++)
        {
            System.out.print(vetor[i] + " ");
        }
        System.out.println("");
        System.out.println("Custo de edição: " + vetor[n - 1]);

    }

}
