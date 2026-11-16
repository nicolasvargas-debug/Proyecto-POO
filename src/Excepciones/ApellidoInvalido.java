package Excepciones;

public class ApellidoInvalido extends RuntimeException {
    public ApellidoInvalido() {
        super("El apellido no es valido");
    }
    public ApellidoInvalido(String mensaje) {
        super(mensaje);
    }
}
