package Ejercicio01;

public class HiloCuenta implements Runnable{
    private Contador contador;

    public HiloCuenta(Contador contador) {
        this.contador = contador;
    }

    @Override
    public void run() {
        synchronized (contador) {
            contador.incrementar();
            System.out.println(contador.valor());
        }
    }
}
