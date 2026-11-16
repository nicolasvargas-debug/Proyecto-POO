package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Modelo.Libro;
import Modelo.Prestamo;
import Modelo.Usuario;

public class PanelPrestamos extends JPanel {
    private JLabel titulo;
    private DefaultListModel<String> modelo;

    public PanelPrestamos() {
        setLayout(new BorderLayout());
        titulo = new JLabel();
        modelo = new DefaultListModel<>();
        add(titulo, BorderLayout.NORTH);
        add(new JScrollPane(new JList<>(modelo)), BorderLayout.CENTER);
    }

    public void vaciar() {
        titulo.setText("");
        modelo.clear();
        setVisible(false);
    }

    public void mostrarUsuario(String usuario, List<Prestamo> prestamos, List<Libro> reservas) {
        vaciar();
        titulo.setText("Préstamos de " + usuario);
        for (Prestamo p : prestamos) {
            modelo.addElement(p.getLibro().getNombre() + " (vence " + p.getFechaFin() + ")");
        }
        for (Libro l : reservas) {
            modelo.addElement("Reserva: " + l.getNombre());
        }
        setVisible(true);
    }

    public void mostrarLibro(String nombre, List<Usuario> reservas) {
        vaciar();
        titulo.setText("Reservas de " + nombre);
        for (Usuario u : reservas) {
            modelo.addElement(u.getUsuario());
        }
        setVisible(true);
    }
}