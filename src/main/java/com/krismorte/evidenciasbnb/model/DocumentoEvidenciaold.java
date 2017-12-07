/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb.model;

import java.io.File;
import java.util.HashMap;

/**
 *
 * @author c007329
 */
public class DocumentoEvidenciaold {

    private String caminhoArquivo;
    private String titulo;
    private String descricao;
    private String autor;
    private String data;
    private HashMap<String, File> arquivos = new HashMap<String, File>();
    //C:\\Users\\c007329\\Documents\\NetBeansProjects\\GeradorDeEvidencia\\
    //C:\\Users\\c007329\\Documents\\NetBeansProjects\\GeradorDeEvidencia\\
    private final String imagemCabecalho = "template\\bnbheader.png";
    private final String dirTemp = "temp\\";

    public DocumentoEvidenciaold(String caminhoArquivo, String titulo, String descricao, String autor, String data, HashMap<String, File> arquivos) {
        this.caminhoArquivo = caminhoArquivo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.autor = autor;
        this.data = data;
        this.arquivos = arquivos;
    }

    public void gerar() {
       /* CreateDocx docx = new CreateDocx("doc");
        addHeaderImage(docx);
        addTitle(docx, getTitulo());
        addIdentify(docx, descricao, autor, data);
        docx.addBreak("page");
        addImages(docx, arquivos);
        HashMap paramsDoc = new HashMap();
        paramsDoc.put("left", "200");
        paramsDoc.put("righ", "200");
        paramsDoc.put("top", "200");
        docx.createDocx(getCaminhoArquivo(), paramsDoc);
        renameToDocExtension();*/
    }

    private void renameToDocExtension() {
        System.out.println(getCaminhoArquivo());
        //if (getCaminhoArquivo().endsWith(".docx")) {
            File arquivoResultante = new File(getCaminhoArquivo() + ".docx");
            arquivoResultante.renameTo(new File(arquivoResultante.getAbsolutePath().replace("docx", "doc")));
            arquivoResultante.delete();
        //}
    }

   /* private void addHeaderImage(CreateDocx docx) {
        HashMap paramsHeadImage = new HashMap();
        paramsHeadImage.put("name", imagemCabecalho);
        paramsHeadImage.put("jc", "center");
        docx.addImage(paramsHeadImage);
    }*/

   /* private void addTitle(CreateDocx docx, String title) {
        HashMap paramsTitle = new HashMap();
        paramsTitle.put("val", "1");
        paramsTitle.put("left", "200");
        paramsTitle.put("pagerAlignment", "center");
        //paramsTitle.put("u", "single");
        paramsTitle.put("sz", "24");
        /*paramsTitle.put("val", "1");
        paramsTitle.put("pagerAlignment", "center");
        paramsTitle.put("jc", "center");
        paramsTitle.put("u", "single");
        paramsTitle.put("sz", "22");*
        paramsTitle.put("font", "Times New Roman");
        for (int i = 0; i < 5; i++) {
            docx.addBreak("line");
        }
        docx.addTitle("\t\t"+title, paramsTitle);
        //docx.addBreak("line");
        //docx.addBreak("line");
        //docx.addBreak("line");
    }*/

    /*private void addIdentify(CreateDocx docx, String descricao, String autor, String data) {
        //HashMap paramItalic = new HashMap();
        //paramItalic.put("i", "single");
        HashMap paramBold = new HashMap();
        paramBold.put("b", "single");
        paramBold.put("jc", "right");
        docx.addText(descricao/*, paramItalic*);
        for (int i = 0; i < 15; i++) {
            docx.addBreak("line");
        }
        docx.addText("Autor: " + autor, paramBold);
        docx.addText("Data de Criação: " + data, paramBold);
    }*/

    /*private void addImages(CreateDocx docx, HashMap<String, File> arquivos) {
        ImagesConverter c = new ImagesConverter();
        removePasta(new File(dirTemp), true);
        for (String s : getArquivos().keySet()) {
            String nameF = getFileNameWithoutExt(arquivos.get(s).getAbsolutePath());
            String filePathConverter = dirTemp + nameF + ".png";
            c.jpgToPng(arquivos.get(s).getAbsolutePath(), filePathConverter);
            docx.addText(s);
            HashMap paramsImage = new HashMap();
            paramsImage.put("name", filePathConverter);
            paramsImage.put("scaling", "30");
            paramsImage.put("textWrap", "1");
            paramsImage.put("border", "1");
            paramsImage.put("borderDiscontinuous", "1");
            docx.addImage(paramsImage);
        }
    }*/

    public static String getFileNameWithoutExt(String filename) {
        if (filename.contains("\\")) {
            int indIni = filename.lastIndexOf("\\");
            int indFim = filename.lastIndexOf(".");
            return filename.substring(indIni, indFim);
        } else {
            int indFim = filename.lastIndexOf(".");
            return filename.substring(0, indFim);
        }
    }

    public static boolean removePasta(File dir, boolean onlyFiles) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = removePasta(new File(dir, children[i]), false);
                if (!success) {
                    return false;
                }
            }
        }
        if (onlyFiles) {
            return true;
        } else {
            return dir.delete();
        }
    }

    /**
     * @return the nome
     */
    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    /**
     * @param nome the nome to set
     */
    public void setCaminhoArquivo(String nome) {
        this.caminhoArquivo = caminhoArquivo;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the arquivos
     */
    public HashMap<String, File> getArquivos() {
        return arquivos;
    }

    /**
     * @param arquivos the arquivos to set
     */
    public void setArquivos(HashMap<String, File> arquivos) {
        this.arquivos = arquivos;
    }

}
