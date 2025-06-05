import java.util.*;

public class AreaAtencion {

    private String nombre; // SAPU, urgencia_adulto y infantil
    private PriorityQueue<Paciente> pacientesHeap;
    private int capacidadMaxima;

    public AreaAtencion(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
    }

    public void ingresarPaciente(Paciente p) {
        pacientesHeap.add(p);
    }

    public Paciente atenderPaciente() {
        return pacientesHeap.poll();
    }

    public boolean estaSaturada() {
        return pacientesHeap.size() >= capacidadMaxima;
    }

    public String getNombre() {
        return nombre;
    }

    List<Paciente> obtenerPacientesPorHeapSort() {
        List<Paciente> pacientesOrdenados = new ArrayList<>();
        PriorityQueue<Paciente> copiaHeap = new PriorityQueue<>(pacientesHeap);
        while(!copiaHeap.isEmpty()) {
            pacientesOrdenados.add(copiaHeap.poll());
        }
        return pacientesOrdenados;
    }




}
