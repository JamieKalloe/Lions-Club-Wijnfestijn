package IPSEN2.controllers.wine;

import IPSEN2.ContentLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class WineController implements Initializable{

    @FXML private TableView table_view;

    public WineController() {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContentLoader.setMainFrameTitle(ContentLoader.WINES_TITLE);
        table_view.setPlaceholder(new Label("Er is geen content om te weergeven"));
    }
}