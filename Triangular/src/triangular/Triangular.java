/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triangular;

/**
 *
 * @author matheus
 */
public class Triangular
{

    public static int[] converterParaInferior(int[][] matriz)
    {
        int n = (int) (Math.pow(matriz.length, 2) - matriz.length) / 2;
        int[] vetor = new int[n];

        for (int i = 1; i < matriz.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                vetor[i + j - 1] = matriz[i][j];
            }
        }

        return vetor;
    }

    public static int getIndiceInferior(int linha, int coluna)
    {
        return (((linha - 1) * linha) / 2) + (coluna);
    }

    public static int[] getLinhaColunaInferior(int indice)
    {
        int[] linhaecoluna = new int[2];

        return linhaecoluna;
    }

    public static int[] converterParaSuperior(int[][] matriz)
    {
        int n = (int) (Math.pow(matriz.length, 2) - matriz.length) / 2;
        int[] vetor = new int[n];

        for (int i = matriz.length - 2; i >= 0; i--)
        {
            for (int j = matriz.length - 1; j > i; j--)
            {
                vetor[i + j - 1] = matriz[i][j];
            }
        }

        return vetor;
    }

    public static int getIndiceSuperior(int linha, int coluna)
    {
        return (((coluna - 1) * coluna) / 2) + (linha);
    }

    public static int[] getLinhaColunaSuperior(int indice)
    {
        int[] linhaecoluna = new int[2];

        return linhaecoluna;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int[][] matriz =
        {
            {
                0, 60, 72
            },
            {
                4, 0, 38
            },
            {
                5, 6, 0
            }
        };

        int[] vetorSuperior = converterParaSuperior(matriz);
        System.out.println();
        System.out.println(vetorSuperior[getIndiceSuperior(0, 2)]);

        int[] vetorInferior = converterParaInferior(matriz);
        System.out.println();
        System.out.println(vetorInferior[getIndiceInferior(2, 0)]);
    }
}
