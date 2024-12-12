package es.cilusu.redes.practica.ejercicio03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 12345;
        int clienteCont = 1;

        try(ServerSocket serverSocket = new ServerSocket(puerto)){
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (clienteCont < 11){
                Socket cliente = serverSocket.accept();
                System.out.println("Cliente Nº"+ clienteCont + " conectado desde: "+ cliente.getInetAddress());

                GestorPeticiones gestorPeticiones = new GestorPeticiones(cliente);
                Thread hilo = new Thread(gestorPeticiones);
                hilo.start();
                clienteCont++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
