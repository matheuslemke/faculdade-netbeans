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
 */
public class Tupla<T, U>
{
    private T t;
    private U u;
    
    public <T, U> Tupla()
    {
    }

    public Tupla(T t, U u)
    {
        this.t = t;
        this.u = u;
    }

    public T getT()
    {
        return t;
    }

    public U getU()
    {
        return u;
    }
    
    
}
