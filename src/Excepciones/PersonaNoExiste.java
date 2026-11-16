package Excepciones;

public class PersonaNoExiste extends RuntimeException {
    public PersonaNoExiste() {
        super("No existe ninguna persona con ese usuario");
    }
    public PersonaNoExiste(String mensaje) {
        super(mensaje);
    }
}
