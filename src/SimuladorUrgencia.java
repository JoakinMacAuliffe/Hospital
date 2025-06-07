public class SimuladorUrgencia {

    long timestamp = 0;
    private int pacientesAcumulados = 0;

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

//    public Paciente ingresarPaciente() {
//
//    }
//

}
