package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PanelDatos extends JPanel {
	private InterfazAdministrador ventana;
    private JLabel titulo;
    private JLabel[] etiquetas = new JLabel[6];
    private JTextField[] campos = new JTextField[6];
    private JButton butActualizar, butEliminar, butBloquear, butDesbloquear;
    
    public PanelDatos(InterfazAdministrador ventana) {
    	this.ventana = ventana;
    	setLayout(new BorderLayout());
    	titulo = new JLabel("Datos de");
    	
    	butActualizar = new JButton("Actualizar");
    	butEliminar = new JButton("Eliminar");
    	butBloquear = new JButton("Bloquear");
    	butDesbloquear = new JButton("Desbloquear");
    	
    	butActualizar.addActionListener(e -> ventana.actualizar());
    	butEliminar.addActionListener(e -> ventana.eliminar());
    	butBloquear.addActionListener(e -> ventana.bloquear());
    	butDesbloquear.addActionListener(e -> ventana.desbloquear());
    	
    	JPanel panelBotones = new JPanel(new GridLayout(2, 2, 4, 4));
    	panelBotones.add(butActualizar);
    	panelBotones.add(butEliminar);
    	panelBotones.add(butBloquear);
    	panelBotones.add(butDesbloquear);
    	
    	
        
        add(titulo, BorderLayout.NORTH);
        
        JPanel formulario = new JPanel(new GridLayout(6, 2, 4, 8));
        for (int i = 0; i < 6; i++) {
            etiquetas[i] = new JLabel();
            campos[i] = new JTextField();
            formulario.add(etiquetas[i]);
            formulario.add(campos[i]);
        }


        JPanel arriba = new JPanel(new BorderLayout(0, 10));
        arriba.add(formulario, BorderLayout.NORTH);
        arriba.add(panelBotones, BorderLayout.SOUTH);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.add(arriba, BorderLayout.NORTH);
        add(contenedor, BorderLayout.CENTER);
    }
    
    public void configurarLibros() {
        configurar("Datos del libro", new String[] { "ISBN", "Nombre", "Autor", "Copias totales" }, false);
    }

    public void configurarUsuarios() {
        configurar("Datos del usuario", new String[] { "Usuario", "Nombre", "Apellido",
                "Contraseña actual", "Contraseña nueva (opcional)" }, true);
    }

    public void configurarAdministradores() {
        configurar("Datos del administrador", new String[] { "Usuario", "Nombre", "Apellido", "Cargo",
                "Contraseña actual", "Contraseña nueva (opcional)" }, false);
    }

    private void configurar(String textoTitulo, String[] nombres, boolean esUsuario) {
        titulo.setText(textoTitulo);
        for (int i = 0; i < 6; i++) {
            boolean visible = i < nombres.length;
            etiquetas[i].setVisible(visible);
            campos[i].setVisible(visible);
            if (visible) {
                etiquetas[i].setText(nombres[i]);
            }
        }
        butBloquear.setVisible(esUsuario);
        butDesbloquear.setVisible(esUsuario);
        limpiar();
    }

    public void limpiar() {
        for (JTextField campo : campos) {
            campo.setText("");
            campo.setEditable(true);
        }
    }

    public void cargarLibro(String[] valores) {
        for (int i = 0; i < 4; i++) {
            campos[i].setText(valores[i]);
        }
        campos[0].setEditable(false); // el ISBN no se cambia
    }

    public void cargarUsuario(String[] valores) {
        for (int i = 0; i < 3; i++) {
            campos[i].setText(valores[i]);
        }
        campos[3].setText("");
        campos[4].setText("");
        campos[0].setEditable(false);
    }

    public void cargarAdministrador(String[] valores) {
        for (int i = 0; i < 3; i++) {
            campos[i].setText(valores[i]);
        }
        campos[3].setText(valores[3]); // el cargo
        campos[4].setText("");
        campos[5].setText("");
        campos[0].setEditable(false);
    }

    public String getCampo(int posicion) {
        return campos[posicion].getText().strip();
    }
}