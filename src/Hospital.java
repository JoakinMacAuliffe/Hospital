import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Hospital {

    private Map<String, Paciente> pacientesTotales;
    private PriorityQueue<Paciente> colaAtencion;
    private Map<String, AreaAtencion> areasAtencion; // String es la id del paciente
    private List<Paciente> pacientesAtendidos;

//    private AreaAtencion = new AreaAtencion("SAPU", )

    public Hospital() {
        areasAtencion.put("SAPU", null);
        areasAtencion.put("urgencia_adulto", null);
        areasAtencion.put("infantil", null);
    }

    public void registrarPaciente(Paciente paciente) {
        colaAtencion.add(paciente);
        pacientesTotales.put(paciente.getId(), paciente);
    }

    public void reasignarCategoria(String id, int nuevaCategoria) {
        Paciente paciente = buscarPacienteId(id);
        if(paciente != null) {
            paciente.setCategoria(nuevaCategoria);
            paciente.registrarCambio("Cambio de categoria.");
        }
    }

    public Paciente atenderSiguiente() {
        Paciente p = colaAtencion.remove();
//        areasAtencion.put(p.getId(), new AreaAtencion(p.getArea(), ));
        pacientesAtendidos.add(p);
        return null;
    }

    public Paciente buscarPacienteId(String id) {
        for(Map.Entry<String, Paciente> entry : pacientesTotales.entrySet()) {
            if(entry.getKey().equals(id)) return entry.getValue();
        }

        return null;
    }

    public Paciente bucarPacienteNombre(String nombre) {
        for(Map.Entry<String, Paciente> entry : pacientesTotales.entrySet()) {
            if(entry.getValue().getNombre().equals(nombre)) return entry.getValue();
        }

        return null;
    }



}
