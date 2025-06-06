import java.util.List;

public class Main{
    public static void main(String[] args) {
        GeneradorPacientes generadorPacientes = new GeneradorPacientes();
        List<Paciente> pacientes = generadorPacientes.generarPacientes(100);

        for (Paciente paciente : pacientes) {
            System.out.println(paciente.getNombre() + " " + paciente.getId());
        }
    }
}
