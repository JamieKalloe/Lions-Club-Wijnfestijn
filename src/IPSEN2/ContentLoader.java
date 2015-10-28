package IPSEN2;

import IPSEN2.controllers.menu.MainFrameController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Philip on 19-09-15.
 */
public abstract class ContentLoader {

    public static final String MAINFRAME = "/IPSEN2/views/menu/MainFrame.fxml";
    public static final String MAINMENU = "/IPSEN2/views/menu/MainMenu.fxml";
    public static final String GUESTS = "/IPSEN2/views/guest/GuestTableView.fxml";
    public static final String WINE = "/IPSEN2/views/wine/WineTableView.fxml";
    public static final String ORDER = "/IPSEN2/views/order/OrderTableView.fxml";
    public static final String EVENTS = "/IPSEN2/views/event/EventTableView.fxml";
    public static final String MANUAL = "/IPSEN2/views/guest/GuestTableView.fxml";
    public static final String EVENT_CHOOSER = "/IPSEN2/views/event/EventChooser.fxml";
    public static final String STYLE = "IPSEN2/styles/style.css";

    public static final String EDIT_GUEST_DIALOG = "/IPSEN2/views/guest/GuestDialogView.fxml";
    public static final String EDIT_WINE_DIALOG = "/IPSEN2/views/wine/WineDialogView.fxml";
    public static final String EDIT_ORDER_DIALOG = "/IPSEN2/views/order/OrderDialogView.fxml";
    public static final String EDIT_EVENT_DIALOG = "/IPSEN2/views/event/EventDialogView.fxml";
    public static final String SELECT_GUEST_DIALOG = "/IPSEN2/views/order/SelectGuestView.fxml";


    public static final String ADD_WINE = "/IPSEN2/views/order/WineIDAndQuantityContainer.fxml";



    public static final String HOME_TITLE = "Home";
    public static final String GUESTS_TITLE = "Gasten";
    public static final String WINES_TITLE = "Wijnen";
    public static final String ORDERS_TITLE = "Bestellingen";
    public static final String EVENTS_TITLE = "Evenementen";
    public static final String MANUAL_TITLE = "Handleiding";
    private static MainFrameController mainController;
    public static Stage primaryStage;
    private static FXMLLoader loader;
    public static int eventId;

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
        addContent(ContentLoader.EVENTS);
        setMainFrameTitle(ContentLoader.HOME_TITLE);

        return mainFrame;
    }

    public static void addContent(Object controller, String fxml) throws IOException{
        FXMLLoader loader = configureFXMLLoader(fxml);
        loader.setController(controller);
       mainController.setContent(loader.load());
    }

    public static FXMLLoader configureFXMLLoader(String fxml){
        loader = new FXMLLoader((ContentLoader.class.getResource(
                fxml)));
        return loader;
    }

    public static Stage getPrimaryStage(Event event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        return primaryStage;
    }

    public static void setMainFrameTitle(String title){
        mainController.setTitle(title);
    }

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

}

