/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb.view;

import com.krismorte.evidenciasbnb.model.GeracaoArquivo;
import com.krismorte.evidenciasbnb.model.Preferencias;
import com.krismorte.dragndrop.DNDImagePanel;
import com.krismorte.dragndrop.DNDSupport;
import com.krismorte.evidenciasbnb.model.DocumentoEvidencia;
import com.krismorte.evidenciasbnb.util.ArquivoUtils;
import com.krismorte.evidenciasbnb.util.TelaUtil;
import com.krismorte.table.Tabela;

import java.awt.GridLayout;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author c007329
 */
public class GeradorDeDocumentosDND extends javax.swing.JFrame implements ITelaExecucao {

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss");
    private String[] colunas = {"texto", "arquivo"};
    private Object[][] linhas;
    //private HashMap<String, File> arquivos = new HashMap<String, File>();
    private List<File> arquivos = new ArrayList<>();
    private Preferencias ajudante;
    private DNDImagePanel paneDragDrop;
    private long MBInByte = 1048576;

    /**
     * Creates new form GeradorDeDocumentos
     */
    public GeradorDeDocumentosDND() {
        initComponents();
        iniciaTela();
    }

    private void iniciaTela() {
        String autor = System.getProperty("user.name").toUpperCase();
        txtAutor.setText(autor);
        ajudante = Preferencias.carregar(autor);
        txtNome.setText(ajudante.nomeArquivo);
        txtDestinyDir.setText(ajudante.ultimaPastaDestino);
        txtRootDir.setText(ajudante.pastaRaiz);
        iniciaPanel();
    }

    private void iniciaPanel() {
        paneDragDrop = new DNDImagePanel(this);
        panelResultados.setLayout(new GridLayout(1, 1));
        panelResultados.setOpaque(false);
        panelResultados.add(paneDragDrop);

        DNDSupport bDNDSupport = new DNDSupport(this, panelResultados);

        //bDNDSupport.
        //panelImages.addMouseListener(bDNDSupport);
        //panelImages.addMouseMotionListener(bDNDSupport);
    }

    private void atualizaTabela() {
        //Tabela.preencheTabela(panelResultados, colunas, linhas);
        int[] num = {0};
        Tabela.preencheTabela(num, panelResultados, colunas, linhas);
    }

    private void preencheLinhas(File[] d) {
        linhas = new Object[d.length][colunas.length];
        String nomesArquivos = "";
        for (int i = 0; i < d.length; i++) {
            nomesArquivos += d[i].getName() + " ";
            String key = "arquivo " + (i + 1) + " " + TelaUtil.getFileNameWithoutExt(d[i].getName());
            linhas[i][0] = key;
            linhas[i][1] = d[i];
            arquivos.add(d[i]);
        }
    }

    private void preencheLinhas(List<File> d) {
        linhas = new Object[d.size()][colunas.length];
        String nomesArquivos = "";
        for (int i = 0; i < d.size(); i++) {
            nomesArquivos += d.get(i).getName() + " ";
            String key = "arquivo " + (i + 1) + " " + TelaUtil.getFileNameWithoutExt(d.get(i).getName());
            linhas[i][0] = key;
            linhas[i][1] = d.get(i);
            arquivos.add(d.get(i));
        }
    }

    public void showTable(File[] d) {
        preencheLinhas(d);
        atualizaTabela();

        txtData.setText(LocalDateTime.now().format(dateFormat));
    }

    public void showTable(List<File> d) {
        preencheLinhas(d);
        atualizaTabela();
        txtData.setText(LocalDateTime.now().format(dateFormat));
    }


