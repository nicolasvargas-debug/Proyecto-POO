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
	
	
	public Persona validarLogin(String nombreUsuario, String contrasena){
		int resultadoAdministradores = buscarBinario(gestor.getAdministradores(), nombreUsuario);
		int resultadoUsuarios = buscarBinario(gestor.getUsuarios(), nombreUsuario);
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
	
	public void prestarLibro(String isbn, String nombreUsuario) {
		Libro libro = libros.buscarLibro(isbn); 
		if ( libro != null) {
			int resultadoUsuario = buscarBinario(gestor.getUsuarios(), nombreUsuario);
			if (resultadoUsuario != -1) {
				Usuario usuario = gestor.getUsuarios().get(resultadoUsuario);
				Prestamo prestamo = new Prestamo(libro, usuario);
				if (libro.getCopiasDisponibles() > 0) {
					usuario.agregarPrestamo(prestamo);
					libro.prestarLibro();
					gestor.guardarPrestamos();
				}
				else {
					throw new IllegalArgumentException("El libro no tiene copias disponibles");
				}
				
			}
			else {
				throw new IllegalArgumentException("No existe ninguna Persona con ese Usuario");
			}
		}
		else {
			throw new IllegalArgumentException("No existe un libro con ese ISBN");
		}
	}
	
	public void devolverLibro(String isbn, String nombreUsuario) {
		Libro libro = libros.buscarLibro(isbn); 
		if ( libro != null) {
			int resultadoUsuario = buscarBinario(gestor.getUsuarios(), nombreUsuario);
			if (resultadoUsuario != -1) {
				Usuario usuario = gestor.getUsuarios().get(resultadoUsuario);
				if (usuario.buscarPrestamo(libro) != null) {
					usuario.getPrestamos().eliminar(usuario.buscarPrestamo(libro));
					if (libro.tieneReserva()) {
						int cantidad = libro.listarReservas().size();
						boolean entregado = false;
						for (int i = 0; i < cantidad; i++) {
							Usuario usuarioSiguiente = libro.siguienteReserva();
							try {
								Prestamo pretamoSiguiente= new Prestamo(libro, usuarioSiguiente); 
								usuarioSiguiente.agregarPrestamo(pretamoSiguiente);
								entregado = true;
								break;
							}
							catch (Exception e) {
								libro.agregarReservas(usuarioSiguiente);
							}
							
							}
						if (!entregado) {                               
	                        libro.devolverLibro();
	                    }
						
						}
					else {
						libro.devolverLibro();
						
					}
					gestor.guardarPrestamos();
					gestor.guardarReservas();
				}
				else {
					throw new IllegalArgumentException("El Usuario no tiene ningun Prestamo con ese libro");
				}
				
			}
			else {
				throw new IllegalArgumentException("No existe ninguna Persona con ese Usuario");
			}
		}
		else {
			throw new IllegalArgumentException("No existe un libro con ese ISBN");
		}
	}
	
	public void renovarLibro(String isbn, String nombreUsuario) {
		Libro libro = libros.buscarLibro(isbn); 
		if ( libro != null) {
			 
				int resultadoUsuario = buscarBinario(gestor.getUsuarios(), nombreUsuario);
				if (resultadoUsuario != -1) {
					Usuario usuario = gestor.getUsuarios().get(resultadoUsuario);
					Prestamo prestamo = usuario.buscarPrestamo(libro);
					if (prestamo != null) {
						prestamo.renovar();
						gestor.guardarPrestamos();
					}
					else {
						 throw new IllegalArgumentException("El Usuario no tiene ningun Prestamo con ese libro");
					}
					
				}
				else {
					throw new IllegalArgumentException("No existe ninguna Persona con ese Usuario");
				}
			
		}
		else {
			throw new IllegalArgumentException("No existe un libro con ese ISBN");
		}
	}
	
	public void reservarLibro(String isbn, String nombreUsuario) {
		Libro libro = libros.buscarLibro(isbn);
		if (libro != null) {
			
			int resultadoUsuario = buscarBinario(gestor.getUsuarios(), nombreUsuario);
			if (resultadoUsuario != -1) {
				Usuario usuario = gestor.getUsuarios().get(resultadoUsuario);
				if (libro.getCopiasDisponibles() == 0) {
					if (usuario.getPrestamos().getTamanio() < 3) {
						if (usuario.buscarPrestamo(libro) == null) {
							if (!libro.listarReservas().contains(usuario)) {
	                            libro.agregarReservas(usuario);
	                            gestor.guardarReservas();
	                        } else {
	                            throw new IllegalArgumentException("El usuario ya está en la fila de este libro");
	                        }
						}
						else {
							throw new IllegalArgumentException("El usuario ya tiene un prestamos de este libro");
						}
					}
					else {
						throw new IllegalArgumentException("El Usuario ya tiene no tiene prestamos disponibles");
					}
				}
				else {
					throw new IllegalArgumentException("El libro tiene copias disponibles");
				}
				
			}
			else {
				throw new IllegalArgumentException("No existe ninguna Persona con ese Usuario");
			}
			
		}
		else {
			throw new IllegalArgumentException("No existe un libro con ese ISBN");
		}
		
	}
	
}
 