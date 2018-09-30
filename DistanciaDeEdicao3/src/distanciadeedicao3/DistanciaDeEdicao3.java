/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distanciadeedicao3;

/**
 *
 * @author juliano
 */
public class DistanciaDeEdicao3
{

    /**
     * @param args the command line arguments
     */
    private static void copiarValor(int[][] matriz, int n)
    {
        for (int i = 0; i < n; i++)
        {
            matriz[0][i] = matriz[1][i];
        }
    }

    public static void main(String[] args)
    {
        String string1 = "canela";
        String string2 = "cravo";
        int n = Math.min(string2.length(), string1.length());
        int matriz[][] = new int[2][n];
        int x, y, custo;
        String menor = string1;
        String maior = string2;
        if (string2.length() == n)
        {
            menor = string2;
            maior = string1;
        }

        if (string1.charAt(0) == string2.charAt(0))
            matriz[0][0] = 0;
        else
            matriz[0][0] = 1;

        for (x = 1; x < n; x++)
        {
            matriz[0][x] = x;
        }

        for (x = 1; x < maior.length(); x++)
        {
            for (y = 0; y < n; y++)
            {
                if (y == 0)
                {
                    matriz[1][y] = matriz[0][y] + 1;
                }
                else
                {
                    if (string1.charAt(x) == string2.charAt(y))
                    {
                        custo = 0;
                    }
                    else
                    {
                        custo = 1;
                    }
                    matriz[1][y] = Math.min(Math.min(matriz[0][y] + 1, matriz[1][y - 1] + 1), matriz[0][y - 1] + custo);
                }
            }
            if (x != maior.length() - 1)
            {
                copiarValor(matriz, n);
            }
        }

        System.out.print("  ");
        for (int j = 0; j < n; j++)
        {
            System.out.print((j + 1) + " ");

        }
        System.out.println();
        for (int i = 0; i < 2; i++)
        {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < n; j++)
            {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nCusto de edição: " + matriz[1][n - 1]);
    }

}
