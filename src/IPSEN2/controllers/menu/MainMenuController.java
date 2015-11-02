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


    @FXML
    public void handleButton(MouseEvent event) throws Exception{
        Object selectedPane = event.getSource();
            if (selectedPane == gastenButton) {
                addContent(GUESTS);
            } else if (selectedPane == wijnenButton) {
                addContent(WINE);
            } else if (selectedPane == bestellingenButton) {
                addContent(ORDER);
            } else if (selectedPane == evenementenButton) {
                ContentLoader.addContent(EVENTS);
            } else if (selectedPane == merchantButton) {
                addContent(MERCHANT);
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         setMainFrameTitle(ContentLoader.HOME_TITLE);
    }
}
