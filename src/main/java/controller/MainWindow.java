package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Jugador;

import java.io.IOException;
import java.util.Optional;

public class MainWindow {

    private Scene scene;
    private Stage stage;
    int turno=1;

    @FXML
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,empezarPartida,btnPuntuacion;

    @FXML
    private MenuItem menuItemClose,menuItemAbout,menuItemCambiar;

    @FXML
    private GridPane grid0;

    @FXML
    private VBox vBox0;

    @FXML
    private RadioButton rBcpuVScpu, rBjVSj, rBjVScpu;



    Puntuaciones puntuaciones = new Puntuaciones();

    private boolean partidaEmpezada=false;

    Optional<String> result;




    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    //metodo asociado a todos los botones del gridPane
    public void boton(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();

            //modo de juego Jugador vs Jugador
            if (rBjVSj.isSelected()){
                if (partidaEmpezada) {
                        if (b.getText() != "X" && b.getText() != "O") {
                            turnoJugador(b);
                            if (turno > 3) comprobarGanador();
                        }
                }
                //modo de juego Jugador vs Bot
            }else if (rBjVScpu.isSelected()){
                if (partidaEmpezada){
                        if (b.getText() != "X" && b.getText() != "O") {
                            turnoJugador(b);
                            if (turno <9) turnoBot();
                            if (turno > 3) comprobarGanadorBot();
                        }

                }
            }
    }

    //metodo para cambiar a la pantalla About.fxml cuando se presiona el boton del menu
    public void clickMenuItemAbout(ActionEvent actionEvent) throws IOException {

        if(menuItemAbout.getText().equals("About")) {
            vBox0.getChildren().remove(grid0);
            VBox temp = FXMLLoader.load(getClass().getResource("/fxml/About.fxml"));
            vBox0.getChildren().add(temp);
            menuItemAbout.setText("Joc");
        }else {
            vBox0.getChildren().remove(1);
            vBox0.getChildren().add(grid0);
            menuItemAbout.setText("About");
        }
    }

    //metodo asociado al boton empezar partida
    public void empezarPartida(ActionEvent actionEvent) {
        //si la partida esta empezada desabilita el boton para que no se pueda empezar una partida encima de otra
        if ((!partidaEmpezada)){

            //comprobar si el radioButton seleccionado es jugador vs jugador o jugador vs maquina
            if (rBjVSj.isSelected() || rBjVScpu.isSelected()){

                if (rBjVSj.isSelected()){
                    turno = 1;
                    btn1.setText("");
                    btn2.setText("");
                    btn3.setText("");
                    btn4.setText("");
                    btn5.setText("");
                    btn6.setText("");
                    btn7.setText("");
                    btn8.setText("");
                    btn9.setText("");
                    partidaEmpezada = true;

                    //enseña una ventana para introducir el nombre del jugador
                    TextInputDialog dialog = new TextInputDialog("jugador1");
                    dialog.setTitle("Nueva partida");
                    dialog.setHeaderText("Introduzca su nombre");
                    dialog.setContentText("nombre:");

                    result = dialog.showAndWait();
                    result.ifPresent(s -> puntuaciones.setPlayer1Name(s));


                    TextInputDialog dialog2 = new TextInputDialog("jugador2");
                    dialog2.setTitle("Nueva partida");
                    dialog2.setHeaderText("Introduzca su nombre");
                    dialog2.setContentText("nombre:");

                    result = dialog.showAndWait();
                    result.ifPresent(s2 -> puntuaciones.setPlayer2Name(s2));

                    //desabilita todos los botones para que no pueda haber conflicto al cambiar de modo en medio de una partida
                    empezarPartida.setDisable(true);
                    rBjVSj.setDisable(true);
                    rBjVScpu.setDisable(true);
                    rBcpuVScpu.setDisable(true);

                }else {

                    turno = 1;
                    btn1.setText("");
                    btn2.setText("");
                    btn3.setText("");
                    btn4.setText("");
                    btn5.setText("");
                    btn6.setText("");
                    btn7.setText("");
                    btn8.setText("");
                    btn9.setText("");
                    partidaEmpezada = true;

                    TextInputDialog dialog = new TextInputDialog("jugador1");
                    dialog.setTitle("Nueva partida");
                    dialog.setHeaderText("Introduzca su nombre");
                    dialog.setContentText("nombre:");

                    Optional<String> result = dialog.showAndWait();
                    result.ifPresent(s -> puntuaciones.setPlayer1Name(s));
                    empezarPartida.setDisable(true);
                    rBjVSj.setDisable(true);
                    rBjVScpu.setDisable(true);
                    rBcpuVScpu.setDisable(true);

                }
            //Modo de juego Bot vs Bot
            }else if (rBcpuVScpu.isSelected()){

                turno = 1;
                btn1.setText("");
                btn2.setText("");
                btn3.setText("");
                btn4.setText("");
                btn5.setText("");
                btn6.setText("");
                btn7.setText("");
                btn8.setText("");
                btn9.setText("");
                partidaEmpezada = true;
                empezarPartida.setDisable(true);
                rBjVSj.setDisable(true);
                rBjVScpu.setDisable(true);
                rBcpuVScpu.setDisable(true);

                while (partidaEmpezada){
                    turnoBot();
                    comprobarGanadorBotvsBot();
                }

            }
        }

    }

