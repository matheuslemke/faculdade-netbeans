package comparatorcomparable;

import java.util.Comparator;

/**
 *
 * @author Matheus
 */
public class ComparadorPessoaPorNome implements Comparator<Pessoa>
{

    @Override
    public int compare(Pessoa p1, Pessoa p2)
    {
        return p1.getNome().compareTo(p2.getNome());
    }
}
