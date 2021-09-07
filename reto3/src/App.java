import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class App extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override 
    public void start(Stage stage) throws Exception {

        URL fxml = getClass().getClassLoader().getResource("views/IngresarDatos.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxml);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Reto 3 GUI");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
