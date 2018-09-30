/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aula25;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author matheus
 */
@Embeddable
public class Endereco implements Serializable
{

    private String rua = null, cidade = null, pais = null;

    public Endereco()
    {
    }

    public Endereco(String rua, String cidade, String pais)
    {
        this.rua = rua;
        this.cidade = cidade;
        this.pais = pais;
    }

    public String getRua()
    {
        return rua;
    }

    public void setRua(String rua)
    {
        this.rua = rua;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public String getPais()
    {
        return pais;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
    }

}
