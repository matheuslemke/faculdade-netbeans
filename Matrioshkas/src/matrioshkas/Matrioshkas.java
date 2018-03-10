package matrioshkas;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Matheus
 */
public class Matrioshkas
{

    static void fechar()
    {
        System.out.println("Try again!");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Stack<Integer> valores = new Stack<>();
        Stack<Integer> capacidades = new Stack<>();

        Scanner in = new Scanner(System.in);

        if (in.hasNext())
        {
            int x = in.nextInt();

            if (x < 0)
            {
                if (valores.empty())
                {
                    valores.push(x);
                    capacidades.push(-x - 1);
                }
                else
                {
                    if (-x <= capacidades.peek())
                    {
                        valores.push(x);
                        capacidades.push(x + capacidades.pop());
                        capacidades.push(-x - 1);
                    }
                    else
                    {
                        System.out.println("Try again!");
                    }

                }
            }
            else
            {
                if (x == (-valores.peek()))
                {
                    valores.pop();
                    capacidades.pop();
                }
                else
                {
                    System.out.println("Try again!");
                }
            }
        }
        if(valores.isEmpty() && capacidades.isEmpty())
        {
            System.out.println("Matrioshka!");
        }
        
        
    }

}
