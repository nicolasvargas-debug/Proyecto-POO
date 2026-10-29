package Loggin;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelButIniciar extends JPanel {
	private JButton butIniciar;
	public PanelButIniciar() {
		
		butIniciar = new JButton("Iniciar Sesión");
		setLayout(new BorderLayout());
		
		add(butIniciar,BorderLayout.CENTER);
		setVisible(true);
	}
}
