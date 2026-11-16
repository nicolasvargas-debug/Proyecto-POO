package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Modelo.Administrador;
import Modelo.Libro;
import Modelo.Usuario;

public class PanelListado extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;
    private InterfazAdministrador ventana;
    public PanelListado(InterfazAdministrador ventana) {
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false; 
            }
        };
        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.ventana = ventana;
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() >= 0) {
                ventana.filaSeleccionada(tabla.getSelectedRow());
            }
        });

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public void mostrarLibros(List<Libro> libros) {
        modelo.setRowCount(0);
        modelo.setColumnIdentifiers(new String[] { "ISBN", "Nombre", "Autor", "Total", "Disponibles" });
        for (Libro l : libros) {
            modelo.addRow(new Object[] { l.getIsbn(), l.getNombre(), l.getAutor(),
                                         l.getCopiasTotales(), l.getCopiasDisponibles() });
        }
    }
    public void mostrarUsuarios(List<Usuario> usuarios) {
        modelo.setRowCount(0);
        modelo.setColumnIdentifiers(new String[] { "Usuario", "Nombre", "Apellido", "Bloqueado" });
        for (Usuario u : usuarios) {
            modelo.addRow(new Object[] { u.getUsuario(), u.getNombre(), u.getApellido(),
                                         u.isBloqueado() ? "Sí" : "No" });
        }
    }

    public void mostrarAdministradores(List<Administrador> administradores) {
        modelo.setRowCount(0);
        modelo.setColumnIdentifiers(new String[] { "Usuario", "Nombre", "Apellido", "Cargo" });
        for (Administrador a : administradores) {
            modelo.addRow(new Object[] { a.getUsuario(), a.getNombre(), a.getApellido(), a.getCargo() });
        }
    }
    
    public String getClave(int fila) {
        return String.valueOf(modelo.getValueAt(fila, 0));
    }
    
    public String[] getFila(int fila) {
        String[] valores = new String[modelo.getColumnCount()];
        for (int i = 0; i < valores.length; i++) {
            valores[i] = String.valueOf(modelo.getValueAt(fila, i));
        }
        return valores;
    }
}