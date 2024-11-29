package Ejercicio04;

import java.lang.Runnable;

public class Cliente implements Runnable {
    Bar bar;
    int cañas;

    public Cliente(Bar bar, int cañas) {
        this.bar = bar;
        this.cañas = cañas;
    }

    @Override
    public void run() {
        for(int i = 0; i < cañas; i++){
            try {
                bar.tomarCaña();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
