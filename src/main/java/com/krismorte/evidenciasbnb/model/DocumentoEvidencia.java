/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb.model;

import com.krismorte.evidenciasbnb.docx4j.Docx4jFooter;
import com.krismorte.evidenciasbnb.docx4j.Docx4jHead;
import com.krismorte.evidenciasbnb.docx4j.Docx4jImage;
import com.krismorte.evidenciasbnb.docx4j.Docx4jText;
import java.io.File;
import java.util.List;
import org.docx4j.Docx4J;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Br;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.STBrType;

/**
 *
 * @author c007329
 */
public class DocumentoEvidencia {

    public final static int PLAIN_TEXT = 0;
    public final static int ITALIC_TEXT = 1;
    public final static int BOLD_TEXT = 2;
    public final static int ITALIC_BOLD_TEXT = 3;


    /* public static void gerar(String filePath) {
        try {

            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                    .createPackage();
            // Delete the Styles part, since it clutters up our output
            MainDocumentPart mainDocument = wordMLPackage.getMainDocumentPart();
            Relationship styleRel = mainDocument.getStyleDefinitionsPart().getSourceRelationships().get(0);
            mainDocument.getRelationshipsPart().removeRelationship(styleRel);

            Relationship relationship = createHeaderPart(wordMLPackage);
            // 2. an entry in SectPr
            createHeaderReference(wordMLPackage, relationship);

            addObject(mainDocument, sampleTextBold, "Constantia", "Mudança teste", 48, null, "right");
            addObject(mainDocument, sampleTextBold, "Constantia", "Mudança teste", null, "B22222", null);

            String filename = filePath;
            //Docx4J.save(wordMLPackage, new java.io.File(filename), Docx4J.FLAG_SAVE_ZIP_FILE);
            Docx4J.save(wordMLPackage, new File(filename + "\\teste.doc"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
     */
    public static void gerar(String filePath,String ticket, String autor, String dataHora, List<File> list) {
        try {
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                    .createPackage();
            MainDocumentPart mainDocument = wordMLPackage.getMainDocumentPart();

            Docx4jHead.add(wordMLPackage);

            /*Relationship relationship = createHeaderPart(wordMLPackage);
            // 2. an entry in SectPr
            createHeaderReference(wordMLPackage, relationship);
**/
            mainDocument.addStyledParagraphOfText("Title", ticket);
            Docx4jText.addParagraph(mainDocument, JcEnumeration.RIGHT, PLAIN_TEXT, 22, "Autor: " + autor);
            Docx4jText.addParagraph(mainDocument, JcEnumeration.RIGHT, PLAIN_TEXT, 22, "DataHora: " + dataHora);
            //addParagraph(mainDocument, JcEnumeration.RIGHT, PLAIN_TEXT, 22, "Autor: " + autor);
            //addParagraph(mainDocument, JcEnumeration.RIGHT, PLAIN_TEXT, 22, "DataHora: " + dataHora);
            /*mainDocument.addStyledParagraphOfText("Title", "Example 1");
            mainDocument.addParagraphOfText("Example 2");

            createColorfulParagraph(mainDocument, "Example 3", "blue");
            createColorfulParagraph(mainDocument, "Example 3.1", "A52A2A");            
            addParagraph(mainDocument, "Example 4");
            addParagraph(mainDocument, ITALIC_TEXT,"Example 5");
            addParagraph(mainDocument, BOLD_TEXT,"Example 5");
            addParagraph(mainDocument,PLAIN_TEXT,40, "Example 6");
            addParagraph(mainDocument,JcEnumeration.CENTER,PLAIN_TEXT,25, "Example 6");
            addParagraph(mainDocument,JcEnumeration.RIGHT,PLAIN_TEXT,25, "Example 6");*/

            ObjectFactory factory = Context.getWmlObjectFactory();
            Br br = factory.createBr();
            br.setType(STBrType.PAGE);
            mainDocument.getContent().add(br);

            Docx4jImage.add(wordMLPackage, mainDocument, list);
            //addImage(wordMLPackage, mainDocument, list);
            Docx4jFooter.add(wordMLPackage, "EvidênciasBNB 1.0 by krisnamourt - C007329");
            //addFooterToDocument(wordMLPackage, "EvidênciasBNB 1.0 by Krismorte C007329");
            Docx4J.save(wordMLPackage, new File(filePath));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
