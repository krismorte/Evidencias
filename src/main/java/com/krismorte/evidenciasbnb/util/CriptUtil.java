/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb.util;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author c007329
 */
public class CriptUtil {

    private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

    public String encrypt(String value) throws Exception {
        Key aesKey = new SecretKeySpec(keyValue, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        // encrypt the text
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(value.getBytes());
        return new String(encrypted);
    }

    public String decrypt(String value) throws Exception {
        Key aesKey = new SecretKeySpec(keyValue, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        //byte[] encrypted = cipher.doFinal(value.getBytes());        
        String decrypted = new String(cipher.doFinal(value.trim().getBytes()));
        return decrypted;
    }

}
