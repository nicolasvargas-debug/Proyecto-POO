package Modelo;

import java.util.List;

public class Biblioteca {
	private ArbolBinario libros;
	private GestorArchivos gestor;
	
	public Biblioteca() {
		libros = new ArbolBinario();
		gestor = new GestorArchivos(libros);
		gestor.cargarLibros();
		gestor.cargarAdministradores();
		gestor.cargarUsuarios();
		gestor.cargarPrestamos();
		gestor.cargarReservas();
		ordenarPorUsuario(gestor.getUsuarios());
		ordenarPorUsuario(gestor.getAdministradores());
		
		
		administradorPredeterminado();
		
		
	}
	
	
	public void administradorPredeterminado(){
		if (gestor.getAdministradores().isEmpty()) {
			Administrador administrador = new Administrador("Nicolas", "Vargas", "Administrador1", "contraseña", false, "Administrador Principal");
			gestor.getAdministradores().add(administrador);
			gestor.guardarAdministradores();
		}
	}
	
	public boolean registrarUsuario(String nombre, String apellido, String usuario, String contrasena) {
		if ( buscarBinario(gestor.getAdministradores(), usuario) == -1  && buscarBinario(gestor.getUsuarios(), usuario)  == -1) {
			Usuario nuevoUsuario = new Usuario(nombre, apellido, usuario, contrasena, false, false);
			gestor.getUsuarios().add(nuevoUsuario);
			ordenarPorUsuario(gestor.getUsuarios());
			gestor.guardarUsuarios();
			return true;
		}
		throw new IllegalArgumentException("El Usuario tiene un usuario que ya existe");
	}
	
	public boolean registrarAdministrador(String nombre, String apellido, String usuario, String contrasena, String cargo) {
		if ( buscarBinario(gestor.getAdministradores(), usuario) == -1  && buscarBinario(gestor.getUsuarios(), usuario)  == -1) {
			Administrador administrador = new Administrador(nombre, apellido, usuario, contrasena, false, cargo);
			gestor.getAdministradores().add(administrador);
			ordenarPorUsuario(gestor.getAdministradores());
			gestor.guardarAdministradores();
			return true;
				
			}
		throw new IllegalArgumentException("El Administrador tiene un usuario que ya existe");
		}
	
	public boolean registrarLibro(String isbn, String nombre, String autor, int copiasTotales){
		if (libros.buscarLibro(isbn) == null) {
			Libro libro = new Libro(isbn, nombre, autor, copiasTotales);
			libros.insertarLibro(libro);
			gestor.guardarLibros();
			return true;
		}
		else {
			throw new IllegalArgumentException("El libro ya existe");
		}
		
	}
	
	
	
	private <T extends Persona> void ordenarPorUsuario(List<T> lista) {
	    for (int i = 0; i < lista.size() - 1; i++) {
	        for (int j = 0; j < lista.size() - 1 - i; j++) {
	            if (lista.get(j).getUsuario().compareTo(lista.get(j + 1).getUsuario()) > 0) {
	                T temporal = lista.get(j);
	                lista.set(j, lista.get(j + 1));
	                lista.set(j + 1, temporal);
	            }
	        }
	    }
	}
	
	private <T extends Persona > int buscarBinario(List<T> lista, String usuario) {
		int inicio = 0;
		int fin = lista.size()-1;
		
		while (inicio <= fin){
			int medio = (inicio + fin)/2;
			int comparacion = lista.get(medio).getUsuario().compareTo(usuario);
			
			if (comparacion == 0) {
				return medio;
			}
			else if (comparacion < 0 ) {
				inicio = medio +1; 
			}
			else {
				fin = medio-1;
			}
		}
		return -1;
	}
	
	
	public Persona validarLogin(String usuario, String contrasena){
		int resultadoAdministradores = buscarBinario(gestor.getAdministradores(), usuario);
		int resultadoUsuarios = buscarBinario(gestor.getUsuarios(), usuario);
		 if (resultadoAdministradores != -1) {
			 Administrador adminstrador = gestor.getAdministradores().get(resultadoAdministradores);
			 if (adminstrador.compararContrasena(contrasena)) {
				 return adminstrador;
			 }
			 else {
				 throw new IllegalArgumentException("La contraseña o el usuario son incorrectas");
			 }
			 
		 }
		 else if (resultadoUsuarios != -1) {
			 Usuario usuario = gestor.getUsuarios().get(resultadoUsuarios);
			 if (usuario.compararContrasena(contrasena)) {
				 return usuario;
			 }
			 else {
				 throw new IllegalArgumentException("La contraseña o el usuario son incorrectas"); 
			 }
		 }
		 else {
			 throw new IllegalArgumentException("La contraseña o el usuario son incorrectas"); 
		 }
	}
	
	public prestarLibro() {
		
	}
	
	
}
 