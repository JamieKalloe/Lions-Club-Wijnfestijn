package IPSEN2;

import IPSEN2.controllers.mail.MailService;
import IPSEN2.controllers.menu.ContextMenuController;
import IPSEN2.models.mail.Mail;
import IPSEN2.models.mail.MailFactory;
import IPSEN2.models.mail.MailMessage;
import IPSEN2.models.mail.MailType;
import com.opencsv.CSVReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Philip on 18-09-15.
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(laadStartPaneel());
        scene.getStylesheets().add(("IPSEN2/styles/Menu.css"));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane laadStartPaneel() throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource(ContentLoader.CONTEXTMENU)));

        Pane startPaneel =   loader.load();


        ContextMenuController mainController = loader.getController();

        ContentLoader.setMainController(mainController);
        ContentLoader.addContent(ContentLoader.HOOFDMENU);
        ContentLoader.setMainFrameTitle(ContentLoader.HOME_TITLE);

        return startPaneel;
    }

    public static void main(String args[]){
        //new GuestController();

        try {

            CSVReader csvReader = new CSVReader(new FileReader("C:\\Users\\Jamie\\Documents\\Book1.csv"));

            List<String[]> allRows = csvReader.readAll();

//            for(String[] row : allRows) {
//                System.out.println(Arrays.toString(row));
//            }

            System.out.println(Arrays.toString(allRows.get(1)));

        } catch (Exception e) {
            e.printStackTrace();
        }

        launch(args);



    }

}

