package IPSEN2.controllers.event;

import IPSEN2.ContentLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class EventController implements Initializable{


   @FXML
   private TableView table_view;

   public EventController() {

   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      table_view.setPlaceholder(new Label("Er is geen content om te weergeven"));
      ContentLoader.setMainFrameTitle(ContentLoader.EVENTS_TITLE);
   }
}