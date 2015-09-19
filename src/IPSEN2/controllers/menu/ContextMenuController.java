package IPSEN2.controllers.menu;

import IPSEN2.ContentLoader;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ContextMenuController implements Initializable{

    private static double xOffset = 0;
    private static double yOffset = 0;
    private Stage primaryStage;
    private static final String TITEL = "Home";

    @FXML private ImageView menuButton;
    @FXML private AnchorPane navigatieMenu;
    @FXML private StackPane contentHolder;
    @FXML private Label titelLabel;
    @FXML private Pane contentCover;

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
                contentCover.setStyle("-fx-background-color:rgba(0,0,0,0.2);");
                FadeTransition animation = new FadeTransition(Duration.millis(200), contentCover);
                animation.setFromValue(0);
                animation.setToValue(1.0);
                animation.play();

                contentCover.setOnMouseClicked(event -> { sluitNavigatieMenu.setToX(-(navigatieMenu.getWidth()));
                    sluitNavigatieMenu.play();
                    contentCover.setStyle(null);
                });

            } else {

                sluitNavigatieMenu.setToX(-(navigatieMenu.getWidth()));
                sluitNavigatieMenu.play();
                contentCover.setStyle(null);

            }
        });
    }


    public void setTitel(String titel){
        titelLabel.setText(titel);
    }

    public void setContent(Node node) {
       contentHolder.getChildren().setAll(node);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareSlideMenuAnimation();
        setTitel(TITEL);
    }
}