package IPSEN2.controllers.menu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable{

    private static double xOffset = 0;
    private static double yOffset = 0;
    private Stage primaryStage;

    public void handleMousePressed(MouseEvent event) throws Exception{
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        xOffset = primaryStage.getX() - event.getScreenX();
        yOffset = primaryStage.getY() - event.getScreenY();
    }

    @FXML
    public void handleMouseDragged(MouseEvent event) throws Exception{
        primaryStage.setX(event.getScreenX() + xOffset);
        primaryStage.setY(event.getScreenY() + yOffset);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}