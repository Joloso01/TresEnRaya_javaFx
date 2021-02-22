package controller;

import java.util.ArrayList;
import java.util.List;

public class Puntuaciones {
    List<Jugador> listaDeJugadores = new ArrayList<>();

    public void addJugador(Jugador jugador){
        listaDeJugadores.add(jugador);
    }

    public void mostrarJugadores(){
        listaDeJugadores.forEach(System.out::println);
    }


}
