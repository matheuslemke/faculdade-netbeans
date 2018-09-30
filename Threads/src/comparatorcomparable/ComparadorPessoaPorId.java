package comparatorcomparable;

import java.util.Comparator;

/**
 *
 * @author Matheus
 */
public class ComparadorPessoaPorId implements Comparator<Pessoa>
{
    @Override
    public int compare(Pessoa p1, Pessoa p2)
    {
        if(p1.getRg() < p2.getRg())
            return -1;
        if(p1.getRg() > p2.getRg())
            return 1;
        return 0;
    }


}
