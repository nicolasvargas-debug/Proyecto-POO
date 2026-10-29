package Modelo;



public abstract class Persona  {
	private String nombre;
	private String apellido;
	private String usuario;
	private String contrasena;
	private int codigo;
	
	public Persona(String nombre, String apellido, String usuario, String contrasena, int codigo) {
		setNombre(nombre);
		setApellido(apellido);
		setUsuario(usuario);
		setContrasena(contrasena);
		setCodigo(codigo);
		verificarInvariante();
	}
	
	
	
	public String getNombre() {
		return nombre;
	}



	private void setNombre(String nombre) {
		if (nombre!= null && !nombre.strip().isEmpty()) {
			this.nombre = nombre;
		}
		else {
			throw new IllegalArgumentException("Nombre invalido: " + nombre);
		}
		
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		if (apellido!= null && !apellido.strip().isEmpty()) {
			this.apellido = apellido;
		}
		else {
			throw new IllegalArgumentException("Apellido invalido: " + apellido);
		}
	}

	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		if (usuario != null && !usuario.strip().isEmpty()) {
			this.usuario = usuario;
		}
		else {
			throw new IllegalArgumentException("Usuario invalido: " + usuario);
		}
	}



	public boolean compararContrasena(String contrasena) {
		if (contrasena == null) {
			return false;
		}
		else if (contrasena.equals(this.contrasena)) {
			return true;
		}
		return false;
		
	}



	private void setContrasena(String contrasena) {
		if (contrasena != null && !contrasena.strip().isEmpty()) {
			this.contrasena = contrasena;
		}
		else {
			throw new IllegalArgumentException("Contraseña invalida");
		}
	}
	
	
	
	public int getCodigo() {
		return codigo;
	}



	private void setCodigo(int codigo) {
		if (codigo >0 ) {
			this.codigo = codigo;
		}
		else {
			throw new IllegalArgumentException("Codigo invalido: "+ codigo);
		}
	}
	



	private void verificarInvariante() {
		assert nombre != null && !nombre.strip().isEmpty(): "El nombre no debe estar vacio";
		assert apellido!= null && !apellido.strip().isEmpty(): "El apellido no debe estar vacio";
		assert usuario != null && !usuario.strip().isEmpty(): "El usuario no debe estar vacio";
		assert contrasena != null && !contrasena.strip().isEmpty(): "La contraseña no debe estar vacia";
		assert codigo > 0 : "El codigo debe ser amyor que 0";
	}


	public abstract String getRol(); 
	
}