    //este metodo hace que se escoja una casilla al azar
    private void turnoBot() {
        boolean puesto=false;
            while (!puesto){
                int casilla = (int) (Math.random() * 9)+1;
                    switch (casilla){
                        case 1:
                            if (!btn1.getText().equals("X") && !btn1.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn1);
                            }
                            break;
                        case 2:
                            if (!btn2.getText().equals("X") && !btn2.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn2);
                            }
                            break;
                        case 3:
                            if (!btn3.getText().equals("X") && !btn3.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn3);

                            }
                            break;
                        case 4:
                            if (!btn4.getText().equals("X") && !btn4.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn4);
                            }
                            break;
                        case 5:
                            if (!btn5.getText().equals("X") && !btn5.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn5);
                            }
                            break;
                        case 6:
                            if (!btn6.getText().equals("X") && !btn6.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn6);
                            }
                            break;
                        case 7:
                            if (!btn7.getText().equals("X") && !btn7.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn7);
                            }
                            break;
                        case 8:
                            if (!btn8.getText().equals("X") && !btn8.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn8);
                            }
                            break;
                        case 9:
                            if (!btn9.getText().equals("X") && !btn9.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn9);
                            }
                            break;
                }
            }
    }

    //metodo para comprobar si el ganador en el modo jugador vs jugador
    public void comprobarGanador(){
        //se comprueba el valor de los botones en horizontal, vertical y diagonal
        if ((btn1.getText().equals("X") && btn2.getText().equals("X") && btn3.getText().equals("X")) ||
                (btn4.getText().equals("X") && btn5.getText().equals("X") && btn6.getText().equals("X")) ||
                (btn7.getText().equals("X") && btn8.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn1.getText().equals("X") && btn4.getText().equals("X") && btn7.getText().equals("X")) ||
                (btn2.getText().equals("X") && btn5.getText().equals("X") && btn8.getText().equals("X")) ||
                (btn3.getText().equals("X") && btn6.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn1.getText().equals("X") && btn5.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn3.getText().equals("X") && btn5.getText().equals("X") && btn7.getText().equals("X"))){

            //victoria jugador 1
            partidaEmpezada=false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Partida finalizada");
            alert.setHeaderText(null);
            alert.setContentText("Ha ganado "+puntuaciones.getPlayer1Name()+ " !");
            alert.showAndWait();
            puntuaciones.victoriaJugador(puntuaciones.getPlayer1Name());
            puntuaciones.derrotaJugador(puntuaciones.getPlayer2Name());
            empezarPartida.setDisable(false);
            rBjVSj.setDisable(false);
            rBjVScpu.setDisable(false);
            rBcpuVScpu.setDisable(false);

            //se comprueba el valor de los botones en horizontal, vertical y diagonal
        }else if ((btn1.getText().equals("O") && btn2.getText().equals("O") && btn3.getText().equals("O")) ||
                (btn4.getText().equals("O") && btn5.getText().equals("O") && btn6.getText().equals("O")) ||
                (btn7.getText().equals("O") && btn8.getText().equals("O") && btn9.getText().equals("O")) ||
                (btn1.getText().equals("O") && btn4.getText().equals("O") && btn7.getText().equals("O")) ||
                (btn2.getText().equals("O") && btn5.getText().equals("O") && btn8.getText().equals("O")) ||
                (btn3.getText().equals("O") && btn6.getText().equals("O") && btn9.getText().equals("O")) ||
                (btn1.getText().equals("O") && btn5.getText().equals("O") && btn9.getText().equals("O")) ||
                (btn3.getText().equals("O") && btn5.getText().equals("O") && btn7.getText().equals("O"))){

            partidaEmpezada=false;
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Partida finalizada");
            alert2.setHeaderText(null);
            alert2.setContentText("Ha ganado "+puntuaciones.getPlayer2Name()+ " !");
            alert2.showAndWait();
            puntuaciones.victoriaJugador(puntuaciones.getPlayer2Name());
            puntuaciones.derrotaJugador(puntuaciones.getPlayer1Name());
            empezarPartida.setDisable(false);
            rBjVSj.setDisable(false);
            rBjVScpu.setDisable(false);
            rBcpuVScpu.setDisable(false);
            //victoria jugador 2

        }else if (turno >9){

            //empate
            partidaEmpezada=false;
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Partida finalizada");
            alert2.setHeaderText(null);
            alert2.setContentText("Ha habido un empate!");
            alert2.showAndWait();
            empezarPartida.setDisable(false);
            rBjVSj.setDisable(false);
            rBjVScpu.setDisable(false);
            rBcpuVScpu.setDisable(false);
            puntuaciones.EmpateJugador(puntuaciones.getPlayer1Name());
            puntuaciones.EmpateJugador(puntuaciones.getPlayer2Name());

        }
    }

    //metodo para comprobar el ganador en el modo jugador vs Bot
    public void comprobarGanadorBot(){
        //se comprueba el valor de los botones en horizontal, vertical y diagonal
        if ((btn1.getText().equals("X") && btn2.getText().equals("X") && btn3.getText().equals("X")) ||
                (btn4.getText().equals("X") && btn5.getText().equals("X") && btn6.getText().equals("X")) ||
                (btn7.getText().equals("X") && btn8.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn1.getText().equals("X") && btn4.getText().equals("X") && btn7.getText().equals("X")) ||
                (btn2.getText().equals("X") && btn5.getText().equals("X") && btn8.getText().equals("X")) ||
                (btn3.getText().equals("X") && btn6.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn1.getText().equals("X") && btn5.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn3.getText().equals("X") && btn5.getText().equals("X") && btn7.getText().equals("X"))){

            //victoria jugador 1
            partidaEmpezada=false;

            //abrir una ventana para enseñar el ganador
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Partida finalizada");
            alert.setHeaderText(null);
            alert.setContentText("Ha ganado "+puntuaciones.getPlayer1Name()+ " !");
            alert.showAndWait();

            //suma una victoria al jugador
            puntuaciones.victoriaJugador(puntuaciones.getPlayer1Name());

            //habilita los botones para poder empezar una nueva partida
            empezarPartida.setDisable(false);
            rBjVSj.setDisable(false);
            rBjVScpu.setDisable(false);
            rBcpuVScpu.setDisable(false);

            //se comprueba el valor de los botones en horizontal, vertical y diagonal
        }else if ((btn1.getText().equals("O") && btn2.getText().equals("O") && btn3.getText().equals("O")) ||
                (btn4.getText().equals("O") && btn5.getText().equals("O") && btn6.getText().equals("O")) ||
                (btn7.getText().equals("O") && btn8.getText().equals("O") && btn9.getText().equals("O")) ||
                (btn1.getText().equals("O") && btn4.getText().equals("O") && btn7.getText().equals("O")) ||
                (btn2.getText().equals("O") && btn5.getText().equals("O") && btn8.getText().equals("O")) ||
                (btn3.getText().equals("O") && btn6.getText().equals("O") && btn9.getText().equals("O")) ||
                (btn1.getText().equals("O") && btn5.getText().equals("O") && btn9.getText().equals("O")) ||
                (btn3.getText().equals("O") && btn5.getText().equals("O") && btn7.getText().equals("O"))){

            //victoria Bot 2
            partidaEmpezada=false;

            //abrir una ventana para enseñar el ganador

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Partida finalizada");
            alert2.setHeaderText(null);
            alert2.setContentText("Ha ganado el BOT !");
            alert2.showAndWait();

            //suma una derrota al jugador
            puntuaciones.derrotaJugador(puntuaciones.getPlayer1Name());
            empezarPartida.setDisable(false);
            rBjVSj.setDisable(false);
            rBjVScpu.setDisable(false);
            rBcpuVScpu.setDisable(false);


        }else if (turno >9){

            //empate
            partidaEmpezada=false;
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Partida finalizada");
            alert2.setHeaderText(null);
            alert2.setContentText("Ha habido un empate!");
            alert2.showAndWait();
            empezarPartida.setDisable(false);
            rBjVSj.setDisable(false);
            rBjVScpu.setDisable(false);
            rBcpuVScpu.setDisable(false);
            puntuaciones.EmpateJugador(puntuaciones.getPlayer1Name());

        }
    }

    //metodo para comprobar el ganador en el modo bot vs bot
    public void comprobarGanadorBotvsBot(){
        //se comprueba el valor de los botones en horizontal, vertical y diagonal
        if ((btn1.getText().equals("X") && btn2.getText().equals("X") && btn3.getText().equals("X")) ||
                (btn4.getText().equals("X") && btn5.getText().equals("X") && btn6.getText().equals("X")) ||
                (btn7.getText().equals("X") && btn8.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn1.getText().equals("X") && btn4.getText().equals("X") && btn7.getText().equals("X")) ||
                (btn2.getText().equals("X") && btn5.getText().equals("X") && btn8.getText().equals("X")) ||
                (btn3.getText().equals("X") && btn6.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn1.getText().equals("X") && btn5.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn3.getText().equals("X") && btn5.getText().equals("X") && btn7.getText().equals("X"))){

            //victoria Bot 1
            partidaEmpezada=false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Partida finalizada");
            alert.setHeaderText(null);
            alert.setContentText("Ha ganado el BOT 1 !");
            alert.showAndWait();
            empezarPartida.setDisable(false);
            rBjVSj.setDisable(false);
            rBjVScpu.setDisable(false);
            rBcpuVScpu.setDisable(false);


            //se comprueba el valor de los botones en horizontal, vertical y diagonal
        }else if ((btn1.getText().equals("O") && btn2.getText().equals("O") && btn3.getText().equals("O")) ||
                (btn4.getText().equals("O") && btn5.getText().equals("O") && btn6.getText().equals("O")) ||
                (btn7.getText().equals("O") && btn8.getText().equals("O") && btn9.getText().equals("O")) ||
                (btn1.getText().equals("O") && btn4.getText().equals("O") && btn7.getText().equals("O")) ||
                (btn2.getText().equals("O") && btn5.getText().equals("O") && btn8.getText().equals("O")) ||
                (btn3.getText().equals("O") && btn6.getText().equals("O") && btn9.getText().equals("O")) ||
                (btn1.getText().equals("O") && btn5.getText().equals("O") && btn9.getText().equals("O")) ||
                (btn3.getText().equals("O") && btn5.getText().equals("O") && btn7.getText().equals("O"))){

            //victoria Bot 2
            partidaEmpezada=false;
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Partida finalizada");
            alert2.setHeaderText(null);
            alert2.setContentText("Ha ganado el BOT 2 !");
            alert2.showAndWait();
            empezarPartida.setDisable(false);
            rBjVSj.setDisable(false);
            rBjVScpu.setDisable(false);
            rBcpuVScpu.setDisable(false);


        }else if (turno >9){

            //empate
            partidaEmpezada=false;
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Partida finalizada");
            alert2.setHeaderText(null);
            alert2.setContentText("Ha habido un empate!");
            alert2.showAndWait();
            empezarPartida.setDisable(false);
            rBjVSj.setDisable(false);
            rBjVScpu.setDisable(false);
            rBcpuVScpu.setDisable(false);

        }
    }

    //este metodo esta asociado al boton del menu close y cierra la ventana del programa
    public void menuItemClose(ActionEvent actionEvent) {
        stage.close();
    }

    //este metodo esta asociado al boton de puntuaciones y muestra una ventana con las puntuaciones de los jugador(Victorias,Derrotas,...)
    public void showPuntuaciones(ActionEvent actionEvent) {

        //instanciar el fxml que contiene la tabla de puntuaciones
        TableView<model.Jugador> puntuacionesLista = null;

        {
            try {
                puntuacionesLista = FXMLLoader.load(getClass().getResource("/fxml/Puntuaciones.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

            Stage stage2 = new Stage();
            stage2.setTitle("Puntuacion de los jugadores");
            stage2.setScene(new Scene(puntuacionesLista));
            stage2.show();

            //limpiar la observeList de las columnas y jugadores para que no se produzca un error de duplicacion
            puntuacionesLista.getItems().clear();
            puntuacionesLista.getColumns().clear();

            //añadir las columnas en el orden que se van a mostrar los datos de los jugadores
            puntuacionesLista.getColumns().addAll(puntuaciones.jugador,
                                                puntuaciones.victorias,
                                                puntuaciones.derrotas,
                                                puntuaciones.empates,
                                                puntuaciones.totalPartidas);

            //añadir la lista de los jugadores
            puntuacionesLista.getItems().addAll(puntuaciones.getPuntuacionesLista());

    }

    //metodo para cambiar de turno entre X, O y escribir el valor en el boton que se le pasa por parametro
    public void turnoJugador(Button b) {
        if (turno % 2 == 0) {
            b.setText("O");
            turno++;
        } else {
            b.setText("X");
            turno++;
        }
    }

    //este metodo esta asociado al boton del menu cambiar css y sirve para intercambiar entre el tema claro y oscuro
    public void menuItemCambiar(ActionEvent actionEvent) {
        String mode;
        scene.getStylesheets().clear();
        if(menuItemCambiar.getText().equals("Oscuro")) {
            scene.getStylesheets().add("css/temaOscuro.css");
            mode = "Claro";
        }else {
            scene.getStylesheets().add("css/temaClaro.css");
            mode = "Oscuro";
        }
        menuItemCambiar.setText(mode);
    }


}

