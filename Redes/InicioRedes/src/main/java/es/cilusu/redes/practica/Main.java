package es.cilusu.redes.practica;

import javax.sound.midi.Soundbank;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        try {
            //El InetAAddress funciona CASI igual que un Integer
            InetAddress direccion = InetAddress.getLocalHost(); //direccion ahora guarda el local host
            String host = direccion.getHostName(); //host guarda el NOMBRE del host
            InetAddress ip = InetAddress.getByName(host); //Usamos el nombre para sacar la ip con el nombre como parámetro

            System.out.println("Dirección: " + direccion);
            System.out.println("Host: " + host);
            System.out.println("ip: " + ip);

            byte[] address = direccion.getAddress();
            for (int i = 0; i < address.length; i++) {
                if(i==0){
                    System.out.println("Matriz de bytes: " + address[i]);
                } else{
                    System.out.println("                 " + address[i]);
                }
            }

            System.out.println("Google: " + InetAddress.getByName("www.google.com"));

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}