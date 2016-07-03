/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registro.view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import registro.model.MaterialBibliografico;
import registro.model.Prestamo;

public class PrestamosTableCustom extends JPanel {

    List<TableCellEditor> editors = new ArrayList<>();
    Object[][] data;
    
    public PrestamosTableCustom(Object[][] data) {
        this.data = data;
    }

    public JTable getTable(List<Prestamo> prestamos) {
        setLayout(new BorderLayout());

        // Create the editors to be used for each row
        for (Prestamo prestamo : prestamos) {
            JComboBox comboBox = new JComboBox();
            for (MaterialBibliografico material : prestamo.getMateriales()) {
                comboBox.addItem(String.format("%s | %s",
                        material.getCodigo(), material.getTitulo()));
            }
            DefaultCellEditor cellE = new DefaultCellEditor(comboBox);
            editors.add(cellE);
        }

        //  Create the table with default data
        /*Object[][] data
                = {
                    {"Color", "Red"},
                    {"Shape", "Square"},
                    {"Fruit", "Banana"},
                    {"Plain", "Text"}
                };*/
        //String[] columnNames = {"Type", "Value"};
        String[] columnNames = {"Código", "Material Retirado", "Persona", "Fecha", "Fecha Dev", "Estado", "Observaciones"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model) {
            //  Determine editor to be used by row
            public TableCellEditor getCellEditor(int row, int column) {
                int modelColumn = convertColumnIndexToModel(column);

                if (modelColumn == 1) {
                    return editors.get(row);
                } else {
                    return super.getCellEditor(row, column);
                }
            }
        };

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        
        return table;

    }

    /*private static void createAndShowUI() {
        JFrame frame = new JFrame("Table Combo Box by Row");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TableComboBoxByRow());
        frame.setSize(200, 200);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }*/

    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowUI();
            }
        });
    }*/
}
