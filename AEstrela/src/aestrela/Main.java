/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aestrela;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author toshi
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Cidade a = new Cidade("A", 0, 0);
        Cidade b = new Cidade("B", 24, 0);
        Cidade c = new Cidade("C", 19, 1);
        Cidade d = new Cidade("D", 13, 4);
        Cidade e = new Cidade("E", 4, 25);
        Cidade f = new Cidade("F", 7, 22);
        Cidade g = new Cidade("G", 11, 20);
        Cidade h = new Cidade("H", 13, 15);
        Cidade i = new Cidade("I", 27, 11);
        Cidade j = new Cidade("J", 13, 28);
        Cidade k = new Cidade("K", 20, 31);
        Cidade l = new Cidade("L", 25, 25);
        Cidade m = new Cidade("M", 44, 11);
        Cidade n = new Cidade("N", 56, 11);
        Cidade o = new Cidade("O", 60, 4);
        Cidade p = new Cidade("P", 34, 18);
        Cidade q = new Cidade("Q", 35, 24);
        Cidade r = new Cidade("R", 31, 35);
        Cidade s = new Cidade("S", 41, 33);
        Cidade t = new Cidade("T", 43, 35);
        Cidade u = new Cidade("U", 48, 35);
        Cidade v = new Cidade("V", 48, 43);
        Cidade w = new Cidade("W", 76, 2);
        Cidade z = new Cidade("Z", 79, 10);
        Cidade a1 = new Cidade("a1", 68, 14);
        Cidade b1 = new Cidade("b1", 52, 27);
        Cidade c1 = new Cidade("c1", 57, 25);
        Cidade d1 = new Cidade("d1", 63, 27);
        Cidade e1 = new Cidade("e1", 71, 21);
        Cidade f1 = new Cidade("f1", 83, 10);
        Cidade g1 = new Cidade("g1", 100, 10);

        a.addSucessor(f);
        a.addSucessor(h);
        a.addSucessor(d);

        b.addSucessor(m);

        c.addSucessor(b);

        d.addSucessor(c);

        e.addSucessor(j);

        f.addSucessor(e);
        f.addSucessor(g);

        g.addSucessor(f);
        g.addSucessor(h);

        h.addSucessor(g);
        h.addSucessor(p);
        h.addSucessor(i);

        i.addSucessor(p);
        i.addSucessor(m);

        j.addSucessor(k);

        k.addSucessor(l);
        k.addSucessor(r);

        l.addSucessor(p);
        l.addSucessor(s);
        l.addSucessor(r);

        m.addSucessor(n);
        m.addSucessor(a1);

        n.addSucessor(o);
        n.addSucessor(a1);

        o.addSucessor(w);
        o.addSucessor(f1);

        p.addSucessor(q);
        p.addSucessor(c1);
        p.addSucessor(m);
        
        q.addSucessor(p);

        r.addSucessor(q);
        r.addSucessor(s);
        r.addSucessor(t);
        r.addSucessor(v);
        
        s.addSucessor(t);
        s.addSucessor(b1);
        
        t.addSucessor(u);
        
        u.addSucessor(b1);
        
        v.addSucessor(u);
        v.addSucessor(d1);
        
        w.addSucessor(f1);
        
        z.addSucessor(f1);
        
        a1.addSucessor(z);
        
        b1.addSucessor(d1);
        b1.addSucessor(c1);
        
        c1.addSucessor(e1);
        
        d1.addSucessor(e1);
        
        e1.addSucessor(f1);
        
        f1.addSucessor(g1);
        
        ArrayList<Cidade> lista = new ArrayList<>();
        lista.add(a);
        lista.add(b);
        lista.add(c);
        lista.add(d);
        lista.add(e);
        lista.add(f);
        lista.add(g);
        lista.add(h);
        lista.add(i);
        lista.add(j);
        lista.add(k);
        lista.add(l);
        lista.add(m);
        lista.add(n);
        lista.add(o);
        lista.add(p);
        lista.add(q);
        lista.add(r);
        lista.add(s);
        lista.add(t);
        lista.add(u);
        lista.add(v);
        lista.add(w);
        lista.add(z);
        lista.add(a1);
        lista.add(b1);
        lista.add(c1);
        lista.add(d1);
        lista.add(e1);
        lista.add(f1);
        lista.add(g1);

        AEstrela algoritmo = new AEstrela(lista, a, g1);

        Long ini = System.currentTimeMillis();
        ArrayList<Cidade> listaFinal = algoritmo.iniciar();
        Long fin = System.currentTimeMillis();
        fin = fin - ini;
        
        System.out.println("");
        System.out.println("TEMPO DE EXECUÇÃO = " + fin.toString() + " MILISSEGUNDOS");
        System.out.println("");
        System.out.println("");
        
        ArrayList<Cidade> listaMelhor = algoritmo.mostrarMelhorCaminho(listaFinal);
        for (Iterator<Cidade> iterator = listaMelhor.iterator(); iterator.hasNext();)
        {
            Cidade next = iterator.next();

            System.out.print(next.toString());

            if (iterator.hasNext())
            {
                System.out.print("-> ");
            }
        }
        System.out.println("");
    }
}
