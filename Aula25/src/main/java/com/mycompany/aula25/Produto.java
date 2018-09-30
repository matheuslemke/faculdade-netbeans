/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aula25;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author matheus
 */
@Entity
public class Produto implements Serializable
{

    private long codigo_de_barra = -1l;
    private String nome = null;
    private BigDecimal preco = BigDecimal.ZERO;
    private PessoaJuridica fornecedor = null;

    public Produto()
    {
    }

    public Produto(String nome, BigDecimal preco, PessoaJuridica fornecedor)
    {
        this.nome = nome;
        this.preco = preco;
        this.fornecedor = fornecedor;
    }

    @Column(precision = 12, scale = 2)
    public BigDecimal getPreco()
    {
        return preco;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getCodigo_de_barra()
    {
        return codigo_de_barra;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public PessoaJuridica getFornecedor()
    {
        return fornecedor;
    }

    public void setFornecedor(PessoaJuridica fornecedor)
    {
        this.fornecedor = fornecedor;
    }

    public void setCodigo_de_barra(long codigo_de_barra)
    {
        this.codigo_de_barra = codigo_de_barra;
    }

    public void setPreco(BigDecimal preco)
    {
        this.preco = preco;
    }

}
