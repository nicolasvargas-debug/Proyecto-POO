package Loggin;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SubPanelIniciar extends JPanel {
	private JLabel labNombre;
	private JLabel labContraseña;
	private JTextField textNombre;
	private JTextField textContraseña;
	
	public SubPanelIniciar() {
		
		setLayout(new GridLayout(2,2));
		
		labNombre = new JLabel("Nombre Usuario");
		labContraseña = new JLabel("Contraseña");
		textNombre = new JTextField();
		textContraseña = new JTextField();
		
		add(labNombre);
		add(textNombre);
		add(labContraseña);
		add(textContraseña);
		
		setVisible(true);
	}
	
	
}
