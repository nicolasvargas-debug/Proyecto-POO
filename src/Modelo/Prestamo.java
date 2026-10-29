package Modelo;

import java.time.LocalDate;

public class Prestamo {
	private Libro libro;
	private Usuario usuario;
	private boolean renovado;
	private LocalDate fechaInicio;
	private static final int DIAS_PRESTAMO = 7;
	
	
	
	
	public Prestamo(Libro libro, Usuario usuario) {
		setLibro(libro);
		setUsuario(usuario);
		
		this.fechaInicio = LocalDate.now();
		this.renovado = false;
		
		verificarInvariante();	
		}
	
	public LocalDate getFechaFin() {
		if(renovado) {
			return fechaInicio.plusDays(DIAS_PRESTAMO *2);
		}
		return fechaInicio.plusDays(DIAS_PRESTAMO);
	}
	
	public boolean estaVencido() {
		LocalDate actual = LocalDate.now(); 
		if (actual.isAfter(getFechaFin())) {
			return true;
		}
		return false;
	}

	public Libro getLibro() {
		return libro;
	}

	private void setLibro(Libro libro) {
		if (libro != null) {
			this.libro = libro;
		}
		else {
			throw new IllegalArgumentException("El libro no puede ser nulo");
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	private void setUsuario(Usuario usuario) {
		if (usuario != null) {
			this.usuario = usuario;
		}
		else {
			throw new IllegalArgumentException("El usuario no puede ser nulo");
		}
	}
	
	public boolean renovar() {
		if (renovado) {
			throw new IllegalStateException("El prestamo ya fue renovado");
		}
		else if (libro.tieneReserva()) {
			throw new IllegalStateException("El libro tiene reservas, no se puede renovar");
			
		}
		else {
			renovado = true;
			return true;
		}
	}

	public boolean isRenovado() {
		return renovado;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	private void verificarInvariante() {
		assert libro != null: "El libro no puede ser nulo";
		assert usuario != null: "El usuario no puede ser nulo";
	}
	
	
	
}
