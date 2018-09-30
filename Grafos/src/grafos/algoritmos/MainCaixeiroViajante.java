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
public class MainCaixeiroViajante
{

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        Vertice portoAlegre = new Vertice("Porto Alegre - RS");
        Vertice florianopolis = new Vertice("Florianópolis - SC");
        Vertice curitiba = new Vertice("Curitiba - PR");
        Vertice saoPaulo = new Vertice("São Paulo - SP");
        Vertice rioDeJaneiro = new Vertice("Rio de Janeiro - RJ");
        Vertice vitoria = new Vertice("Vitória - ES");
        Vertice beloHorizonte = new Vertice("Belo Horizonte - MG");
        Vertice brasilia = new Vertice("Brasília - DF");

        portoAlegre.addAresta(florianopolis, 476);
        portoAlegre.addAresta(curitiba, 711);
        portoAlegre.addAresta(saoPaulo, 1109);
        portoAlegre.addAresta(rioDeJaneiro, 1553);
        portoAlegre.addAresta(beloHorizonte, 1712);
        portoAlegre.addAresta(vitoria, 2001);
        portoAlegre.addAresta(brasilia, 2027);

        florianopolis.addAresta(portoAlegre, 476);
        florianopolis.addAresta(curitiba, 300);
        florianopolis.addAresta(saoPaulo, 705);
        florianopolis.addAresta(rioDeJaneiro, 1144);
        florianopolis.addAresta(beloHorizonte, 1301);
        florianopolis.addAresta(vitoria, 1597);
        florianopolis.addAresta(brasilia, 1673);

        curitiba.addAresta(portoAlegre, 711);
        curitiba.addAresta(florianopolis, 300);
        curitiba.addAresta(saoPaulo, 408);
        curitiba.addAresta(rioDeJaneiro, 852);
        curitiba.addAresta(beloHorizonte, 1004);
        curitiba.addAresta(vitoria, 1300);
        curitiba.addAresta(brasilia, 1366);

        saoPaulo.addAresta(portoAlegre, 1109);
        saoPaulo.addAresta(florianopolis, 705);
        saoPaulo.addAresta(curitiba, 408);
        saoPaulo.addAresta(rioDeJaneiro, 429);
        saoPaulo.addAresta(beloHorizonte, 586);
        saoPaulo.addAresta(vitoria, 882);
        saoPaulo.addAresta(brasilia, 1015);

        rioDeJaneiro.addAresta(portoAlegre, 1553);
        rioDeJaneiro.addAresta(florianopolis, 1144);
        rioDeJaneiro.addAresta(curitiba, 852);
        rioDeJaneiro.addAresta(saoPaulo, 429);
        rioDeJaneiro.addAresta(beloHorizonte, 434);
        rioDeJaneiro.addAresta(vitoria, 521);
        rioDeJaneiro.addAresta(brasilia, 1148);

        vitoria.addAresta(portoAlegre, 2001);
        vitoria.addAresta(florianopolis, 1597);
        vitoria.addAresta(curitiba, 1300);
        vitoria.addAresta(saoPaulo, 882);
        vitoria.addAresta(rioDeJaneiro, 521);
        vitoria.addAresta(beloHorizonte, 524);
        vitoria.addAresta(brasilia, 1239);

        beloHorizonte.addAresta(portoAlegre, 1712);
        beloHorizonte.addAresta(florianopolis, 1301);
        beloHorizonte.addAresta(curitiba, 1004);
        beloHorizonte.addAresta(saoPaulo, 586);
        beloHorizonte.addAresta(rioDeJaneiro, 434);
        beloHorizonte.addAresta(vitoria, 524);
        beloHorizonte.addAresta(brasilia, 716);

        brasilia.addAresta(portoAlegre, 2027);
        brasilia.addAresta(florianopolis, 1673);
        brasilia.addAresta(curitiba, 1366);
        brasilia.addAresta(saoPaulo, 1015);
        brasilia.addAresta(rioDeJaneiro, 1148);
        brasilia.addAresta(beloHorizonte, 716);
        brasilia.addAresta(vitoria, 1239);

        Grafo g = new Grafo();
        g.addVertice(portoAlegre);
        g.addVertice(florianopolis);
        g.addVertice(curitiba);
        g.addVertice(saoPaulo);
        g.addVertice(rioDeJaneiro);
        g.addVertice(beloHorizonte);
        g.addVertice(vitoria);
        g.addVertice(brasilia);

        CaixeiroViajante caixeiro = new CaixeiroViajante(portoAlegre, g, 8);
        caixeiro.viajar(portoAlegre, 0);

        System.out.println("\n\n\n---MELHOR CAMINHO---");
        System.out.println(caixeiro.getMelhorCaminho().getVerticesCaminho());
        System.out.print("COMPRIMENTO/CUSTO: ");
        System.out.println(caixeiro.getMelhorCaminho().getCusto());

    }

}
