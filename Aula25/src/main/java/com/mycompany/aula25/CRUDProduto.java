/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aula25;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author matheus
 */
public class CRUDProduto
{

    public static void create(Produto p)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            session.save(p);

            tx.commit();
        } catch (Exception e)
        {
            if (tx != null)
            {
                tx.rollback();
            }
            throw e;
        } finally
        {
            session.close();
        }
    }

    public static Produto getByCodigo(long codigo)
    {
        Produto p = null;
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            p = (Produto) session.get(Produto.class, codigo);
            tx.commit();

        } catch (Exception e)
        {
            if (tx != null)
            {
                tx.rollback();
            }
            throw e;
        } finally
        {
            session.close();
        }

        return p;
    }

    public static void update(Produto p2)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            Produto p1 = (Produto) session.get(Produto.class, p2.getCodigo_de_barra());

            p1.setCodigo_de_barra(p2.getCodigo_de_barra());
            p1.setFornecedor(p2.getFornecedor());
            p1.setNome(p2.getNome());
            p1.setPreco(p2.getPreco());

            session.save(p1);
            tx.commit();
        } catch (Exception e)
        {
            if (tx != null)
            {
                tx.rollback();
            }
            throw e;
        } finally
        {
            session.close();
        }
    }

    public static Produto delete(Produto p)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            session.delete(p);
            tx.commit();
        } catch (Exception e)
        {
            if (tx != null)
            {
                tx.rollback();
            }
            throw e;
        } finally
        {
            session.close();
        }
        return p;
    }
}
