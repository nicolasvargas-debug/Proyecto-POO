package InterfazAdm;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelContLibros extends JPanel{
	private JLabel mensaje;
	private PanelBotLibros panelBotLibros;
	
	public PanelContLibros() {
		panelBotLibros = new PanelBotLibros();
		mensaje = new JLabel("Funciones Libro");
		setLayout(new BorderLayout());
		
		add(mensaje,BorderLayout.NORTH);
		add(panelBotLibros,BorderLayout.CENTER);
		setVisible(true);
		
	}
	
}
