package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {
	private List<Usuario> usuarios;
	private List<Administrador> administradores;
	private final static String SEPARADOR = ",";
	private final static String RUTA_ADMINISTRADORES = "Data/RegistroAdministrador.csv";
	private final static String RUTA_USUARIOS = "Data/RegistroUsuarios.csv";
	private final static String RUTA_PRESTAMOS = "Data/RegistroPrestamos.csv";
	private final static String RUTA_RESERVAS = "Data/RegistroReservas.csv";
	private final static String RUTA_LIBROS = "Data/RegistroLibros.csv";
	private ArbolBinario arbolBinario;
	
	
	public GestorArchivos(ArbolBinario arbolBinario ) {
		usuarios =  new ArrayList<Usuario>();
		administradores = new ArrayList<Administrador>();
		this.arbolBinario = arbolBinario;
		
		
	}


	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public List<Administrador> getAdministradores() {
		return administradores;
	}
	
	public void cargarLibros() {
	    try (BufferedReader lector = new BufferedReader(new FileReader(RUTA_LIBROS))) {
	        String linea;
	        while ((linea = lector.readLine()) != null) {
	            String[] partes = linea.split(SEPARADOR);
	            if (partes.length == 4) {
	                Libro libro = new Libro(partes[0], partes[1], partes[2], Integer.parseInt(partes[3]) );
	                arbolBinario.insertarLibro(libro);
	            }
	        }
	    } catch (IOException e) {
	        throw new IllegalArgumentException("Error al cargar libros " + RUTA_LIBROS);
	    }
	}
	
	
	
	public void cargarUsuarios() {
		usuarios.clear();
		try(BufferedReader lector = new BufferedReader(new FileReader(RUTA_USUARIOS))){
			String linea;
			while ((linea = lector.readLine()) != null) {
				String[] partes = linea.split(SEPARADOR);
				if (partes.length == 5) {
					Usuario usuario = new Usuario(partes[0], partes[1], partes[2], partes[3], true, Boolean.parseBoolean(partes[4]));
					usuarios.add(usuario);
				}
			}
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error al cargar Usuarios " + RUTA_USUARIOS);
		}
	}
	
	public void cargarAdministradores() {
	    administradores.clear();
	    try (BufferedReader lector = new BufferedReader(new FileReader(RUTA_ADMINISTRADORES))) {
	        String linea;
	        while ((linea = lector.readLine()) != null) {
	            String[] partes = linea.split(SEPARADOR);
	            if (partes.length == 5) {
	                Administrador administrador = new Administrador(
	                    partes[0], partes[1], partes[2], partes[3], true, partes[4]);
	                administradores.add(administrador);
	            }
	        }
	    } catch (IOException e) {
	        throw new IllegalArgumentException("Error al cargar administradores " + RUTA_ADMINISTRADORES);
	    }
	}
	
	

	
	public void cargarPrestamos() {
		try(BufferedReader lector = new BufferedReader(new FileReader(RUTA_PRESTAMOS))){
			String linea;
			while ((linea = lector.readLine()) != null) {
				String[] partes = linea.split(SEPARADOR);
				if (partes.length == 4) {
					Libro libro = arbolBinario.buscarLibro(partes[0]);
					if (libro != null ) {
						Usuario usuario = buscarUsuario(partes[1]);
						if ( usuario != null) {
							Prestamo prestamo = new Prestamo( libro  , usuario  , Boolean.parseBoolean(partes[2]), LocalDate.parse(partes[3])) ;
							usuario.getPrestamos().agregar(prestamo);
							libro.prestarLibro();
						}
						else {
							throw new IllegalArgumentException("El usuario no se encontro " + partes[1] );
						}
							
					}
					else {
						throw new IllegalArgumentException("El libro no se encontro " + partes[0] );
					}
					
					
				}
			}
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error al cargar Prestamos " + RUTA_PRESTAMOS);
		}
		catch ( DateTimeException e) {
			throw new IllegalArgumentException("Error con la fecha " + e);
		}
	}
	
	public void cargarReservas() {
		try (BufferedReader lector = new BufferedReader( new FileReader(RUTA_RESERVAS))){
			String linea;
			while ((linea = lector.readLine()) != null) {
				String[] partes = linea.split(SEPARADOR);
				if(partes.length == 2) {
					Libro libro = arbolBinario.buscarLibro(partes[0]);
					Usuario usuario = buscarUsuario(partes[1]);
					if (libro != null) {
						if (usuario != null) {
							libro.agregarReservas(usuario);
						}
						else {
							throw new IllegalArgumentException("El Usuario no existe");
						}
					}
					else {
						throw new IllegalArgumentException("El Libro no existe");
					}
				}
			}
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error al cargar reservas" + RUTA_RESERVAS);
		}
	}
	
	
	public void guardarPrestamos( ) {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(RUTA_PRESTAMOS))){
			for (Usuario usuario : usuarios) {
				Nodo<Prestamo> actual = usuario.getPrestamos().getInicio(); 
				while (actual != null  ){
					Prestamo prestamo = actual.getContenido();
					String linea = prestamo.getLibro().getIsbn() + SEPARADOR + usuario.getUsuario() + SEPARADOR + prestamo.isRenovado() + SEPARADOR + prestamo.getFechaInicio() ;
					escritor.write(linea);
					escritor.newLine();
					actual = actual.getSiguiente();
				}	
			}
			
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error al guardar Prestamo "+ RUTA_PRESTAMOS);
		}
		
	}
	
	public void  guardarUsuarios() {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(RUTA_USUARIOS))){
			for (Usuario usuario : usuarios) {
				String linea = usuario.getNombre() + SEPARADOR + usuario.getApellido() + SEPARADOR  + usuario.getUsuario() + SEPARADOR +  usuario.getContrasenaHash() + SEPARADOR + usuario.isBloqueado();
				escritor.write(linea);
				escritor.newLine();
			}
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error al guardar Usuario "+ RUTA_USUARIOS);
		}
		
	}
	
	public void guardarAdministradores() {
		try (BufferedWriter escritor = new BufferedWriter( new FileWriter(RUTA_ADMINISTRADORES))){
			for (Administrador administador : administradores) {
				String linea = administador.getNombre() + SEPARADOR+ administador.getApellido() + SEPARADOR + administador.getUsuario() +SEPARADOR + administador.getContrasenaHash() +  SEPARADOR+ administador.getCargo() ;
				escritor.write(linea);
				escritor.newLine();
			}
			
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error al guardar administrador" + RUTA_ADMINISTRADORES);
		}
	}
	
	public void guardarLibros() {
			try (BufferedWriter escritor = new BufferedWriter( new FileWriter(RUTA_LIBROS))){
				for (Libro libro : arbolBinario.obtenerLibros()) {
					String linea =  libro.getIsbn() + SEPARADOR + libro.getNombre() + SEPARADOR +   libro.getAutor()  + SEPARADOR +libro.getCopiasTotales() ;
					escritor.write(linea);
					escritor.newLine();
				}
				
			}
			catch (IOException e) {
				throw new IllegalArgumentException("Error al guardar Libro" + RUTA_LIBROS);
			}
	}
	
	private Usuario buscarUsuario(String nombreUsuario) {
		for (Usuario usuario: usuarios) {
			if (usuario.getUsuario().equals(nombreUsuario)) {
				return usuario;
			}
		}
		return null;

	}
	
	public void guardarReservas() {
		try (BufferedWriter escritor = new BufferedWriter( new FileWriter(RUTA_RESERVAS))){
			for (Libro libro : arbolBinario.obtenerLibros()) {
				List<Usuario> reservas = libro.listarReservas();
				for (Usuario usuario: reservas) {
					String linea =  libro.getIsbn() + SEPARADOR + usuario.getUsuario() ;
					escritor.write(linea);
					escritor.newLine();
				}
			}
			
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error al guardar Reserva" + RUTA_RESERVAS);
		}
	}
	

}
