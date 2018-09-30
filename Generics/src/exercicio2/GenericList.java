package exercicio2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 * @param <T>
 */
public class GenericList<T> extends ArrayList<T>
{

    public GenericList()
    {
    }

    public List<T> newListPair()
    {
        GenericList<T> l = new GenericList<>();

        for (int i = 0; i < this.size(); i = i + 2)
        {
            l.add(this.get(i));
        }
        return l;
    }

    public List<T> newListOdd()
    {
        GenericList<T> l = new GenericList<>();

        for (int i = 1; i < this.size(); i = i + 2)
        {
            l.add(this.get(i));
        }
        return l;
    }
}
