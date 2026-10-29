package Modelo;

public class Cola<T>{
	private Nodo<T> inicio;   
	private Nodo<T> fin;      
	private int tamanio;

	public Cola() {
		this.inicio = null;
		this.fin = null;
		this.tamanio = 0;
	}

	public boolean estaVacia() {
		return inicio == null;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void add(T contenido) {
		Nodo<T> nuevo = new Nodo<>(contenido);
		if (estaVacia()) {
			inicio = nuevo;
			fin = nuevo;
		} else {
			fin.setSiguiente(nuevo);
			fin = nuevo;
		}
		tamanio++;
	}

	public T poll() {
		if (estaVacia()) {
			return null;
		}
		T contenido = inicio.getContenido();
		inicio = inicio.getSiguiente();
		if (inicio == null) {
			fin = null;
		}
		tamanio--;
		return contenido;
	}

	public T peek() {
		if (estaVacia()) {
			return null;
		}
		return inicio.getContenido();
	}

}
