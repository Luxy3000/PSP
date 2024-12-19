package es.cilusu.redes.practica.proyecto2eva;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {
    private static final int PUERTO = 12345;
    private static final int MAX_JUGADORES = 5;

    public static void main(String[] args) {
        System.out.println("Servidor iniciado...");
        Random random = new Random();
        int miNumero = random.nextInt(10) + 1;

        JuegoCompartido juego = new JuegoCompartido(miNumero);

        try(ServerSocket serverSocket = new ServerSocket(PUERTO)){
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            int clientCont = 0;

            while(clientCont < MAX_JUGADORES){
                Socket cliente = serverSocket.accept();
                clientCont++;
                System.out.println("Cliente conectado: Jugador " + clientCont);

                GestorCliente gestor = new GestorCliente(cliente, juego, clientCont);
                new Thread(gestor).start();
            }
            System.out.println("Se ha alcanzado el máximo de jugadores. No se aceptan más conexiones.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
