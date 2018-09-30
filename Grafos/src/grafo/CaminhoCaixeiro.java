/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.util.ArrayList;

/**
 *
 * @author matheus
 */
public class CaminhoCaixeiro
{

    private ArrayList<Vertice> verticesCaminho = new ArrayList<>();
    private int custo;

    public CaminhoCaixeiro()
    {
    }

    public CaminhoCaixeiro(ArrayList<Vertice> verticesCAminho, int custo)
    {
        this.verticesCaminho = verticesCAminho;
        this.custo = custo;
    }

    public ArrayList<Vertice> getVerticesCaminho()
    {
        return verticesCaminho;
    }

    public void setVerticesCaminho(ArrayList<Vertice> verticesCaminho)
    {
        this.verticesCaminho = verticesCaminho;
    }

    public int getCusto()
    {
        return custo;
    }

    public void setCusto(int custo)
    {
        this.custo = custo;
    }

    public int custo(int n)
    {
        int result = 0;
        Vertice atual, proximo;
        Aresta aresta;

        for (int i = 0; i < n; i++)
        {
            atual = verticesCaminho.get(i);
            proximo = verticesCaminho.get(i + 1);
            aresta = atual.getAresta(proximo);
            result += aresta.getCapacidade();
        }

        return result;
    }
}
