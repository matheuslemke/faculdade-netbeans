package exercicio2aula8;

import java.util.Comparator;

/**
 *
 * @author Matheus
 */
public class ComparatorMovimentacaoCrescenteValor implements Comparator<Movimentacao>
{

    @Override
    public int compare(Movimentacao o1, Movimentacao o2)
    {
        if (o1.getData().before(o2.getData()))
        {
            return -1;
        }
        else
        {
            if (o1.getData().after(o2.getData()))
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }

}
