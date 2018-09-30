/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aula25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author matheus
 */
public class CRUDPessoa
{

    public static void create(PessoaFisica pf)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            session.save(pf);

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

    public static PessoaFisica getFisicaByRG(long RG)
    {
        PessoaFisica pessoaFisica = null;
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            pessoaFisica = (PessoaFisica) session.get(PessoaFisica.class, RG);
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

        return pessoaFisica;
    }

    public static void update(PessoaFisica p)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            session.update(p);

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

    public static void deleteFisica(PessoaFisica p)
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

    }

    public static void create(PessoaJuridica pj)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            session.save(pj);

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

    public static PessoaJuridica getJuridicaByCNPJ(long CNPJ)
    {
        PessoaJuridica pessoaJuridica = null;
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            pessoaJuridica = (PessoaJuridica) session.get(PessoaJuridica.class, CNPJ);
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

        return pessoaJuridica;
    }

    public static void update(PessoaJuridica p)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            session.update(p);
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

    public static PessoaJuridica deleteJuridica(PessoaJuridica pj)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            session.delete(pj);
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

        return pj;
    }

    public static List<Produto> produtosMaisCompradosPessoaFisica(PessoaFisica p)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        List<Object[]> resultQuery;
        List<Produto> maisComprados = new ArrayList<>();
        try
        {
            tx = session.beginTransaction();

            String hql = "SELECT pr "
                    + "FROM PessoaFisica p JOIN p.notas nf JOIN nf.itens it JOIN it.produto pr "
                    + "WHERE p.rg = ? "
                    + "GROUP BY pr "
                    + "ORDER BY SUM(it.quantidade) DESC";

            Query q = session.createQuery(hql);
            q.setParameter(0, p.getRg());
            resultQuery = q.list();

            List<Object> aux = Arrays.asList(resultQuery);

            maisComprados = (List<Produto>) aux.get(0);

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
        return maisComprados;
    }

    public static List<Produto> produtosMaisVendidosPessoaJuridica(PessoaJuridica p)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;

        List<Produto> maisVendidos = new ArrayList<>();

        try
        {
            tx = session.beginTransaction();

            String hql = "SELECT pr "
                    + "FROM ItemNota it JOIN it.produto pr JOIN pr.fornecedor p "
                    + "WHERE p.cnpj = :cnpj "
                    + "GROUP BY pr "
                    + "ORDER BY SUM(it.quantidade) DESC";

            Query q = session.createQuery(hql);
            q.setLong("cnpj", p.getCnpj());

            List<Object[]> resultQuery = q.list();

            List<Object> aux = Arrays.asList(resultQuery);

            maisVendidos = (List<Produto>) aux.get(0);

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
        return maisVendidos;
    }

}
