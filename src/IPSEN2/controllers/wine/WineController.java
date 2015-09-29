package IPSEN2.controllers.wine;

import IPSEN2.ContentLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class WineController implements Initializable{

    public WineController() {

    }

    public void cmdToevoegenWijn() {

    }

    public void cmdVerwijderenWijn() {

    }

    public void cmdAanpassenWijn() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContentLoader.setMainFrameTitle(ContentLoader.WINES_TITLE);
    }
}