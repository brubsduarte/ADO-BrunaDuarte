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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Calculos calculos = new Calculos();
        
        calculos.lerArquivoEstado();
        calculos.calcularPercentualPib();
        calculos.imprimirListaEstado();
        
        calculos.lerArquivoRegiao();
        calculos.calcularPibRegiao();
        calculos.gerarArquivoListaRegiao();
            
    }
    
}
