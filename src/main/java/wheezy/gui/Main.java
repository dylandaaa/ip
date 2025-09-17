package wheezy.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import wheezy.Wheezy;

/**
 * A GUI for Wheezy using FXML.
 */
public class Main extends Application {

    private Wheezy wheezy = new Wheezy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Wheezy");
            fxmlLoader.<MainWindow>getController().setWheezy(wheezy);  // inject the Wheezy instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
