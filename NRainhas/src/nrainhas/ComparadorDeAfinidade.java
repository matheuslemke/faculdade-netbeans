/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrainhas;

import java.util.Comparator;

/**
 *
 * @author matheus
 */
public class ComparadorDeAfinidade implements Comparator<Integer[]>
{

    @Override
    public int compare(Integer[] a, Integer[] b)
    {
        int n = a.length - 1;
        return Integer.compare(b[n], a[n]);
    }

}
