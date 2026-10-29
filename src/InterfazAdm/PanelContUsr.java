package InterfazAdm;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelContUsr extends JPanel {
	private JLabel mensaje;
	private PanelBotUsr panelBotUsr;
	
	private PanelContUsr panelContUsr;
	public PanelContUsr() {
		mensaje = new JLabel("Funciones Ususario");
		panelBotUsr = new PanelBotUsr();
		
		setLayout(new BorderLayout());
		
		add(mensaje,BorderLayout.NORTH);
		add(panelBotUsr,BorderLayout.CENTER);
	}
}
