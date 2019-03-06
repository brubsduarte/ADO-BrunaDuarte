/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ado.brunaduarte;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruna
 */
public class Calculos {

    private final List<Estado> listaEstado;
    private final List<Regiao> listaRegiao;

    public Calculos() {
        this.listaEstado = new ArrayList<>();
        this.listaRegiao = new ArrayList<>();
    }

    /**
     * Formata e imprime a lista de estados.
     */
    public void imprimirListaEstado() {
        NumberFormat formatter = new DecimalFormat("0.00");
        for (Estado estado : listaEstado) {
            System.out.println(estado.getNome() + " - " + formatter.format(estado.getPorcentagemPib()) + "%");
        }
    }

    public void gerarArquivoListaRegiao() {
        String arquivoDeSaida = "saida.txt";

        try {

            FileWriter fileWriter = new FileWriter(arquivoDeSaida);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            NumberFormat formatter = new DecimalFormat("0.00");
            
            for (Regiao regiao : listaRegiao) {
                bufferedWriter.write(regiao.getNome() + " PIB - " + formatter.format(regiao.getPib()));
                bufferedWriter.newLine();  
            }
            
            // feche o arquivo
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Erro de escrita em '" + arquivoDeSaida + "'");
        }    

    }

    /**
     * Calculo da porcentagem do PIB dos estados em relação ao total.
     */
    public void calcularPercentualPib() {
        double totalPib = 0.0;

        for (Estado estado : listaEstado) {
            totalPib = estado.getPib() + totalPib;
        }

        for (Estado estado : listaEstado) {
            estado.setPorcentagemPib((estado.getPib() * 100.0) / totalPib);
        }
    }

    /**
     * Calculo da soma de todos os pibs por regiao.
     */
    public void calcularPibRegiao() {
        for (Regiao regiao : listaRegiao) {
            double totalPib = 0;
            for (Estado estado : regiao.getListaEstado()) {
                totalPib += estado.getPib();
            }
            regiao.setPib(totalPib);
       
        }
    }

    /**
     * ler o arquivo .txt com os estados e adicionar na lista de estados
     * separando por estado e número do PIB.
     */
    public void lerArquivoEstado() {

        String nomeDoArquivo = "estado.txt";

        String linhaTemporaria;

        try {
            FileReader fileReader = new FileReader(nomeDoArquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // loop por cada linha do arquivo 
            while ((linhaTemporaria = bufferedReader.readLine()) != null) {
                // cria um objeto do tipo estado
                Estado estado = new Estado();
                estado.setNome(linhaTemporaria.split(";")[0]);
                estado.setPib(Double.parseDouble(linhaTemporaria.split(";")[1]));
                // e coloca em uma lista
                listaEstado.add(estado);
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo inexistente: '" + nomeDoArquivo + "'");
        } catch (IOException ex) {
            System.out.println("Erro de leitura do arquivo '" + nomeDoArquivo + "'");
        }

    }

    /**
     * A primeira linha é o nome da região, As demais linhas são os estados,
     * Quando encontrar uma linha vazia ele começa uma nova região.
     */
    public void lerArquivoRegiao() {
        String nomeDoArquivo = "regioes.txt";

        String linhaTemporaria;

        try {
            FileReader fileReader = new FileReader(nomeDoArquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            Regiao regiao = new Regiao();
            List<Estado> estados = new ArrayList();

            while ((linhaTemporaria = bufferedReader.readLine()) != null) {
                //Quando ele encontra uma linha em branco 
                if (linhaTemporaria.isEmpty()) {
                    
                    //ele salva a lista de estados na região e add a regiao na lista de regiões.
                    regiao.setListaEstado(estados);
                    listaRegiao.add(regiao);
                    //e apaga as variáveis região e estados. 
                    regiao = new Regiao();
                    estados = new ArrayList();
                    
                } else if (regiao.getNome().isEmpty()) { // Quando o nome de uma região é vazio ele cria uma nova região
                    
                    regiao.setNome(linhaTemporaria);
                    
                } else { // Quando não cai em nenhuma das outras condições ele add um novo estado a lista de estados. 
                   
                    estados.add(pesquisaEstado(linhaTemporaria));
                }

            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo inexistente: '" + nomeDoArquivo + "'");
        } catch (IOException ex) {
            System.out.println("Erro de leitura do arquivo '" + nomeDoArquivo + "'");
        }

    }

    private Estado pesquisaEstado(String nomeEstado) {
        for (Estado estado : listaEstado) {
            if (estado.getNome().equals(nomeEstado)) {
                return estado;
            }
        }
        return null;
    }

}
