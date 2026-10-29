package Modelo;

public class ListaSimple<T> {
	private Nodo<T> inicio;
	private int tamanio;
	private static final int MAX_PRESTAMOS = 3;

	public ListaSimple() {
		this.inicio = null;
		this.tamanio = 0;
	}

	public boolean estaVacia() {
		return inicio == null;
	}


	public boolean agregar(T contenido) {
		if (tamanio >= MAX_PRESTAMOS) {
			System.out.println("Error: Se ha alcanzado el límite máximo de 3 préstamos.");
			return false; 
		}

		Nodo<T> nuevoNodo = new Nodo<>(contenido);
		if (estaVacia()) {
			inicio = nuevoNodo;
		} else {
			Nodo<T> actual = inicio;
			while (actual.getSiguiente() != null) {
				actual = actual.getSiguiente();
			}
			actual.setSiguiente(nuevoNodo);
		}
		tamanio++;
		return true; 
	}

	public Nodo<T> getInicio() {
		return inicio;
	}

	public void setInicio(Nodo<T> inicio) {
		this.inicio = inicio;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

}
