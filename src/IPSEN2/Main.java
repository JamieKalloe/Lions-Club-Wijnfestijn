package IPSEN2;

import IPSEN2.generators.csv.ImportCSV;
import IPSEN2.generators.pdf.InvoiceGenerator;

import IPSEN2.services.event.EventService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Date;
import java.util.HashMap;


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
        //EVENT TESTING: Works
        /*EventService service = new EventService();
        HashMap data = new HashMap();
        data.put("name", "Wijnproeverij deze shit");
        data.put("startDate", new Date());
        data.put("endDate", new Date());
        data.put("zipCode", "1354RT");
        data.put("street", "Wat is deze laan");
        data.put("houseNumber", "4");
        data.put("country", "Nederland");
        data.put("city", "Gekke dorp");

        service.add(data);*/
        try {
            ImportCSV importCSV = new ImportCSV();
            importCSV.importGuests();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

