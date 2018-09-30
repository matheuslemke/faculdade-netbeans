/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.algoritmos;

import grafo.Aresta;
import grafo.CaminhoFluxoRedes;
import grafo.Grafo;
import grafo.Vertice;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author matheus
 */
public class EdmondsKarp
{

    private final Vertice start;
    private final Vertice finish;
    private final Grafo grafo;

    public EdmondsKarp(Vertice start, Vertice finish, Grafo grafo)
    {
        this.start = start;
        this.finish = finish;
        this.grafo = grafo;
    }

    public int iniciar()
    {
        ArrayList<CaminhoFluxoRedes> caminhos = new ArrayList<>();
        CaminhoFluxoRedes menorCaminho;
        int total = 0;

        CaminhoFluxoRedes c_aux = acharCaminho();

        while (!c_aux.isNull())
        {
            System.out.println("Caminho encontrado: ");
            for (Vertice vertice : c_aux.getVertices())
            {
                System.out.println(vertice.getNome());
            }
            caminhos.add(c_aux);

            definirFluxo(c_aux);

            total += c_aux.getLimitante();
            c_aux = acharCaminho();

        }

//        while (!caminhos.isEmpty())
//        {
//            menorCaminho = caminhos.get(0);
//            for (CaminhoFluxoRedes caminho : caminhos)
//            {
//                if (menorCaminho.getVertices().size() > caminho.getVertices().size())
//                {
//                    menorCaminho = caminho;
//                }
//            }
//
//            definirFluxo(menorCaminho);
//            caminhos.remove(menorCaminho);
//            total += menorCaminho.getLimitante();
//        }
        return total;

    }

    private void definirFluxo(CaminhoFluxoRedes c)
    {
        for (Aresta aresta : c.getArestas())
        {
            aresta.setFluxo(aresta.getFluxo() + c.getLimitante());
        }
    }

    private CaminhoFluxoRedes acharCaminho()
    {
        LinkedList<Vertice> abertos = new LinkedList<>();
        LinkedList<Vertice> fechados = new LinkedList<>();
        CaminhoFluxoRedes caminho = new CaminhoFluxoRedes();
        Vertice x, aux = finish;

        abertos.addLast(start);

        while (!abertos.isEmpty())
        {
            x = abertos.remove();

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
                    abertos.addLast(aresta.getVertice());
                    aresta.getVertice().setPai(x);
                }
            }
            fechados.addLast(x);
        }
        return caminho;
    }

}
