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
public class Quadrupla<T, U, V, W>
{
    private Tupla<T, U> tupla1;
    private Tupla<V, W> tupla2;

    public Quadrupla(T t, U u, V v, W w)
    {
        this.tupla1 = new Tupla<>(t, u);
        this.tupla2 = new Tupla<>(v, w);
    }
    
    public T getT()
    {
        return tupla1.getT();
    }

    public U getU()
    {
        return tupla1.getU();
    }
    
    public V getV()
    {
        return tupla2.getT();
    }

    public W getW()
    {
        return tupla2.getU();
    }
}
