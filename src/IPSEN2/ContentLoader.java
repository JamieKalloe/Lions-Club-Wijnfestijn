package IPSEN2;

import IPSEN2.controllers.menu.MainFrameController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Philip on 19-09-15.
 */
public abstract class ContentLoader {

    private static MainFrameController mainController;
    /**
     * The constant primaryStage.
     */
    public static Stage primaryStage;
    private static FXMLLoader loader;
    /**
     * The constant eventId.
     */
    public static int eventId;
    /**
     * The constant lastWindow.
     */
    public static String lastWindow;

    /**
     * Sets main controller.
     *
     * @param mainFrameController the main frame controller
     */
    public static void setMainController(MainFrameController mainFrameController) {
        mainController = mainFrameController;
    }

    /**
     * Add content.
     *
     * @param fxml the fxml
     */
    public static void addContent(String fxml) {

        mainController.removeAllContent();

        try {
            loader = configureFXMLLoader();
            setFXMLFileForLoader(loader, fxml);
            mainController.setContent(loader
                    .load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load main frame pane.
     *
     * @return the pane
     * @throws IOException the io exception
     */
    public static Pane loadMainFrame() throws IOException {
        loader = configureFXMLLoader();
        setFXMLFileForLoader(loader, loader.getResources().getString("MAINFRAME"));
        Pane mainFrame =   loader.load();
        mainFrame.getStylesheets().add(loader.getResources().getString("STYLE"));
        MainFrameController mainController = loader.getController();

        setMainController(mainController);
        addContent(loader.getResources().getString("EVENTS"));

        return mainFrame;
    }

    /**
     * Add content.
     *
     * @param controller the controller
     * @param fxml       the fxml
     */
    public static void addContent(Object controller, String fxml) {
        FXMLLoader loader = configureFXMLLoader();
        setFXMLFileForLoader(loader, fxml);
        loader.setController(controller);
        try {
            mainController.setContent(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialises FXMLLoader
     */
    private static FXMLLoader configureFXMLLoader(){
        loader = new FXMLLoader();
        loader.setResources(ResourceBundle.getBundle("IPSEN2.resources.Bundle", Locale.ENGLISH));
        return loader;
    }

    /**
     * Sets fxml file for FXMLLoader object <br>
     */
    private static void setFXMLFileForLoader(FXMLLoader loader, String fxml) {
        loader.setLocation((ContentLoader.class.getResource(
                fxml)));
    }

    /**
     * Gets primary stage.
     *
     * @param event the event
     * @return the primary stage
     */
    public static Stage getPrimaryStage(Event event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        return primaryStage;
    }

    /**
     * Set main frame title.
     *
     * @param title the title
     */
    public static void setMainFrameTitle(String title){
        mainController.setTitle(title);
    }


}

