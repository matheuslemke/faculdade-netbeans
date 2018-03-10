/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparatorcomparable;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Matheus
 */
public class Main1
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Pessoa p1 = new Pessoa(1, "João", "Ferreira", 1.78, 89);
        Pessoa p2 = new Pessoa(2, "Maria", "Neves", 1.67, 76.5);
        Pessoa p3 = new Pessoa(3, "José", "Flores", 1.66, 83.7);
        Pessoa p4 = new Pessoa(4, "Ana", "Cruz", 1.69, 79);

        ArrayList<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(p2);
        pessoas.add(p4);
        pessoas.add(p3);
        pessoas.add(p1);

        System.out.println("ordem de inserção:".toUpperCase());
        for (Pessoa pessoa : pessoas)
        {
            System.out.println("Rg: " + pessoa.getRg() + "\nNome: " + pessoa.getNome() + "\nAltura: " + pessoa.getAltura() + "\nPeso: " + pessoa.getPeso());
            System.out.println("");
        }

        Collections.sort(pessoas);
        System.out.println("\n\nSort:".toUpperCase());
        for (Pessoa pessoa : pessoas)
        {
            System.out.println("Rg: " + pessoa.getRg() + "\nNome: " + pessoa.getNome() + "\nAltura: " + pessoa.getAltura() + "\nPeso: " + pessoa.getPeso());
            System.out.println("");
        }

        Collections.sort(pessoas, new ComparadorPessoaPorNome());
        System.out.println("\n\nPor nome:".toUpperCase());
        for (Pessoa pessoa : pessoas)
        {
            System.out.println("Rg: " + pessoa.getRg() + "\nNome: " + pessoa.getNome() + "\nAltura: " + pessoa.getAltura() + "\nPeso: " + pessoa.getPeso());
            System.out.println("");
        }

        Collections.sort(pessoas, new ComparadorPessoaPorId());
        System.out.println("\n\nPor Id:".toUpperCase());
        for (Pessoa pessoa : pessoas)
        {
            System.out.println("Rg: " + pessoa.getRg() + "\nNome: " + pessoa.getNome() + "\nAltura: " + pessoa.getAltura() + "\nPeso: " + pessoa.getPeso());
            System.out.println("");
        }

        Collections.sort(pessoas, new ComparadorPessoaPorAltura());
        System.out.println("\n\nPor altura:".toUpperCase());
        for (Pessoa pessoa : pessoas)
        {
            System.out.println("Rg: " + pessoa.getRg() + "\nNome: " + pessoa.getNome() + "\nAltura: " + pessoa.getAltura() + "\nPeso: " + pessoa.getPeso());
            System.out.println("");
        }

        Collections.sort(pessoas, new ComparadorPessoaPorPeso());
        System.out.println("\n\nPor peso:".toUpperCase());
        for (Pessoa pessoa : pessoas)
        {
            System.out.println("Rg: " + pessoa.getRg() + "\nNome: " + pessoa.getNome() + "\nAltura: " + pessoa.getAltura() + "\nPeso: " + pessoa.getPeso());
            System.out.println("");
        }
    }

}
