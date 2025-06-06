import java.util.*;

public class GeneradorPacientes {

    private String[] nombres = {"Martin", "Sergio", "Joakin", "Pergio", "Oliver", "Jair"
            , "Terence", "Nano", "Jalil", "Jalib", "Jacobiano"};

    private String[] apellidos = {"Correa", "Pinto", "Mac Auliffe", "Olivares", "Landero"
            , "Novoa", "Olguin", "Tapia", "Lopez", "Turbes"};

    private Set<String> runSet = new HashSet<>();
    private int contadorId = 1;
    private List<Paciente> pacientes = new ArrayList<>();
    private Random random = new Random();

    public List<Paciente> generarPacientes(int n) {
        for (int i = 0; i < n; i++) {
            String nombre = nombres[random.nextInt(nombres.length - 1)];
            String apellido = apellidos[random.nextInt(apellidos.length - 1)];
            String id = generarRUTUnico();
            int categoria = generarCategoria();
            Stack<String> historialCambios = new Stack<>();

            Paciente paciente = new Paciente(nombre, apellido, id, categoria, "en_espera", historialCambios);

            pacientes.add(paciente);
        }
        return pacientes;
    }

    public String generarRUT() {
        int min = 7000000;
        int max = 26000000;
        int numero = min + random.nextInt(max - min + 1); // RUT sin dígito verificador
        String numeroStr = String.valueOf(numero);

        // Calcular dígito verificador
        int suma = 0;
        int factor = 2;
        for(int i = numeroStr.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(numeroStr.charAt(i)) * factor;
            if (factor == 7) {
                factor = 2;
            } else {
                factor++;
            }
        }
            int digitoVerificadorInt = 11 - (suma % 11);
            String digitoVerificador;
            if (digitoVerificadorInt == 11) {
                digitoVerificador = "0";
            } else if (digitoVerificadorInt == 10) {
                digitoVerificador = "k";
            } else {
                digitoVerificador = String.valueOf(digitoVerificadorInt);
            }
            return numeroStr + "-" + digitoVerificador;
        }

    public String generarRUTUnico() {
        while(true) {
            String run = generarRUT();
            if(runSet.add(run)) {
                return run;
            }
        }
    }

    public int generarCategoria() { // Genera categorías según probabilidad
        int probabilidad = random.nextInt(100);
        if(probabilidad < 10) {
            return 1;
        } else if(probabilidad < 25) {
            return 2;
        } else if(probabilidad < 43) {
            return 3;
        } else if(probabilidad < 70) {
            return 4;
        } else {
            return 5;
        }
    }

}
