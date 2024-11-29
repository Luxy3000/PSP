package es.cilusu.redes.practica.ejercicio01;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class cliente {
    static String host = "localhost";
    static int puerto = 5000;
    static Socket socket;


    static InputStreamReader isr;
    static BufferedReader entrada;
    static PrintWriter salida;

    public static void main(String[] args) {
        try{
            socket = conectarServer();
            System.out.println("Conectado al servidor.");

            isr = new InputStreamReader(socket.getInputStream());
            entrada = new BufferedReader(isr);
            salida = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);
            String mensaje;
            while (true){
                System.out.println("Ingrese mensaje: ");
                mensaje = sc.nextLine();

                salida.println(mensaje); //enviar un mensaje
                if(mensaje.equals("salir")){
                    socket.close();
                    break;
                }
                String respuesta = entrada.readLine(); //recoger el mensaje del server
                System.out.println("Servidor: " + respuesta); //imprimir el mensaje
            }

            socket.close();
            System.out.println("Servidor desconectado.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Socket conectarServer() throws IOException {
        return new Socket(host, puerto);
    }
}
