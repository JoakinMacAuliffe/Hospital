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
        areasAtencion.get(paciente.getArea()).ingresarPaciente(paciente);
        System.out.println("Paciente " + paciente.getId() + " registrado en " + paciente.getArea());
        //busca el area de atencion en el mapa y agrega al paciente a la espera
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

    public Paciente atenderSiguiente(long timeStamp) {

        if(!colaAtencion.isEmpty()) {

            PriorityQueue<Paciente> copia = new PriorityQueue<>();

            while(!colaAtencion.isEmpty()){

                Paciente paciente = colaAtencion.poll();
                long time = paciente.tiempoEsperaActual(timeStamp);

                switch (paciente.getCategoria()){ //revisa si alguno de los pacientes de la cola ha esperado demasiado

                    case 2:
                        if(time >= 30) paciente.setEspera();
                        break;
                    case 3:
                        if(time >= 90) paciente.setEspera();
                        break;
                    case 4:
                        if(time >= 180) paciente.setEspera();
                    case 5:
                    default:
                        break;
                }

                copia.add(paciente);
            }

            colaAtencion = copia;

            Paciente paciente = colaAtencion.poll();
            if (paciente == null) {
                System.out.println("Error: Cola de atencion vacia tras reconstrucción.");
                return null;
            }
            AreaAtencion areaAtencion = areasAtencion.get(paciente.getArea());
            if (areaAtencion != null && (!areaAtencion.estaSaturada())) {
                // Si el area del paciente existe y no esta saturada
                pacientesAtendidos.add(paciente);
                paciente.setEstado("en_atencion");
                return areaAtencion.atenderPaciente();

            } else {
                System.out.println("Error: Area de atencion invalida o saturada.");
                colaAtencion.add(paciente);
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

        Stack<String> a = new Stack<>();
        a.push("no");

        Paciente p1 = new Paciente("hola", "juan", "1", 3, 1, "espera", "SAPU", a);
        Paciente p2 = new Paciente("chao", "lol", "3", 2, 99, "espera", "SAPU", a);

        hospital.registrarPaciente(p1);
        hospital.registrarPaciente(p2);

        // Imprimamos el estado antes de atender
        System.out.println("Estado inicial:");
        System.out.println("p1: categoria=" + p1.getCategoria() + ", sobrepasoEspera=" + p1.getEsperaDentroDelTiempo());
        System.out.println("p2: categoria=" + p2.getCategoria() + ", sobrepasoEspera=" + p2.getEsperaDentroDelTiempo());

        // Atendemos en t=100
        System.out.println("\nAtendiendo en t=100:");
        System.out.println(hospital.atenderSiguiente(100).getNombre());


    }

}
