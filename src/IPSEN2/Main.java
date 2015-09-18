package IPSEN2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Philip on 18-09-15.
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("IPSEN2/views/menu/Menu.fxml"));
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Wijnfestijn");
        primaryStage.show();

    }

    public static void main(String args[]){
        launch(args);
    }

}
