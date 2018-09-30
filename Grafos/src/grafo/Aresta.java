/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

/**
 *
 * @author matheus
 */
public class Aresta
{

    private Vertice vertice;
    private int capacidade; //ou peso
    private int fluxo;
    private Vertice pai;

    public Aresta(Vertice vertice, int capacidade)
    {
        this.vertice = vertice;
        this.capacidade = capacidade;
    }

    public Vertice getPai()
    {
        return pai;
    }

    public void setPai(Vertice pai)
    {
        this.pai = pai;
    }

    /**
     * @return the vertice
     */
    public Vertice getVertice()
    {
        return vertice;
    }

    /**
     * @param vertice the vertice to set
     */
    public void setVertice(Vertice vertice)
    {
        this.vertice = vertice;
    }

    /**
     * @return the capacidade
     */
    public int getCapacidade()
    {
        return capacidade;
    }

    /**
     * @param capacidade the capacidade to set
     */
    public void setCapacidade(int capacidade)
    {
        this.capacidade = capacidade;
    }

    /**
     * @return the fluxo
     */
    public int getFluxo()
    {
        return fluxo;
    }

    /**
     * @param fluxo the fluxo to set
     */
    public void setFluxo(int fluxo)
    {
        this.fluxo = fluxo;
    }

    public int getCapacidadeAtual()
    {
        return capacidade - fluxo;
    }

}
