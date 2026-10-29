package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import InterfazAdm.VentanaAdm;
import InterfazUsr.VentanaUsr;
import Loggin.PanelLogin;

public class Principal extends JFrame {
	private PanelLogin panelLogin;
	private JLabel mensaje;
	
	public Principal(){
		setTitle("Loggin");
		
		setResizable(false);
		setSize(450 ,150 );
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setVisible(true);
		
		mensaje = new JLabel("Ingrese su Usuario y contraseña:");
		panelLogin = new PanelLogin();
		
		
		
		add(mensaje,BorderLayout.NORTH);
		add(panelLogin,BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		Principal p = new Principal();
		VentanaAdm a = new VentanaAdm();
		VentanaUsr u = new VentanaUsr();
	}
	 
	
}
