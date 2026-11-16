package Interfaz;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.Libro;

public class PanelMisReservas extends JPanel {
    private DefaultTableModel modelo;

    public PanelMisReservas() {
        setLayout(new BorderLayout());
        add(new JLabel("Mis reservas"), BorderLayout.NORTH);

        modelo = new DefaultTableModel(new String[] { "Libro", "Autor" }, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        add(new JScrollPane(new JTable(modelo)), BorderLayout.CENTER);
    }

    public void mostrar(List<Libro> libros) {
        modelo.setRowCount(0);
        for (Libro l : libros) {
            modelo.addRow(new Object[] { l.getNombre(), l.getAutor() });
        }
    }
}