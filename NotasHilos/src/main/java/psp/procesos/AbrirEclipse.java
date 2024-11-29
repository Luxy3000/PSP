package psp.procesos;

import java.io.IOException;

public class AbrirEclipse {
    public static void main(String[] args) {
        try {
            // Reemplaza la ruta con la ruta completa de Eclipse en tu computadora
            String rutaEclipse = "C:\\ruta\\a\\eclipse\\eclipse.exe";

            // Crear el ProcessBuilder con la ruta al ejecutable
            ProcessBuilder processBuilder = new ProcessBuilder(rutaEclipse);

            // Iniciar el proceso
            processBuilder.start();
            System.out.println("Eclipse se está abriendo...");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
