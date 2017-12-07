/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.table;

/**
 *
 * @author C007329
 */
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TabelaCelulaCustomizada extends DefaultTableCellRenderer {

    private Object[][] objects;
    private int vefiryColumn;
    private String positiveValue;
    private Color positiveColor;
    private Color positiveSelectedColor;
    private String negativeValue;
    private Color negativeColor;
    private Color negativeSelectedColor;

    /***
     *
     * exemplode chamada
     *
     * TableCellRenderer renderer = new CustomTableCellRenderer(data,1,"Ganho",new Color(152,251,152),Color.GREEN,"Custo",new Color(255,99,71),Color.RED);

    try {
    table.setDefaultRenderer(Class.forName("java.lang.String"), renderer);
    } catch (ClassNotFoundException ex) {
    System.exit(0);
    }
     *
     * @param objects
     * @param vefiryColumn
     * @param positiveValue
     * @param positiveColor
     * @param positiveSelectedColor
     * @param negativeValue
     * @param negativeColor
     * @param negativeSelectedColor
     */
    public TabelaCelulaCustomizada(Object[][] objects, int vefiryColumn, String positiveValue, Color positiveColor, Color positiveSelectedColor, String negativeValue, Color negativeColor, Color negativeSelectedColor) {
        this.objects = objects;
        this.vefiryColumn = vefiryColumn;
        this.positiveValue = positiveValue;
        this.positiveColor = positiveColor;
        this.positiveSelectedColor = positiveSelectedColor;
        this.negativeValue = negativeValue;
        this.negativeColor = negativeColor;
        this.negativeSelectedColor = negativeSelectedColor;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (objects[row][vefiryColumn].toString().equals(negativeValue)) {
            //System.out.println(objects[row][vefiryColumn].toString()+" "+negativeValue);
            if (isSelected) {
                cell.setBackground(negativeSelectedColor);
            } else {
                cell.setBackground(negativeColor);
            }
        } else if (objects[row][vefiryColumn].toString().equals(positiveValue)) {
            //System.out.println(objects[row][vefiryColumn].toString()+" "+positiveValue);
            if (isSelected) {
                cell.setBackground(positiveSelectedColor);
            } else {
                cell.setBackground(positiveColor);
            }
        } else {
            cell.setBackground(Color.white);
        }

        return cell;

    }
}
