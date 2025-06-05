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

    public Paciente() {
        tiempoLlegada = System.currentTimeMillis() / 1000;
    }

    public void registrarCambio(String descripcion) {
        historialCambios.add(descripcion);
    }

    public String obtenerUltimoCambio() {
        return historialCambios.pop();
    }

    public long tiempoEsperaActual() {

    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

}
