package Modelo;

public class Usuario extends Persona{
	private ListaSimple<Libro> prestamos; 
	private boolean bloqueado;
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
	
	public boolean agregarPrestamo(Libro libro) {
		if (bloqueado) {
			throw new IllegalStateException("El ususario esta bloqueado");
		}
		return prestamos.agregar(libro);
	}
	
	public ListaSimple<Libro> getPretamos(){
		return prestamos;
	}
	

	
}
