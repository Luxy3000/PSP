package es.cilusu.redes.practica.proyecto2eva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GestorCliente  implements Runnable{
    private final Socket cliente;
    private final JuegoCompartido juego;
    private final int jugadorId;
    private static final int MAX_INTENTOS = 5;


    public GestorCliente(Socket cliente, JuegoCompartido juego, int jugadorId) {
        this.cliente = cliente;
        this.juego = juego;
        this.jugadorId = jugadorId;
    }

    @Override
    public void run() {
        try(BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)){
            salida.println("Bienvenido Jugador " + jugadorId + ", tienes " + MAX_INTENTOS + " intentos para adivinar el número.");
            int intentosRest = MAX_INTENTOS;

            while(intentosRest > 0 && !juego.isJuegoEnd()){
                salida.println("Introduce tu número: ");
                String input = entrada.readLine();

                try{
                    int numeroIntentos = Integer.parseInt(input);
                    String respuesta = juego.comprobar(jugadorId, numeroIntentos);
                    salida.println(respuesta);
                    if(respuesta.contains("¡Felicidades")){
                        break;
                    }

                    intentosRest--;

                    if(intentosRest > 0 && !juego.isJuegoEnd()){
                        salida.println("Te quedan " + intentosRest + " intentos.");
                    }
                } catch(NumberFormatException e){
                    salida.println("Por favor, introduce un número válido.");
                }
            }

            if (!juego.isJuegoEnd()) {
                salida.println("Se han agotado tus intentos. ¡Mejor suerte la próxima vez!");
            } else {
                salida.println("Juego acabado. Ganador Jugador " + juego.getJugadorWin());
            }

            salida.println("FIN");

            cliente.close();
        }catch(IOException e){
            System.out.println("Error con el jugador " + jugadorId + ": " + e.getMessage());
        }
    }
}
