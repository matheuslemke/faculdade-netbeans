package exercicio3;

/**
 *
 * @author Matheus
 */
public class Automovel
{
    private int litros;

    public Automovel()
    {
    }    
    
    public <C extends Combustivel & CombustivelAutomovel> void abastecer(C combustivel, int litros)
    {
        this.litros+=litros;
    }

    public int getLitros()
    {
        return litros;
    }
    
}
