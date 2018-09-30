/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.bancoa22;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lucas
 */
public class CrudProduto
{

    public static void salvar(Produto produto)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            session.save(produto);

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

    public static void update(Produto produto)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            Produto p1 = (Produto) session.get(Produto.class, produto.getCodigoBarra());

            p1.setNome(produto.getNome());
            p1.setPreco(produto.getPreco());
            p1.setFornecedor(produto.getFornecedor());
            p1.setValidade(produto.getValidade());

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

    public static void delete(long id)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            Produto p1 = (Produto) session.get(Produto.class, id);
            session.delete(p1);

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
    /*
     public static List<Produto> getVencidos()
     {
     Session session = Main.getSessionFactory().openSession();
     Transaction tx = null;
     List<Produto> l = new ArrayList<>();
     try
     {
     tx = session.beginTransaction();

     Query q = session.createQuery("from Produto p  where validade <= :date");
     q.setDate("date", new Date(System.currentTimeMillis()));
     l = (List<Produto>) q.list();

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

     return l;
     }
     */

    public static Produto getProduto(long id)
    {
        Produto produto = null;
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            produto = (Produto) session.get(Produto.class, id);

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

        return produto;
    }

    public static void salvar(NotaFiscal nf)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            for (ItemNota in : nf.getItems())
            {
                session.saveOrUpdate(in);
                session.saveOrUpdate(in.getProduto());
            }
            session.save(nf);

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

}
