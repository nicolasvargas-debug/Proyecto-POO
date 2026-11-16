package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.Prestamo;

public class PanelMisPrestamos extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;
    private List<Prestamo> prestamos = new ArrayList<>();

    public PanelMisPrestamos(InterfazUsuario ventana) {
        setLayout(new BorderLayout());
        add(new JLabel("Mis préstamos"), BorderLayout.NORTH);

        modelo = new DefaultTableModel(new String[] { "Libro", "Vence", "Renovado" }, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JButton butDevolver = new JButton("Devolver");
        JButton butRenovar = new JButton("Renovar");
        butDevolver.addActionListener(e -> ventana.devolver());
        butRenovar.addActionListener(e -> ventana.renovar());

        JPanel botones = new JPanel(new GridLayout(1, 2, 4, 4));
        botones.add(butDevolver);
        botones.add(butRenovar);
        add(botones, BorderLayout.SOUTH);
    }

    public void mostrar(List<Prestamo> nuevos) {
        prestamos = nuevos;
        modelo.setRowCount(0);
        for (Prestamo p : nuevos) {
            modelo.addRow(new Object[] { p.getLibro().getNombre(), p.getFechaFin(),
                    p.isRenovado() ? "Sí" : "No" });
        }
    }

    public Prestamo getSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            return null;
        }
        return prestamos.get(fila);
    }
}