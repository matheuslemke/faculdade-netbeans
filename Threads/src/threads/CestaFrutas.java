package threads;

/**
 *
 * @author Matheus
 */
public class CestaFrutas implements Runnable
{

    @Override
    public void run()
    {
        String [] ingredientes = {"Banana", "Mamão", "Maçã", "Abacate"};
        System.out.println("Início do run()");
        for (String fruta : ingredientes)
        {
            System.out.println(fruta);
            try
            {
                Thread.sleep(3*1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Fim do run()");
    }

}
