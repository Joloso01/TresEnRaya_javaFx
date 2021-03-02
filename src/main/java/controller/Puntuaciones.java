package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import model.Jugador;

import java.io.IOException;

public class Puntuaciones {

    private String player1Name;
    private String player2Name;

    TableView<Jugador> puntuacionesLista;
    private boolean encontrado;

    {
        try {
            puntuacionesLista = FXMLLoader.load(getClass().getResource("/fxml/Puntuaciones.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    javafx.scene.control.TableColumn<Jugador,String> coljugador;
    javafx.scene.control.TableColumn<Jugador,Integer> colvictorias;
    javafx.scene.control.TableColumn<Jugador,Integer> colderrotas;
    javafx.scene.control.TableColumn<Jugador,Integer> colempates;
    javafx.scene.control.TableColumn<Jugador,Integer> coltotal_partidas;

    public void addJugador(String nombre){
        puntuacionesLista.getItems().add(new Jugador(nombre));
        puntuacionesLista.setEditable(true);
    }

    public void victoriaJugador(String nombre){
       Jugador jugador= buscarJugador(nombre);
        if (encontrado){
            jugador.setVictorias(jugador.getVictorias()+1);
            jugador.setPartidasJugadas(jugador.getPartidasJugadas()+1);
        }else {
            addJugador(nombre);
            jugador = buscarJugador(nombre);
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
            jugador = buscarJugador(nombre);
            if (encontrado){
                jugador.setDerrotas(jugador.getDerrotas()+1);
                jugador.setPartidasJugadas(jugador.getPartidasJugadas()+1);
            }
        }
    }

    public Jugador buscarJugador(String nombre){

        for (Jugador j:puntuacionesLista.getItems()){
            if (j.getNom().equals(nombre)){
                encontrado = true;
                return j;
            }else {
                encontrado = false;
                return null;
            }
        }
        return null;
    }

    public void addLinea(String nombre){
        Jugador jugador = buscarJugador(nombre);


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
