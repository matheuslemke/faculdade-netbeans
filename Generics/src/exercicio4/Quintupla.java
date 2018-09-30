/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio4;

/**
 *
 * @author Matheus
 */
public class Quintupla<T, U, V, W, X>
{

    private Tripla<T, U, V> tripla;
    private Tupla<W, X> tupla;

    public Quintupla(T t, U u, V v, W w, X x)
    {
        this.tripla = new Tripla<>(t, u, v);
        this.tupla = new Tupla<>(w, x);
    }

    public T getT()
    {
        return tripla.getT();
    }

    public U getU()
    {
        return tripla.getU();
    }

    public V getV()
    {
        return tripla.getV();
    }
    
    public W getW()
    {
        return tupla.getT();
    }
    
    public X getX()
    {
        return tupla.getU();
    }
}
