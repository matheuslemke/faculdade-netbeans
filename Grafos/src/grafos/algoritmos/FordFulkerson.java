/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.algoritmos;

import grafo.CaminhoFluxoRedes;
import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author matheus
 */
public class FordFulkerson
{

    private final Vertice start;
    private final Vertice finish;
    private final Grafo grafo;

    public FordFulkerson(Vertice start, Vertice finish, Grafo grafo)
    {
        this.start = start;
        this.finish = finish;
        this.grafo = grafo;
    }

    public int iniciar()
    {
        CaminhoFluxoRedes caminho = acharCaminho();

        for (Vertice vertice : caminho.getVertices())
        {
            System.out.println("Caminho encontrado: ");

            System.out.println(vertice.getNome());
        }

        int total = 0;

        while (!caminho.isNull())
        {
            encontrarLimitante(caminho);
            definirLimitante(caminho);
            total += caminho.getLimitante();

            caminho = acharCaminho();
        }

//        while (caminho)
//        {
//            caminho[0] = start;
//            while vertice != finish
//            {
//                testa saturacao
//                caminho = caminho + vertice
//            }
//        }
//        depois percorrer o caminho de novo para setar o fluxo de acordo com 
//        o limitante do caminho
        return total;
    }

    private void encontrarLimitante(CaminhoFluxoRedes caminho)
    {
        Vertice aux = finish;
        while (aux != start)
        {
            if (aux.getPai().getAresta(aux).getCapacidadeAtual() < caminho.getLimitante())
                caminho.setLimitante(aux.getPai().getAresta(aux).getCapacidadeAtual());
            aux = aux.getPai();
        }
    }

    private void definirLimitante(CaminhoFluxoRedes caminho)
    {
        for (Aresta aresta : caminho.getArestas())
        {
            aresta.setFluxo(aresta.getFluxo() + caminho.getLimitante());
        }
    }

    private CaminhoFluxoRedes acharCaminho()
    {
        Stack<Vertice> abertos = new Stack<>();
        LinkedList<Vertice> fechados = new LinkedList<>();
        Vertice x, aux = finish;
        CaminhoFluxoRedes caminho = new CaminhoFluxoRedes();

        abertos.push(start);

        while (!abertos.empty())
        {
            x = abertos.pop();

            if (x == finish)
            {
                while (aux != start)
                {
                    caminho.add(aux);
                    caminho.add(aux.getPai().getAresta(aux));
                    caminho.verificarLimitante(aux.getPai().getAresta(aux));
                    aux = aux.getPai();
                }
                return caminho;
            }
            for (Aresta aresta : x.getArestas())
            {
                if (aresta.getCapacidadeAtual() > 0
                        && !abertos.contains(aresta.getVertice())
                        && !fechados.contains(aresta.getVertice()))
                {
                    abertos.push(aresta.getVertice());
                    aresta.getVertice().setPai(x);
                }
            }
            fechados.addLast(x);

        }
        return caminho;
    }

    private CaminhoFluxoRedes encontrarCaminho()
    {
        CaminhoFluxoRedes caminho = new CaminhoFluxoRedes();

        Vertice v = start;

        while (v != finish)
        {
            System.out.println("V = " + v.getNome());
            for (Aresta aresta : v.getArestas())
            {
                if (aresta.getCapacidadeAtual() != 0)
                {
                    caminho.add(v);
                    caminho.add(aresta);
                    aresta.getVertice().setPai(v);

                    v = aresta.getVertice();
                    if (v == finish)
                        caminho.add(finish);
                    break;
                }
            }
            if (!caminho.getVertices().contains(finish))
                caminho = new CaminhoFluxoRedes();
        }
        return caminho;
    }

}
