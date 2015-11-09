package IPSEN2.controllers.menu;

import IPSEN2.ContentLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Philip on 21-09-15.
 */
public class MainMenuController extends ContentLoader implements Initializable {

    @FXML private Pane gastenButton, wijnenButton, bestellingenButton,
            evenementenButton, merchantButton;

    private ResourceBundle resources;

    /**
     * Handles shortcut button
     */
    @FXML
    public void handleShortcutButton(MouseEvent event) throws Exception{
        Object selectedPane = event.getSource();
            if (selectedPane == gastenButton) {
                addContent(resources.getString("GUESTS"));
            } else if (selectedPane == wijnenButton) {
                addContent(resources.getString("WINE"));
            } else if (selectedPane == bestellingenButton) {
                addContent(resources.getString("ORDER"));
            } else if (selectedPane == evenementenButton) {
                ContentLoader.addContent(resources.getString("EVENTS"));
            } else if (selectedPane == merchantButton) {
                addContent(resources.getString("MERCHANT"));
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
         setMainFrameTitle(resources.getString("HOME_TITLE"));
    }
}
