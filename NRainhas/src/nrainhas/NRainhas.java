/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrainhas;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author matheus
 */
public class NRainhas
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // (n, gerações, anticorpos, fator*, nSubstituidos, taxaHipermutaçãoPorClone)
        Solucionador solucionador = new Solucionador(8, 10, 100, 5, 30, 25);
        solucionador.solucionar();
        solucionador.mostrarAnticorpos();
    }
}
