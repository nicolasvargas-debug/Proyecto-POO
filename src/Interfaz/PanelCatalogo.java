package Interfaz;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Modelo.Libro;

public class PanelCatalogo extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelCatalogo(InterfazUsuario ventana) {
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new String[] { "ISBN", "Nombre", "Autor", "Total", "Disponibles" }, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() >= 0) {
                ventana.filaSeleccionada(tabla.getSelectedRow());
            }
        });

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public void mostrarLibros(List<Libro> libros) {
        modelo.setRowCount(0);
        for (Libro l : libros) {
            modelo.addRow(new Object[] { l.getIsbn(), l.getNombre(), l.getAutor(),
                    l.getCopiasTotales(), l.getCopiasDisponibles() });
        }
    }

    public String getClave(int fila) {
        return String.valueOf(modelo.getValueAt(fila, 0));
    }
}