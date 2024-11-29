package es.cilusu.redes.practica.socket;

import java.io.*;
import java.net.Socket;

public class cliente {
    public static void main(String[] args) throws IOException {
        Socket cliente = new Socket("172.17.169.139", 49200);
        DataInputStream entrada = new DataInputStream(cliente.getInputStream());
        PrintStream salida = new PrintStream(cliente.getOutputStream());
        salida.println(2);
        salida.close();
        entrada.close();
        cliente.close();
    }
}
