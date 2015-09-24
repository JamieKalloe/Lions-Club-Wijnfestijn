package IPSEN2;

import IPSEN2.controllers.menu.MainFrameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by Philip on 19-09-15.
 */
public abstract class ContentLoader {

    public static final String MAINFRAME = "/IPSEN2/views/menu/MainFrame.fxml";
    public static final String HOOFDMENU = "/IPSEN2/views/menu/HoofdMenu.fxml";
    public static final String GASTEN = "/IPSEN2/views/klant/Gasten.fxml";
    public static final String WIJNEN = "/IPSEN2/views/wijn/Wijnen.fxml";
    public static final String BESTELLINGEN = "/IPSEN2/views/bestelling/Bestellingen.fxml";
    public static final String EVENEMENTEN = "/IPSEN2/views/evenement/Evenementen.fxml";
    public static final String HANDLEIDING = "/IPSEN2/views/klant/Gasten.fxml";
    public static final String STYLE = "IPSEN2/styles/Menu.css";

    public static final String HOME_TITLE = "Home";
    public static final String GASTEN_TITLE = "Gasten";
    public static final String WIJNEN_TITLE = "Wijnen";
    public static final String BESTELLINGEN_TITLE = "Bestellingen";
    public static final String EVENEMENTEN_TITLE = "Evenementen";
    public static final String HANDLEIDING_TITLE = "Handleiding";
    private static MainFrameController mainController;

    public static void setMainController(MainFrameController mainFrameController) {
        mainController = mainFrameController;
    }

    public static void addContent(String fxml) {

        mainController.removeContent();
        try {
            mainController.setContent(
                    configureFXMLLoader(fxml).load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Pane loadMainFrame() throws IOException {

        FXMLLoader loader = configureFXMLLoader(MAINFRAME);
        Pane mainFrame =   loader.load();
        MainFrameController mainController = loader.getController();

        ContentLoader.setMainController(mainController);
        ContentLoader.addContent(ContentLoader.HOOFDMENU);
        ContentLoader.setMainFrameTitle(ContentLoader.HOME_TITLE);

        return mainFrame;
    }

    private static FXMLLoader configureFXMLLoader(String fxml){
        return new FXMLLoader((ContentLoader.class.getResource(
                fxml)));
    }

    public static void setMainFrameTitle(String title){
        mainController.setTitel(title);
    }
}

