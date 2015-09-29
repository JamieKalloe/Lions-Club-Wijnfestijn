package IPSEN2;

import IPSEN2.controllers.guest.AddGuestController;
import IPSEN2.controllers.guest.EditGuestController;
import IPSEN2.controllers.menu.MainFrameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by Philip on 19-09-15.
 */
public abstract class ContentLoader {

    public static final String MAINFRAME = "/IPSEN2/views/menu/MainFrame.fxml";
    public static final String MAINMENU = "/IPSEN2/views/menu/MainMenu.fxml";
    public static final String GUESTS = "/IPSEN2/views/guest/GuestTableView.fxml";
    public static final String WIJNEN = "/IPSEN2/views/wine/WineTableView.fxml";
    public static final String ORDER = "/IPSEN2/views/order/OrderTableView.fxml";
    public static final String EVENTS = "/IPSEN2/views/evenement/Evenementen.fxml";
    public static final String MANUAL = "/IPSEN2/views/guest/GuestTableView.fxml";
    public static final String STYLE = "IPSEN2/styles/Style.css";

    public static final String EDIT_GUEST_DIALOG = "/IPSEN2/views/guest/GuestDialogView.fxml";

    public static final String HOME_TITLE = "Home";
    public static final String GUESTS_TITLE = "Gasten";
    public static final String WINES_TITLE = "Wijnen";
    public static final String ORDERS_TITLE = "Bestellingen";
    public static final String EVENTS_TITLE = "Evenementen";
    public static final String MANUAL_TITLE = "Handleiding";
    private static MainFrameController mainController;
    private static FXMLLoader loader;

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

        loader = configureFXMLLoader(MAINFRAME);
        Pane mainFrame =   loader.load();
        MainFrameController mainController = loader.getController();

        setMainController(mainController);
        addContent(ContentLoader.MAINMENU);
        setMainFrameTitle(ContentLoader.HOME_TITLE);

        return mainFrame;
    }

    public static void addContent(Object controller) throws IOException{
        FXMLLoader loader = configureFXMLLoader(EDIT_GUEST_DIALOG);
        loader.setController(controller);
       mainController.setContent(loader.load());
    }

    public static FXMLLoader configureFXMLLoader(String fxml){
        loader = new FXMLLoader((ContentLoader.class.getResource(
                fxml)));
        return loader;
    }

    public static void setMainFrameTitle(String title){
        mainController.setTitel(title);
    }
}

