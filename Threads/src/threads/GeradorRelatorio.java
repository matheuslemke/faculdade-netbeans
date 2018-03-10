package threads;

/**
 *
 * @author Matheus
 */
public class GeradorRelatorio implements Runnable
{

    @Override
    public void run()
    {
        for (int i = 0; i < 50; i++)
        {
            System.out.println("Linha: "+i);
        }
    }

}
