package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Jugador;

import java.io.IOException;

public class Puntuaciones {

    private String player1Name;
    private String player2Name;
    private TableView<Jugador> jugadorTableView= null;
    private ObservableList<Jugador> puntuacionesLista;
    ObservableList columnasTabla;
    private boolean encontrado;

    TableColumn<Jugador, String> jugador = new TableColumn<Jugador, String>("Jugador");
    TableColumn<Jugador, Integer> victorias = new TableColumn<>("Victorias");
    TableColumn<Jugador, Integer> derrotas = new TableColumn<Jugador, Integer>("Derrotas");
    TableColumn<Jugador, Integer> empates = new TableColumn<>("Empates");
    TableColumn<Jugador, Integer> totalPartidas = new TableColumn<>("Total Partidas");


    public Puntuaciones() {


        try {
            jugadorTableView = FXMLLoader.load(getClass().getResource("/fxml/Puntuaciones.fxml"));
            puntuacionesLista = jugadorTableView.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jugador.setCellValueFactory(new PropertyValueFactory<>("nom"));
        victorias.setCellValueFactory(new PropertyValueFactory<>("victorias"));
        derrotas.setCellValueFactory(new PropertyValueFactory<>("derrotas"));
        empates.setCellValueFactory(new PropertyValueFactory<>("empates"));
        totalPartidas.setCellValueFactory(new PropertyValueFactory<>("partidasJugadas"));
    }


    public void addJugador(String nombre){
        puntuacionesLista.add(new Jugador(nombre));

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

        if (nombre.equals(player1Name)){
            player1Name="";
        }else {
            player2Name="";
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

        if (nombre.equals(player1Name)){
            player1Name="";
        }else {
            player2Name="";
        }
    }

    public void EmpateJugador(String nombre){
        Jugador jugador= buscarJugador(nombre);
        if (encontrado){
            jugador.setEmpates(jugador.getEmpates()+1);
            jugador.setPartidasJugadas(jugador.getPartidasJugadas()+1);

        }else {
            addJugador(nombre);
            jugador = buscarJugador(nombre);
            if (encontrado){
                jugador.setEmpates(jugador.getEmpates()+1);
                jugador.setPartidasJugadas(jugador.getPartidasJugadas()+1);
            }

        }

        if (nombre.equals(player1Name)){
            player1Name="";
        }else {
            player2Name="";
        }
    }

    public Jugador buscarJugador(String nombre){
        for (Jugador j:puntuacionesLista){
            if (j.getNom().equals(nombre)){
                encontrado = true;
                return j;
            }else {
                encontrado = false;
            }
        }
        return null;
    }

    public ObservableList<Jugador> getPuntuacionesLista() {
        return puntuacionesLista;
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
