import java.util.List;

public class Main{

    public static void main(String[] main) {

        SimuladorUrgencia simuladorUrgencia = new SimuladorUrgencia();
        List<Paciente> pacienteList = simuladorUrgencia.leerPacientesDesdeArchivo("Pacientes_24h.txt");
        simuladorUrgencia.simular();

    }
}
