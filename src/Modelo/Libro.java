package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Libro {
	private String isbn;
	private String nombre;
	private String autor;
	private Cola<Usuario> reservas;
	private int	copiasTotales;
	private int cantidadPrestamos;
	
	
	public Libro(String isbn, String nombre, String autor, int copiasTotales) {
		setIsbn(isbn);
		setNombre(nombre);
		setAutor(autor);
		setCopiasTotales(copiasTotales);
		reservas = new Cola<Usuario>();
		setCantidadPrestamos(0);
		verificarInvariante();
		
		
	}
 
	public String getIsbn() {
		return isbn;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre != null && !nombre.strip().isEmpty()  && !nombre.contains(",")) {
			this.nombre = nombre;
		}
		else {
			throw new IllegalArgumentException("Nombre invalido: " + nombre);
		}
		
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		
		if (autor != null && !autor.strip().isEmpty() && !autor.contains(","))
			this.autor = autor;
		else {
			throw new IllegalArgumentException("Autor invalido: " +autor);
		}
	}

	

	public int getCopiasTotales() {
		return copiasTotales;
	}
	private void setCantidadPrestamos(int cantidadPrestamos) {
		if (cantidadPrestamos <= copiasTotales  && cantidadPrestamos >= 0) {
			this.cantidadPrestamos = cantidadPrestamos;
		}
		else {
			throw new IllegalArgumentException("La cantidad de prestamos esta mal");
		}
	}
	
	public int getCantidadPrestamos () {
		return cantidadPrestamos;
	}
	
	public void setCopiasTotales(int copiasTotales) {
		if (copiasTotales > 0 &&   copiasTotales >= getCantidadPrestamos()) {
			this.copiasTotales = copiasTotales;
			
		}
		else {
			throw new IllegalArgumentException("Copias Totales invalidas: " + copiasTotales);
		}
		
	}
	public int getCopiasDisponibles() { 
				return getCopiasTotales() - getCantidadPrestamos();
		}
	
	
	
	

	private void setIsbn(String isbn) {
		if (isbn == null || isbn.strip().isEmpty() || isbn.contains(",")) {
			throw new IllegalArgumentException("Isbn invalido: "+ isbn);
		}
		else {
			this.isbn = isbn;
		}
	}
	
	public void prestarLibro() {
		setCantidadPrestamos(getCantidadPrestamos() + 1);
		verificarInvariante();
	}
	
	public void devolverLibro() {
		setCantidadPrestamos(getCantidadPrestamos() - 1);
		verificarInvariante();
		
	}
	public boolean tieneReserva(){
		return !reservas.estaVacia();
	}
		
	
	public boolean agregarReservas(Usuario u) {
		if (u == null) {
			throw new IllegalArgumentException("El usuario no puede ser nulo");
		}
		else if (getReservas().contains(u)) {
			throw new IllegalArgumentException("El usuario ya tiene una reserva de este libro");
		}
		else if (cantidadPrestamos == copiasTotales) {
			reservas.add(u);
			return true;
		}
			throw new IllegalArgumentException("Todavia hay copias disponibles");
		
	}
	public Usuario siguienteReserva() {
		return reservas.poll();
	}
	
	public List<Usuario> getReservas(){
		List<Usuario> lista = new ArrayList<Usuario>();
		Nodo<Usuario> actual = reservas.getInicio();
		while (actual != null) {
			lista.add(actual.getContenido());
			actual = actual.getSiguiente();
		}
		return lista;
	}
	
	public void quitarReserva(Usuario u) {
	    int cantidad = reservas.getTamanio();
	    for (int i = 0; i < cantidad; i++) {
	        Usuario actual = reservas.poll();
	        if (actual != u) {
	            reservas.add(actual);
	        }
	    }
	}
	
	private void verificarInvariante()
	{
		assert nombre != null && !nombre.trim().isEmpty() && !nombre.contains(",") : "El nombre no puede ser nulo ni estar vacio";
		assert copiasTotales > 0  : "Las copias totales no pueden ser menores que 0";
		assert copiasTotales >= cantidadPrestamos : "Las copias totales no pueden ser menores que las copias disponibles";
		assert isbn != null && !isbn.trim().isEmpty()  && !isbn.contains(","): "El codigo Isbn no puede ser nulo ni estar vacio";
		assert autor != null && !autor.strip().isEmpty() && !autor.contains(","): "El autor no puede ser nulo ni estar vacio"; 
		assert cantidadPrestamos <= copiasTotales: "La cantidad de pretsamos debe ser menor o igual que las copias totales";
}
}



