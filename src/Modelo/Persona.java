package Modelo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Base64;

public abstract class Persona  {
	private String nombre;
	private String apellido;
	private String usuario;
	private String contrasena;
	private boolean yaEsHash;

	
	public Persona(String nombre, String apellido, String usuario, String contrasena, boolean yaEsHash) {
		this.yaEsHash = false;
		setNombre(nombre);
		setApellido(apellido);
		setUsuario(usuario);
		setContrasena(contrasena,yaEsHash);

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



	public boolean compararContrasena(String contrasenaIngresada) {
		if (contrasenaIngresada == null || contrasenaIngresada.strip().isEmpty()) {
			return false;
		}
		else if (contrasena.equals(conversorHash(contrasenaIngresada))) {
			return true;
		}
		return false;
		
	}



	private void setContrasena(String contrasena, boolean esHash) {
		if (contrasena != null && !contrasena.strip().isEmpty()) {
			if (esHash) {
				this.contrasena = contrasena;
			}
			else {
				this.contrasena = conversorHash(contrasena);
				yaEsHash = false;
			}
			
		}
		else {
			throw new IllegalArgumentException("Contraseña invalida");
		}
	}
	
	
	
	
	



	private void verificarInvariante() {
		assert nombre != null && !nombre.strip().isEmpty(): "El nombre no debe estar vacio";
		assert apellido!= null && !apellido.strip().isEmpty(): "El apellido no debe estar vacio";
		assert usuario != null && !usuario.strip().isEmpty(): "El usuario no debe estar vacio";
		assert contrasena != null && !contrasena.strip().isEmpty(): "La contraseña no debe estar vacia";
		
	}

	public static String conversorHash(String texto){
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);
			byte[] resultado = md.digest(bytes);
			return Base64.getEncoder().encodeToString(resultado);
		}
		catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("Error con la creacion del hash");}
		}
		
	
	public String getContrasenaHash() {
		return contrasena;
	}
	
	public abstract String getRol(); 
	
}
