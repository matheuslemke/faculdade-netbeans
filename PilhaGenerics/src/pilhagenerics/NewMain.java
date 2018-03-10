/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilhagenerics;

/**
 *
 * @author Matheus
 */
public class NewMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {

        Pilha<Integer> p = new Pilha<>();
        p.push(1);
        p.push(2);
        System.out.println(p.toString());
        p.pop();
        System.out.println(p.toString());
    }

}
