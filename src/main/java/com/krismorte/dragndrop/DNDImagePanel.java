/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.dragndrop;

import com.krismorte.evidenciasbnb.view.GeradorDeDocumentosDND;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.io.File;
import java.util.List;
import java.util.TooManyListenersException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DNDImagePanel extends JPanel {

    private DropTarget dropTarget;
    private DropTargetImageHandler dropTargetHandler;

    private boolean dragOver = false;
    private boolean multiFile = true;

    private File image;
    private JLabel labelImage;

    private GeradorDeDocumentosDND geradorDeDocumentosDND;
    
    public DNDImagePanel(GeradorDeDocumentosDND geradorDeDocumentosDND) {
        this.geradorDeDocumentosDND=geradorDeDocumentosDND;
        this.setLayout(new GridLayout(1, 1));
        this.setFocusable(true);
        this.setOpaque(false);
    }

    public DNDImagePanel(String title) {
        this.setBorder(BorderFactory.createTitledBorder(title));
        this.setLayout(new GridLayout(1, 1));
        this.setFocusable(true);
        this.setOpaque(false);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 150);
    }

    protected DropTarget getMyDropTarget() {
        if (dropTarget == null) {
            dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, null);
        }

        return dropTarget;
    }

    protected DropTargetImageHandler getDropTargetHandler() {
        if (dropTargetHandler == null) {
            dropTargetHandler = new DropTargetImageHandler(this);
        }

        return dropTargetHandler;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        try {
            getMyDropTarget().addDropTargetListener(getDropTargetHandler());
        } catch (TooManyListenersException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        getMyDropTarget().removeDropTargetListener(getDropTargetHandler());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (dragOver) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(new Color(0, 255, 0, 64));
            g2d.fill(new Rectangle(getWidth(), getHeight()));

            g2d.dispose();
        }

    }

    protected void addJLabel() {
        this.add(labelImage);
        //validate();
        repaint();
    }

    public void clear() {
        image = null;
        removeAll();
        validate();
        repaint();
    }

    protected void importFiles(final File importImage) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                /*clear();
                image = importImage;
                labelImage = new JLabel(new ImageIcon((Image) ImgUtils.scaleImage(getWidth(), getHeight(), image.getAbsolutePath())));
                labelImage.setLocation(5, 15);
                labelImage.setSize(new Dimension(getWidth(), getHeight() - 15));
                addJLabel();*/
                geradorDeDocumentosDND.showTable(getImages());
            }
        };
        SwingUtilities.invokeLater(run);
    }

    public void setDragOver(boolean dragOver) {
        this.dragOver = dragOver;
    }

    public boolean isMultiFile() {
        return multiFile;
    }

    public File getImage() {
        return image;
    }

    public List<File> getImages() {
        return dropTargetHandler.getFiles();
    }

}
