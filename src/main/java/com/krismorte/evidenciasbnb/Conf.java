/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author c007329
 */
public class Conf {

    private static final String arquivo = "config.properties";

    public static boolean fileExists() {
        return new File(arquivo).exists();
    }

    public static void write(Map<String, String> values) {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream(arquivo);

            // set the properties value
            for (Map.Entry<String, String> entry : values.entrySet()) {
                prop.setProperty(entry.getKey(), entry.getValue());
            }
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Properties load() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(arquivo);

            // load a properties file
            //prop.load(input);
            prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

}
