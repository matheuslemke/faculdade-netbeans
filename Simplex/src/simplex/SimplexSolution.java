/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

/**
 *
 * @author ander
 */
public class SimplexSolution
{

    private final double[][] A;
    private final BasicPartition basicPartition;
    private final double[] x_;
    private final double solutionValue;
    private final String label;

    public SimplexSolution(double[][] A, BasicPartition basicPartition, double[] x_, double solutionValue, String label)
    {
        this.A = A;
        this.basicPartition = basicPartition;
        this.x_ = x_;
        this.solutionValue = solutionValue;
        this.label = label;
    }

    public double[][] getA()
    {
        return A;
    }

    public BasicPartition getBasicPartition()
    {
        return basicPartition;
    }

    public double[] getX_()
    {
        return x_;
    }

    public double getSolutionValue()
    {
        return solutionValue;
    }

    public String getLabel()
    {
        return label;
    }
}
