package Modelo;

public class ArbolBinario {
	private NodoArbol raiz;
	private int tamanio;

	public ArbolBinario(){
		this.raiz = null;
		this.tamanio = 0;
	
	}
	

	public boolean estaVacio() {
		return raiz == null;
	}

	public int getTamanio() {
		return tamanio;
	}
	
	
	public void guardarLibro(Libro libro) {
		if (libro == null) {
			throw new IllegalArgumentException("El libro no puede ser nulo");
		}
		raiz = insertar(raiz, libro);
	}
	

	private NodoArbol insertar(NodoArbol actual, Libro libro) {
		if (actual == null) {
			tamanio++;
			return new NodoArbol(libro);
			}
		int comparacion = actual.getContenido().getIsbn().compareTo(libro.getIsbn());
		if (comparacion < 0) {
			actual.setIzquierdo(insertar(actual.getIzquierdo(), libro));
		}
		else if (comparacion > 0) {
			
			actual.setDerecho(insertar(actual.getDerecho(), libro));
		}
		else {
			throw new IllegalArgumentException("Ya existe un Libro con ese ISBN");
		}
		return actual;
	}
	
	
	public Libro buscarLibro(String isbn) {
		if (isbn != null && !isbn.strip().isEmpty()) {
			return busqueda(raiz, isbn);
		}
		throw new IllegalArgumentException("El ISBN del libro no puede estar vacio o ser nulo");
	}
	
	private Libro busqueda(NodoArbol actual, String ISBN) {
		if (actual != null) {
			int comparacion = actual.getContenido().getIsbn().compareTo(ISBN);
			if (comparacion > 0) {
				return busqueda(actual.getIzquierdo(), ISBN);
			}
			else if(comparacion < 0) {
				return busqueda(actual.getDerecho(), ISBN);
			}
			else {
				return actual.getContenido();
			}
		}
		return null;		
	}
}
