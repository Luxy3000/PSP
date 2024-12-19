package es.cilusu.redes.practica.proyecto2eva;

public class JuegoCompartido {
    private final int numero;
    private boolean juegoEnd;
    private int jugadorWin;

    public JuegoCompartido(int numero) {
        this.numero = numero;
        this.juegoEnd = false;
        this.jugadorWin = -1;
    }

    public synchronized String comprobar(int jugador, int intento){
        if(juegoEnd){
            return "Juego acabado. Ganador Jugador " + jugadorWin;
        }

        if(intento == numero){
            juegoEnd = true;
            jugadorWin = jugador;
            return  "¡Felicidades Jugador " + jugador + "! Has adivinado el número.";
        } else if (intento < numero) {
            return  "El número es mayor";
        } else {
            return  "El número es menor";
        }
    }

    public synchronized boolean isJuegoEnd() {
        return juegoEnd;
    }

    public synchronized int getJugadorWin() {
        return jugadorWin;
    }
}
