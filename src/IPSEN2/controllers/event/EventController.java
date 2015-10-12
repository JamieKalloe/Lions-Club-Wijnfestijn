package IPSEN2.controllers.event;

import IPSEN2.ContentLoader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EventController extends ContentLoader implements Initializable{


  @FXML
  private TableView<Event> table_view;

   @FXML
   private TableColumn<Event, String> nameColumn;

    @FXML
    private TableColumn<Event, String> idColumn;

    @FXML
    private TableColumn<Event, String> eventPlaceNameColumn;

    @FXML
    private TableColumn<Event, String> eventAddressColumn;

    @FXML
    private TableColumn<Event, String> eventDateColumn;

   private ArrayList<Event> eventData;

   public EventController() {

   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      table_view.setPlaceholder(new Label("Er is geen content om te weergeven"));
      setMainFrameTitle(EVENTS_TITLE);

      eventData = new ArrayList<>();
      eventData.add(new Event("WijnFestijn", "Poelgeest", "Weegbreetuin 5", "1-12-15"));

       idColumn.setCellValueFactory(new PropertyValueFactory<>("eventID"));
      nameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
       eventPlaceNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventPlace"));
       eventAddressColumn.setCellValueFactory(new PropertyValueFactory<>("eventAddress"));
       eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));


      table_view.setItems(FXCollections.observableArrayList(eventData));
       table_view.setRowFactory(table -> {
           TableRow<Event> row = new TableRow<>();
           row.getStyleClass().add("pane");

           row.setOnMouseClicked(event -> addContent(MAINMENU));
           return row;
       });

   }

}