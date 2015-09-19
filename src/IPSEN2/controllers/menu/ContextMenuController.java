package IPSEN2.controllers.menu;

import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ContextMenuController implements Initializable{

    private static double xOffset = 0;
    private static double yOffset = 0;
    private Stage primaryStage;

    @FXML private ImageView menuButton;
    @FXML private AnchorPane navigatieMenu;

    public void handleMousePressed(MouseEvent event) throws Exception{
        getPrimaryStage(event);
        xOffset = primaryStage.getX() - event.getScreenX();
        yOffset = primaryStage.getY() - event.getScreenY();
    }

    @FXML
    public void handleMouseDragged(MouseEvent event) throws Exception{
        primaryStage.setX(event.getScreenX() + xOffset);
        primaryStage.setY(event.getScreenY() + yOffset);
    }

    public void handleMinimizeButton(MouseEvent event) {
        getPrimaryStage(event).toBack();
    }

    @FXML
    public void handleCloseButton(MouseEvent event) throws Exception {
            getPrimaryStage(event).close();
    }

    public Stage getPrimaryStage(Event event) {
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        return primaryStage;
    }


    private void prepareSlideMenuAnimation() {

        TranslateTransition openNavigatieMenu = new TranslateTransition(new Duration(350), navigatieMenu);
        openNavigatieMenu.setToX(0);
;
        TranslateTransition sluitNavigatieMenu = new TranslateTransition(new Duration(350), navigatieMenu);

        menuButton.setOnMouseClicked((MouseEvent evt) -> {
            if (navigatieMenu.getTranslateX() != 0) {
                openNavigatieMenu.play();
            } else {
                sluitNavigatieMenu.setToX(-(navigatieMenu.getWidth()));
                sluitNavigatieMenu.play();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareSlideMenuAnimation();
    }
}