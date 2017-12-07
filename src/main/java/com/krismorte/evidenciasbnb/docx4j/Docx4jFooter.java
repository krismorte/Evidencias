/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb.docx4j;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.CTRel;
import org.docx4j.wml.FldChar;
import org.docx4j.wml.FooterReference;
import org.docx4j.wml.Ftr;
import org.docx4j.wml.HdrFtrRef;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STFldCharType;
import org.docx4j.wml.SectPr;
import org.docx4j.wml.Text;

/**
 *
 * @author c007329
 */
public class Docx4jFooter {

    /**
     * Add Footer to the document
     *
     * @param wordMLPackage
     * @param docVersionNumber
     * @throws InvalidFormatException
     */
    public static void add(WordprocessingMLPackage wordMLPackage,
            String docVersionNumber) throws InvalidFormatException {
        ObjectFactory factory = Context.getWmlObjectFactory();
        Relationship relationship = createFooterPart(wordMLPackage, factory,
                docVersionNumber);
        createFooterReference(relationship, wordMLPackage, factory);
    }

    /**
     * This method creates a footer part and set the package on it. Then we add
     * some text and add the footer part to the package. Finally we return the
     * corresponding relationship.
     *
     * @param wordMLPackage the word ml package
     * @param versionNumber
     * @param documentNumber
     * @return the relationship
     * @throws InvalidFormatException the invalid format exception
     */
    private static Relationship createFooterPart(
            WordprocessingMLPackage wordMLPackage, ObjectFactory factory,
            String docversionNumber) throws InvalidFormatException {
        FooterPart footerPart = new FooterPart();
        footerPart.setPackage(wordMLPackage);
        /*StringBuilder footerText = new StringBuilder("");
        String documentNumber[] = docversionNumber.split("\\.");
        footerText.append(documentNumber[0]);
        footerText.append("v.");
        footerText.append(documentNumber[1]);*/
        footerPart.setJaxbElement(createFooter(docversionNumber/*footerText.toString()*/, factory));
        return wordMLPackage.getMainDocumentPart().addTargetPart(footerPart);
    }

    /**
     * First we retrieve the document sections from the package. As we want to
     * add a footer, we get the last section and take the section properties
     * from it. The section is always present, but it might not have properties,
     * so we check if they exist to see if we should create them. If they need
     * to be created, we do and add them to the main document part and the
     * section. Then we create a reference to the footer, give it the id of the
     * relationship, set the type to header/footer reference and add it to the
     * collection of references to headers and footers in the section
     * properties.
     *
     * @param relationship
     * @param wordMLPackage
     * @param factory
     */
    private static void createFooterReference(Relationship relationship,
            WordprocessingMLPackage wordMLPackage, ObjectFactory factory) {
        List<SectionWrapper> sections = wordMLPackage.getDocumentModel()
                .getSections();
        SectPr sectionProperties = sections.get(sections.size() - 1)
                .getSectPr();
        // There is always a section wrapper, but it might not contain a sectPr
        if (sectionProperties == null) {
            sectionProperties = factory.createSectPr();
            wordMLPackage.getMainDocumentPart().addObject(sectionProperties);
            sections.get(sections.size() - 1).setSectPr(sectionProperties);
        }

        /*
        * Remove footer if it already exists.
         */
        List<CTRel> relations = sectionProperties.getEGHdrFtrReferences();
        Iterator<CTRel> relationsItr = relations.iterator();
        while (relationsItr.hasNext()) {
            CTRel relation = relationsItr.next();
            if (relation instanceof FooterReference) {
                relationsItr.remove();
            }
        }

        FooterReference footerReference = factory.createFooterReference();
        footerReference.setId(relationship.getId());
        footerReference.setType(HdrFtrRef.DEFAULT);
        sectionProperties.getEGHdrFtrReferences().add(footerReference);
        FooterReference firstPagefooterRef = factory.createFooterReference();
        firstPagefooterRef.setId(relationship.getId());
        firstPagefooterRef.setType(HdrFtrRef.FIRST);
        sectionProperties.getEGHdrFtrReferences().add(firstPagefooterRef);
    }

    /**
     * First we create a footer, a paragraph, a run and a text. We add the given
     * given content to the text and add that to the run. The run is then added
     * to the paragraph, which is in turn added to the footer. Finally we return
     * the footer.
     *
     * @param content
     * @return
     */
    private static Ftr createFooter(String content, ObjectFactory factory) {
        Ftr footer = factory.createFtr();
        P paragraph = factory.createP();
        R run = factory.createR();
        /*
      * Change the font size to 8 points(the font size is defined to be in
      * half-point size so set the value as 16).
         */
        RPr rpr = new RPr();
        HpsMeasure size = new HpsMeasure();
        size.setVal(BigInteger.valueOf(16));
        rpr.setSz(size);

        PPr ppr = new PPr();
        Jc jc = new Jc();
        jc.setVal(JcEnumeration.RIGHT);
        ppr.setJc(jc);
        paragraph.setPPr(ppr);

        run.setRPr(rpr);
        Text text = new Text();
        text.setValue(content);
        run.getContent().add(text);
        paragraph.getContent().add(run);
        footer.getContent().add(paragraph);
        P pageNumParagraph = factory.createP();
        addFieldBegin(factory, pageNumParagraph);
        addPageNumberField(factory, pageNumParagraph);
        addFieldEnd(factory, pageNumParagraph);
        footer.getContent().add(pageNumParagraph);
        return footer;
    }

    /**
     * Every fields needs to be delimited by complex field characters. This
     * method adds the delimiter that precedes the actual field to the given
     * paragraph.
     *
     * @param paragraph
     */
    private static void addFieldBegin(ObjectFactory factory, P paragraph) {
        R run = factory.createR();
        FldChar fldchar = factory.createFldChar();
        fldchar.setFldCharType(STFldCharType.BEGIN);
        run.getContent().add(fldchar);
        paragraph.getContent().add(run);
    }

    /**
     * Creating the page number field is nearly the same as creating the field
     * in the TOC example. The only difference is in the value. We use the PAGE
     * command, which prints the number of the current page, together with the
     * MERGEFORMAT switch, which indicates that the current formatting should be
     * preserved when the field is updated.
     *
     * @param paragraph
     */
    private static void addPageNumberField(ObjectFactory factory, P paragraph) {
        R run = factory.createR();
        PPr ppr = new PPr();
        Jc jc = new Jc();
        jc.setVal(JcEnumeration.CENTER);
        ppr.setJc(jc);
        paragraph.setPPr(ppr);
        Text txt = new Text();
        txt.setSpace("preserve");
        txt.setValue(" PAGE   \\* MERGEFORMAT ");
        run.getContent().add(factory.createRInstrText(txt));
        paragraph.getContent().add(run);
    }

    /**
     * Every fields needs to be delimited by complex field characters. This
     * method adds the delimiter that follows the actual field to the given
     * paragraph.
     *
     * @param paragraph
     */
    private static void addFieldEnd(ObjectFactory factory, P paragraph) {
        FldChar fldcharend = factory.createFldChar();
        fldcharend.setFldCharType(STFldCharType.END);
        R run3 = factory.createR();
        run3.getContent().add(fldcharend);
        paragraph.getContent().add(run3);
    }

}
