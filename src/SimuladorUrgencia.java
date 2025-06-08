import java.io.*;
import java.util.*;

public class SimuladorUrgencia {

    private int pacientesAcumulados = 0;
    private List<Paciente> listaPacientes;

    public void simular() {

        GeneradorPacientes generadorPacientes = new GeneradorPacientes();

        listaPacientes = leerPacientesDesdeArchivo("Pacientes_24h.txt");

        Hospital hospital = new Hospital();

        for(int tiempoActualMinutos = 1; tiempoActualMinutos <= 1440; tiempoActualMinutos++) {

            if (!listaPacientes.isEmpty() && listaPacientes.getFirst().getTiempoLlegada() == tiempoActualMinutos) {
                Paciente paciente = listaPacientes.removeFirst();
                hospital.registrarPaciente(paciente);
                System.out.println(paciente.tiempoEsperaActual(tiempoActualMinutos));
                pacientesAcumulados++;
            }

            if(pacientesAcumulados == 3) {
                // Atender dos pacientes inmediatamente
                hospital.atenderSiguiente(tiempoActualMinutos);
                hospital.atenderSiguiente(tiempoActualMinutos);
                pacientesAcumulados = 0; // Reiniciar contador
            }
            else if(tiempoActualMinutos % 15 == 0) hospital.atenderSiguiente(tiempoActualMinutos);

        }

    }

    public List<Paciente> leerPacientesDesdeArchivo(String nombreArchivo) {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String nombre = parts[0].split(": ")[1];
                String apellido = parts[1].split(": ")[1];
                String id = parts[2].split(": ")[1];
                int categoria = Integer.parseInt(parts[3].split(": ")[1]);
                long tiempoLlegada = Long.parseLong(parts[4].split(": ")[1]);
                String estado = parts[5].split(": ")[1];
                String area = parts[6].split(": ")[1];
                Stack<String> historialCambios = new Stack<>();
                Paciente paciente = new Paciente(nombre, apellido, id, categoria, tiempoLlegada, estado, area, historialCambios);
                pacientes.add(paciente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    public static void main(String[] main) {
        SimuladorUrgencia simuladorUrgencia = new SimuladorUrgencia();
        List<Paciente> pacienteList = simuladorUrgencia.leerPacientesDesdeArchivo("Pacientes_24h.txt");
        simuladorUrgencia.simular();

    }


}
