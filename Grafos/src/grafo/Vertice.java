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
public class Vertice
{

    private String nome;
    private ArrayList<Aresta> arestas = new ArrayList<>();
    private boolean visitado = false;
    private Vertice pai;

    public void setPai(Vertice pai)
    {
        this.pai = pai;
    }

    public Vertice getPai()
    {
        return pai;
    }

    public Vertice(String nome)
    {
        this.nome = nome;
    }

    public boolean isVisitado()
    {
        return visitado;
    }

    public void setVisitado(boolean visitado)
    {
        this.visitado = visitado;
    }

    /**
     * @return the nome
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    /**
     * @return the arestas
     */
    public ArrayList<Aresta> getArestas()
    {
        return arestas;
    }

    /**
     * @param arestas the arestas to set
     */
    public void setArestas(ArrayList<Aresta> arestas)
    {
        this.arestas = arestas;
    }

    public void addAresta(Vertice v, int peso)
    {
        arestas.add(new Aresta(v, peso));
    }

    public Aresta getAresta(Vertice v)
    {
        for (Aresta aresta : arestas)
        {
            if (aresta.getVertice() == v)
                return aresta;
        }
        return null;
    }

    @Override
    public String toString()
    {
        return nome;
    }

}
