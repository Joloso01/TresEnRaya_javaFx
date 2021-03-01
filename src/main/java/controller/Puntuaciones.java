package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import model.Jugador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Puntuaciones {
    ObservableList<Jugador> jugadorList;
    List<Jugador> listaJugadores = new ArrayList<>();

    private String player1Name;
    private String player2Name;

    TableView puntuacionesLista;
    private boolean encontrado;

    {
        try {
            puntuacionesLista = FXMLLoader.load(getClass().getResource("/fxml/Puntuaciones.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addJugador(String nombre){
        jugadorList.add(new Jugador(nombre));
    }

    public void victoriaJugador(String nombre){
       Jugador jugador= buscarJugador(nombre);
        if (encontrado){
            jugador.setVictorias(jugador.getVictorias()+1);
            jugador.setPartidasJugadas(jugador.getPartidasJugadas()+1);
        }else {
            addJugador(nombre);
            if (encontrado){
                jugador.setVictorias(jugador.getVictorias()+1);
                jugador.setPartidasJugadas(jugador.getPartidasJugadas()+1);
            }
        }
    }

    public void derrotaJugador(String nombre){
        Jugador jugador= buscarJugador(nombre);
        if (encontrado){
            jugador.setDerrotas(jugador.getDerrotas()+1);
            jugador.setPartidasJugadas(jugador.getPartidasJugadas()+1);
        }else {
            addJugador(nombre);
            buscarJugador(nombre);
            if (encontrado){
                jugador.setDerrotas(jugador.getDerrotas()+1);
                jugador.setPartidasJugadas(jugador.getPartidasJugadas()+1);
            }
        }
    }

    public Jugador buscarJugador(String nombre){

        for (Jugador j:jugadorList){
            if (j.getNom().equals(nombre)){
                encontrado = true;
                return j;
            }else {
                encontrado = false;
            }
        }
        return null;
    }

    public void setTableViewData(){
        puntuacionesLista.setItems(jugadorList);
    }

    public void getTableView(){
        jugadorList = (ObservableList<Jugador>) puntuacionesLista.getItems();
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }
}
