package IPSEN2;

import IPSEN2.controllers.guest.GuestController;
import IPSEN2.controllers.mail.MailService;
import IPSEN2.controllers.menu.ContextMenuController;
import IPSEN2.database.Database;
import IPSEN2.models.mail.Mail;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Properties;

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
            MailService mService = new MailService("smtp.live.com",
                    "ipsen2groep1@hotmail.com",
                    "hsLeiden",
                    587);

                mService.sendMail(new Mail(
                        mService.getSession(),
                        "ipsen2groep1@hotmail.com",
                        "ipsen2groep1@hotmail.com",
                        "Wijnfestijn 2015 - Test email 0" + i,
                        "This is an auto-generated test e-mail. \n Number: 0" + i,
                        "D:\\ic_launcher.png"
                ));

        } catch (Exception e) {
            e.printStackTrace();
        }

        launch(args);



    }

}

