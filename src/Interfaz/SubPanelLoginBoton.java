package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Modelo.Administrador;
import Modelo.Biblioteca;
import Modelo.Persona;
import Modelo.Usuario;

public class SubPanelLoginBoton extends JPanel implements ActionListener{
	private JButton butIniciar;
	private SubPanelLoginTexto panelTexto;
	private Biblioteca biblioteca;
	public SubPanelLoginBoton(SubPanelLoginTexto panelTexto, Biblioteca biblioteca) {
		this.panelTexto = panelTexto;
	    this.biblioteca = biblioteca;

		butIniciar = new JButton("Iniciar Sesión");
		butIniciar.addActionListener(this);
		setLayout(new BorderLayout());
		
		add(butIniciar,BorderLayout.CENTER);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
            Persona p = biblioteca.validarLogin(panelTexto.getUsuario(), panelTexto.getContrasena());
            if (p.getRol().equals(Administrador.ROL_ADMINISTRADOR)) {
                new InterfazAdministrador(biblioteca);
                SwingUtilities.getWindowAncestor(this).dispose();
            }
            else if (p.getRol().equals(Usuario.ROL_USUARIO)) {
            	new InterfazUsuario(biblioteca, p.getUsuario());
            	SwingUtilities.getWindowAncestor(this).dispose();
            }
            
            JOptionPane.showMessageDialog(this, "Bienvenido " + p.getUsuario());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
		
	}
}
