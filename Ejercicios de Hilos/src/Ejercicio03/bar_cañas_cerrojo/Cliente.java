package Ejercicio03.bar_cañas_cerrojo;

import java.lang.Runnable;

public class Cliente implements Runnable {
    private final Bar bar; //Referencia al bar

    public Cliente(Bar bar){
        this.bar = bar;     //Inicializa la referencia
    }

    public void run(){
        bar.entrar();       //Intentar entrar al bar
        try {
            Thread.sleep((long) (Math.random() * 1000)); //Simular tiempo que pasa dentro
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();         //Restaurar el estado de interrupción
        }
        bar.salir();   //Salir del bar
    }
}
