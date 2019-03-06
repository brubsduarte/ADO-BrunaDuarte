/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ado.brunaduarte;

/**
 *
 * @author Bruna
 */
public class Estado {
    private String nome;
    private double pib;
    private double porcentagemPib;

    public double getPorcentagemPib() {
        return porcentagemPib;
    }

    public void setPorcentagemPib(double porcentagemPib) {
        this.porcentagemPib = porcentagemPib;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPib() {
        return pib;
    }

    public void setPib(double pib) {
        this.pib = pib;
    }   
}
