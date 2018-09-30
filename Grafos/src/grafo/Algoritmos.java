/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author matheus
 */
public class Algoritmos
{

    public static ArrayList<CaminhoFluxoRedes> buscaLargura(Grafo g, Vertice start, Vertice finish)
    {
        ArrayList<CaminhoFluxoRedes> caminhos = new ArrayList<>();

        CaminhoFluxoRedes caminho = new CaminhoFluxoRedes();
        LinkedList<Vertice> fila = new LinkedList<>();
        fila.add(start);
        caminho.add(start);

        while (!fila.isEmpty())
        {
            Vertice v = fila.remove();

            for (Aresta aresta : v.getArestas())
            {
                if (aresta.getCapacidadeAtual() != 0)
                {
                    Vertice adj = aresta.getVertice();

                    if (adj == finish)
                    {
                        caminho.add(adj);

                    }

                    fila.add(adj);

                    caminho.add(adj);

                }
                
            }
        }

        return null;
    }
}
