package Modelo;

public class Administrador extends Persona {
	private String cargo;
	public static final String ROL_ADMINISTRADOR = "administrador";
	
	public Administrador(String nombre, String apellido, String usuario, String contrasena, int codigo,  String cargo) {
		super(nombre, apellido, usuario, contrasena, codigo);
		setCargo(cargo);
		verificarInvariante();
	}
	
	
	
	public String getCargo() {
		return cargo;
	}



	public void setCargo(String cargo) {
		if (cargo != null && !cargo.strip().isEmpty()) {
			this.cargo = cargo;
		}
		else {
			throw new IllegalArgumentException("Cargo invalido: " + cargo);
		}
	}



	@Override
	public String getRol() {
		return ROL_ADMINISTRADOR;
	}
	
	private void verificarInvariante() {
		assert cargo != null && !cargo.strip().isEmpty(): "El cargo no puede ser nulo ni estar vacio";
	}
}
