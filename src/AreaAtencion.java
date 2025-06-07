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
        if(!estaSaturada()) {
            pacientesHeap.add(p);
        }
    }

    public Paciente atenderPaciente() {
        if(!pacientesHeap.isEmpty()) {
            Paciente paciente = pacientesHeap.poll();
            paciente.setEstado("atendido");
            return paciente;
        } else {
            return null;
        }
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
