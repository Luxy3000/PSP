package Ejercicio03.bar_cañas_cerrojo;

import java.util.concurrent.locks.*;

public class Bar {
    private final int aforoMaximo;
    private int clienteDentro = 0;
    private Lock cerrojoClientes;
    private final Condition esperarEntrar;

    public Bar (int aforo){
        this.aforoMaximo = aforo;
        this.cerrojoClientes = new ReentrantLock();
        this.esperarEntrar = cerrojoClientes.newCondition();
    }

    public void entrar(){
        cerrojoClientes.lock();
        try {
            while( aforoMaximo == clienteDentro){
                System.out.println(Thread.currentThread().getName() + " esperando para entrar.");
                esperarEntrar.await();
            }
            //Cliente entra
            clienteDentro++;
            abrirPuertaE();
            System.out.println(Thread.currentThread().getName() + " ha entrado. Clientes dentro: "+ clienteDentro);

        } catch(InterruptedException e){
            e.printStackTrace();
        } finally {
            cerrojoClientes.unlock();
        }

    }

    public void salir(){
        cerrojoClientes.lock();
        try{
            //Cliente sale
            clienteDentro--;
            abrirPuertas();
            System.out.println(Thread.currentThread().getName() + " ha salido. Clientes dentro: " + clienteDentro);

            //Notificar a los que quieren entrar
            if(clienteDentro == aforoMaximo){
                esperarEntrar.signal();
            }
        }finally {
            cerrojoClientes.unlock();
        }
    }

    private void abrirPuertaE(){
        System.out.println("La puerta de entrada se abre.");
    }

    private void abrirPuertas(){
        System.out.println("La puerta de salida se abre.");
    }
}
