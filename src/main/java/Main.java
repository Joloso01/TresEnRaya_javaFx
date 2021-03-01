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
    private List<Jugador> list=new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

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
