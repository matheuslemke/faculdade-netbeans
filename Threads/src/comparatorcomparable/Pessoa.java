package comparatorcomparable;

/**
 *
 * @author Matheus
 */
public class Pessoa implements Comparable<Pessoa>
{

    private int rg;
    private String nome;
    private String sobrenome;
    private double altura;
    private double peso;

    public Pessoa(int rg, String nome, String sobrenome, double altura, double peso)
    {
        this.rg = rg;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.altura = altura;
        this.peso = peso;
    }

    public Pessoa()
    {
    }

    public int getRg()
    {
        return rg;
    }

    public String getNome()
    {
        return nome;
    }

    public double getAltura()
    {
        return altura;
    }

    public double getPeso()
    {
        return peso;
    }

    @Override
    public int compareTo(Pessoa p)
    {
        if (this.rg < p.getRg())
        {
            return -1;
        }
        if (this.rg > p.getRg())
        {
            return 1;
        }
        return 0;

    }

}
