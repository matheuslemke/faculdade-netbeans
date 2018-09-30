/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distanciadeedicao2;

/**
 *
 * @author juliano
 */
public class DistanciaDeEdicao2
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String string1 = "canela";
        String string2 = "cravo";
        Celula matriz[][] = new Celula[string1.length()][string2.length()];
        int custo;

        for (int i = 0; i < string1.length(); i++)
        {
            for (int j = 0; j < string2.length(); j++)
            {
                matriz[i][j] = new Celula();
            }
        }

        if (string1.charAt(0) == string2.charAt(0))
        {
            matriz[0][0].operacao = Operacoes.M;
            matriz[0][0].custo = 0;
        }
        else
        {
            matriz[0][0].operacao = Operacoes.T;
            matriz[0][0].custo = 1;
        }

        for (int i = 1; i < string1.length(); i++)
        {
            matriz[i][0].custo = matriz[i - 1][0].custo + 1;
            matriz[i][0].operacao = Operacoes.I;
        }

        for (int j = 1; j < string2.length(); j++)
        {
            matriz[0][j].custo = matriz[0][j - 1].custo + 1;
            matriz[0][j].operacao = Operacoes.I;
        }

        for (int i = 1; i < string1.length(); i++)
        {
            for (int j = 1; j < string2.length(); j++)
            {
                if (string1.charAt(i) == string2.charAt(j))
                {
                    matriz[i][j].operacao = Operacoes.M;
                    custo = 0;
                }
                else
                {
                    matriz[i][j].operacao = Operacoes.T;
                    custo = 1;
                }

                if (matriz[i - 1][j].custo + 1 < matriz[i - 1][j - 1].custo + custo || matriz[i][j - 1].custo + 1 < matriz[i - 1][j - 1].custo + custo)
                {
                    matriz[i][j].operacao = Operacoes.Q;
                }
                matriz[i][j].custo = Math.min(Math.min(matriz[i - 1][j].custo + 1, matriz[i][j - 1].custo + 1), matriz[i - 1][j - 1].custo + custo);
            }
        }
        System.out.print("  ");
        for (int j = 0; j < string2.length(); j++)
        {
            System.out.print((j + 1) + " ");

        }
        System.out.println();
        for (int i = 0; i < string1.length(); i++)
        {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < string2.length(); j++)
            {
                System.out.print(matriz[i][j].custo + " ");
            }
            System.out.println();
        }

        System.out.println("\nOPERAÇÕES\n");

        System.out.print("  ");
        for (int j = 0; j < string2.length(); j++)
        {
            System.out.print((j + 1) + " ");

        }
        System.out.println();
        for (int i = 0; i < string1.length(); i++)
        {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < string2.length(); j++)
            {
                System.out.print(matriz[i][j].operacao + " ");
            }
            System.out.println();
        }
        System.out.println("\nI = Inserção\nT = Troca\nM = Matching\nR = Remoção\nQ = Remoção ou Inserção\n");

        System.out.println("Custo de edição: " + matriz[string1.length() - 1][string2.length() - 1].custo);
    }

}
