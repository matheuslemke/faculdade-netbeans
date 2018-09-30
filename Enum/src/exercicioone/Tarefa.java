package exercicioone;


/**
 *
 * @author Matheus
 */
public class Tarefa
{

    private Data data;
    private String descricao;

    public Tarefa(Data data, String descricao)
    {
        this.data = data;
        this.descricao = descricao;
    }

    /**
     * Aqui a função requerida pelo exercício.
     *
     * @return dia da semana.
     */
    public DiaSemana getDiaSemana()
    {
        if (data.getMonth() < 2)
        {
            return DiaSemana.DOMINGO;
        }
        if (data.getMonth() < 4)
        {
            return DiaSemana.SEGUNDA;
        }
        if (data.getMonth() < 6)
        {
            return DiaSemana.TERCA;
        }
        if (data.getMonth() < 8)
        {
            return DiaSemana.QUARTA;
        }
        if (data.getMonth() < 10)
        {
            return DiaSemana.QUINTA;
        }
        if (data.getMonth() == 10)
        {
            return DiaSemana.SEXTA;
        }
        else
        {
            return DiaSemana.SABADO;
        }
    }
    
    public Mes getMonth()
    {
        if (data.getMonth() == 0)
        {
            return Mes.JANEIRO;
        }
        if (data.getMonth() == 1)
        {
            return Mes.FEVEREIRO;
        }
        if (data.getMonth() == 2)
        {
            return Mes.MARCO;
        }
        if (data.getMonth() == 3)
        {
            return Mes.ABRIL;
        }
        if (data.getMonth() == 4)
        {
            return Mes.MAIO;
        }
        if (data.getMonth() == 5)
        {
            return Mes.JUNHO;
        }
        if (data.getMonth() == 6)
        {
            return Mes.JULHO;
        }
        if (data.getMonth() == 7)
        {
            return Mes.AGOSTO;
        }
        if (data.getMonth() == 8)
        {
            return Mes.SETEMBRO;
        }
        if (data.getMonth() == 9)
        {
            return Mes.OUTUBRO;
        }
        if (data.getMonth() == 10)
        {
            return Mes.NOVEMBRO;
        }
        if (data.getMonth() == 11)
        {
            return Mes.DEZEMBRO;
        }
        else return null;
    }
}
