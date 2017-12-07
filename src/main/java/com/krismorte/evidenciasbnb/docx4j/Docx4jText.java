/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb.docx4j;

import static com.krismorte.evidenciasbnb.model.DocumentoEvidencia.BOLD_TEXT;
import static com.krismorte.evidenciasbnb.model.DocumentoEvidencia.ITALIC_BOLD_TEXT;
import static com.krismorte.evidenciasbnb.model.DocumentoEvidencia.ITALIC_TEXT;
import java.math.BigInteger;
import java.util.HashMap;
import javax.xml.bind.JAXBException;
import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.Color;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.Text;

/**
 *
 * @author c007329
 */
public class Docx4jText {

    private static void addParagraph(MainDocumentPart mainDocument, String text) {
        ObjectFactory factory = Context.getWmlObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text t = factory.createText();
        t.setValue(text);
        r.getContent().add(t);
        p.getContent().add(r);
        mainDocument.getContent().add(p);
    }

    private static void addParagraph(MainDocumentPart mainDocument, int style, String text) {
        ObjectFactory factory = Context.getWmlObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text t = factory.createText();
        t.setValue(text);
        r.getContent().add(t);
        p.getContent().add(r);
        RPr rpr = factory.createRPr();
        BooleanDefaultTrue b = new BooleanDefaultTrue();
        if (style == ITALIC_TEXT) {
            rpr.setI(b);
        } else if (style == BOLD_TEXT) {
            rpr.setB(b);
        } else if (style == ITALIC_BOLD_TEXT) {
            rpr.setB(b);
            rpr.setI(b);
        }
        r.setRPr(rpr);
        mainDocument.getContent().add(p);
    }

    private static void addParagraph(MainDocumentPart mainDocument, int style, int size, String text) {
        ObjectFactory factory = Context.getWmlObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text t = factory.createText();
        t.setValue(text);
        r.getContent().add(t);
        p.getContent().add(r);
        RPr rpr = factory.createRPr();
        BooleanDefaultTrue b = new BooleanDefaultTrue();
        if (style == ITALIC_TEXT) {
            rpr.setI(b);
        } else if (style == BOLD_TEXT) {
            rpr.setB(b);
        } else if (style == ITALIC_BOLD_TEXT) {
            rpr.setB(b);
            rpr.setI(b);
        }
        HpsMeasure measure = new HpsMeasure();
        measure.setVal(new BigInteger("" + size));
        r.setRPr(rpr);
        mainDocument.getContent().add(p);
    }

    public static void addParagraph(MainDocumentPart mainDocument, JcEnumeration hAlign, int style, int size, String text) {
        ObjectFactory factory = Context.getWmlObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text t = factory.createText();
        PPr pprop = new PPr();
        Jc align = new Jc();
        align.setVal(hAlign);
        pprop.setJc(align);
        p.setPPr(pprop);
        t.setValue(text);
        r.getContent().add(t);
        p.getContent().add(r);
        RPr rpr = factory.createRPr();
        BooleanDefaultTrue b = new BooleanDefaultTrue();
        if (style == ITALIC_TEXT) {
            rpr.setI(b);
        } else if (style == BOLD_TEXT) {
            rpr.setB(b);
        } else if (style == ITALIC_BOLD_TEXT) {
            rpr.setB(b);
            rpr.setI(b);
        }
        HpsMeasure measure = new HpsMeasure();
        measure.setVal(new BigInteger("" + size));
        r.setRPr(rpr);
        mainDocument.getContent().add(p);
    }

    private static void addItalicParagraph(MainDocumentPart mainDocument, String text) {
        ObjectFactory factory = Context.getWmlObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text t = factory.createText();
        t.setValue(text);
        r.getContent().add(t);
        p.getContent().add(r);
        RPr rpr = factory.createRPr();
        BooleanDefaultTrue b = new BooleanDefaultTrue();
        rpr.setI(b);
        r.setRPr(rpr);
        mainDocument.getContent().add(p);
    }

