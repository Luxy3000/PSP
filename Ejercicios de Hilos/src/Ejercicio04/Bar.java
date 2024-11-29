package Ejercicio04;

public class Bar {
    private int nCañas;
    private int cañasServidas;
    /***
     * Atributos para controlar las cañas que se sirven de cada barril
     * y cuando debe ser repuesto uno nuevo
     *
     * @param nCañas Nº de cañas que pueden ser servidas de un barril
     */
    public Bar (int nCañas){
        this.nCañas = nCañas;
        this.cañasServidas = 0;
    }

    public synchronized void tomarCaña() throws InterruptedException {
        if(cañasServidas == nCañas){
            notifyAll();
        }
        while (cañasServidas >= nCañas){
            wait();
            System.out.println("Esperando a nuevo barril");
        }
        cañasServidas++;
        servirCaña();
    }

    public synchronized void reponer(){
        while (cañasServidas < nCañas){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        cañasServidas = 0;
        cambiarBarril();
        notifyAll();
    }

    private void servirCaña() {
        System.out.println("Caña servida. Cañas servidas: " +cañasServidas);
    }

    private void cambiarBarril() {
        System.out.println("Barril repuesto.");
    }
}
