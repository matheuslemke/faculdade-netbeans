/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aula25;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author matheus
 */
@Entity
public class NotaFiscal implements Serializable
{

    private long id = -1l;
    private List<ItemNota> itens = new ArrayList<>();
    private PessoaFisica cliente = null;
    private Date data_emissao = null;

    public NotaFiscal()
    {
    }

    public NotaFiscal(List<ItemNota> itens, PessoaFisica cliente, Date data_emissao)
    {
        this.itens = itens;
        this.cliente = cliente;
        this.data_emissao = data_emissao;
    }

    public NotaFiscal(PessoaFisica cliente, Date data_emissao)
    {
        this.cliente = cliente;
        this.data_emissao = data_emissao;
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

    @OneToMany(mappedBy = "nota", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<ItemNota> getItens()
    {
        return itens;
    }

    public void setItens(List<ItemNota> itens)
    {
        this.itens = itens;
    }

    @ManyToOne
    public PessoaFisica getCliente()
    {
        return cliente;
    }

    public void setCliente(PessoaFisica cliente)
    {
        this.cliente = cliente;
    }

    @Temporal(TemporalType.DATE)
    public Date getData_emissao()
    {
        return data_emissao;
    }

    public void setData_emissao(Date data_emissao)
    {
        this.data_emissao = data_emissao;
    }

    public void addItem(ItemNota in)
    {
        itens.add(in);
    }

    public BigDecimal valorTotal()
    {
        BigDecimal valor = BigDecimal.ZERO;
        for (ItemNota itemNota : itens)
        {
            BigDecimal m = new BigDecimal(itemNota.getQuantidade());
            valor = valor.add(itemNota.getProduto().getPreco().multiply(m));
        }
        return valor;
    }

}
