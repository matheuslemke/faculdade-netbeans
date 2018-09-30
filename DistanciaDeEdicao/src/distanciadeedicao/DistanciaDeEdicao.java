/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distanciadeedicao;

/**
 *
 * @author juliano
 */
public class DistanciaDeEdicao
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String string1 = "canela";
        String string2 = "cravo";

        int matriz[][] = new int[string1.length()][string2.length()];
        int custo;

        if (string1.charAt(0) == string2.charAt(0))
            matriz[0][0] = 0;//matching
        else
            matriz[0][0] = 1;//troca

        for (int i = 1; i < string1.length(); i++)
        {
            matriz[i][0] = matriz[i - 1][0] + 1;
        }
        for (int j = 1; j < string2.length(); j++)
        {
            matriz[0][j] = matriz[0][j - 1] + 1;
        }

        for (int i = 1; i < string1.length(); i++)
        {
            for (int j = 1; j < string2.length(); j++)
            {
                if (string1.charAt(i) == string2.charAt(j))
                {
                    custo = 0;
                }
                else
                {
                    custo = 1;
                }
                matriz[i][j] = Math.min(Math.min(matriz[i - 1][j] + 1, matriz[i][j - 1] + 1), matriz[i - 1][j - 1] + custo);
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
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nCusto de edição: " + matriz[string1.length() - 1][string2.length() - 1]);
    }

}
