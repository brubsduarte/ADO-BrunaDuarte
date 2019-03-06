/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ado.brunaduarte;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruna
 */
public class Regiao {
    private String nome;
    private double pib;
    private List<Estado> listaEstado;

    public Regiao() {
        nome = "";
        pib = 0;
        listaEstado = new ArrayList();
    }

    public List<Estado> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(List<Estado> listaEstado) {
        this.listaEstado = listaEstado;
    }

    public double getPib() {
        return pib;
    }

    public void setPib(double pib) {
        this.pib = pib;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
