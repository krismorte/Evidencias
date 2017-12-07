/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb.util;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author c007329
 */
public class ArquivoUtils {

    public static String validateAndGenerateSubDirPath(String path, String ticket) {
        LocalDate actualDate = LocalDate.now();
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM-yyyy");
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        String pathResult = /*txtRootDir.getText()*/ path + "\\" + actualDate.getYear() + "\\" + actualDate.format(month) + "\\" + actualDate.format(date) + "\\" + ticket/*txtTicket.getText()*/;
        if (!(new File(pathResult).exists())) {
            new File(pathResult).mkdirs();
        }
        //txtDestinyDir.setText(path);
        return pathResult;
    }

    public static void createFiles(String path) {
        //String path = txtDestinyDir.getText();
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
    }

    public static boolean fileConflict(String filePath, JTextField txt) {
        boolean conflitoTratado = false;
        Object[] options1 = {"substituir", "renomear",
            "cancelar"};

        JPanel panel = new JPanel();
        panel.add(new JLabel("Novo nome:"));
        JTextField textField = new JTextField(10);
        panel.add(textField);
        textField.setText(txt.getText());
        int result = JOptionPane.showOptionDialog(null, panel, "Arquivos já existe!",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1, null);
        if (result == JOptionPane.YES_OPTION) {
            //JOptionPane.showMessageDialog(null, textField.getText());
            new File(filePath).delete();
            conflitoTratado = true;
        } else if (result == JOptionPane.NO_OPTION) {
            if (textField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite o novo nome!");
            } else {
                txt.setText(textField.getText());
                conflitoTratado = true;
            }
        } else if (result == JOptionPane.CANCEL_OPTION) {
            conflitoTratado = false;
        }
        return conflitoTratado;
    }

}