    /*private HashMap<String, File> getHashMap() {
        HashMap<String, File> hash = new HashMap<String, File>();
        for (Object[] o : Tabela.linhas) {
            hash.put(o[0].toString(), (File) o[1]);
        }
        return hash;
    }*/
    private List<File> getListFile() {
        List<File> list = new ArrayList<>();
        for (Object[] o : Tabela.linhas) {
            list.add((File) o[1]);
        }
        return list;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAutor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        panelResultados = new javax.swing.JPanel();
        btnGenerate = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        txtDestinyDir = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtRootDir = new javax.swing.JTextField();
        btnDir1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtTicket = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnDownload = new javax.swing.JButton();
        barra = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerador de Doc de Evidências");

        jLabel1.setText("Nome do Arquivo:");

        jLabel2.setText("Autor: ");

        txtAutor.setEditable(false);

        jLabel3.setText("Data:");

        txtData.setEditable(false);

        jLabel4.setText("Descrição:");

        txtDescricao.setColumns(20);
        txtDescricao.setRows(3);
        jScrollPane1.setViewportView(txtDescricao);

        panelResultados.setBorder(javax.swing.BorderFactory.createTitledBorder("Imagens"));

        javax.swing.GroupLayout panelResultadosLayout = new javax.swing.GroupLayout(panelResultados);
        panelResultados.setLayout(panelResultadosLayout);
        panelResultadosLayout.setHorizontalGroup(
            panelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelResultadosLayout.setVerticalGroup(
            panelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 222, Short.MAX_VALUE)
        );

        btnGenerate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new-file.png"))); // NOI18N
        btnGenerate.setText("gerar documento");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        jLabel6.setText("Título:");

        txtDestinyDir.setEditable(false);
        txtDestinyDir.setToolTipText("diretorio de destino");

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/erase.png"))); // NOI18N
        btnClear.setText("limpar arquivos");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel5.setText("Raiz:");

        txtRootDir.setEditable(false);

        btnDir1.setText("jButton2");
        btnDir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDir1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Mudança:");

        txtTicket.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTicket.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new-folder.png"))); // NOI18N
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnDownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/down-files.png"))); // NOI18N
        btnDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome)
                            .addComponent(txtTitulo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(70, 70, 70)
                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtRootDir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDir1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDownload)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jScrollPane1)
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClear)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(panelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(452, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDestinyDir)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtRootDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDir1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnCreate)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTicket)
                            .addComponent(jLabel7)))
                    .addComponent(btnDownload))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear)
                    .addComponent(btnGenerate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(barra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDestinyDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        if (TelaUtil.verifyEmpytFields(null, "Ticket e Nome do arquivo são obrigatórios", txtNome, txtTicket)) {
            if (TelaUtil.verifyEmpytFields(null, "Escolha diretório de destino", txtDestinyDir)) {
                if (arquivos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione os arquivos antes de gerar o arquivo!");
                } else {
                    txtDestinyDir.setText(ArquivoUtils.validateAndGenerateSubDirPath(txtRootDir.getText(), txtTicket.getText()));
                    File dir = new File(txtDestinyDir.getText());
                    String filePath = dir.getAbsolutePath() + "\\" + txtNome.getText() + ".doc";
                    GeracaoArquivo processoArquivo = new GeracaoArquivo(barra, this);
                    if (new File(filePath).exists()) {
                        if (ArquivoUtils.fileConflict(filePath, txtNome)) {
                            String newFilePath = dir.getAbsolutePath() + "\\" + txtNome.getText() + ".doc";
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    processoArquivo.gerar(newFilePath, txtTicket.getText(),txtAutor.getText(), txtData.getText(), arquivos);
                                }
                            });

                        }
                    } else {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                processoArquivo.gerar(filePath,txtTicket.getText(), txtAutor.getText(), txtData.getText(), arquivos);
                            }
                        });
                    }
                }
            }
        }
    }//GEN-LAST:event_btnGenerateActionPerformed

    /*private void validateAndGenerateSubDirPath() {
        LocalDate actualDate = LocalDate.now();
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM-yyyy");
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        String path = txtRootDir.getText() + "\\" + actualDate.getYear() + "\\" + actualDate.format(month) + "\\" + actualDate.format(date) + "\\" + txtTicket.getText();
        if (!(new File(path).exists())) {
            new File(path).mkdirs();
        }
        txtDestinyDir.setText(path);
    }*/

 /*private void createFiles() {
        String path = txtDestinyDir.getText();
        try {
            if (!new File(path + "\\aba10.sql").exists()) {
                FileWriter aba10 = new FileWriter(path + "\\aba10.sql");
                aba10.write("[SQL]\r\n");
                aba10.close();
            }
            if (!new File(path + "\\aba12.sql").exists()) {
                FileWriter aba12 = new FileWriter(path + "\\aba12.sql");
                aba12.write("[SQL]\r\n");
                aba12.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/
    private void generateFile() {
        //preencheMap();
        //File dir = TelaUtil.getDirectoryPath("");      
        String desc = txtDescricao.getText();
        if (desc.equals("")) {
            desc = " ";
        }
        String filePath = txtDestinyDir.getText() + "\\" + txtNome.getText() + ".doc";

        if (arquivos.size() > 4) {

            int inicioIndice = 0;
            int ultimoIndice = arquivos.size();
            int totalExecucoes = arquivos.size() / 4;
            System.out.println("TotalArquivos: " + arquivos.size());
            //HashMap<String, File> arquivosDividos = new HashMap<String, File>();
            List<File> arquivosDividos = new ArrayList<>();
            for (int i = 0; i <= totalExecucoes; i++) {
                System.out.println("Ind: " + inicioIndice + ", " + ultimoIndice);
                int contadorDeArquivos = 0;
                for (int z = inicioIndice; z < ultimoIndice; z++) {
                    //arquivosDividos.put(arquivos.get(z).getName(), arquivos.get(z));
                    arquivosDividos.add(arquivos.get(z));
                    contadorDeArquivos++;
                    inicioIndice++;
                    if (contadorDeArquivos == 4) {
                        contadorDeArquivos = 0;
                        break;
                    }
                }
                /* DocumentoEvidenciaold d = new DocumentoEvidenciaold(filePath + "_" + totalExecucoes, txtTitulo.getText(), desc, txtAutor.getText(), txtData.getText(), arquivosDividos);
                d.gerar();*/
                DocumentoEvidencia.gerar(filePath,txtTicket.getText(), txtAutor.getText(), txtData.getText(), arquivosDividos);
                arquivosDividos = new ArrayList<>();
                filePath = txtDestinyDir.getText() + "\\" + txtNome.getText() + "_" + totalExecucoes + ".doc";
            }

        } else {
            /*DocumentoEvidenciaold d = new DocumentoEvidenciaold(filePath, txtTitulo.getText(), desc, txtAutor.getText(), txtData.getText(), getHashMap());
            d.gerar();*/
            DocumentoEvidencia.gerar(filePath,txtTicket.getText(), txtAutor.getText(), txtData.getText(), arquivos);
        }

        ajudante.nomeArquivo = txtNome.getText();
        ajudante.pastaRaiz = txtRootDir.getText();
        Preferencias.salvar(ajudante);
        JOptionPane.showMessageDialog(null, "Documento gerado com sucesso!");
    }

    private void generateFile2() {
        String filePath = txtDestinyDir.getText() + "\\" + txtNome.getText() + ".doc";

        long totalSizeFiles = 0;
        List<File> arquivosDividos = new ArrayList<>();
        int totalExecucoes = 0;

        //BarraDeProgresso barraDeProgresso = new BarraDeProgresso(arquivos.size());
        //barraDeProgresso.start();
        /*EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    barraDeProgresso.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/
        for (File arquivo : arquivos) {
            //System.out.println("Arq: " + arquivo.length());
            //System.out.println("Cal: " + (totalSizeFiles + arquivo.length() / MBInByte));
            if (((totalSizeFiles + arquivo.length()) / MBInByte) > 2.8) {
                if (totalExecucoes == 0) {
                    DocumentoEvidencia.gerar(filePath,txtTicket.getText(), txtAutor.getText(), txtData.getText(), arquivosDividos);
                    arquivosDividos = new ArrayList<>();
                    totalSizeFiles = 0;
                    totalExecucoes++;
                } else {
                    filePath = txtDestinyDir.getText() + "\\" + txtNome.getText() + "_" + totalExecucoes + ".doc";
                    DocumentoEvidencia.gerar(filePath,txtTicket.getText(), txtAutor.getText(), txtData.getText(), arquivosDividos);
                    arquivosDividos = new ArrayList<>();
                    totalSizeFiles = 0;
                    totalExecucoes++;
                }
            }
            totalSizeFiles += arquivo.length();
            arquivosDividos.add(arquivo);

            /*SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        barraDeProgresso.contador++;
                        java.lang.Thread.sleep(100);
                    } catch (InterruptedException e) {
                        ;
                    }
                }
            });*/
        }

        DocumentoEvidencia.gerar(filePath,txtTicket.getText(), txtAutor.getText(), txtData.getText(), arquivosDividos);

        ajudante.nomeArquivo = txtNome.getText();
        ajudante.pastaRaiz = txtRootDir.getText();
        Preferencias.salvar(ajudante);
        JOptionPane.showMessageDialog(null, "Documento gerado com sucesso!");
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        panelResultados.removeAll();
        iniciaPanel();
        arquivos.clear();
        panelResultados.validate();
        barra.setValue(0);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnDir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDir1ActionPerformed
        File dir = TelaUtil.getDirectoryPath("");
        if (dir != null) {
            txtRootDir.setText(dir.getAbsolutePath());
            ajudante.pastaRaiz = dir.getAbsolutePath();
            ajudante.ultimaPastaDestino = dir.getAbsolutePath();
            Preferencias.salvar(ajudante);
            txtDestinyDir.setText(dir.getAbsolutePath());
        }

        /*try {
            this.setState(Frame.ICONIFIED);
            Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenCapture = new Robot().createScreenCapture(screen);

            Image cursor = ImageIO.read(new File("c:/cursor.gif"));
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;

            Graphics2D graphics2D = screenCapture.createGraphics();
            graphics2D.drawImage(cursor, x, y, 16, 16, null); // cursor.gif is 16x16 size.
            ImageIO.write(screenCapture, "GIF", new File("capture.gif"));

            // BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            //ImageIO.write(image, "png", new File("screenshot.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }//GEN-LAST:event_btnDir1ActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        txtDestinyDir.setText(ArquivoUtils.validateAndGenerateSubDirPath(txtRootDir.getText(), txtTicket.getText()));
        ArquivoUtils.createFiles(txtDestinyDir.getText());
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadActionPerformed
        DownloadFiles downloadFiles = new DownloadFiles(txtDestinyDir.getText());
        downloadFiles.setVisible(true);
    }//GEN-LAST:event_btnDownloadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GeradorDeDocumentosDND.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GeradorDeDocumentosDND.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GeradorDeDocumentosDND.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GeradorDeDocumentosDND.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GeradorDeDocumentosDND().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barra;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDir1;
    private javax.swing.JButton btnDownload;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelResultados;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtDestinyDir;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRootDir;
    private javax.swing.JTextField txtTicket;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables

    private void habilitaButoes(boolean habilita) {
        btnClear.setEnabled(habilita);
        btnCreate.setEnabled(habilita);
        btnDir1.setEnabled(habilita);
        btnGenerate.setEnabled(habilita);
        btnDownload.setEnabled(habilita);
    }

    @Override
    public void iniciar() {
        habilitaButoes(false);
    }

    @Override
    public void finalizar() {
        habilitaButoes(true);
        ajudante.nomeArquivo = txtNome.getText();
        ajudante.pastaRaiz = txtRootDir.getText();
        Preferencias.salvar(ajudante);
        JOptionPane.showMessageDialog(null, "Documento gerado com sucesso!");
    }
}
