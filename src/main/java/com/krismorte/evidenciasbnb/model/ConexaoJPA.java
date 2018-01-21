/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author c007329
 */
public class ConexaoJPA {

    private static final String driveKey = "drive";
    private static final String dialectKey = "dialect";
    private static final String urlKey = "url";
    private static final String userKey = "user";
    private static final String passKey = "pass";

    public String drive;
    public String dialect;
    public String url;
    public String user;
    public String pass;

    public static ConexaoJPA loadFromProperties(Properties prop) {
        ConexaoJPA connJpa = new ConexaoJPA();

        connJpa.dialect = prop.getProperty(dialectKey);
        connJpa.drive = prop.getProperty(driveKey);
        connJpa.url = prop.getProperty(urlKey);
        connJpa.user = prop.getProperty(userKey);
        connJpa.pass = prop.getProperty(passKey);

        return connJpa;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put(urlKey, url);
        map.put(driveKey, drive);
        map.put(dialectKey, dialect);
        map.put(userKey, user);
        map.put(passKey, pass);
        return map;
    }

}
