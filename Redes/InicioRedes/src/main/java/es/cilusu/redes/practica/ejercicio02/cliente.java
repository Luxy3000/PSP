package es.cilusu.redes.practica.ejercicio02;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class cliente {
    static String host = "localhost";
    static int port = 8080;
    static Socket socket;

    static InputStreamReader in;
    static BufferedReader entrada;
    static PrintWriter salida;

    public static void main(String[] args) {
        try{
            conectar();

            ruta();

            desconectar();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void conectar() throws IOException {
        socket = new Socket(host, port); //Crea el socket y conecta con el servidor
        System.out.println("Conectado al servidor.");
        salida = new PrintWriter(socket.getOutputStream(), true);
        in = new InputStreamReader(socket.getInputStream()); //contruir el flujo de caracteres
        entrada = new BufferedReader(in); //crear el canal de entrada por el servidor
    }

    public static void desconectar() throws IOException {
        System.out.println("Desconectado del servidor.");
        socket.close(); //Desconecta del servidor
    }

    public static void ruta() throws IOException {
        Scanner sc = new Scanner(System.in); //Entrada de teclado
        String ruta;

        while(true){
            System.out.println("Ingrese la ruta del fichero: ");
            ruta = sc.nextLine(); //guarda la entrada de teclado en una variable

            if("salir".equalsIgnoreCase(ruta)){
                salida.println("salir");
                break;
            }

            salida.println(ruta); //Envía la ruta al servidor
            try{
                recibirDatos();
            } catch(IOException e){
                e.printStackTrace();
                break;
            }
        }
    }

    public static void recibirDatos() throws IOException {
        String datos; //leer lo que envía el server
        System.out.println("Inicio fichero. ");
        while((datos = entrada.readLine()) != null){
            if ("EOF".equals(datos)) {
                System.out.println("Fin del fichero.");
                break;
            }
            System.out.println(datos); //imprime por pantalla lo que ha enviado
        }
    }

}
