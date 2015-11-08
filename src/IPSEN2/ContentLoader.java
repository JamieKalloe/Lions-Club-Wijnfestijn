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
    public static Stage primaryStage;
    private static FXMLLoader loader;
    public static int eventId;
    public static String lastWindow;

    public static void setMainController(MainFrameController mainFrameController) {
        mainController = mainFrameController;
    }

    public static void addContent(String fxml) {

        mainController.removeContent();

        try {
            loader = configureFXMLLoader();
            setFXMLFileForLoader(loader, fxml);
            mainController.setContent(loader
                    .load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private static FXMLLoader configureFXMLLoader(){
        loader = new FXMLLoader();
        loader.setResources(ResourceBundle.getBundle("IPSEN2.resources.Bundle", Locale.ENGLISH));
        return loader;
    }


    private static void setFXMLFileForLoader(FXMLLoader loader, String fxml) {
        loader.setLocation((ContentLoader.class.getResource(
                fxml)));
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

