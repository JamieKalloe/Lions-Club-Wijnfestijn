package IPSEN2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * Created by Philip on 18-09-15.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("IPSEN2/images/HomeButton.png"));
        Scene scene = new Scene(ContentLoader.loadMainFrame());
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String args[]){
        launch(args);
    }

}

