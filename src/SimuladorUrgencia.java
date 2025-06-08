import java.io.*;
import java.util.*;

public class SimuladorUrgencia {

    private int pacientesAcumulados = 0;
    private List<Paciente> listaPacientes;

    Paciente pacienteCat4 = null;
    long tiempoLlegadaCat4 = -1;
    long tiempoAtencionCat4 = -1;

    public void simular() {

        GeneradorPacientes generadorPacientes = new GeneradorPacientes();

        listaPacientes = leerPacientesDesdeArchivo("Pacientes_24h.txt");

        Hospital hospital = new Hospital();

        for(int tiempoActualMinutos = 1; tiempoActualMinutos <= 1440; tiempoActualMinutos++) {

            if (!listaPacientes.isEmpty() && listaPacientes.getFirst().getTiempoLlegada() == tiempoActualMinutos) {
                Paciente paciente = listaPacientes.removeFirst();
                hospital.registrarPaciente(paciente);
                pacientesAcumulados++;

                if(pacienteCat4 == null && paciente.getCategoria() == 4) { // Obtener primer paciente con categoria 4 para hacer pruebas
                    pacienteCat4 = paciente;
                    tiempoLlegadaCat4 = tiempoActualMinutos;
                }

            }

            if(pacientesAcumulados == 3) {
                for(int i = 0; i < 2; i++) { // Atender dos pacientes inmediatamente
                    Paciente atendido = hospital.atenderSiguiente(tiempoActualMinutos);
                    if(atendido != null && atendido.equals(pacienteCat4)) {
                        tiempoAtencionCat4 = tiempoActualMinutos;
                    }
                }
                pacientesAcumulados = 0; // Reiniciar contador
            } else if(tiempoActualMinutos % 15 == 0) {
                Paciente atendido = hospital.atenderSiguiente(tiempoActualMinutos);
                if(atendido != null && atendido.equals(pacienteCat4)) {
                    tiempoAtencionCat4 = tiempoActualMinutos;
                }
            }
        }

        System.out.println("Tiempo de atencion de paciente de categoria 4: " + tiempoAtencionCat4 + " minutos");

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
