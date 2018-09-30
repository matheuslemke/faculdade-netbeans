/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exercicio4;

/**
 *
 * @author Matheus
 * @param <T>
 * @param <U>
 * @param <V>
 */
public class Tripla<T, U, V>
{
    private Tupla<T, U> tupla;
    private V v;

    public Tripla(T t, U u, V v)
    {
        this.tupla = new Tupla<>(t, u);
        this.v = v;
    }

    public <T, U, V> Tripla()
    {
    }

    public T getT()
    {
        return tupla.getT();
    }

    public U getU()
    {
        return tupla.getU();
    }

    public V getV()
    {
        return v;
    }
}
