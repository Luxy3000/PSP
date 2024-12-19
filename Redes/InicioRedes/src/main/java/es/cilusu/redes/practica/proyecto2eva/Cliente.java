package es.cilusu.redes.practica.proyecto2eva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 22222;

    public static void main(String[] args) {
        try(Socket cliente = new Socket(HOST, PUERTO);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
            ){
            System.out.println(entrada.readLine());
            String respuesta;

            do {
                respuesta = entrada.readLine();

                if (respuesta == null) {
                    System.out.println("Conexión cerrada por el servidor.");
                    break;
                }

                if (respuesta.startsWith("Introduce")) {
                    System.out.print(respuesta + " ");
                    int numero = sc.nextInt();
                    salida.println(numero);
                } else {
                    System.out.println(respuesta);
                }
            } while (!respuesta.equals("FIN") && !respuesta.startsWith("Juego acabado.") && !respuesta.startsWith("Se han agotado tus intentos."));
        } catch (IOException e){
            System.out.println("Error de conexión con el servidor: " + e.getMessage());
        }
    }

}
