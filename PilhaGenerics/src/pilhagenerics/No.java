package pilhagenerics;

public class No<T>
{

    private T value;
    private No<T> link;

    public No(T value, No<T> link)
    {
        this.value = value;
        this.link = link;
    }

    /**
     * @return the value
     */
    public T getValue()
    {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(T value)
    {
        this.value = value;
    }

    /**
     * @return the link
     */
    public No<T> getLink()
    {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(No<T> link)
    {
        this.link = link;
    }

    @Override
    public String toString()
    {
        return "No{" + "value=" + value + ", link=" + link + '}';
    }

}
