/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.algoritmos;

import grafo.*;

/**
 *
 * @author matheus
 */
public class MainFulkersonEdmonds
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Vertice one = new Vertice("1");
        Vertice two = new Vertice("2");
        Vertice thr = new Vertice("3");
        Vertice fou = new Vertice("4");
        Vertice fiv = new Vertice("5");
        Vertice six = new Vertice("6");
        Vertice sev = new Vertice("7");
        Vertice eig = new Vertice("8");

        one.addAresta(two, 20);
        one.addAresta(thr, 10);

        two.addAresta(thr, 12);
        two.addAresta(six, 15);

        thr.addAresta(fou, 9);
        thr.addAresta(fiv, 7);

        fou.addAresta(fiv, 6);
        fou.addAresta(eig, 12);

        fiv.addAresta(two, 5);
        fiv.addAresta(six, 4);
        fiv.addAresta(sev, 8);

        six.addAresta(sev, 15);

        eig.addAresta(sev, 8);

        Grafo g = new Grafo();
        g.addVertice(one);
        g.addVertice(two);
        g.addVertice(thr);
        g.addVertice(fou);
        g.addVertice(fiv);
        g.addVertice(six);
        g.addVertice(sev);
        g.addVertice(eig);

        EdmondsKarp ek = new EdmondsKarp(one, sev, g);
        System.out.println(ek.iniciar());
        
        System.out.println("\n\n\n\n\n");
        
        
        FordFulkerson ff = new FordFulkerson(one, sev, g);
        System.out.println(ff.iniciar());
        
        
    }

}
