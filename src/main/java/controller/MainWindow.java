package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private boolean Jugador=true;

    Optional<String> result;




    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void boton(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();

            if (rBjVSj.isSelected()){
                if (partidaEmpezada) {
                    if ((Jugador)) {
                        if (b.getText() != "X" && b.getText() != "O") {
                            turnoJugador(b);
                            if (turno > 3) comprobarGanador();
                        }
                    }
                }
            }else if (rBjVScpu.isSelected()){
                if (partidaEmpezada){
                    if ((Jugador)){
                        if (b.getText() != "X" && b.getText() != "O") {
                            turnoJugador(b);
                            Jugador=false;
                            if (turno <9) turnoBot();

                            if (turno > 3) comprobarGanadorBot();
                        }
                    }
                }
            }
    }

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


    public void empezarPartida(ActionEvent actionEvent) {
        //comprobar modo de juego
        if ((!partidaEmpezada)){

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
                    Jugador=true;

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
                    Jugador=true;

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
                    Jugador=false;
                    turnoBot();
                    comprobarGanadorBotvsBot();
                }

            }
        }else {
            empezarPartida.setDisable(true);
            rBjVSj.setDisable(true);
            rBjVScpu.setDisable(true);
            rBcpuVScpu.setDisable(true);
        }

    }

    private void turnoBot() {
        boolean puesto=false;
        if (!Jugador){
            while (!puesto){
                int casilla = (int) ((Math.random()*8)+1);
                    switch (casilla){
                        case 1:
                            if (!btn1.getText().equals("X") && !btn1.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn1);
                                Jugador=true;
                            }
                            break;
                        case 2:
                            if (!btn2.getText().equals("X") && !btn2.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn2);
                                Jugador=true;
                            }
                            break;
                        case 3:
                            if (!btn3.getText().equals("X") && !btn3.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn3);
                                Jugador=true;

                            }
                            break;
                        case 4:
                            if (!btn4.getText().equals("X") && !btn4.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn4);
                                Jugador=true;
                            }
                            break;
                        case 5:
                            if (!btn5.getText().equals("X") && !btn5.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn5);
                                Jugador=true;
                            }
                            break;
                        case 6:
                            if (!btn6.getText().equals("X") && !btn6.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn6);
                                Jugador=true;
                            }
                            break;
                        case 7:
                            if (!btn7.getText().equals("X") && !btn7.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn7);
                                Jugador=true;
                            }
                            break;
                        case 8:
                            if (!btn8.getText().equals("X") && !btn8.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn8);
                                Jugador=true;
                            }
                            break;
                        case 9:
                            if (!btn9.getText().equals("X") && !btn9.getText().equals("O")){
                                puesto=true;
                                turnoJugador(btn9);
                                Jugador=true;
                            }
                            break;
                }
            }
        }else {

        }


    }

    public void comprobarGanador(){
        if ((btn1.getText().equals("X") && btn2.getText().equals("X") && btn3.getText().equals("X")) ||
                (btn4.getText().equals("X") && btn5.getText().equals("X") && btn6.getText().equals("X")) ||
                (btn7.getText().equals("X") && btn8.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn1.getText().equals("X") && btn4.getText().equals("X") && btn7.getText().equals("X")) ||
                (btn2.getText().equals("X") && btn5.getText().equals("X") && btn8.getText().equals("X")) ||
                (btn3.getText().equals("X") && btn6.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn1.getText().equals("X") && btn5.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn3.getText().equals("X") && btn5.getText().equals("X") && btn7.getText().equals("X"))){

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

            //victoria jugador 1

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

        }
    }

    public void comprobarGanadorBot(){
        if ((btn1.getText().equals("X") && btn2.getText().equals("X") && btn3.getText().equals("X")) ||
                (btn4.getText().equals("X") && btn5.getText().equals("X") && btn6.getText().equals("X")) ||
                (btn7.getText().equals("X") && btn8.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn1.getText().equals("X") && btn4.getText().equals("X") && btn7.getText().equals("X")) ||
                (btn2.getText().equals("X") && btn5.getText().equals("X") && btn8.getText().equals("X")) ||
                (btn3.getText().equals("X") && btn6.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn1.getText().equals("X") && btn5.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn3.getText().equals("X") && btn5.getText().equals("X") && btn7.getText().equals("X"))){

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

            //victoria jugador 1

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
            alert2.setContentText("Ha ganado el BOT !");
            alert2.showAndWait();
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

        }
    }

    public void comprobarGanadorBotvsBot(){
        if ((btn1.getText().equals("X") && btn2.getText().equals("X") && btn3.getText().equals("X")) ||
                (btn4.getText().equals("X") && btn5.getText().equals("X") && btn6.getText().equals("X")) ||
                (btn7.getText().equals("X") && btn8.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn1.getText().equals("X") && btn4.getText().equals("X") && btn7.getText().equals("X")) ||
                (btn2.getText().equals("X") && btn5.getText().equals("X") && btn8.getText().equals("X")) ||
                (btn3.getText().equals("X") && btn6.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn1.getText().equals("X") && btn5.getText().equals("X") && btn9.getText().equals("X")) ||
                (btn3.getText().equals("X") && btn5.getText().equals("X") && btn7.getText().equals("X"))){

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

            //victoria jugador 1

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
            alert2.setContentText("Ha ganado el BOT 2 !");
            alert2.showAndWait();
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

        }
    }



    public void menuItemClose(ActionEvent actionEvent) {
        stage.close();
    }

    public void showPuntuaciones(ActionEvent actionEvent) {
        TableView puntuacionesLista = null;

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
    }

    public void turnoJugador(Button b) {
        if (turno % 2 == 0) {
            b.setText("O");
            turno++;
        } else {
            b.setText("X");
            turno++;
        }
    }

    public void menuItemCambiar(ActionEvent actionEvent) {
        String mode;
        scene.getStylesheets().clear();
        if(menuItemCambiar.getText().equals("Oscuro")) {
            scene.getStylesheets().add("css/temaClaro.css");
            mode = "Claro";
        }else {
            scene.getStylesheets().add("css/temaOscuro.css");
            mode = "Oscuro";
        }
        menuItemCambiar.setText(mode);
    }


}

