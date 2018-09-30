/**
 * Com a lógica que eu desenvolvi, certos meses possuem somente dias específicos
 * da semana, por exemplo, de janeiro a fevereiro só existem domingos.
 */
package exercicioone;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Matheus
 */
public class Agenda
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ArrayList<Tarefa> agenda = new ArrayList<>();
        agenda.add(new Tarefa(new Data(19, 1), "Teste"));
        agenda.add(new Tarefa(new Data(19, 3), "Teste"));
        agenda.add(new Tarefa(new Data(19, 5), "Teste"));
        agenda.add(new Tarefa(new Data(19, 7), "Teste"));
        agenda.add(new Tarefa(new Data(19, 9), "Teste2"));
        agenda.add(new Tarefa(new Data(19, 10), "Teste2"));
        agenda.add(new Tarefa(new Data(19, 11), "Teste2"));

        /**
         * Exemplo de chamada ao método requerido pelo exercício para a tarefa
         * de posição 0 na agenda. O retorno será domingo, pois, dia 19 de
         * fevereiro (mês 1) é num domingo, de acordo com a lógica proposta.
         */
        System.out.println(agenda.get(0).getDiaSemana());
        System.out.println(agenda.get(0).getMonth());
    }

}
