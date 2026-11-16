package Interfaz;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JLabel;

import Modelo.Biblioteca;



public class Principal extends JFrame {
	private PanelLogin panelLogin;
	private JLabel mensaje;
	private Biblioteca biblioteca;
	
	public Principal(){
		setTitle("Loggin");
		
		setResizable(false);
		setSize(450 ,150 );
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setVisible(true);
		biblioteca = new Biblioteca();
		mensaje = new JLabel("Ingrese su Usuario y contraseña:");
		panelLogin = new PanelLogin(biblioteca);
		
		
		
		add(mensaje,BorderLayout.NORTH);
		add(panelLogin,BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		 try {
		        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		            if ("Nimbus".equals(info.getName())) {
		                javax.swing.UIManager.setLookAndFeel(info.getClassName());
		            }
		        }
		    } catch (Exception e) {
		    }
		    Principal p = new Principal();
	}
	 
	
}
