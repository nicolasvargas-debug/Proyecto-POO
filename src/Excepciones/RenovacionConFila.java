package Excepciones;

public class RenovacionConFila extends RuntimeException {
    public RenovacionConFila() {
        super("El libro tiene reservas, no se puede renovar");
    }
    public RenovacionConFila(String mensaje) {
        super(mensaje);
    }
}
