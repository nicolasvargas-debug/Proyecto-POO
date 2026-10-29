package InterfazAdm;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotAdm extends JPanel {
	private JButton butCrear;
	private JButton butBuscar;
	private JButton butActualizar;
	private JButton butEliminar;
	private JButton butListar;
	public PanelBotAdm(){
		butCrear = new JButton("Crear");
		butBuscar = new JButton("Buscar");
		butActualizar = new JButton("Actualizar");
		butEliminar = new JButton("Eliminar");
		butListar = new JButton("Listar");
		setLayout(new GridLayout(5,1));
		setVisible(true);
		
		add(butCrear);
		add(butBuscar);
		add(butActualizar);
		add(butEliminar);
		add(butListar);
		
		
		
	}
	
}
