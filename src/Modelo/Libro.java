package Modelo;

public class Libro {
	private String isbn;
	private String nombre;
	private String autor;
	private int	copiasTotales;
	private int copiasDisponibles;
	
	
	public Libro(String isbn, String nombre, String autor, int copiasTotales) {
		setIsbn(isbn);
		setNombre(nombre);
		setAutor(autor);
		setCopiasTotales(copiasTotales);
		setCopiasDisponibles(copiasTotales);
		verificarInvariante();
	}

	public String getIsbn() {
		return isbn;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre != null && !nombre.strip().isEmpty() ) {
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
		
		if (autor != null && !autor.strip().isEmpty())
			this.autor = autor;
		else {
			throw new IllegalArgumentException("Autor invalido: " +autor);
		}
	}

	

	public int getCopiasTotales() {
		return copiasTotales;
	}

	public void setCopiasTotales(int copiasTotales) {
		int adicionarDisponible = copiasTotales - getCopiasTotales();
		if (copiasTotales > 0 &&  getCopiasDisponibles() + adicionarDisponible >= 0) {
			this.copiasTotales = copiasTotales;
			setCopiasDisponibles(getCopiasDisponibles()+ adicionarDisponible);//para aumentar ó reducir las disponibles al mismo tiempo
			
		}
		else {
			throw new IllegalArgumentException("Copias Totales invalidas: " + copiasTotales);
		}
		
	}
	 public int getCopiasDisponibles() {
		 return copiasDisponibles;
	 }
	
	private void setCopiasDisponibles(int copiasDisponibles) {
		if (  copiasTotales >= copiasDisponibles && copiasDisponibles >= 0 ) {
			this.copiasDisponibles = copiasDisponibles;
			
		}
		else {
			throw new IllegalArgumentException("Copias Disponibles invalidas: "  + copiasDisponibles);
		}
		
	}

	private void setIsbn(String isbn) {
		if (isbn == null || isbn.strip().isEmpty()) {
			throw new IllegalArgumentException("Isbn invalido: "+ isbn);
		}
		else {
			this.isbn = isbn;
		}
	}
	
	public void prestarLibro() {
		int cambio = getCopiasDisponibles() -1;
		setCopiasDisponibles(cambio);
		verificarInvariante();
	}
	
	public void devolverLibro() {
		int cambio = getCopiasDisponibles() +1 ;
		setCopiasDisponibles(cambio);
		verificarInvariante();
		
	}

	private void verificarInvariante()
	{
		assert nombre != null && !nombre.trim().isEmpty() : "El nombre no puede ser nulo ni estar vacio";
		assert copiasTotales > 0  : "Las copias totales no pueden ser menores que 0";
		assert copiasDisponibles >= 0: "Las copias disponibles no pueden ser menores que 0";
		assert copiasTotales >= copiasDisponibles  : "Las copias totales no pueden ser menores que las copias disponibles";
		assert isbn != null && !isbn.trim().isEmpty() : "El codigo Isbn no puede ser nulo ni estar vacio";
		assert autor != null && !autor.strip().isEmpty(): "El autor no puede ser nulo ni estar vacio"; 
		
}
}



