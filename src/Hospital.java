import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Hospital {

    private Map<String, Paciente> pacientesTotales;
    private PriorityQueue<Paciente> colaAtencion;
    private Map<String, AreaAtencion> areasAtencion;
    private List<Paciente> pacientesAtendidos;

    public Hospital() {
        areasAtencion.put("SAPU", null);
        areasAtencion.put("urgencia_adulto", null);
        areasAtencion.put("infantil", null);
    }

    public void registrarPaciente(Paciente p) {
        colaAtencion.add(p);
        pacientesTotales.put(p.getId(), p);
    }

    public void reasignarCategoria(String id, int nuevaCategoria) {
        Paciente paciente = buscarPacienteId(id);
        if(paciente != null) {
            paciente.setCategoria(nuevaCategoria);
            paciente.registrarCambio("Cambio de categoria.");
        }
    }

    Paciente atenderSiguiente() {
        Paciente p = colaAtencion.remove();
        switch(p.get)
    }

    public Paciente buscarPacienteId(String id) {
        for(Map.Entry<String, Paciente> entry : pacientesTotales.entrySet()) {
            if(entry.getKey().equals(id)) return entry.getValue();
        }

        return null;
    }

}
