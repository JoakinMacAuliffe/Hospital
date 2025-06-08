import java.util.*;
import java.time.Instant;

public class Paciente implements Comparable<Paciente>{

    private String nombre;
    private String apellido;
    private String id;
    private int categoria; // de 1 a 5
    private long tiempoLlegada; //  formato Unix timestamp
    private String estado;
    private String area; // SAPU, urgencia_adulto e infantil
    private Stack<String> historialCambios = new Stack<>();
    private Boolean esperaDentroDelTiempo;

    public Paciente(String nombre, String apellido, String id, int categoria,
                    long tiempoLlegada, String estado, String area, Stack<String> historialCambios) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.categoria = categoria;
        this.tiempoLlegada = tiempoLlegada;
        this.estado = "en_espera";
        this.area = area;
        this.historialCambios = historialCambios;
        this.esperaDentroDelTiempo = true;
    }

    public void registrarCambio(String descripcion) {
        historialCambios.add(descripcion);
    }

    public String obtenerUltimoCambio() {
        return historialCambios.pop();
    }

    public long tiempoEsperaActual(long timestamp) {
        return (timestamp - tiempoLlegada);
    }



    @Override
    public int compareTo(Paciente otro) {
        //este if pone como prioridad al paciente que haya superado su tiempo de espera segun su categoria
        if(this.esperaDentroDelTiempo != otro.esperaDentroDelTiempo) return Boolean.compare(this.esperaDentroDelTiempo, otro.esperaDentroDelTiempo);

        else if(this.categoria != otro.getCategoria())  return Integer.compare(this.categoria, otro.getCategoria());

        else return Long.compare(this.tiempoLlegada, otro.getTiempoLlegada());
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

    public String getEstado() {
        return estado;
    }

    public int getCategoria() {
        return categoria;
    }

    public long getTiempoLlegada() {
        return tiempoLlegada;
    }

    public Boolean getEsperaDentroDelTiempo(){
        return this.esperaDentroDelTiempo;
    }

    public void setArea(AreaAtencion areaAtencion) {
        this.area = areaAtencion.getNombre();
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setEspera() {
        this.esperaDentroDelTiempo = false;
    } //false para que tenga prioridad en el comparable

}
