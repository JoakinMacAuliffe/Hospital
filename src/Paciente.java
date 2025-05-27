import java.util.*;
import java.time.Instant;

public class Paciente {

    private String nombre;
    private String apellido;
    private String id;
    private int categoria; // de 1 a 5
    private long tiempoLlegada; //  formato Unix timestamp
    private String estado;
    private String area; // SAPU, urgencia_adulto e infantil
    private Stack<String> historialCambios = new Stack<>();

    public void registrarCambio(String descripcion) {
        historialCambios.add(descripcion);
    }

    String obtenerUltimoCambio() {
        return historialCambios.pop();
    }


}
