package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainWindow {

    private Scene scene;
    private Stage stage;
    int turno=1;

    @FXML
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;


    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void boton(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        if(b.getText() != "X" || b.getText() != "O")
        if (turno %2 == 0){
            b.setText("O");
            turno++;
        }else {
            b.setText("X");
            turno++;
        }
    }
}
