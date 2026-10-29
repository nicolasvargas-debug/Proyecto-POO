package InterfazUsr;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import InterfazAdm.PanelBotUsr;

public class VentanaUsr extends JFrame {
	private JLabel mensaje;
	private PanelBotUsr panelBotUsr;

	public VentanaUsr() {
		mensaje = new JLabel("Elija la opcion que desea hacer: ");
		panelBotUsr = new PanelBotUsr();
		setLayout(new BorderLayout());
		setSize(220, 550);
		setResizable(true);
		setLayout(new BorderLayout());
		add(mensaje,BorderLayout.NORTH);
		add(panelBotUsr,BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
