package Excepciones;

public class LoginInvalido extends RuntimeException {
    public LoginInvalido() {
        super("El usuario o la contrasena son incorrectos");
    }
    public LoginInvalido(String mensaje) {
        super(mensaje);
    }
}
