/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilmatrix;

/**
 *
 * @author ander
 */
public class Printer
{

    public static void printMatrix(double[][] A)
    {
        for (double[] A1 : A)
        {
            for (int j = 0; j < A[0].length; j++)
                System.out.format("%.2f\t", A1[j]);
            System.out.println();
        }
        System.out.println();
    }

    public static void printArray(double[] b)
    {
        for (int i = 0; i < b.length; i++)
            System.out.format("%.2f\t", b[i]);
        System.out.println("\n");
    }

    public static void printArray(int[] b)
    {
        for (int i = 0; i < b.length; i++)
            System.out.format("%d\t", b[i]);
        System.out.println("\n");
    }
}
