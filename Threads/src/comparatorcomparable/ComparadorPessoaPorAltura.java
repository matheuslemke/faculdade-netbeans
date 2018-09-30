package comparatorcomparable;

import java.util.Comparator;

/**
 *
 * @author Matheus
 */
public class ComparadorPessoaPorAltura implements Comparator<Pessoa>
{
    @Override
    public int compare(Pessoa p1, Pessoa p2)
    {
        if(p1.getAltura()< p2.getAltura())
            return -1;
        if(p1.getAltura()> p2.getAltura())
            return 1;
        return 0;
    }

}
