package Interfaz;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SubPanelLoginTexto extends JPanel {
	private JLabel labNombre;
	private JLabel labContrasena;
	private JTextField textNombre;
	private JPasswordField textContrasena;
	
	public SubPanelLoginTexto() {
		
		setLayout(new GridLayout(2,2));
		
		labNombre = new JLabel("Nombre Usuario");
		labContrasena = new JLabel("Contraseña");
		textNombre = new JTextField();
		textContrasena = new JPasswordField();
		
		add(labNombre);
		add(textNombre);
		add(labContrasena);
		add(textContrasena);
		
		setVisible(true);
	}

	public String getContrasena() {
		return String.valueOf(textContrasena.getPassword());
	}


	public String getUsuario() {
		return textNombre.getText().strip();
	}
	
}
