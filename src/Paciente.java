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

    public Paciente(String nombre, String apellido, String id, int categoria, String estado, Stack<String> historialCambios) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.categoria = categoria;
        this.estado = estado;
        this.historialCambios = historialCambios;
    }

    public void registrarCambio(String descripcion) {
        historialCambios.add(descripcion);
    }

    public String obtenerUltimoCambio() {
        return historialCambios.pop();
    }

    public long tiempoEsperaActual(long timestamp) {
        return tiempoLlegada - timestamp;
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

    public void setArea(AreaAtencion areaAtencion) {
        this.area = areaAtencion.getNombre();
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

}
