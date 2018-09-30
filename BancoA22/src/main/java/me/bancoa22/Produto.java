/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.bancoa22;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Lucas
 */
@Entity
public class Produto implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigoBarra = -1;

    private String nome;
    private double preco;
    private String fornecedor;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.REMOVE)
    private ItemNota itemNota;

    @Temporal(TemporalType.DATE)
    private Date validade;

    public Produto()
    {
    }

    public Produto(String nome, double preco, String fornecedor, Date validade)
    {
        this.nome = nome;
        this.preco = preco;
        this.fornecedor = fornecedor;
        this.validade = validade;
    }

    public long getCodigoBarra()
    {
        return codigoBarra;
    }

    public void setCodigoBarra(long codigoBarra)
    {
        this.codigoBarra = codigoBarra;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public double getPreco()
    {
        return preco;
    }

    public void setPreco(double preco)
    {
        this.preco = preco;
    }

    public String getFornecedor()
    {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor)
    {
        this.fornecedor = fornecedor;
    }

    public Date getValidade()
    {
        return validade;
    }

    public void setValidade(Date validade)
    {
        this.validade = validade;
    }

    public void setItemNota(ItemNota itemNota)
    {
        this.itemNota = itemNota;
    }

    public ItemNota getItemNota()
    {
        return itemNota;
    }

}
