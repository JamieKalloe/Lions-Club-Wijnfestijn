package IPSEN2.controllers.klant;

import IPSEN2.ContentLoader;
import IPSEN2.services.guest.GuestService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GastController implements Initializable{



    @FXML private  TableView table;


    public void handleAddButton() {

    }

    public void cmdAanmakenKlant() {

    }

    public void cmdAanpassenKlant() {

    }

    public void cmdVerwijderenKlant() {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContentLoader.setMainFrameTitle(ContentLoader.GASTEN_TITLE);

    }
}