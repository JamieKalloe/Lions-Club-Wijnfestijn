package IPSEN2;

import IPSEN2.controllers.menu.ContextMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;

import java.io.IOException;

/**
 * Created by Philip on 19-09-15.
 */
public abstract class ContentLoader {

    public static final String CONTEXTMENU = "/IPSEN2/views/menu/ContextMenu.fxml";
    public static final String HOOFDMENU = "/IPSEN2/views/menu/HoofdMenu.fxml";
    public static final String GASTEN = "";
    public static final String WIJNEN = "";
    public static final String BESTELLINGEN = "";
    public static final String HANDLEIDING = "";
    public static final String CONTEXTMENU_OPMAAK = "IPSEN2/styles/Menu.css";

    private static ContextMenuController mainController;

    public static void setMainController(ContextMenuController contextMenuController) {
        mainController = contextMenuController;
    }

    public static void laadContent(String fxml) {
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

    public static ContextMenuController getMainController(){
        return mainController;
    }
}

