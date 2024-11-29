package Ejercicio04;

import java.lang.Runnable;

public class Camarero implements Runnable{
    Bar bar;
    public Camarero(Bar bar){
        this.bar = bar;
    }

    @Override
    public void run() {
        while(true){
            bar.reponer();
            try{
                Thread.sleep(2000);
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
       }
    }
}
