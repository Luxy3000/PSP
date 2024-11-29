package Ejercicio04;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int nCañas = 20;
        int nBarriles = 2;

        Bar bar = new Bar(nCañas);

        Thread camarero = new Thread(new Camarero(bar));
        camarero.start();

        for (int i = 0; i < nBarriles; i++) {
            // Cada barril tendrá clientes que se sirvan hasta agotarlo
            Thread cliente = new Thread(new Cliente(bar, nCañas));
            cliente.start();

            // Esperar a que el cliente termine para que el siguiente pueda empezar
            try {
                cliente.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Una vez que el cliente ha terminado, se avisa al camarero para que reponga el barril
            synchronized (bar) {
                bar.reponer();
            }
        }

        camarero.interrupt();
    }
}
