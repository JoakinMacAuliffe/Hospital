import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Hospital {

    private Map<String, Paciente> pacientesTotales;
    private PriorityQueue<Paciente> colaAtencion;
    private Map<String, AreaAtencion> areasAtencion; // String es la id del paciente
    private List<Paciente> pacientesAtendidos;

    private AreaAtencion urgenciaAdulto = new AreaAtencion("urgencia_adulto", 100);
    private AreaAtencion infantil = new AreaAtencion("infantil", 100);
    private AreaAtencion SAPU = new AreaAtencion("SAPU", 100);


    public void registrarPaciente(Paciente paciente) {
        colaAtencion.add(paciente);
        pacientesTotales.put(paciente.getId(), paciente);
    }

    public void reasignarCategoria(String id, int nuevaCategoria) {
        if(nuevaCategoria > 0 && nuevaCategoria <= 5) {
            Paciente paciente = pacientesTotales.get(id);
            if (paciente != null) {
                paciente.setCategoria(nuevaCategoria);
                paciente.registrarCambio("Cambio de categoria.");
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
