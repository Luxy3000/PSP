package es.cilusu.redes.practica.ejercicio02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class servidor {
    static int puerto = 8080;
    static ServerSocket servidor;
    static Socket cliente;

    static BufferedReader entrada;
    static PrintWriter salida;
    static InputStreamReader leer;

    public static void main(String[] args) {
        try{
            iniciar();
            cliente = servidor.accept();
            System.out.println("Cliente conectado: " + cliente);

            manejarCliente(cliente);

            cerrarServidor();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void iniciar() throws IOException {
        servidor = new ServerSocket(puerto);
        System.out.println("Servidor iniciado.");
        System.out.println("Servidor esperando conexión...");
    }

    public static void cerrarServidor() throws IOException {
        servidor.close();
        System.out.println("Servidor cerrado.");
    }
    public static Socket acceptarCLiente() throws IOException {
        return servidor.accept();
    }

    public static void manejarCliente(Socket cliente) throws IOException {
        leer = new InputStreamReader(cliente.getInputStream());
        entrada = new BufferedReader(leer);
        salida = new PrintWriter(cliente.getOutputStream(), true);

        String ruta = entrada.readLine();
        if (ruta != null) {
            System.out.println("Ruta: " + ruta);
            FileReader fr = new FileReader(ruta);
            BufferedReader ler = new BufferedReader(fr);
            String mensaje;
            while ((mensaje = ler.readLine() ) != null) {
                salida.println(mensaje);
            }
        }
    }
}
