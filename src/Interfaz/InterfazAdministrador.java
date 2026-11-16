package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import Modelo.Biblioteca;
import Modelo.GestorFotos;

public class InterfazAdministrador extends JFrame {
    private Biblioteca biblioteca;

    private JPanel panelOpciones;
    private JButton butAdministradores, butUsuarios, butLibros;

    private JPanel panelCuerpo, panelOeste, panelEste;
    
    private PanelFoto panelFoto;
    private PanelDatos panelDatos;
    private PanelListado panelListado;
    private PanelPrestamos panelPrestamos;  
    private PanelCrear panelCrear;
    
    private static final int ADMINISTRADORES = 0;
    private static final int USUARIOS = 1;
    private static final int LIBROS = 2;
    private int modo;
    

    public InterfazAdministrador(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        
       
        
        setTitle("Administrador");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        

        
        panelOpciones = new JPanel(new GridLayout(1, 3));
        butAdministradores = new JButton("Administradores");
        butUsuarios = new JButton("Usuarios");
        butLibros = new JButton("Libros");
        panelOpciones.add(butAdministradores);
        panelOpciones.add(butUsuarios);
        panelOpciones.add(butLibros);
        
        butAdministradores.addActionListener(e -> cambiarModo(ADMINISTRADORES));
        butUsuarios.addActionListener(e -> cambiarModo(USUARIOS));
        butLibros.addActionListener(e -> cambiarModo(LIBROS));

       
        panelListado = new PanelListado(this);
        panelCuerpo = new JPanel(new BorderLayout());
        panelFoto = new PanelFoto();
        panelDatos = new PanelDatos(this);
        panelPrestamos = new PanelPrestamos();
        panelCrear = new PanelCrear(this);
        cambiarModo(LIBROS);
        panelPrestamos.vaciar();
        
        
        panelOeste = new JPanel(new BorderLayout());
        panelOeste.setPreferredSize(new Dimension(260, 0));

        panelOeste.add(panelFoto, BorderLayout.NORTH);
        panelOeste.add(panelDatos, BorderLayout.CENTER);

       
        panelEste = new JPanel(new GridLayout(2, 1));
        panelEste.add(panelPrestamos);
        panelEste.add(panelCrear);

        panelCuerpo.add(panelOeste, BorderLayout.WEST);
        panelCuerpo.add(panelListado, BorderLayout.CENTER);
        panelCuerpo.add(panelEste, BorderLayout.EAST);

        add(panelOpciones, BorderLayout.NORTH);
        add(panelCuerpo, BorderLayout.CENTER);
        
        setVisible(true);
        
        
    }    
 
    private void cambiarModo(int nuevoModo) {
        modo = nuevoModo;
        if (modo == ADMINISTRADORES) {
            panelListado.mostrarAdministradores(biblioteca.listarAdministradores());
            panelDatos.configurarAdministradores();
            panelCrear.configurarAdministradores();
        } else if (modo == USUARIOS) {
            panelListado.mostrarUsuarios(biblioteca.listarUsuarios());
            panelDatos.configurarUsuarios();
            panelCrear.configurarUsuarios();
        } else {
            panelListado.mostrarLibros(biblioteca.listarLibros());
            panelDatos.configurarLibros();
            panelCrear.configurarLibros();
        }
        panelFoto.mostrar(new File(GestorFotos.RUTA_BASE + "sinFoto.png"));
        panelPrestamos.vaciar();
        
    }
    
    
    public void filaSeleccionada(int fila) {
    	
        String clave = panelListado.getClave(fila);
        String[] valores = panelListado.getFila(fila);
        String carpeta;
        if (modo == ADMINISTRADORES) {
            carpeta = GestorFotos.ADMINISTRADORES;
            panelDatos.cargarAdministrador(valores);
        } else if (modo == USUARIOS) {
            carpeta = GestorFotos.USUARIOS;
            panelDatos.cargarUsuario(valores);
        } else {
            carpeta = GestorFotos.LIBROS;
            panelDatos.cargarLibro(valores);
        }
        if (modo == USUARIOS) {
            panelPrestamos.mostrarUsuario(clave, biblioteca.prestamoDe(clave), biblioteca.reservasDe(clave));
        } else if (modo == LIBROS) {
            panelPrestamos.mostrarLibro(valores[1], biblioteca.reservasDeLibro(clave));
        }
        panelFoto.mostrar(GestorFotos.obtener(carpeta, clave));
        
    }
    
    public void actualizar() {
        try {
            if (modo == USUARIOS) {
                biblioteca.actualizarUsuario(panelDatos.getCampo(0), panelDatos.getCampo(1),
                		panelDatos.getCampo(2), panelDatos.getCampo(3), panelDatos.getCampo(4));
       
            } else if (modo == ADMINISTRADORES) {
                biblioteca.actualizarAdministrador(panelDatos.getCampo(0), panelDatos.getCampo(1),
                        panelDatos.getCampo(2), panelDatos.getCampo(4), panelDatos.getCampo(5),
                        panelDatos.getCampo(3));
                
            } else {
                biblioteca.actualizarLibro(panelDatos.getCampo(0), panelDatos.getCampo(1),
                        panelDatos.getCampo(2), Integer.parseInt(panelDatos.getCampo(3)));
                
            }
            JOptionPane.showMessageDialog(this, "Actualizado");
            cambiarModo(modo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void eliminar() {
        String clave = panelDatos.getCampo(0);
        if (clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla");
            return;
        }
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Eliminar " + clave + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            if (modo == USUARIOS) {
                biblioteca.eliminarUsuario(clave);
            } else if (modo == ADMINISTRADORES) {
                biblioteca.eliminarAdministrador(clave);
            } else {
                biblioteca.eliminarLibro(clave);
            }
            JOptionPane.showMessageDialog(this, "Eliminado");
            cambiarModo(modo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void bloquear() {
        try {
            biblioteca.bloquearUsuario(panelDatos.getCampo(0));
            JOptionPane.showMessageDialog(this, "Usuario bloqueado");
            cambiarModo(modo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void desbloquear() {
        try {
            biblioteca.desbloquearUsuario(panelDatos.getCampo(0));
            JOptionPane.showMessageDialog(this, "Usuario desbloqueado");
            cambiarModo(modo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    public void crear() {
        try {
            if (modo == USUARIOS) {
                biblioteca.registrarUsuario(panelCrear.getCampo(1), panelCrear.getCampo(2),
                        panelCrear.getCampo(0), panelCrear.getCampo(3));
            } else if (modo == ADMINISTRADORES) {
                biblioteca.registrarAdministrador(panelCrear.getCampo(1), panelCrear.getCampo(2),
                        panelCrear.getCampo(0), panelCrear.getCampo(3), panelCrear.getCampo(4));
            } else {
                biblioteca.registrarLibro(panelCrear.getCampo(0), panelCrear.getCampo(1),
                        panelCrear.getCampo(2), Integer.parseInt(panelCrear.getCampo(3)));
            }
            JOptionPane.showMessageDialog(this, "Creado");
            cambiarModo(modo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
}