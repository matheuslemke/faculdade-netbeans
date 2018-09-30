/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aestrela;

import java.util.ArrayList;

/**
 *
 * @author toshi
 */
public class AEstrela
{

    private ArrayList<Cidade> cidades = new ArrayList<>();

    private ArrayList<Cidade> abertos = new ArrayList<>();
    private ArrayList<Cidade> fechados = new ArrayList<>();

    private Cidade pontoPartida;
    private Cidade meta;

    public AEstrela(ArrayList<Cidade> cidades, Cidade pontoPartida, Cidade meta)
    {
        this.pontoPartida = pontoPartida;
        this.meta = meta;
    }

    public ArrayList<Cidade> iniciar()
    {
        pontoPartida.setCaminhoPercorrido(0.0);
        pontoPartida.calcularCaminhoEstimado(meta);
        pontoPartida.calcularFuncao();
        abertos.add(pontoPartida);

        while (!abertos.isEmpty())
        {
            Cidade atual = menorFuncao();

            if (atual.equals(meta))
            {
                return fechados;
            }

            for (Cidade sucessor : atual.getSucessores().keySet())
            {
                atual.getSucessores().put(sucessor, atual.calcularDistancia(sucessor)); //atribuir distância entre atual e sucessor

                System.out.print("De " + atual.getNome() + " para " + sucessor.getNome() + " = ");
                System.out.println(atual.getSucessores().get(sucessor));

                sucessor.calcularCaminhoEstimado(meta);
                sucessor.setCaminhoPercorrido(atual.getCaminhoPercorrido() + atual.getSucessores().get(sucessor));
                sucessor.calcularFuncao();

                imprimir(sucessor);

                if (abertos.contains(sucessor))
                {
                    abertos.remove(sucessor);
                } else if (fechados.contains(sucessor))
                {
                    fechados.remove(sucessor);
                }
                abertos.add(sucessor);
            }

            abertos.remove(atual);
            fechados.add(atual);

        }
        return fechados;
    }

    public Cidade menorFuncao()
    {
        Cidade melhor = abertos.get(0);
        if (abertos.size() > 1)
        {
            for (Cidade cidade : abertos)
            {
                if (cidade.getFuncao() < melhor.getFuncao())
                {
                    melhor = cidade;
                }
            }
        }
        return melhor;
    }

    private void imprimir(Cidade sucessor)
    {

        System.out.println(sucessor.getNome());

        System.out.print("g(" + sucessor.getNome() + ") = ");
        System.out.printf("%.2f\n", sucessor.getCaminhoPercorrido());

        System.out.print("h(" + sucessor.getNome() + ") = ");
        System.out.println(sucessor.getCaminhoEstimado());

        System.out.print("f(" + sucessor.getNome() + ") = ");
        System.out.printf("%.2f\n", sucessor.getFuncao());

        System.out.println();

    }

    public ArrayList<Cidade> mostrarMelhorCaminho(ArrayList<Cidade> fechados)
    {
        ArrayList<Cidade> melhor = new ArrayList<>();

        melhor.add(pontoPartida);
        fechados.add(meta);
        for (Cidade nextFechado : fechados)
        {
            for (Cidade nextSucessor : nextFechado.getSucessores().keySet())
            {
                if (!melhor.contains(nextSucessor))
                {
                    if (fechados.contains(nextSucessor))
                    {
                        for (Cidade nextSucessor2 : nextSucessor.getSucessores().keySet())
                        {
                            if (fechados.contains(nextSucessor2))
                            {
                                melhor.add(nextSucessor);
                                break;
                            }
                        }
                    }
                }
            }
        }
        melhor.add(meta);
        return melhor;
    }
}
