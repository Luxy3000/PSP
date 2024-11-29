package es.cilusu.redes.practica.ejercicio01;

import java.io.*;
import java.net.*;

public class servidor {
    static int puerto = 5000;
    static ServerSocket servidor;
    static Socket cliente;

    static BufferedReader entrada; //leer mensajes
    static PrintWriter salida; //enviar mensajes
    static InputStreamReader isr; //convertir bytes a caracteres

    public static void main(String[] args) {
        try {
            iniciar();
            cliente = acceptar();
            System.out.println("Cliente conectado: " + cliente);

            manejarCliente(cliente);

            cerrar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void iniciar()throws IOException{
        servidor = new ServerSocket(puerto);
        System.out.println("Servidor esperando conexión...");
    }
    public static Socket acceptar() throws IOException {
        return servidor.accept();
    }
    public static void cerrar() throws IOException{
        servidor.close();
        System.out.println("Conexión cerrada.");
    }

    public static void manejarCliente(Socket socket) throws IOException {
        isr = new InputStreamReader(socket.getInputStream());
        entrada = new BufferedReader(isr);

        salida = new PrintWriter(socket.getOutputStream(), true);

        String mensaje; //recibir mensaje del client
        while((mensaje = entrada.readLine()) != null){
            System.out.println("Cliente: " + mensaje); //imprimir el mensaje del client
            if(mensaje.equals("salir")){
                System.out.println("Conexión cerrada por el cliente.");
                break;
            }

            salida.println("Recibido."); //mandar un mensaje de conf
        }
    }

}
