/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aula25;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author matheus
 */
@Entity
public class PessoaJuridica extends Pessoa implements Serializable
{

    private long cnpj = -1;
    private List<Produto> produtos = new ArrayList<>();

    public PessoaJuridica()
    {
    }

    public PessoaJuridica(Date data_de_nascimento, String ocupacao, String nome, Endereco endereco, List<Produto> produtos)
    {
        super(data_de_nascimento, ocupacao, nome, endereco);
        this.produtos = produtos;
    }

    public PessoaJuridica(Date data_de_nascimento, String ocupacao, String nome, Endereco endereco)
    {
        super(data_de_nascimento, ocupacao, nome, endereco);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getCnpj()
    {
        return cnpj;
    }

    public void setCnpj(long cnpj)
    {
        this.cnpj = cnpj;
    }

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.PERSIST)
    public List<Produto> getProdutos()
    {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos)
    {
        this.produtos = produtos;
    }

}
