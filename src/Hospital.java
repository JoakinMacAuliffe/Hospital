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

        areasAtencion.put("SAPU", new AreaAtencion("urgencia_adulto", 100));
        areasAtencion.put("infantil", new AreaAtencion("infantil", 100));
        areasAtencion.put("urgencia_adulto", new AreaAtencion("urgencia_adulto", 100));

    }

    public void registrarPaciente(Paciente paciente) {
        colaAtencion.add(paciente);
        pacientesTotales.put(paciente.getId(), paciente);
        switch(paciente.getArea()) { // Añadir paciente a su respectiva area
            case "SAPU":
                areasAtencion.get("SAPU").ingresarPaciente(paciente);
            case "infantil":
                areasAtencion.get("infantil").ingresarPaciente(paciente);
            case "urgencia_adulto":
                areasAtencion.get("urgencia_adulto").ingresarPaciente(paciente);
        }
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
        Paciente p = colaAtencion.remove();
//        areasAtencion.put(p.getId(), new AreaAtencion(p.getArea(), ));
        pacientesAtendidos.add(p);
        return null;
    }



}
