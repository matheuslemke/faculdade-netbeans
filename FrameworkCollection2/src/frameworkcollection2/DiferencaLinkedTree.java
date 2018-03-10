package frameworkcollection2;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 *
 * @author Matheus
 */
public class DiferencaLinkedTree
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Collection<Integer> cTree = new TreeSet<>();
        Collection<Integer> cLink = new LinkedHashSet<>();

        cTree.add(3);
        cTree.add(1);
        cTree.add(2);
        cLink.add(3);
        cLink.add(1);
        cLink.add(2);

        System.out.println("TreeSet = " + cTree.toString());
        System.out.println("LinkedHashSet = " + cLink.toString());
        System.out.println("\n\tA diferença entre TreeSet e LinkedHashSet é que "
                + "\no primeiro mantém a ordem dos elementos de forma crescente e"
                + "\no segundo mantém a ordem de acordo com a ordem em que estes"
                + "\nelementos foram inseridos.");
        
        System.out.println("\n\tO SortedSet é uma interface que mantém na ordem"
                + "\nnatural dos elementos, ou seja, ordem de classificação"
                + "\na partir da implementação de um Comparator");
    }

}
