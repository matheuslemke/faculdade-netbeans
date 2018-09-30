package comparatorcomparable;

import java.util.Comparator;

/**
 *
 * @author Matheus
 */
public class ComparadorPessoaPorPeso implements Comparator<Pessoa>
{

    @Override
    public int compare(Pessoa p1, Pessoa p2)
    {
        if(p1.getPeso()< p2.getPeso())
            return -1;
        if(p1.getPeso()> p2.getPeso())
            return 1;
        return 0;
    }

}
