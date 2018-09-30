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
public class BasicPartition
{

    private int[] N;
    private int[] B;

    public BasicPartition(int[] N, int[] B)
    {
        this.N = N;
        this.B = B;
    }

    public int[] getN()
    {
        return N;
    }

    public void setN(int[] N)
    {
        this.N = N;
    }

    public int[] getB()
    {
        return B;
    }

    public void setB(int[] B)
    {
        this.B = B;
    }

}
