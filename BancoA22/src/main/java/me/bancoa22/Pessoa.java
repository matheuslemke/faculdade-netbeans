/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.bancoa22;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Lucas
 */
@Entity
public class Pessoa implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1;
    private String nome = null;
    private String tratamento = null;
    private String ocupacao = null;
    private int idade = -1;
    private char sexo;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.REMOVE)
    private Endereco endereco;

    public Pessoa()
    {
    }

    public Pessoa(String nome, String tratamento, String ocupacao, int idade, char sexo)
    {
        this.nome = nome;
        this.tratamento = tratamento;
        this.ocupacao = ocupacao;
        this.idade = idade;
        this.sexo = sexo;
    }

    /**
     * @return the id
     */
    public long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    /**
     * @return the tratamento
     */
    public String getTratamento()
    {
        return tratamento;
    }

    /**
     * @param tratamento the tratamento to set
     */
    public void setTratamento(String tratamento)
    {
        this.tratamento = tratamento;
    }

    /**
     * @return the ocupacao
     */
    public String getOcupacao()
    {
        return ocupacao;
    }

    /**
     * @param ocupacao the ocupacao to set
     */
    public void setOcupacao(String ocupacao)
    {
        this.ocupacao = ocupacao;
    }

    /**
     * @return the idade
     */
    public int getIdade()
    {
        return idade;
    }

    /**
     * @param idade the idade to set
     */
    public void setIdade(int idade)
    {
        this.idade = idade;
    }

    /**
     * @return the sexo
     */
    public char getSexo()
    {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(char sexo)
    {
        this.sexo = sexo;
    }

    public Endereco getEndereco()
    {
        return endereco;
    }

    public void setEndereco(Endereco endereco)
    {
        this.endereco = endereco;
    }

    public String getRua()
    {
        return endereco.getRua();
    }

    @Override
    public String toString()
    {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", tratamento=" + tratamento + ", ocupacao=" + ocupacao + ", idade=" + idade + ", sexo=" + sexo + '}';
    }

}
