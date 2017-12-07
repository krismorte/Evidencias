/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author C007329
 */
public class Preferencias {

    public String nome="";
    public String nomeArquivo="";
    public String ultimaPastaArquivo="";
    public String ultimaPastaDestino="";
    public String pastaRaiz="";
    private static final String TagNome = "nome";
    private static final String TagNomeArquivo = "nomeArquivo";
    private static final String TagUltimaPastaArquivo = "ultimaPastaArquivo";
    private static final String TagUltimaPastaDestino = "ultimaPastaDestino";
    private static final String TagPastaRaiz = "pastaRaiz";

    private Preferencias() {

    }

    /*public Ajudante(String nome, String nomeArquivo, String ultimaPastaArquivo, String ultimaPastaDestino) {
        this.nome = nome + ".prop";
        this.nomeArquivo = nomeArquivo;
        this.ultimaPastaArquivo = ultimaPastaArquivo;
        this.ultimaPastaDestino = ultimaPastaDestino;
    }*/
    public static void salvar(Preferencias ajudante) {
        //File arquivo = new File(nome);
        //if (arquivo.exists()) {
        try {
            Properties prop = new Properties();
            prop.setProperty(TagNome, ajudante.nome);
            prop.setProperty(TagNomeArquivo, ajudante.nomeArquivo);
            prop.setProperty(TagUltimaPastaArquivo, ajudante.ultimaPastaArquivo);
            prop.setProperty(TagUltimaPastaDestino, ajudante.ultimaPastaDestino);
            prop.setProperty(TagPastaRaiz, ajudante.pastaRaiz);
            prop.store(new FileOutputStream(ajudante.nome + ".properties"), null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //}
    }

    public static Preferencias carregar(String nomeAjudante) {
        try {
            Preferencias ajudante = new Preferencias();
            if (new File(nomeAjudante + ".properties").exists()) {
                Properties prop = new Properties();
                prop.load(new FileInputStream(nomeAjudante + ".properties"));
                ajudante.nome = prop.getProperty(TagNome);
                ajudante.nomeArquivo = prop.getProperty(TagNomeArquivo);
                ajudante.ultimaPastaArquivo = prop.getProperty(TagUltimaPastaArquivo);
                ajudante.ultimaPastaDestino = prop.getProperty(TagUltimaPastaDestino);
                ajudante.pastaRaiz = prop.getProperty(TagPastaRaiz);
            } else {
                ajudante.nome = nomeAjudante;
                salvar(ajudante);
            }
            return ajudante;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
