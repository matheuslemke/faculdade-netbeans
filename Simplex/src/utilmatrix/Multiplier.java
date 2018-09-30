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
public class Multiplier
{

    public static double multiply(double[] a, double[] b)
    {
        double sum = 0;
        for (int i = 0; i < a.length; i++)
            sum += a[i] * b[i];
        return sum;
    }

    public static double[] multiply(double[] x, double[][] a)
    {
        int m = a.length;
        int n = a[0].length;
        if (x.length != m)
            throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[n];
        for (int j = 0; j < n; j++)
            for (int i = 0; i < m; i++)
                y[j] += a[i][j] * x[i];
        return y;
    }

    // matrix-vector multiplication (y = A * x)
    public static double[] multiply(double[][] a, double[] x)
    {
        int m = a.length;
        int n = a[0].length;
        if (x.length != n)
            throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i] += a[i][j] * x[j];
        return y;
    }
}
