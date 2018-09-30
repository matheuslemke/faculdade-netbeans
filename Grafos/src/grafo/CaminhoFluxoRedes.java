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
public class CaminhoFluxoRedes
{

    private ArrayList<Vertice> vertices = new ArrayList<>();
    private ArrayList<Aresta> arestas = new ArrayList<>();
    private int limitante = Integer.MAX_VALUE;
    private int peso; //não precisa ser usado sempre

    public CaminhoFluxoRedes(int peso, ArrayList<Vertice> vertices)
    {
        this.vertices = vertices;
        this.peso = peso;
    }

    public CaminhoFluxoRedes(ArrayList<Vertice> vertices)
    {
        this.vertices = vertices;
    }

    public CaminhoFluxoRedes()
    {
    }

    public CaminhoFluxoRedes(int peso)
    {
        this.peso = peso;
    }

    /**
     * @return the vertices
     */
    public ArrayList<Vertice> getVertices()
    {
        return vertices;
    }

    /**
     * @param vertices the vertices to set
     */
    public void setVertices(ArrayList<Vertice> vertices)
    {
        this.vertices = vertices;
    }

    /**
     * @return the limitante
     */
    public int getLimitante()
    {
        return limitante;
    }

    /**
     * @param limitante the limitante to set
     */
    public void setLimitante(int limitante)
    {
        this.limitante = limitante;
    }

    public void add(Vertice v)
    {
        vertices.add(v);
    }

    public void add(Aresta a)
    {
        arestas.add(a);
    }

    public void setArestas(ArrayList<Aresta> arestas)
    {
        this.arestas = arestas;
    }

    public ArrayList<Aresta> getArestas()
    {
        return arestas;
    }

    public boolean isNull()
    {
        return vertices.isEmpty() && arestas.isEmpty() && limitante == Integer.MAX_VALUE && peso == 0;
    }

    public void verificarLimitante(Vertice v, Vertice adj)
    {
        if (v.getAresta(adj).getCapacidadeAtual() < limitante)
            limitante = v.getAresta(adj).getCapacidadeAtual();
    }

    public void verificarLimitante(Aresta a)
    {
        if (a.getCapacidadeAtual() < limitante)
            limitante = a.getCapacidadeAtual();
    }

}
