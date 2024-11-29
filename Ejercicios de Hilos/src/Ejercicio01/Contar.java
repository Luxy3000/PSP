package Ejercicio01;

public class Contar {
    public static void main(String[] args) {
        Contador contador = new Contador();

        // Crear y lanzar 10 hilos
        for (int i = 0; i < 10; i++) {
           Thread hilo = new Thread(new HiloCuenta(contador));
           hilo.start();
        }
    }
}
