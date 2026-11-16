package Excepciones;

public class ReservaDuplicada extends RuntimeException {
    public ReservaDuplicada() {
        super("El usuario ya tiene una reserva de este libro");
    }
    public ReservaDuplicada(String mensaje) {
        super(mensaje);
    }
}
