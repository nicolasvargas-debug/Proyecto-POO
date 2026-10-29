package Excepciones;

public class SinCopias extends RuntimeException {
	public SinCopias(String mensaje) {
        super(mensaje);
    }
}
