package IPSEN2.controllers.menu;

import IPSEN2.ContentLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Philip on 21-09-15.
 */
public class HoofdMenuController extends ContentLoader implements Initializable {

    @FXML private Pane gastenButton, wijnenButton, bestellingenButton,
            evenementenButton, handLeidingButton;


    @FXML
    public void handleButton(MouseEvent event) throws Exception{
        Object selectedPane = event.getSource();
            if (selectedPane == gastenButton) {
                ContentLoader.addContent(ContentLoader.GASTEN);
            } else if (selectedPane == wijnenButton) {
                ContentLoader.addContent(ContentLoader.WIJNEN);
            } else if (selectedPane == bestellingenButton) {
                ContentLoader.addContent(ContentLoader.BESTELLINGEN);
            } else if (selectedPane == evenementenButton) {
                ContentLoader.addContent(ContentLoader.EVENEMENTEN);
            } else if (selectedPane == handLeidingButton) {
                ContentLoader.addContent(ContentLoader.WIJNEN);
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContentLoader.setMainFrameTitle(ContentLoader.HOME_TITLE);
    }
}
