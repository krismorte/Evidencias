/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb;

import com.krismorte.evidenciasbnb.view.GeradorDeDocumentosDND;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author c007329
 */
public class Main {

    /*
    
    select c.chg_ref_num from wf w 
inner join chg c on c.id=w.object_id
where w.assignee = (select contact_uuid from ca_contact where userid='c007329')
and w.status not in('COMP','CNCL','SKIP','CANCEL')
    
     */

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        GeradorDeDocumentosDND g = new GeradorDeDocumentosDND();
        g.setLocationRelativeTo(null);
        g.setVisible(true);
    }

}
