/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.bancoa22;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lucas
 */
public class CrudPessoa
{

    public static void salvar(Pessoa pessoa)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            Endereco endereco = pessoa.getEndereco();
            endereco.setPessoa(pessoa);

            session.saveOrUpdate(endereco);

            session.save(pessoa);

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

    public static void update(Pessoa pessoa)
    {
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            Pessoa p1 = (Pessoa) session.get(Pessoa.class, pessoa.getId());
            p1.setNome(pessoa.getNome());
            p1.setTratamento(pessoa.getTratamento());
            p1.setOcupacao(pessoa.getOcupacao());
            p1.setIdade(pessoa.getIdade());
            p1.setSexo(pessoa.getSexo());
            p1.setEndereco(pessoa.getEndereco());

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

            Pessoa p1 = (Pessoa) session.get(Pessoa.class, id);
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

    public static Pessoa getByNome(String nome)
    {
        Pessoa pessoa = null;
        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            Query q = session.createQuery("from Pessoa p where p.nome = :nome");
            q.setString("nome", nome);

            pessoa = (Pessoa) q.uniqueResult();

            Hibernate.initialize(pessoa.getEndereco());

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

        return pessoa;
    }

    public static Pessoa getByID(Long id)
    {
        Pessoa pessoa = null;

        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            pessoa = (Pessoa) session.get(Pessoa.class, id);

            if (pessoa != null)
            {
                Hibernate.initialize(pessoa.getEndereco());
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

        return pessoa;
    }

    public static List<Pessoa> getByCidade(String cidade)
    {
        List<Pessoa> pessoas = null;

        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            String hql = "from Pessoa p where p.endereco.cidade = ?";
            pessoas = session.createQuery(hql)
                    .setString(0, cidade)
                    .list();

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

        return pessoas;
    }

    public static List<Pessoa> getByParameter(Pessoa p)
    {
        List<Pessoa> pessoas = null;

        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            String hql = "from Pessoa p where p.nome = :nome and p.endereco.rua = :rua";
            pessoas = session.createQuery(hql)
                    .setProperties(p).list();

            /*
             String hql = "from Pessoa p where p.nome = :nome and p.tratamento = :tratamento"
             + "and p.ocupacao = :ocupacao and p.idade = :idade and p.sexo = :sexo";
             pessoas = session.createQuery(hql).setParameter("nome", p.getNome()).list();
             */
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

        return pessoas;
    }

    public static List<Object[]> getMediaCidade()
    {
        List<Object[]> lista = null;

        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            //Forma correta de passar parâmetros!
            String hql = "select pessoa.endereco.cidade, avg(pessoa.idade) "
                    + "from Pessoa pessoa "
                    + "group by pessoa.endereco.cidade "
                    + "order by avg(pessoa.idade) asc";

            lista = session.createQuery(hql).list();

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

        return lista;
    }

    public static List<Object[]> getProdutoSoma()
    {
        List<Object[]> saida = null;

        Session session = Main.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            String hql = "select item.produto.nome, sum(item.produto.preco) "
                    + "from ItemNota item "
                    + "group by item.produto.nome "
                    + "order by sum(item.produto.preco) asc";

            saida = session.createQuery(hql).list();

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

        return saida;
    }

}
