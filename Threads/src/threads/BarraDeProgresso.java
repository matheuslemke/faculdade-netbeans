package threads;

/**
 *
 * @author Matheus
 */
public class BarraDeProgresso implements Runnable
{

    @Override
    public void run()
    {
        for (int i = 0; i < 20; i++)
        {
            System.out.println("== Barra: "+i);
        }
    }

}
