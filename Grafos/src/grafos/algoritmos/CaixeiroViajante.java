/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.algoritmos;

import grafo.*;
import java.util.ArrayList;

/**
 *
 * @author matheus
 */
public class CaixeiroViajante
{

    private Vertice start;
    private Grafo grafo;
    private int n, it = 1;
    private CaminhoCaixeiro melhorCaminho = new CaminhoCaixeiro();
    private CaminhoCaixeiro caminhoAtual = new CaminhoCaixeiro();

    public CaixeiroViajante()
    {
    }

    public CaixeiroViajante(Vertice start, Grafo grafo, int n)
    {
        this.start = start;
        this.grafo = grafo;
        this.n = n;
        initCaminhos();
    }

    private void initCaminhos()
    {
        melhorCaminho.setVerticesCaminho(new ArrayList<>());
        caminhoAtual.setVerticesCaminho(new ArrayList<>());
        melhorCaminho.setCusto(Integer.MAX_VALUE);

        for (int i = 0; i < n; i++)
        {
            caminhoAtual.getVerticesCaminho().add(null);
            melhorCaminho.getVerticesCaminho().add(null);
        }
        caminhoAtual.getVerticesCaminho().add(start);
        melhorCaminho.getVerticesCaminho().add(start);

    }

    public int getIt()
    {
        return it;
    }

    public void setIt(int it)
    {
        this.it = it;
    }

    public CaminhoCaixeiro getMelhorCaminho()
    {
        return melhorCaminho;
    }

    public void setMelhorCaminho(CaminhoCaixeiro melhorCaminho)
    {
        this.melhorCaminho = melhorCaminho;
    }

    public CaminhoCaixeiro getCaminhoAtual()
    {
        return caminhoAtual;
    }

    public void setCaminhoAtual(CaminhoCaixeiro caminhoAtual)
    {
        this.caminhoAtual = caminhoAtual;
    }

    public Vertice getStart()
    {
        return start;
    }

    public void setStart(Vertice start)
    {
        this.start = start;
    }

    public Grafo getGrafo()
    {
        return grafo;
    }

    public void setGrafo(Grafo grafo)
    {
        this.grafo = grafo;
    }

    public int getN()
    {
        return n;
    }

    public void setN(int n)
    {
        this.n = n;
    }

    public void viajar(Vertice vertice, int nivel)
    {
        vertice.setVisitado(true);

        caminhoAtual.getVerticesCaminho().set(nivel, vertice);

        if (nivel == (n - 1))
        {
            int custo = caminhoAtual.custo(n);
            caminhoAtual.setCusto(custo);

            System.out.println("Iteração número " + it);
            it++;

            if (caminhoAtual.getCusto() < melhorCaminho.getCusto())
            {
                for (int i = 0; i < n; i++)
                {
                    melhorCaminho.getVerticesCaminho().set(i, caminhoAtual.getVerticesCaminho().get(i));
                }

                melhorCaminho.setCusto(caminhoAtual.getCusto());

                System.out.println("    --ACHOU UM MELHOR!--\n" + melhorCaminho.getVerticesCaminho()
                        + "\nCusto: " + melhorCaminho.getCusto() + "\n\n");

            }
            else if (caminhoAtual.getCusto() == melhorCaminho.getCusto())
                System.out.println("    --IGUAL AO MELHOR!--\n" + caminhoAtual.getVerticesCaminho()
                        + "\nCusto: " + caminhoAtual.getCusto() + "\n\n");
            else
                System.out.println(caminhoAtual.getVerticesCaminho() + "\nCusto: " + caminhoAtual.getCusto() + "\n\n");

        }
        else
            for (Aresta aresta : vertice.getArestas())
            {
                Vertice proximo = aresta.getVertice();
                if (!proximo.isVisitado())
                {
                    viajar(proximo, nivel + 1);
                    proximo.setVisitado(false);
                }
            }
    }
}
