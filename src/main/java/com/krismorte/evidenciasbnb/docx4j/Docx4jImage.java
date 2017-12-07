/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.evidenciasbnb.docx4j;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;

/**
 *
 * @author c007329
 */
public class Docx4jImage {

    public static void add(WordprocessingMLPackage wordMLPackage, MainDocumentPart mainDocument, List<File> list) throws Exception {
        for (File file : list) {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            BinaryPartAbstractImage imagePart = BinaryPartAbstractImage
                    .createImagePart(wordMLPackage, fileContent);
            Inline inline = imagePart.createImageInline(
                    "Baeldung Image (filename hint)", "Alt Text", 1, 2, false);
            P Imageparagraph = addImageToParagraph(inline);
            mainDocument.getContent().add(Imageparagraph);
        }
    }

    private static P addImageToParagraph(Inline inline) {
        ObjectFactory factory = new ObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        p.getContent().add(r);
        Drawing drawing = factory.createDrawing();
        r.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);
        return p;
    }
}
