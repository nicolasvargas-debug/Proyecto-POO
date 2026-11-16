package Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Modelo.Biblioteca;
import Modelo.GestorFotos;
import Modelo.Prestamo;

public class InterfazUsuario extends JFrame  {
    private Biblioteca biblioteca;
    private String usuario;
    private String isbnSeleccionado = "";

    private PanelFoto panelFoto;
    private PanelCatalogo panelCatalogo;
    private PanelMisPrestamos panelMisPrestamos;
    private PanelMisReservas panelMisReservas;

    public InterfazUsuario(Biblioteca biblioteca, String usuario) {
        this.biblioteca = biblioteca;
        this.usuario = usuario;

        setTitle("Usuario: " + usuario);
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelFoto = new PanelFoto();
        panelCatalogo = new PanelCatalogo(this);
        panelMisPrestamos = new PanelMisPrestamos(this);
        panelMisReservas = new PanelMisReservas();

        // Oeste: foto arriba, botones debajo
        JButton butPrestar = new JButton("Prestar");
        JButton butReservar = new JButton("Reservar");
        butPrestar.addActionListener(e -> prestar());
        butReservar.addActionListener(e -> reservar());

        JPanel acciones = new JPanel(new GridLayout(2, 1, 4, 8));
        acciones.add(butPrestar);
        acciones.add(butReservar);
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.add(acciones, BorderLayout.NORTH);

        JPanel panelOeste = new JPanel(new BorderLayout());
        panelOeste.setPreferredSize(new Dimension(260, 0));
        panelOeste.add(panelFoto, BorderLayout.NORTH);
        panelOeste.add(contenedor, BorderLayout.CENTER);

        // Este: préstamos arriba, reservas abajo
        JPanel panelEste = new JPanel(new GridLayout(2, 1));
        panelEste.setPreferredSize(new Dimension(260, 0));
        panelEste.add(panelMisPrestamos);
        panelEste.add(panelMisReservas);

        add(panelOeste, BorderLayout.WEST);
        add(panelCatalogo, BorderLayout.CENTER);
        add(panelEste, BorderLayout.EAST);

        refrescar();
        setVisible(true);
    }

    private void refrescar() {
        isbnSeleccionado = "";
        panelCatalogo.mostrarLibros(biblioteca.listarLibros());
        panelMisPrestamos.mostrar(biblioteca.prestamoDe(usuario));
        panelMisReservas.mostrar(biblioteca.reservasDe(usuario));
        panelFoto.mostrar(new File(GestorFotos.RUTA_BASE + "sinFoto.png"));
    }

    public void filaSeleccionada(int fila) {
        isbnSeleccionado = panelCatalogo.getClave(fila);
        panelFoto.mostrar(GestorFotos.obtener(GestorFotos.LIBROS, isbnSeleccionado));
    }

    public void prestar() {
        if (isbnSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro del listado");
            return;
        }
        try {
            biblioteca.prestarLibro(isbnSeleccionado, usuario);
            JOptionPane.showMessageDialog(this, "Préstamo realizado");
            refrescar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void reservar() {
        if (isbnSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro del listado");
            return;
        }
        try {
            biblioteca.reservarLibro(isbnSeleccionado, usuario);
            JOptionPane.showMessageDialog(this, "Reserva realizada");
            refrescar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void devolver() {
        Prestamo p = panelMisPrestamos.getSeleccionado();
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un préstamo de la lista");
            return;
        }
        try {
            biblioteca.devolverLibro(p.getLibro().getIsbn(), usuario);
            JOptionPane.showMessageDialog(this, "Libro devuelto");
            refrescar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void renovar() {
        Prestamo p = panelMisPrestamos.getSeleccionado();
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un préstamo de la lista");
            return;
        }
        try {
            biblioteca.renovarLibro(p.getLibro().getIsbn(), usuario);
            JOptionPane.showMessageDialog(this, "Libro renovado");
            refrescar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}