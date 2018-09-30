/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aula25;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author matheus
 */
public class CRUDNota
{

    public static void create(NotaFiscal nf)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

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

    public static NotaFiscal getNotaById(long id)
    {
        Session session = Main.getSessionFactory().openSession();

        NotaFiscal nf = null;
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            nf = (NotaFiscal) session.get(NotaFiscal.class, id);

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
        return nf;
    }

    public static ItemNota getItemById(long id)
    {
        Session session = Main.getSessionFactory().openSession();

        ItemNota resultado = null;
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            resultado = (ItemNota) session.get(ItemNota.class, id);

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
        return resultado;
    }

    public static void update(NotaFiscal nf)
    {

        Session session = Main.getSessionFactory().openSession();

        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            session.saveOrUpdate(nf);

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

    public static NotaFiscal delete(NotaFiscal nf)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            session.delete(nf);
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
        return nf;
    }

    public static BigDecimal valorTotal(NotaFiscal nf)
    {
        Session session = Main.getSessionFactory().openSession();

        BigDecimal resultado = BigDecimal.ZERO;

        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            String hql = "SELECT SUM(it.quantidade * pr.preco) as soma"
                    + " FROM ItemNota it JOIN it.nota nf JOIN it.produto pr"
                    + " WHERE nf.id = :id"
                    + " GROUP BY it.quantidade * pr.preco"
                    + " ORDER BY soma";

            Query q = session.createQuery(hql);
            q.setParameter("id", nf.getId());

            List<BigDecimal> resultQuery = q.list();

            for (BigDecimal valor : resultQuery)
            {
                resultado = resultado.add(valor);
            }

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
        return resultado;
    }

}
