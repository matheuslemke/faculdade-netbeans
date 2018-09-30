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
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author matheus
 */
@Entity
public class PessoaFisica extends Pessoa implements Serializable
{

    private long rg = -1l;
    private long cpf = -1l;
    private Sexo sexo = null;
    private List<NotaFiscal> notas = new ArrayList<>();

    public PessoaFisica()
    {

    }

    public PessoaFisica(long cpf, Sexo sexo, Date data_de_nascimento, String ocupacao, String nome, Endereco endereco, List<NotaFiscal> notas)
    {
        super(data_de_nascimento, ocupacao, nome, endereco);
        this.cpf = cpf;
        this.sexo = sexo;
        this.notas = notas;
    }

    public PessoaFisica(long cpf, Sexo sexo, Date data_de_nascimento, String ocupacao, String nome, Endereco endereco)
    {
        super(data_de_nascimento, ocupacao, nome, endereco);
        this.cpf = cpf;
        this.sexo = sexo;
    }

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
    public List<NotaFiscal> getNotas()
    {
        return notas;
    }

    @Enumerated
    public Sexo getSexo()
    {
        return sexo;
    }

    public void setSexo(Sexo sexo)
    {
        this.sexo = sexo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getRg()
    {
        return rg;
    }

    public void setRg(long rg)
    {
        this.rg = rg;
    }

    public long getCpf()
    {
        return cpf;
    }

    public void setCpf(long cpf)
    {
        this.cpf = cpf;
    }

    public void setNotas(List<NotaFiscal> notas)
    {
        this.notas = notas;
    }

    public void addNota(NotaFiscal nf)
    {
        notas.add(nf);
    }

}
