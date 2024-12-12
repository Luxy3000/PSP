package es.cilusu.redes.practica.ejercicio03;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class GestorPeticiones implements Runnable {

    private final Socket cliente;

    public GestorPeticiones(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try(OutputStream entrada = cliente.getOutputStream();
            PrintWriter salida = new PrintWriter(entrada, true)){
            salida.println("Conexión establecida con el servidior.");

            Random random = new Random();
            int numero = random.nextInt(10) + 1;
            System.out.println("Número generado para cliente: " + numero);

            Thread.sleep(numero * 1000);

            salida.println("El servidor esperó " + numero + " segundos.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                cliente.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
