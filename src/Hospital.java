import java.util.*;

public class Hospital {

    private Map<String, Paciente> pacientesTotales;
    private PriorityQueue<Paciente> colaAtencion;
    private Map<String, AreaAtencion> areasAtencion; // String es la id del paciente
    private List<Paciente> pacientesAtendidos;

    Hospital() {

        pacientesTotales = new HashMap<>();
        colaAtencion = new PriorityQueue<>();
        areasAtencion = new HashMap<>();
        pacientesAtendidos = new ArrayList<>();

        areasAtencion.put("SAPU", new AreaAtencion("SAPU", 100));
        areasAtencion.put("infantil", new AreaAtencion("infantil", 100));
        areasAtencion.put("urgencia_adulto", new AreaAtencion("urgencia_adulto", 100));

    }

    public void registrarPaciente(Paciente paciente) {
        colaAtencion.add(paciente);
        pacientesTotales.put(paciente.getId(), paciente);
        colaAtencion.add(paciente);
    }

    public void reasignarCategoria(String id, int nuevaCategoria) {
        if(nuevaCategoria > 0 && nuevaCategoria <= 5) {
            Paciente paciente = pacientesTotales.get(id);
            if (paciente != null) {
                colaAtencion.remove(paciente);
                int categoriaAnterior = paciente.getCategoria();
                paciente.setCategoria(nuevaCategoria);
                paciente.registrarCambio("Cambio de categoria de C" + categoriaAnterior + " a C" + paciente.getCategoria());
                colaAtencion.add(paciente);
            }
        }
    }

    public Paciente atenderSiguiente() {
        if(!colaAtencion.isEmpty()) {

            Paciente paciente = colaAtencion.poll();
            AreaAtencion areaAtencion = areasAtencion.get(paciente.getArea());
            if (areaAtencion != null && (!areaAtencion.estaSaturada())) {
                // Si el area del paciente existe y no esta saturada
                pacientesAtendidos.add(paciente);
                paciente.setEstado("en_atencion");
                return paciente;
            } else {
                System.out.println("Error: Area de atencion invalida.");
            }
        } else {
            System.out.println("Error: Cola de atencion vacia.");
        }
        return null;
    }

    public List<Paciente> getPacientesPorCategoria(int categoria) {
        List<Paciente> pacientesPorCategoria = new ArrayList<>();
        for(Map.Entry<String, Paciente> entry : pacientesTotales.entrySet()) { // Recorre map mediante un iterador
            if(entry.getValue().getCategoria() == categoria) {
                pacientesPorCategoria.add(entry.getValue());
            }
        }
        return pacientesPorCategoria;
    }

    public AreaAtencion obtenerArea(String nombre) {
        return areasAtencion.get(nombre);
    }

    public static void main(String[] args) {

        GeneradorPacientes generadorPacientes = new GeneradorPacientes();
        Hospital hospital = new Hospital();

//        Paciente p1 = generadorPacientes.generarPaciente();
//        Paciente p2 = generadorPacientes.generarPaciente();

//        hospital.registrarPaciente(p1);
//        hospital.registrarPaciente(p2);

    }

}
