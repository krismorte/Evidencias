/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb.docx4j;

import java.io.File;
import java.util.List;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.utils.BufferUtil;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.HdrFtrRef;
import org.docx4j.wml.HeaderReference;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.SectPr;

/**
 *
 * @author c007329
 */
public class Docx4jHead {

    private static ObjectFactory objectFactory = new ObjectFactory();

    public static void add(WordprocessingMLPackage wordMLPackage) throws Exception {

        Relationship relationship = createHeaderPart(wordMLPackage);
        // 2. an entry in SectPr
        createHeaderReference(wordMLPackage, relationship);

    }

    private static Relationship createHeaderPart(
            WordprocessingMLPackage wordprocessingMLPackage)
            throws Exception {

        HeaderPart headerPart = new HeaderPart();
        Relationship rel = wordprocessingMLPackage.getMainDocumentPart()
                .addTargetPart(headerPart);

        // After addTargetPart, so image can be added properly
        headerPart.setJaxbElement(getHdr(wordprocessingMLPackage, headerPart));

        return rel;
    }

    private static Hdr getHdr(WordprocessingMLPackage wordprocessingMLPackage,
            Part sourcePart) throws Exception {

        Hdr hdr = objectFactory.createHdr();
        File file = new File("template\\bnbheader.png");
        /*File file = new File(System.getProperty("user.dir")
                + "/src/test/resources/images/greentick.png");*/
        java.io.InputStream is = new java.io.FileInputStream(file);

        hdr.getContent().add(
                newImage(wordprocessingMLPackage,
                        sourcePart,
                        BufferUtil.getBytesFromInputStream(is),
                        "filename", "alttext", 1, 2
                )
        );
        return hdr;

    }

    //	public static P getP() {
//		P headerP = objectFactory.createP();
//		R run1 = objectFactory.createR();
//		Text text = objectFactory.createText();
//		text.setValue("123head123");
//		run1.getRunContent().add(text);
//		headerP.getParagraphContent().add(run1);
//		return headerP;
//	}
    private static org.docx4j.wml.P newImage(WordprocessingMLPackage wordMLPackage,
            Part sourcePart,
            byte[] bytes,
            String filenameHint, String altText,
            int id1, int id2) throws Exception {

        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage,
                sourcePart, bytes);

        Inline inline = imagePart.createImageInline(filenameHint, altText,
                id1, id2, false);

        // Now add the inline in w:p/w:r/w:drawing
        org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
        org.docx4j.wml.P p = factory.createP();
        org.docx4j.wml.R run = factory.createR();
        p.getContent().add(run);
        org.docx4j.wml.Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);

        return p;

    }

    private static void createHeaderReference(
            WordprocessingMLPackage wordprocessingMLPackage,
            Relationship relationship)
            throws InvalidFormatException {

        List<SectionWrapper> sections = wordprocessingMLPackage.getDocumentModel().getSections();

        SectPr sectPr = sections.get(sections.size() - 1).getSectPr();
        // There is always a section wrapper, but it might not contain a sectPr
        if (sectPr == null) {
            sectPr = objectFactory.createSectPr();
            wordprocessingMLPackage.getMainDocumentPart().addObject(sectPr);
            sections.get(sections.size() - 1).setSectPr(sectPr);
        }

        HeaderReference headerReference = objectFactory.createHeaderReference();
        headerReference.setId(relationship.getId());
        headerReference.setType(HdrFtrRef.DEFAULT);
        sectPr.getEGHdrFtrReferences().add(headerReference);// add header or
        // footer references

    }

}
