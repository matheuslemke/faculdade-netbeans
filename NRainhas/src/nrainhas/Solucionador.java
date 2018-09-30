/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrainhas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author matheus
 */
public class Solucionador
{

    private final int n;
    private final int nGeracoes, nAnticorpos, fatorMult, nSubstituidos, nGenesHipermutantes;
    private final int notaCorte = 5;
    private LinkedList<Integer[]> anticorpos, clones;
    private final Random random = new Random(System.currentTimeMillis());
    private final ComparadorDeAfinidade comparador = new ComparadorDeAfinidade();

    public Solucionador(int n, int nGeracoes, int nAnticorpos, int fatorMult, int nSubstituidos, int taxaHipermutacao)
    {
        this.n = n;
        this.nGeracoes = nGeracoes;
        this.nAnticorpos = nAnticorpos;
        this.fatorMult = fatorMult;
        this.nSubstituidos = nSubstituidos;
        this.nGenesHipermutantes = (n * taxaHipermutacao) / 100;
        this.anticorpos = new LinkedList<>();
    }

    public void solucionar()
    {
        gerarAnticorposAleatoriamente();
        calcularAfinidade(anticorpos);
        rankear(anticorpos);

        for (int i = 0; i < nGeracoes; i++)
        {
            System.out.println("Geração " + i);
            rankear(anticorpos);

            clonarAnticorpos();

            hipermutarClones();
            calcularAfinidade(clones);
            rankear(clones);

            rankear(clones);

            inserirClonesNaPopulacao();

            substituirAnticorpos();

            calcularAfinidade(anticorpos);

            rankear(anticorpos);
        }
        calcularAfinidade(anticorpos);
        rankear(anticorpos);
    }

    private void gerarAnticorposAleatoriamente()
    {
        for (int i = 0; i < nAnticorpos; i++)
        {
            anticorpos.add(gerarAnticorpoAleatoriamente());
        }
    }

    private Integer[] gerarAnticorpoAleatoriamente()
    {
        Integer[] anticorpo = new Integer[n + 1];
        anticorpo[n] = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++)
        {
            anticorpo[j] = random.nextInt(n);
        }
        return anticorpo;
    }

    private void calcularAfinidade(LinkedList<Integer[]> individuos)
    {
        for (Integer[] individuo : individuos)
        {
            calcularAfinidadeDoAnticorpo(individuo);
        }
    }

    private void calcularAfinidadeDoAnticorpo(Integer[] anticorpo)
    {
        int diferencaLinhas, diferencaValores;
        anticorpo[n] = 0; //inicializa o valor de fitness
        for (int j = 0; j < n - 1; j++)
        {
            for (int k = j + 1; k < n; k++)
            {
                if (Objects.equals(anticorpo[j], anticorpo[k]))
                {
                    anticorpo[n]++;
                }
                diferencaLinhas = Math.abs(j - k);
                diferencaValores = Math.abs(anticorpo[j] - anticorpo[k]);
                if (diferencaLinhas == diferencaValores)
                {
                    anticorpo[n]++;
                }
            }
        }
    }

    private void rankear(LinkedList<Integer[]> anticorpos)
    {
        Collections.sort(anticorpos, comparador);
    }

    private void selecaoPositiva(LinkedList<Integer[]> anticorpos)
    {
        for (int i = 0; i < anticorpos.size(); i++)
        {
            if (anticorpos.get(i)[n] > notaCorte)
            {
                Integer[] anticorpo = gerarAnticorpoAleatoriamente();
                calcularAfinidadeDoAnticorpo(anticorpo);
                //Pode criar um anticorpo sendo melhor que a nota de corte? (utilizando um while)
                anticorpos.set(i, anticorpo);
            }
        }
    }

    private void clonarAnticorpos()
    {
        int nClones;
        clones = new LinkedList<>();
        for (int i = 0; i < anticorpos.size(); i++)
        {
            nClones = Math.abs((fatorMult * anticorpos.size()) / (i + 1));
            for (int j = 0; j < nClones; j++)
            {
                clones.add(anticorpos.get(i).clone());
            }
        }
    }

    private void hipermutarClones()
    {
        LinkedList<Integer[]> clonesModificados = new LinkedList<>();
        for (Integer[] clone : clones)
        {
            Integer[] copiaClone = clone;
            ArrayList<Integer> genesHipermutarPorClone = gerarGenesHipermutantes();

            for (Integer gene : genesHipermutarPorClone)
            {
                //Se for a primeira posição gera um valor aleatório
                if (gene == 0)
                {
                    int novoValor = random.nextInt(n);

                    while (novoValor == copiaClone[gene])
                    {
                        novoValor = random.nextInt(n);
                    }
                    copiaClone[gene] = novoValor;
                } //Se não, ele soma +2 com o alelo do gene anterior

                else
                {
                    int valorSomar = random.nextInt(((n - 2) - 2) + 1) + 2;
                    copiaClone[gene] = (copiaClone[gene - 1] + valorSomar) % n;
                }
            }
            clonesModificados.add(copiaClone);
        }
        clones = clonesModificados;
    }

    private ArrayList<Integer> gerarGenesHipermutantes()
    {
        ArrayList<Integer> genesHipermutarPorClone = new ArrayList<>(nGenesHipermutantes);
        int gene;
        for (int i = 0; i < nGenesHipermutantes;)
        {
            gene = random.nextInt(n);
            //Garante que não serão gerados genes (índices) repetidos
            if (!genesHipermutarPorClone.contains(gene))
            {
                genesHipermutarPorClone.add(gene);
                i++;
            }
        }
        Collections.sort(genesHipermutarPorClone);
        return genesHipermutarPorClone;
    }

    private void inserirClonesNaPopulacao()
    {
        for (Integer[] clone : clones)
        {
            anticorpos.add(clone);
        }
    }

    private void substituirAnticorpos()
    {
        //Remove o número de anticorpos acima do número máximo. A remoção começa
        //pelos piores.
        for (int i = 0; (anticorpos.size() > nAnticorpos); i++)
        {
            anticorpos.remove(anticorpos.removeFirst());
        }

        //Substitui os piores por novos anticorpos aleatórios
        for (int i = 0, j = 0; i < anticorpos.size() && j < nSubstituidos; i++, j++)
        {
            anticorpos.set(i, gerarAnticorpoAleatoriamente());
        }
    }

    public void mostrarAnticorpos()
    {
        int i = 0;
        for (Integer[] anticorpo : anticorpos)
        {
            System.out.println("Anticorpo " + i);
            for (int j = 0; j < n + 1; j++)
            {
                System.out.print(anticorpo[j] + " ");
            }
            System.out.println("");
            i++;
        }
    }

    private void mostrarClones()
    {
        int i = 0;
        for (Integer[] clone : clones)
        {
            System.out.println("Clone " + i);
            for (int j = 0; j < n + 1; j++)
            {
                System.out.print(clone[j] + " ");
            }
            System.out.println("");
            i++;
        }
    }

    private void mostrarClone(Integer[] clone)
    {
        for (Integer alelo : clone)
        {
            System.out.print(alelo + " ");
        }
        System.out.println();
    }
}
