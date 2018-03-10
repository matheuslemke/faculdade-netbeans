package exercicio2;

/**
 *
 * @author Matheus
 */
public class NewMain2
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        GenericList<Number> gl = new GenericList<>();
        gl.add(0);
        gl.add(1);
        gl.add(2);
        gl.add(3);
        gl.add(4);
        gl.add(5);
        gl.add(6.8);
        System.out.println(gl);
        System.out.println(gl.newListPair());
        System.out.println(gl.newListOdd());
    }

}
