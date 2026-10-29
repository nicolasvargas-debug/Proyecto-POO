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
		if (comparacion > 0) {                                      // CAMBIO: antes decia < 0
			actual.setIzquierdo(insertar(actual.getIzquierdo(), libro));
		}
		else if (comparacion < 0) {

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
		if (actual == null) {
			return null;
		}
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

	public NodoArbol eliminarLibro(String isbn) {
		if (isbn != null && !isbn.strip().isEmpty()) {
			raiz = eliminar(raiz, isbn);                            // CAMBIO: antes era return eliminar(raiz, isbn);
			return raiz;
		}
		throw new IllegalArgumentException("El ISBN del libro no puede estar vacio o ser nulo");
	}

	private NodoArbol eliminar(NodoArbol actual, String ISBN) {
		if (actual == null) {
			return null;
			}

		int comparacion = actual.getContenido().getIsbn().compareTo(ISBN);
		if (comparacion > 0) {
			actual.setIzquierdo(eliminar(actual.getIzquierdo(), ISBN));
		}
		else if(comparacion < 0) {
			actual.setDerecho(eliminar(actual.getDerecho(), ISBN));
		}
		else {
			if (actual.getIzquierdo() == null) {
				tamanio--;
				return actual.getDerecho();
			}
			if (actual.getDerecho() == null) {
				tamanio--;
				return actual.getIzquierdo();
		}
			NodoArbol sucesor = actual.getDerecho();
			while (sucesor.getIzquierdo() != null) {
				sucesor = sucesor.getIzquierdo();
			}
			actual.setContenido(sucesor.getContenido());
			actual.setDerecho(eliminar(actual.getDerecho(), sucesor.getContenido().getIsbn()));

		}
		return actual;
	}

	public Libro actualizarLibro(String isbn ,String nombre, String autor, int copiasTotales) {
		if (isbn != null && !isbn.strip().isEmpty() && nombre != null && !nombre.strip().isEmpty() && autor != null && !autor.strip().isEmpty() && copiasTotales >0) {
			return actualizar( isbn, nombre, autor, copiasTotales);
		}
		throw new IllegalArgumentException("Los parametros tienen alguna falla");

	}

	private Libro actualizar( String ISBN, String nombre, String autor, int copiasTotales) {
		Libro buscado = buscarLibro(ISBN);
		if (buscado == null) {
			return null;
		}
		buscado.setCopiasTotales(copiasTotales);
		buscado.setAutor(autor);
		buscado.setNombre(nombre);
		return buscado;
	}

}