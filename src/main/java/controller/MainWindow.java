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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainWindow {

    private Scene scene;
    private Stage stage;
    private List<Jugador> jugadorList = new ArrayList<>();
    int turno=1;

    @FXML
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,empezarPartida,btnPuntuacion;

    @FXML
    private MenuItem menuItemClose,menuItemAbout;

    @FXML
    private GridPane grid0;

    @FXML
    private VBox vBox0;

    @FXML
    private RadioButton rBcpuVScpu, rBjVSj, rBjVScpu;


    private boolean partidaEmpezada=false;

    Optional<String> result;




    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void boton(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();

        if (partidaEmpezada){
            if (b.isHover()){


                if (b.getText() != "X" && b.getText() != "O") {
                    turnoJugador(b);

                    if (turno > 3) comprobarGanador();

                } else {
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
        turno = 0;
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



        if (rBjVSj.isSelected() || rBjVScpu.isSelected()){

            if (rBjVSj.isSelected()){
                TextInputDialog dialog = new TextInputDialog("jugador1");
                dialog.setTitle("Nueva partida");
                dialog.setHeaderText("Introduzca su nombre");
                dialog.setContentText("nombre:");

                result = dialog.showAndWait();
                result.ifPresent(s -> System.out.println("Your name: " + s));


                TextInputDialog dialog2 = new TextInputDialog("jugador2");
                dialog2.setTitle("Nueva partida");
                dialog2.setHeaderText("Introduzca su nombre");
                dialog2.setContentText("nombre:");

                result = dialog.showAndWait();
                result.ifPresent(s2 -> System.out.println("Your name: " + s2));

                jugadorList.add(new Jugador(result.get()));

            }else {
                TextInputDialog dialog = new TextInputDialog("jugador1");
                dialog.setTitle("Nueva partida");
                dialog.setHeaderText("Introduzca su nombre");
                dialog.setContentText("nombre:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(s -> System.out.println("Your name: " + s));
            }

            turno++;

        }else if (rBcpuVScpu.isSelected()){

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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Partida finalizada");
            alert.setHeaderText(null);
            alert.setContentText("Ha ganado el jugador 1!");
            alert.showAndWait();


            //victoria jugador 1

        }else if ((btn1.getText().equals("O") && btn2.getText().equals("O") && btn3.getText().equals("O")) ||
                (btn4.getText().equals("O") && btn5.getText().equals("O") && btn6.getText().equals("O")) ||
                (btn7.getText().equals("O") && btn8.getText().equals("O") && btn9.getText().equals("O")) ||
                (btn1.getText().equals("O") && btn4.getText().equals("O") && btn7.getText().equals("O")) ||
                (btn2.getText().equals("O") && btn5.getText().equals("O") && btn8.getText().equals("O")) ||
                (btn3.getText().equals("O") && btn6.getText().equals("O") && btn9.getText().equals("O")) ||
                (btn1.getText().equals("O") && btn5.getText().equals("O") && btn9.getText().equals("O")) ||
                (btn3.getText().equals("O") && btn5.getText().equals("O") && btn7.getText().equals("O"))){

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Partida finalizada");
            alert2.setHeaderText(null);
            alert2.setContentText("Ha ganado el jugador 2!");
            alert2.showAndWait();
            //victoria jugador 2

        }else {

            //empate

        }
    }



    public void menuItemClose(ActionEvent actionEvent) {
        stage.close();
    }

    public void showPuntuaciones(ActionEvent actionEvent) {
        try {

            DialogPane temp = FXMLLoader.load(getClass().getResource("/fxml/Puntuaciones.fxml"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setDialogPane(temp);
            alert.setTitle("Tabla de puntuaciones");
            alert.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
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
}

