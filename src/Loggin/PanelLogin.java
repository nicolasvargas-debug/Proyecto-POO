package Loggin;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelLogin extends JPanel {
	private SubPanelIniciar subPanelInicial;
	private PanelButIniciar panelButIniciar;
	
	public PanelLogin() {
		setLayout(new BorderLayout());
		
		subPanelInicial = new SubPanelIniciar();
		panelButIniciar = new PanelButIniciar();
		
		add(subPanelInicial,BorderLayout.CENTER);
		add(panelButIniciar,BorderLayout.SOUTH);
	}
	
	
	
	
	
	
}
