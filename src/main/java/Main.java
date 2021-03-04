import controller.Puntuaciones;
import javafx.scene.control.TableView;
import model.Jugador;
import controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        //cargar el css con el que iniciara la ventana
        String css = getClass().getResource("css/temaClaro.css").toExternalForm();
        //cargar el fxml con el que iniciara la ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainWindow.fxml"));
        Parent root = loader.load();
        //crea escena
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);

        //se instancia la clase mainwindow para poder pasarle el stage y la scene
        MainWindow mainWindow = loader.getController();
        mainWindow.setStage(primaryStage);
        mainWindow.setScene(scene);

        primaryStage.setTitle("Tres en raya");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
