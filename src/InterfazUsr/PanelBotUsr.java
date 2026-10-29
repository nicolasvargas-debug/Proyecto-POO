package InterfazUsr;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotUsr extends JPanel{
	private JButton butBuscar;
	private JButton butListar;
	private JButton butSldPrestar;
	private JButton butRevPrestamos;
	
	public PanelBotUsr() {
		butBuscar = new JButton("Buscar Libro");
		butListar = new JButton("Listar Libros Disponibles");
		butSldPrestar = new JButton("Solicitar prestamo");
		butRevPrestamos = new JButton("Revisar Prestamos");
		setLayout(new GridLayout(4,1));
		
		add(butBuscar);
		add(butListar);
		add(butSldPrestar);
		add(butRevPrestamos);
		
	}
}
