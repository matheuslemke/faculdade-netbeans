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
public class Grafo
{

    private ArrayList<Vertice> verticesArray = new ArrayList<>();

    public Grafo()
    {
    }

    public Grafo(ArrayList<Vertice> verticesArray)
    {
        this.verticesArray = verticesArray;
    }

    public ArrayList<Vertice> getVerticesArray()
    {
        return verticesArray;
    }

    public void setVerticesArray(ArrayList<Vertice> verticesArray)
    {
        this.verticesArray = verticesArray;
    }

    public void setNaoVisitados()
    {
        verticesArray.stream().forEach((Vertice vertice) ->
        {
            vertice.setVisitado(false);
        });
    }

    public void addVertice(Vertice v)
    {
        verticesArray.add(v);
    }

}
