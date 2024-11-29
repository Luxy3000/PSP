package es.cilusu.redes.practica.socket;

import java.io.*;
import java.net.*;

public class servidor {
    static InputStream entrada = null;
    static OutputStream salida = null;
    static int puerto = 4444;
    public static <SocketTCPServer> void main(String[] args) throws IOException {
        try(ServerSocket ss = new ServerSocket(puerto)){
            System.out.println("Esperando conexión...");
            Socket cliente = ss.accept();
            System.out.println("Cliente estableciendo conexión...");
            entrada = cliente.getInputStream();
            salida = cliente.getOutputStream();
            System.out.println("Conexión con Cliente establecida");
            salida.write(1);
            salida.flush();
            System.out.println("Cerrando conexión...");
            salida.close();
            entrada.close();
            cliente.close();
            System.out.println("Conexiones cerradas...");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
