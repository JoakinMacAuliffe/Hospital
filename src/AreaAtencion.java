import java.util.PriorityQueue;

public class AreaAtencion {

    private String nombre; // SAPU, urgencia_adulto y infantil
    private PriorityQueue<Paciente> pacientesHeap;
    private int capacidadMaxima;

    public void ingresarPaciente(Paciente p) {
        pacientesHeap.add(p);
    }

    public Paciente atenderPaciente() {
        return pacientesHeap.poll();
    }

    public boolean estaSaturada() {

    }

}
