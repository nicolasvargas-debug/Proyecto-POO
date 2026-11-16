package Excepciones;

public class CargoInvalido extends RuntimeException {
    public CargoInvalido() {
        super("El cargo no es valido");
    }
    public CargoInvalido(String mensaje) {
        super(mensaje);
    }
}
