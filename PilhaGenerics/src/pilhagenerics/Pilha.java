package pilhagenerics;

public class Pilha<T>
{

    private No<T> topo = null;

    public Pilha()
    {
    }

    public boolean isVazia()
    {
        return this.topo == null;
    }

    public void push(T t)
    {
        No<T> n = new No<>(t, null);
        if (this.isVazia() == true)
        {
            this.topo = n;
        }
        else
        {
            No<T> temp = topo;
            while (temp.getLink() != null)
            {
                temp = temp.getLink();
            }
            temp.setLink(n);
        }
    }

    public void pop()
    {
        if (this.isVazia() == true)
        {
            return;
        }
        if (topo.getLink() == null)
        {
            topo = null;
        }
        else
        {
            No<T> temp = topo.getLink();
            No<T> ant = topo;
            while (temp.getLink() != null)
            {
                ant = temp;
                temp = temp.getLink();
            }
            ant.setLink(null);
        }
    }

    @Override
    public String toString()
    {
        No<T> temp = topo;
        StringBuilder sb = new StringBuilder();
        sb.append("Pilha\n");
        while (temp != null)
        {
            sb.append(temp.getValue());
            sb.append("\n");
            temp = temp.getLink();
        }
        return sb.toString();
    }

}
