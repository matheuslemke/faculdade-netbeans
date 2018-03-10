/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio3;

/**
 *
 * @author Matheus
 */
public class NewMain3
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Gasolina g = new Gasolina();
        Diesel d = new Diesel();
        Automovel a = new Automovel();
        a.abastecer(g, 10);
        System.out.println(a.getLitros());
    }

}
