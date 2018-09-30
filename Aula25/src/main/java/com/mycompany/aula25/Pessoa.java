/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aula25;

import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

/**
 *
 * @author matheus
 */
@MappedSuperclass
public abstract class Pessoa
{

    private Date data_de_nascimento = null;
    private String ocupacao = null;
    private String nome = null;
    private Endereco endereco = null;

    public Pessoa(Date data_de_nascimento, String ocupacao, String nome, Endereco endereco)
    {
        this.data_de_nascimento = data_de_nascimento;
        this.ocupacao = ocupacao;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Pessoa()
    {
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getData_de_nascimento()
    {
        return data_de_nascimento;
    }

    public void setData_de_nascimento(Date data_de_nascimento)
    {
        this.data_de_nascimento = data_de_nascimento;
    }

    public String getOcupacao()
    {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao)
    {
        this.ocupacao = ocupacao;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    @Embedded
    public Endereco getEndereco()
    {
        return endereco;
    }

    public void setEndereco(Endereco endereco)
    {
        this.endereco = endereco;
    }

}
