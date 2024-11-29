package Ejercicio03.bar_cañas_cerrojo;

public class Main {
    public static void main(String[] args) {
        Bar bar = new Bar(5);

        for(int i = 0; i < 10; i++){
            Cliente cliente = new Cliente(bar);
            new Thread(cliente, "Cliente " + (i +1)).start();
        }
    }
}
