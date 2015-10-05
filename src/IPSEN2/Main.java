package IPSEN2;

import IPSEN2.generators.csv.ImportCSV;
import IPSEN2.generators.pdf.InvoiceGenerator;

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

        //TODO: delete test code, debug only.
        try {
            ImportCSV importCSV = new ImportCSV();
            importCSV.importTable("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

