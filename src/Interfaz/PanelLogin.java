package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Modelo.Biblioteca;

public class PanelLogin extends JPanel {
	private SubPanelLoginTexto subPanelInicial;
	private SubPanelLoginBoton panelButIniciar;
	
	public PanelLogin(Biblioteca biblioteca) {
		setLayout(new BorderLayout());
		
		subPanelInicial = new SubPanelLoginTexto();
		panelButIniciar = new SubPanelLoginBoton(subPanelInicial, biblioteca);
		
		add(subPanelInicial,BorderLayout.CENTER);
		add(panelButIniciar,BorderLayout.SOUTH);
	}
	
	
	
	
	
	
}
