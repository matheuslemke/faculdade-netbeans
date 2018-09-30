/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aula25;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author matheus
 */
@Entity
public class ItemNota implements Serializable
{

    private long id = -1;
    private NotaFiscal nota = null;
    private Produto produto = null;
    private int quantidade = -1;

    public ItemNota()
    {
    }

    public ItemNota(NotaFiscal nota, Produto produto, int quantidade)
    {
        this.nota = nota;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public NotaFiscal getNota()
    {
        return nota;
    }

    @OneToOne
    public Produto getProduto()
    {
        return produto;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }

    public void setNota(NotaFiscal nota)
    {
        this.nota = nota;
    }

    public void setProduto(Produto produto)
    {
        this.produto = produto;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

}
