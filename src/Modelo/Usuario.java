package Modelo;

public class Usuario extends Persona{
	private ListaSimple<Prestamo> prestamos; 
	private boolean bloqueado;
	private static final int MAX_PRESTAMOS = 3;
	public static final String ROL_USUARIO = "usuario";
	
	public Usuario(String nombre, String apellido, String usuario, String contrasena,int codigo) {
		super(nombre, apellido, usuario, contrasena, codigo );
		this.prestamos = new ListaSimple<>();
		this.bloqueado = false;
		
	}

	@Override
	public String getRol() {
		return ROL_USUARIO;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}
	
	public boolean bloquear() {
		if (!bloqueado) {
			this.bloqueado = true;
			return true;
		}
		return false;
	}
	
	public boolean desbloquear() {
		if (bloqueado) {
			this.bloqueado = false;
			return true;
		}
		return false;
	}
	
	public boolean agregarPrestamo(Prestamo p) {
		if (bloqueado) {
			throw new IllegalStateException("El usuario esta bloqueado");
		}
		if (prestamos.getTamanio()>= MAX_PRESTAMOS) {
			throw new IllegalStateException("El usuario ya alcanzo el maximo de prestamos");
		}
		return prestamos.agregar(p);
		
		
	}
	
	public Prestamo buscarprestamo(Libro l) {
		Nodo<Prestamo> actual = prestamos.getInicio();
		while (actual != null) {
			if (actual.getContenido().getLibro() == l) {
				return actual.getContenido();
			}
			actual.getSiguiente();
		}
		return null;
		
	}
	
	public ListaSimple<Prestamo> getPretamos(){
		return prestamos;
	}
	

	
}
