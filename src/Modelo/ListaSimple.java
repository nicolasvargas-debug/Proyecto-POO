package Modelo;

public class ListaSimple<T> {
	private Nodo<T> inicio;
	private int tamanio;
	

	public ListaSimple() {
		this.inicio = null;
		this.tamanio = 0;
	}

	public boolean estaVacia() {
		return inicio == null;
	}


	public boolean agregar(T contenido) {
		Nodo<T> nuevoNodo = new Nodo<>(contenido);
		nuevoNodo.setSiguiente(inicio);
		inicio = nuevoNodo;
		tamanio++;
		return true; 
	}
	
	public boolean eliminar(T contenido) {
		if (estaVacia()) {
			return false;
		}
		else if (inicio.getContenido() == contenido){
			 Nodo<T> siguiente = inicio.getSiguiente();
			 inicio.setSiguiente(null);
			 inicio = siguiente;
			 tamanio --;
			 return true;
		}
		else {
			Nodo<T> anterior = inicio;
			Nodo<T> actual = anterior.getSiguiente();
			while (actual != null) {
				if (actual.getContenido() == contenido) {
					Nodo<T> siguiente = actual.getSiguiente();
					anterior.setSiguiente(siguiente);
					tamanio --;
					return true;
				}
				anterior = actual;
				actual = actual.getSiguiente();
			
			}		
		}
		return false;
	}
	
	
	
	public Nodo<T> getInicio() {
		return inicio;
	}

	public int getTamanio() {
		return tamanio;
	}


}
