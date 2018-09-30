/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.bancoa22;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Lucas
 */
@Entity
public class NotaFiscal implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = -1;

    @OneToMany(mappedBy = "nota", fetch = FetchType.LAZY)
    private List<ItemNota> itens = new ArrayList<>();

    @Temporal(TemporalType.DATE)
    private Date emissao;

    public NotaFiscal(Date emissao)
    {
        this.emissao = emissao;
    }

    public NotaFiscal(int id, List<ItemNota> itens, Date emissao)
    {
        this.id = id;
        this.itens = itens;
        this.emissao = emissao;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public List<ItemNota> getItems()
    {
        return itens;
    }

    public void setItems(List<ItemNota> itens)
    {
        this.itens = itens;
    }

    public Date getEmissao()
    {
        return emissao;
    }

    public void setEmissao(Date emissao)
    {
        this.emissao = emissao;
    }

    public void addItemNota(ItemNota in)
    {
        itens.add(in);
    }
}
