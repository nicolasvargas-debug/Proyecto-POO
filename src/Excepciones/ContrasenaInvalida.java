package Excepciones;

public class ContrasenaInvalida extends RuntimeException {
    public ContrasenaInvalida() {
        super("La contrasena no es valida");
    }
    public ContrasenaInvalida(String mensaje) {
        super(mensaje);
    }
}
