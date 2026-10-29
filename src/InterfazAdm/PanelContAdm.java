package InterfazAdm;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelContAdm extends JPanel{
	private JLabel mensaje;
	private PanelBotAdm panelBotAdm;
	public PanelContAdm() {
		panelBotAdm = new PanelBotAdm();
		mensaje = new JLabel("Funciones Adm");
		setLayout(new BorderLayout());
		add(mensaje,BorderLayout.NORTH);
		add(panelBotAdm,BorderLayout.CENTER);
		
		
		
		setVisible(true);
	}
}
