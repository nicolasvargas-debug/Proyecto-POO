package InterfazAdm;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class VentanaAdm extends JFrame {
	private PanelContLibros panelContLibros;
	private PanelContAdm panelContAdm;
	private PanelContUsr panelContUsr;
	
	public VentanaAdm() {
		
		panelContAdm = new PanelContAdm();
		panelContLibros = new PanelContLibros();
		panelContUsr = new PanelContUsr();
		setSize(450 ,550 );
		setLayout(new GridLayout(1,3));
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		add(panelContLibros);
		add(panelContAdm);
		add(panelContUsr);
	}
	

}
