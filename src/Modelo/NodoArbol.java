package Modelo;

public class NodoArbol {
	private Libro contenido;
	private NodoArbol izquierdo;
	private NodoArbol derecho;

	public NodoArbol(Libro contenido) {
		this.contenido = contenido;
		this.izquierdo = null;
		this.derecho = null;
	}

	public Libro getContenido() {
		return contenido;
	}

	public void setContenido(Libro contenido) {
		this.contenido = contenido;
	}

	public NodoArbol getIzquierdo() {
		return izquierdo;
	}

	public void setIzquierdo(NodoArbol izquierdo) {
		this.izquierdo = izquierdo;
	}

	public NodoArbol getDerecho() {
		return derecho;
	}

	public void setDerecho(NodoArbol derecho) {
		this.derecho = derecho;
	}
	
	
	
	
}