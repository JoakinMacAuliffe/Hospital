import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorPacientes {

    private String[] nombres = {"Martin", "Sergio", "Joakin", "Pergio", "Oliver", "Jair", "Terence", "Nano"};
    private String[] apellidos = {"Correa", "Pinto", "Mac Auliffe", "Olivares", "Landero", "Novoa", "Olguin", "Tapia", "Lopez"};
    private int contadorId = 1;

    private Random random = new Random();


    private List<Paciente> pacientes = new ArrayList<>();

    GeneradorPacientes(int n) {
        for (int i = 0; i < n; i++) {
            String nombre = generarNombre();
            String id = generarRUN();
            Paciente paciente = new Paciente();
        }
    }

    public String generarNombre() {
        String nombre = nombres[random.nextInt(nombres.length - 1)] + " " + apellidos[random.nextInt(apellidos.length - 1)];
        System.out.println(nombre);
        return nombre;
    }

    public String generarRUN() {
        int min = 7000000;
        int max = 26000000;
        int numero = min + random.nextInt(max - min + 1); // RUT sin dígito verificador
        String numeroStr = String.valueOf(numero);

        // Calcular dígito verificador
        int suma = 0;
        int factor = 2;
        for (int i = numeroStr.length() - 1; i >= 0; i--) {
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
    }
