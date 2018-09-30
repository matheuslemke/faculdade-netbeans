/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.bancoa22;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Lucas
 */
@Entity
public class ItemNota implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private NotaFiscal nota;

    @OneToOne
    private Produto produto;

    private double preco;
    private int quantidade;

    public ItemNota()
    {
    }

    public ItemNota(Produto produto, int quantidade)
    {
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public NotaFiscal getNota()
    {
        return nota;
    }

    public void setNota(NotaFiscal nota)
    {
        this.nota = nota;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Produto getProduto()
    {
        return produto;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setProduto(Produto produto)
    {
        this.produto = produto;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }

}
