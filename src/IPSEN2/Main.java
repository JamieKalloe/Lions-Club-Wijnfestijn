package IPSEN2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * Created by Philip on 18-09-15.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(ContentLoader.loadMainFrame());
        scene.getStylesheets().add((ContentLoader.STYLE));

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String args[]){

//        try {
//            new InvoiceGenerator().generate("bin/invoice.pdf");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        launch(args);
    }

}