    private static void addBoldParagraph(MainDocumentPart mainDocument, String text) {
        ObjectFactory factory = Context.getWmlObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text t = factory.createText();
        t.setValue(text);
        r.getContent().add(t);
        p.getContent().add(r);
        RPr rpr = factory.createRPr();
        BooleanDefaultTrue b = new BooleanDefaultTrue();
        rpr.setB(b);
        r.setRPr(rpr);
        mainDocument.getContent().add(p);
    }

    private static void createColorfulParagraph(MainDocumentPart mainDocument, String text, String colorName) {
        ObjectFactory factory = Context.getWmlObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text t = factory.createText();
        t.setValue(text);
        r.getContent().add(t);
        p.getContent().add(r);
        RPr rpr = factory.createRPr();
        BooleanDefaultTrue b = new BooleanDefaultTrue();
        rpr.setB(b);
        rpr.setI(b);
        rpr.setCaps(b);
        Color color = factory.createColor();
        color.setVal(colorName);
        rpr.setColor(color);
        r.setRPr(rpr);
        mainDocument.getContent().add(p);
    }

    private static void addObject(MainDocumentPart wordDocumentPart, String template, String fontName, String text, Integer size, String color, String align) throws JAXBException {

        HashMap substitution = new HashMap();
        substitution.put("fontname", fontName);
        substitution.put("text", text);
        if (size != null) {
            substitution.put("size", size);
        } else {
            substitution.put("size", 14);
        }
        if (color != null) {
            substitution.put("color", color);
        } else {
            substitution.put("color", "000000");
        }
        if (align != null) {
            substitution.put("align", align);
        } else {
            substitution.put("align", "left");
        }
        Object o = XmlUtils.unmarshallFromTemplate(template, substitution);
        wordDocumentPart.addObject(o);

    }

    final static String sampleText = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">"
            + "<w:r>"
            + "<w:rPr>"
            + "<w:rFonts w:ascii=\"${fontname}\" w:eastAsia=\"${fontname}\" w:hAnsi=\"${fontname}\" w:cs=\"${fontname}\" />"
            + "<w:color w:val=\"${color}\"/>"
            + "<w:sz w:val=\"${size}\"/>"
            + "</w:rPr>"
            + "<w:t xml:space=\"preserve\">${text}</w:t>"
            + "</w:r>"
            + "</w:p>";
    final static String sampleTextBold = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">"
            + "<w:r>"
            + "<w:rPr>"
            + "<w:rFonts w:ascii=\"${fontname}\" w:eastAsia=\"${fontname}\" w:hAnsi=\"${fontname}\" w:cs=\"${fontname}\" />"
            + "<w:sz w:val=\"${size}\"/>"
            + "<w:color w:val=\"${color}\"/>"
            + "<w:b />"
            + "</w:rPr>"
            + "<w:t>${text}</w:t>"
            + "</w:r>"
            + "</w:p>";
    final static String sampleTextItalic = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">"
            + "<w:r>"
            + "<w:rFonts w:ascii=\"${fontname}\" w:eastAsia=\"${fontname}\" w:hAnsi=\"${fontname}\" w:cs=\"${fontname}\" />"
            + "<w:sz w:val=\"${size}\"/>"
            + "<w:color w:val=\"${color}\"/>"
            + "<w:i />"
            + "</w:rPr>"
            + "<w:t>${text}</w:t>"
            + "</w:r>"
            + "</w:p>";
    final static String sampleTextBoldItalic = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">"
            + "<w:r>"
            + "<w:rPr>"
            + "<w:rFonts w:ascii=\"${fontname}\" w:eastAsia=\"${fontname}\" w:hAnsi=\"${fontname}\" w:cs=\"${fontname}\" />"
            + "<w:sz w:val=\"${size}\"/>"
            + "<w:color w:val=\"${color}\" />"
            + "<w:b />"
            + "<w:i />"
            + "</w:rPr>"
            + "<w:t>${text}</w:t>"
            + "</w:r>"
            + "</w:p>";

}
