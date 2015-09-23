package IPSEN2;

import IPSEN2.controllers.menu.ContextMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

/**
 * Created by Philip on 19-09-15.
 */
public abstract class ContentLoader {

    public static final String CONTEXTMENU = "/IPSEN2/views/menu/ContextMenu.fxml";
    public static final String HOOFDMENU = "/IPSEN2/views/menu/HoofdMenu.fxml";
    public static final String GASTEN = "/IPSEN2/views/klant/Gasten.fxml";
    public static final String WIJNEN = "/IPSEN2/views/wijn/Wijnen.fxml";
    public static final String BESTELLINGEN = "/IPSEN2/views/bestelling/Bestellingen.fxml";
    public static final String EVENEMENTEN = "/IPSEN2/views/evenement/Evenementen.fxml";
    public static final String HANDLEIDING = "/IPSEN2/views/klant/Gasten.fxml";
    public static final String CONTEXTMENU_OPMAAK = "IPSEN2/styles/Menu.css";

    public static final String HOME_TITLE = "Home";
    public static final String GASTEN_TITLE = "Gasten";
    public static final String WIJNEN_TITLE = "Wijnen";
    public static final String BESTELLINGEN_TITLE = "Bestellingen";
    public static final String EVENEMENTEN_TITLE = "Evenementen";
    public static final String HANDLEIDING_TITLE = "Handleiding";
    private static ContextMenuController mainController;

    public static void setMainController(ContextMenuController contextMenuController) {
        mainController = contextMenuController;
    }

    public static void addContent(String fxml) {

        mainController.removeContent();
        try {
            mainController.setContent(
                    FXMLLoader.load(
                            ContentLoader.class.getResource(
                                    fxml
                            )
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setMainFrameTitle(String title){
        mainController.setTitel(title);
    }
}

