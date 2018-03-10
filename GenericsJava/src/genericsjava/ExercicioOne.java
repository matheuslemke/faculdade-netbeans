package genericsjava;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class ExercicioOne
{

    /**
     * Exercício 1
     */
    public static <T extends Integer> int countOddInteger(List<T> l)
    {
        int number = 0;
        for (T item : l)
        {
            if (item % 2 != 0)
            {
                number++;
            }
        }
        return number;
    }

    /**
     * Exercício 2 Não vai funcionar pois java não permite a utilização de
     * operadores primitivos quando os operados são do tipo Generics.
     *
     */
    /**
     * Exercicio 3
     */
    public static <A> void exchange(A[] a)
    {
        A aux;
        if (a.length > 1)
        {
            aux = a[0];
            a[0] = a[1];
            a[1] = aux;
        }
    }

    /**
     * Exercício 7 Sim, vai funcionar normalmente.
     *
     */
    public static void print(List<? extends Number> list)
    {
        for (Number n : list)
        {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    /**
     * Exercício 8
     *
     */
    public static <T extends Comparable> T maximal(List<T> l, int begin, int end)
    {
        T max = null;
        for (int i = begin; i <= end; i++)
        {
            if (l.get(i).compareTo(max) > 0)
            {
                max = l.get(i);
            }
        }
        return max;
    }

    /**
     * Exercício 9 Não vai compilar, pois um tipo genérico não pode ser
     * instanciado em um escopo estático.
     *
     */
    /*
     public static class Singleton<T>
     {

     public static T getInstance()
     {
     if (instance == null)
     {
     instance = new Singleton<T>();
     }

     return instance;
     }

     private static T instance = null;
     }
     */
    /**
     * Exercício 10 Não vai funcionar pois as instâncias ns e nc não são do
     * mesmo tipo.
     *
     */
    class Shape
    { /* ... */ }

    class Circle extends Shape
    { /* ... */ }

    class Rectangle extends Shape
    { /* ... */ }

    class Node<T>
    { /* ... */ }

    /**
     * Node<Circle> nc = new Node<>(); Node<Shape> ns = nc;
     *
     */
    /**
     * Exercício 11 Sim.
     *
     */
    /**
     * Exercício 12
     *
     *
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ArrayList<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        System.out.println(countOddInteger(l));
        Integer vetor[] =
        {
            0, 0
        };
        vetor[0] = 2;
        vetor[1] = 1;
        exchange(vetor);

        print(l);
    }

}

/**
 * Exercício 5 Converte tudo para Object
 *
 */
/**
 * Exercício 9 Não vai compilar, pois um tipo genérico não pode ser instanciado
 * em um escopo estático.
 *
 */
/**
 * Exercício 10 Não vai funcionar pois as instâncias ns e nc não são do mesmo
 * tipo.
 *
 */



