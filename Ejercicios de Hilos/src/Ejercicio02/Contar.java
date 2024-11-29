package Ejercicio02;

public class Contar {
    public static void main(String[] args) {
        Contador contador = new Contador();
        Thread[] hilos = new Thread[10];
        // Crear y lanzar 10 hilos
        for (int i = 0; i < 10; i++) {
           hilos[i] = new Thread(new HiloCuenta(contador));
           hilos[i].start();
        }

        //Esperar a que todos terminen
        for (int i = 0; i < 10; i++) {
            try{
                hilos[i].join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Todos los hilos han terminado");
    }
}
