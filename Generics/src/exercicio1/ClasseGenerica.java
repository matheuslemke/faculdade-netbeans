package exercicio1;

public class ClasseGenerica
{

    public ClasseGenerica()
    {
    }

    public static <T extends Number & Comparable<T>> T maior(T a, T b)
    {
        if(a.compareTo(b) > 0)
            return a;
        return b;
    }
    
    public static <T extends Number & Comparable<T>> T menor(T a, T b)
    {
        if(a.compareTo(b) < 0)
            return a;
        return b;
    }
    
    public static <T extends Number & Comparable<T>> boolean isIgual(T a, T b)
    {
        return(a.compareTo(b) == 0);
    }
    
}
