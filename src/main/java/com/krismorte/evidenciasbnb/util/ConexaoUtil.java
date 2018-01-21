/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb.util;

import com.krismorte.evidenciasbnb.model.ConexaoJPA;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author c007329
 */
public class ConexaoUtil {
    
    public static boolean test(ConexaoJPA conexaoJPA) throws Exception {       
        CriptUtil criptUtil = new CriptUtil();
        return test(conexaoJPA.drive, conexaoJPA.url, conexaoJPA.user, criptUtil.decrypt(conexaoJPA.pass));
        
    }
    
    public static boolean test(String drive, String url, String usuario, String senha) {
        boolean retornoOk = false;
        Connection c = null;
        try {
            Class.forName(drive);
            c = DriverManager.getConnection(url, usuario, senha);
            retornoOk = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return retornoOk;
        
    }
}
