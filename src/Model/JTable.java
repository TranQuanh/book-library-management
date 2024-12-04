package Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class JTable extends javax.swing.JTable {

    public JTable(String[][] data, String[] header, Color color1, Color color2) {
        super(data, header);
        setRowHeight(40);
        setBackground(color1);

        DefaultTableModel tableModel = new DefaultTableModel(data, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setModel(tableModel);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);
                setFont(new Font("Noto Serif Regular", Font.BOLD, 20));
                setForeground(color1);
                if (hasFocus) setBorder(null);
                setBackground(Color.white);

                return this;
            }
        };

        for (int i=0;i<getColumnModel().getColumnCount();i++) {
            getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);
                setFont(new Font("Noto Serif Regular", Font.BOLD, 20));
                setBackground(color1);
                setForeground(Color.white);
                setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                return this;
            }
        };
        getTableHeader().setDefaultRenderer(headerRenderer);
        getTableHeader().setBorder(BorderFactory.createMatteBorder(2, 2, 1, 2, color1));
        setBorder(BorderFactory.createMatteBorder(1, 2, 2, 2, color1));
        setGridColor(color1);
        setRowSelectionAllowed(false);
    }

}
