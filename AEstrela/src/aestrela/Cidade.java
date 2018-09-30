/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aestrela;

import java.util.HashMap;

/**
 *
 * @author toshi
 */
public class Cidade
{

    private String nome;
    private Double caminhoPercorrido;
    private Double caminhoEstimado; //h 
    private Double funcao; //f
    private HashMap<Cidade, Double> sucessores = new HashMap<>();
    private int x;
    private int y;

    public Cidade(String nome, int x, int y)
    {
        this.x = x;
        this.y = y;
        this.nome = nome;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Double getCaminhoPercorrido()
    {
        return caminhoPercorrido;
    }

    public void setCaminhoPercorrido(Double caminhoPercorrido)
    {
        this.caminhoPercorrido = caminhoPercorrido;
    }

    public Double getCaminhoEstimado()
    {
        return caminhoEstimado;
    }

    public HashMap<Cidade, Double> getSucessores()
    {
        return sucessores;
    }

    public Double getFuncao()
    {
        return funcao;
    }

    public void setFuncao(Double funcao)
    {
        this.funcao = funcao;
    }

    public String getNome()
    {
        return nome;
    }

    public void addSucessor(Cidade cidade)
    {
        Double custo = sucessores.put(cidade, this.calcularDistancia(cidade));
        sucessores.put(cidade, custo);
    }

    public Double calcularDistancia(Cidade c)
    {
        Double result = Math.sqrt((Math.pow(c.getX() - x, 2)) + Math.pow((c.getY() - y), 2));
        result = truncar(result, 3);
        return result;
    }

    public Double calcularCaminhoEstimado(Cidade meta)
    {
        Double result = calcularDistancia(meta);
        caminhoEstimado = result;

        return result;
    }

    public Double calcularFuncao()
    {
        Double result = caminhoPercorrido + caminhoEstimado;
        result = truncar(result, 3);

        this.funcao = result;
        return result;
    }

    public Double truncar(Double d, int casas_decimais)
    {
        int var1 = d.intValue();   // Remove a parte decimal do número... 2.3777 fica 2  
        double var2 = var1 * Math.pow(10, casas_decimais); // adiciona zeros..2.0 fica 200.0  
        double var3 = (d - var1) * Math.pow(10, casas_decimais);
        /**
         * Primeiro retira a parte decimal fazendo 2.3777 - 2 ..fica 0.3777,
         * depois multiplica por 10^(casas decimais) por exemplo se o número de
         * casas decimais que queres considerar for 2, então fica 0.3777*10^2 =
         * 37.77 *
         */
        int var4 = (int) var3; // Remove a parte decimal da var3, ficando 37  
        int var5 = (int) var2; // Só para não haver erros de precisão: 200.0 passa a 200  
        int resultado = var5 + var4; // O resultado será 200+37 = 237  
        Double resultado_final = resultado / Math.pow(10, casas_decimais); // Finalmente divide-se o resultado pelo número de casas decimais, 237/100 = 2.37  
        return resultado_final; // Retorna o resultado_final :P   
    }

    @Override
    public String toString()
    {
        return (new StringBuilder()).append(nome).append(" ").toString();
    }
}
