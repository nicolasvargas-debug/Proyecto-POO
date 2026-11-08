package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
	private ArbolBinario libros;
	private GestorArchivos gestorArchivos;
	
	public Biblioteca() {
		libros = new ArbolBinario();
		gestorArchivos = new GestorArchivos(libros);
		gestorArchivos.cargarLibros();
		gestorArchivos.cargarAdministradores();
		gestorArchivos.cargarUsuarios();
		gestorArchivos.cargarPrestamos();
		gestorArchivos.cargarReservas();
		ordenarPorUsuario(gestorArchivos.getUsuarios());
		ordenarPorUsuario(gestorArchivos.getAdministradores());
		
		
		administradorPredeterminado();
		
		
	}
	
	
	public void administradorPredeterminado(){
		if (gestorArchivos.getAdministradores().isEmpty()) {
			Administrador administrador = new Administrador("Nicolas", "Vargas", "Administrador1", "contraseña", false, "Administrador Principal");
			gestorArchivos.getAdministradores().add(administrador);
			gestorArchivos.guardarAdministradores();
		}
	}
	
	public boolean registrarUsuario(String nombre, String apellido, String usuario, String contrasena) {
		if ( buscarBinario(gestorArchivos.getAdministradores(), usuario) == -1  && buscarBinario(gestorArchivos.getUsuarios(), usuario)  == -1) {
			Usuario nuevoUsuario = new Usuario(nombre, apellido, usuario, contrasena, false, false);
			gestorArchivos.getUsuarios().add(nuevoUsuario);
			ordenarPorUsuario(gestorArchivos.getUsuarios());
			gestorArchivos.guardarUsuarios();
			return true;
		}
		throw new IllegalArgumentException("El Usuario tiene un usuario que ya existe");
	}
	
	public boolean registrarAdministrador(String nombre, String apellido, String usuario, String contrasena, String cargo) {
		if ( buscarBinario(gestorArchivos.getAdministradores(), usuario) == -1  && buscarBinario(gestorArchivos.getUsuarios(), usuario)  == -1) {
			Administrador administrador = new Administrador(nombre, apellido, usuario, contrasena, false, cargo);
			gestorArchivos.getAdministradores().add(administrador);
			ordenarPorUsuario(gestorArchivos.getAdministradores());
			gestorArchivos.guardarAdministradores();
			return true;
				
			}
		throw new IllegalArgumentException("El Administrador tiene un usuario que ya existe");
		}
	
	public boolean registrarLibro(String isbn, String nombre, String autor, int copiasTotales){
	 
			Libro libro = new Libro(isbn, nombre, autor, copiasTotales);
			libros.insertarLibro(libro);
			gestorArchivos.guardarLibros();
			return true;
		
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
		int resultadoAdministradores = buscarBinario(gestorArchivos.getAdministradores(), nombreUsuario);
		int resultadoUsuarios = buscarBinario(gestorArchivos.getUsuarios(), nombreUsuario);
		 if (resultadoAdministradores != -1) {
			 Administrador adminstrador = gestorArchivos.getAdministradores().get(resultadoAdministradores);
			 if (adminstrador.compararContrasena(contrasena)) {
				 return adminstrador;
			 }
			 else {
				 throw new IllegalArgumentException("La contraseña o el usuario son incorrectas");
			 }
			 
		 }
		 else if (resultadoUsuarios != -1) {
			 Usuario usuario = gestorArchivos.getUsuarios().get(resultadoUsuarios);
			 if (usuario.compararContrasena(contrasena)) {
				 if (usuario.isBloqueado()) {
					    throw new IllegalArgumentException("El usuario esta bloqueado");
				 }
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
	
	public boolean prestarLibro(String isbn, String nombreUsuario) {
		Libro libro = libros.buscarLibro(isbn); 
		if ( libro != null) {
			int resultadoUsuario = buscarBinario(gestorArchivos.getUsuarios(), nombreUsuario);
			if (resultadoUsuario != -1) {
				Usuario usuario = gestorArchivos.getUsuarios().get(resultadoUsuario);
				Prestamo prestamo = new Prestamo(libro, usuario);
				if (usuario.getPrestamos().getTamanio() + contarReservas(usuario) >= 3) {
				    throw new IllegalArgumentException("El usuario ya tiene 3 entre prestamos y reservas");
				}
				if (libro.getCopiasDisponibles() > 0) {
					usuario.agregarPrestamo(prestamo);
					libro.prestarLibro();
					gestorArchivos.guardarPrestamos();
					return true;
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
	
	public boolean devolverLibro(String isbn, String nombreUsuario) {
		Libro libro = libros.buscarLibro(isbn); 
		if ( libro != null) {
			int resultadoUsuario = buscarBinario(gestorArchivos.getUsuarios(), nombreUsuario);
			if (resultadoUsuario != -1) {
				Usuario usuario = gestorArchivos.getUsuarios().get(resultadoUsuario);
				if (usuario.buscarPrestamo(libro) != null) {
					usuario.getPrestamos().eliminar(usuario.buscarPrestamo(libro));
					if (libro.tieneReserva()) {
						int cantidad = libro.getReservas().size();
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
					gestorArchivos.guardarPrestamos();
					gestorArchivos.guardarReservas();
					return true;
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
	
	public boolean renovarLibro(String isbn, String nombreUsuario) {
		Libro libro = libros.buscarLibro(isbn); 
		if ( libro != null) {
			 
				int resultadoUsuario = buscarBinario(gestorArchivos.getUsuarios(), nombreUsuario);
				if (resultadoUsuario != -1) {
					Usuario usuario = gestorArchivos.getUsuarios().get(resultadoUsuario);
					if (usuario.isBloqueado()) {
					    throw new IllegalArgumentException("El usuario esta bloqueado");
					}
					Prestamo prestamo = usuario.buscarPrestamo(libro);
					if (prestamo != null) {
						prestamo.renovar();
						gestorArchivos.guardarPrestamos();
						return true;
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
	
	public boolean reservarLibro(String isbn, String nombreUsuario) {
	    Libro libro = libros.buscarLibro(isbn);
	    if (libro != null) {
	        int resultadoUsuario = buscarBinario(gestorArchivos.getUsuarios(), nombreUsuario);
	        if (resultadoUsuario != -1) {
	            Usuario usuario = gestorArchivos.getUsuarios().get(resultadoUsuario);
	            if (usuario.isBloqueado()) {
	                throw new IllegalArgumentException("El usuario esta bloqueado");
	            }
	            if (usuario.getPrestamos().getTamanio() + contarReservas(usuario) < 3) {
	                if (usuario.buscarPrestamo(libro) == null) {
	                    if (!libro.getReservas().contains(usuario)) {
	                        libro.agregarReservas(usuario);   // lanza si todavía hay copias
	                        gestorArchivos.guardarReservas();
	                        return true;
	                    } else {
	                        throw new IllegalArgumentException("El usuario ya está en la fila de este libro");
	                    }
	                } else {
	                    throw new IllegalArgumentException("El usuario ya tiene un prestamo de este libro");
	                }
	            } else {
	                throw new IllegalArgumentException("El usuario ya tiene 3 entre prestamos y reservas");
	            }
	        } else {
	            throw new IllegalArgumentException("No existe ninguna Persona con ese Usuario");
	        }
	    } else {
	        throw new IllegalArgumentException("No existe un libro con ese ISBN");
	    }
	}
	
	public boolean eliminarLibro(String isbn) {
		Libro libro = libros.buscarLibro(isbn); 
		if ( libro != null){
			if (!libro.tieneReserva()) {
				if (libro.getCopiasTotales() == libro.getCopiasDisponibles()) {
					libros.eliminarLibro(isbn);
					gestorArchivos.guardarLibros();
					GestorFotos.eliminar(GestorFotos.LIBROS, isbn);
					return true;
				}
				else {
					throw new IllegalArgumentException("El libro no se puede eliminar porque tiene prestamos vigentes ");
				}
			}
			else {
				throw new IllegalArgumentException("El libro no se puede eliminar porque tiene reservas");
			}
		}
		else {
			throw new IllegalArgumentException("No existe ningun libro con ese ISBN");
		}
		}
	public boolean actualizarLibro( String ISBN, String nombre, String autor, int copiasTotales) {
		Libro libro = libros.actualizarLibro(ISBN, nombre, autor, copiasTotales);
		if (libro != null) {
			gestorArchivos.guardarLibros();
			return true;
		}
		else {
			throw new IllegalArgumentException("No existe ningun libro con ese ISBN");
		}
	}
	
	private int contarReservas(Usuario usuario) {
		int numeroReservas = 0;
		for (Libro libro : libros.getLibros()) {
			if (libro.getReservas().contains(usuario)) {
				numeroReservas += 1;
			}
		}
		return numeroReservas;
	}
	
	public boolean bloquearUsuario(String nombreUsuario) {
		int resultadoUsuario = buscarBinario(gestorArchivos.getUsuarios(), nombreUsuario);
		if (resultadoUsuario != -1) {
			Usuario usuario = gestorArchivos.getUsuarios().get(resultadoUsuario);
			if (!usuario.isBloqueado()) {
				usuario.bloquear();
				for (Libro libro : libros.getLibros()) {
					libro.quitarReserva(usuario);
				}
				gestorArchivos.guardarUsuarios();
				gestorArchivos.guardarReservas();
				return true;
			}
			else {
				throw new IllegalArgumentException("El usuario ya esta bloqueado");
			}
		}
		else {
			throw new IllegalArgumentException("No existe ninguna Persona con ese Usuario");
		}
	}
	
	public boolean desbloquearUsuario (String nombreUsuario) {
		int resultadoUsuario = buscarBinario(gestorArchivos.getUsuarios(), nombreUsuario);
		if (resultadoUsuario != -1) {
			Usuario usuario = gestorArchivos.getUsuarios().get(resultadoUsuario);
			if (usuario.isBloqueado()) {
				usuario.desbloquear();
				gestorArchivos.guardarUsuarios();
				return true;
			}
			else {
				throw new IllegalArgumentException("El usuario no esta bloqueado");
			}
		}
		else {
			throw new IllegalArgumentException("No existe ninguna Persona con ese Usuario");
		}
	
	}
	
	public boolean eliminarUsuario(String nombreUsuario) {
		int resultadoUsuario = buscarBinario(gestorArchivos.getUsuarios(), nombreUsuario);
		if (resultadoUsuario == -1) {
			throw new IllegalArgumentException("No existe ninguna Persona con ese Usuario");
		}
		Usuario usuario = gestorArchivos.getUsuarios().get(resultadoUsuario);
		if (!usuario.getPrestamos().estaVacia()) {
			throw new IllegalArgumentException("No se puede eliminar un usuario con prestamos");
		}
		for (Libro libro : libros.getLibros()) {
			libro.quitarReserva(usuario);
		}
		gestorArchivos.getUsuarios().remove(usuario);
		gestorArchivos.guardarUsuarios();
		gestorArchivos.guardarReservas();
		GestorFotos.eliminar(GestorFotos.USUARIOS, nombreUsuario);
		return true;
	}
	
	public boolean eliminarAdministrador(String nombreUsuario) {
		int resultadoAdministrador = buscarBinario(gestorArchivos.getAdministradores(), nombreUsuario);
		if (resultadoAdministrador == -1) {
			throw new IllegalArgumentException("No existe ninguna Persona con ese Usuario");
		}
		if (gestorArchivos.getAdministradores().size() == 1) {
			throw new IllegalArgumentException("No se puede eliminar al ultimo administrador");
		}
		gestorArchivos.getAdministradores().remove(resultadoAdministrador);
		gestorArchivos.guardarAdministradores();
		GestorFotos.eliminar(GestorFotos.ADMINISTRADORES, nombreUsuario);
		return true;
	}
	
	
	public boolean actualizarUsuario(String nombreUsuario, String nombre, String apellido, String contrasena) {
		int resultadoUsuario = buscarBinario(gestorArchivos.getUsuarios(), nombreUsuario) ; 
		if (resultadoUsuario == -1) {
			throw new IllegalArgumentException("No existe ninguna Persona con ese Usuario");
		 }
		Usuario usuario = gestorArchivos.getUsuarios().get(resultadoUsuario);
		usuario.actualizar(nombre, apellido, contrasena);
		gestorArchivos.guardarUsuarios();
		return true;
	}
	
	public boolean actualizarAdministrador(String nombreUsuario,  String nombre, String apellido, String contrasena, String cargo) {
		int resultadoAdministrador = buscarBinario(gestorArchivos.getAdministradores(), nombreUsuario) ; 
		if (resultadoAdministrador == -1) {
			throw new IllegalArgumentException("No existe ninguna Persona con ese Usuario");
		 }
		Administrador administrador = gestorArchivos.getAdministradores().get(resultadoAdministrador);
		administrador.actualizar(nombre, apellido, contrasena, cargo);
		gestorArchivos.guardarAdministradores();
		return true;
	}
	
	public List<Libro> listarLibros(){
		return new ArrayList<Libro>(libros.getLibros());
		
	}
	
	public List<Usuario> listarUsuarios(){
		return new ArrayList<Usuario>(gestorArchivos.getUsuarios());
	}
	
	public List<Administrador> listarAdministradores(){
		return new ArrayList<Administrador>(gestorArchivos.getAdministradores());
	}
	
	public List<Prestamo> prestamoDe(String nombreUsuario){
		int resultadoUsuario = buscarBinario(gestorArchivos.getUsuarios(), nombreUsuario);
		if (resultadoUsuario == -1) {
			throw new IllegalArgumentException("No existe ninguna Persona con ese Usuario");
		 }
		Usuario usuario = gestorArchivos.getUsuarios().get(resultadoUsuario);
		List<Prestamo> lista = new ArrayList<Prestamo>();
		Nodo<Prestamo> actual = usuario.getPrestamos().getInicio();
		while(actual != null){
			lista.add(actual.getContenido());
			actual = actual.getSiguiente();
		}
		return lista;
	}
	
	public List<Libro> reservasDe(String nombreUsuario) {
	    int resultadoUsuario = buscarBinario(gestorArchivos.getUsuarios(), nombreUsuario);
	    if (resultadoUsuario == -1) {
	        throw new IllegalArgumentException("No existe ninguna Persona con ese Usuario");
	    }
	    Usuario usuario = gestorArchivos.getUsuarios().get(resultadoUsuario);
	    List<Libro> lista = new ArrayList<Libro>();
	    for (Libro libro : libros.getLibros()) {
	        if (libro.getReservas().contains(usuario)) {                
	            lista.add(libro);
	        }
	    }
	    return lista;
	}
	
	
}
 