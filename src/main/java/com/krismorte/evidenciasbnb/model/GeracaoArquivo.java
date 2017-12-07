/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb.model;

import com.krismorte.evidenciasbnb.view.ITelaExecucao;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JProgressBar;

/**
 *
 * @author c007329
 */
public class GeracaoArquivo extends Thread {

    private int contador;
    private int totalDeArquivos;
    private long MBInByte = 1048576;
    private JProgressBar barra;
    private ITelaExecucao tela;
    private String filePath;
    private String ticket;
    private String autor;
    private String datahora;
    private List<File> files;

    public GeracaoArquivo(JProgressBar barra, ITelaExecucao tela) {
        contador = 0;
        barra.setMinimum(contador);
        barra.setStringPainted(true);
        this.barra = barra;
        this.tela = tela;
    }

    public void gerar(String filePath, String ticket,String autor, String datahora, List<File> files) {
        this.filePath = filePath;
        this.autor = autor;
        this.ticket = ticket;
        this.datahora = datahora;
        this.files = files;
        totalDeArquivos = files.size() + 1;
        barra.setMaximum(totalDeArquivos);
        start();
    }

    @Override
    public void run() {
        tela.iniciar();
        long totalSizeFiles = 0;
        List<File> arquivosDividos = new ArrayList<>();
        int totalExecucoes = 0;

        for (File arquivo : files) {
            //System.out.println("Arq: " + arquivo.length());
            //System.out.println("Cal: " + (totalSizeFiles + arquivo.length() / MBInByte));
            if (((totalSizeFiles + arquivo.length()) / MBInByte) > 2.8) {
                if (totalExecucoes == 0) {
                    DocumentoEvidencia.gerar(filePath,ticket, autor, datahora, arquivosDividos);
                    arquivosDividos = new ArrayList<>();
                    totalSizeFiles = 0;
                    totalExecucoes++;
                } else {
                    filePath = filePath.replace(".doc", "_" + totalExecucoes + ".doc");
                    DocumentoEvidencia.gerar(filePath,ticket, autor, datahora, arquivosDividos);
                    arquivosDividos = new ArrayList<>();
                    totalSizeFiles = 0;
                    totalExecucoes++;
                }
            }
            totalSizeFiles += arquivo.length();
            arquivosDividos.add(arquivo);
            contador++;
            barra.setValue(contador);

        }

        DocumentoEvidencia.gerar(filePath,ticket, autor, datahora, arquivosDividos);
        contador++;
        barra.setValue(contador);
        tela.finalizar();
    }

}
