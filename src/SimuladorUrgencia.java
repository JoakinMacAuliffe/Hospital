import java.io.*;
import java.util.*;

public class SimuladorUrgencia {

    private long timestamp = 0;
    private int pacientesAcumulados = 0;
    private List<Paciente> listaPacientes;

    public void simular(int pacientesPorDia) {

        Hospital hospital = new Hospital();

        for(int i = 0; i < 1440; i++) {

            timestamp += 60; // Pasa un minuto

            if(timestamp%600 == 0) { // Cada 10 minutos
                // Llega un nuevo paciente
                pacientesAcumulados++;
            } else if (timestamp%900 == 0) { // Cada 15 minutos

            }

            if(pacientesAcumulados == 3) {
                // Atender dos pacientes inmediatamente
                pacientesAcumulados = 0; // Reiniciar contador
            }

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
        List<Paciente> pacienteList = simuladorUrgencia.leerPacientesDesdeArchivo("test.txt");

    }


}
