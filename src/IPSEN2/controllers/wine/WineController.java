package IPSEN2.controllers.wine;

import IPSEN2.ContentLoader;
import IPSEN2.models.wine.Wine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class WineController extends ContentLoader implements Initializable{

    @FXML private TableView<Wine> table_view;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn

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