import java.io.*;
import java.util.*;

public class SimuladorUrgencia {

    private int pacientesAcumulados = 0;
    private List<Paciente> listaPacientes;
    private int pacientesAtendidos;
    private int[] cantidadPorCategoria = new int[6];
    private long[] sumaTiemposPorCategoria = new long[6];
    private int[] atendidosPorCategoria = new int[6];
    private List<Paciente> pacientesExcedenTiempo = new ArrayList<>();



    Paciente pacienteCat4 = null;
    private long tiempoLlegadaCat4 = -1;
    private long tiempoAtencionCat4 = -1;

    public void simular() {

        // Modificar este valor para cambiar la duracion de la simulacion, 1440 minutos son 24 horas.
        int duracionSimulacionMinutos = 10080;

        GeneradorPacientes generadorPacientes = new GeneradorPacientes();

//        listaPacientes = leerPacientesDesdeArchivo("Pacientes_24h.txt");
        listaPacientes = generadorPacientes.generarPacientes(1008);

        Hospital hospital = new Hospital();

        for(int tiempoActualMinutos = 1; tiempoActualMinutos <= duracionSimulacionMinutos; tiempoActualMinutos++) {

            System.out.println("Minuto: " + tiempoActualMinutos);

            if (!listaPacientes.isEmpty() && listaPacientes.getFirst().getTiempoLlegada() == tiempoActualMinutos) {
                Paciente paciente = listaPacientes.removeFirst();
                hospital.registrarPaciente(paciente);
                cantidadPorCategoria[paciente.getCategoria()]++;
                pacientesAcumulados++;

                if(pacienteCat4 == null && paciente.getCategoria() == 4) { // Obtener primer paciente con categoria 4 para hacer pruebas
                    pacienteCat4 = paciente;
                    tiempoLlegadaCat4 = tiempoActualMinutos;
                }

            }

            if(pacientesAcumulados == 3) {
                for(int i = 0; i < 2; i++) { // Atender dos pacientes inmediatamente
                    Paciente atendido = hospital.atenderSiguiente(tiempoActualMinutos);
                    if(!atendido.getEsperaDentroDelTiempo()) {
                        pacientesExcedenTiempo.add(atendido);
                    }
                    pacientesAtendidos++;

                    // Calcular tiempos de atencion por categoria
                    int categoria = atendido.getCategoria();
                    long tiempoEspera = atendido.tiempoEsperaActual(tiempoActualMinutos);
                    sumaTiemposPorCategoria[categoria] += tiempoEspera;
                    atendidosPorCategoria[categoria]++;

                    System.out.println("Paciente " + atendido.getId() + " atendido en " + atendido.tiempoEsperaActual(tiempoActualMinutos) + " minutos.");
                    if(atendido.equals(pacienteCat4)) {
                        tiempoAtencionCat4 = tiempoActualMinutos;
                    }
                }
                pacientesAcumulados = 0; // Reiniciar contador
            } else if(tiempoActualMinutos % 15 == 0) {
                Paciente atendido = hospital.atenderSiguiente(tiempoActualMinutos);
                if(!atendido.getEsperaDentroDelTiempo()) {
                    pacientesExcedenTiempo.add(atendido);
                }
                pacientesAtendidos++;
                System.out.println("Paciente " + atendido.getId() + " atendido en " + atendido.tiempoEsperaActual(tiempoActualMinutos) + " minutos.");
                if(atendido.equals(pacienteCat4)) {
                    tiempoAtencionCat4 = tiempoActualMinutos;
                }
            }
        }
        System.out.println("-----------------------");

        System.out.println("Pacientes por categoria: ");

        for(int i = 1; i <= cantidadPorCategoria.length-1; i++) {
            System.out.println("Categoria " + i + ": " + cantidadPorCategoria[i]);
        }

        System.out.println("\nPromedio de tiempos de espera por categoria:");
        for(int i = 1; i <= 5; i++) {
            if(atendidosPorCategoria[i] > 0) {
                double promedio = (double) sumaTiemposPorCategoria[i] / atendidosPorCategoria[i];
                System.out.println("Categoria " + i + ": " + ((int)(promedio * 100)) / 100.0 + " minutos");
            }
        }

        System.out.println("\nPacientes que excedieron su tiempo de espera: " + pacientesExcedenTiempo.size());
        System.out.println("\nPacientes atendidos: " + pacientesAtendidos);
        System.out.println("\nTiempo de atencion de primer paciente de categoria 4: " + tiempoAtencionCat4 + " minutos");

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
