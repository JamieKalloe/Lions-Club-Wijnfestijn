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

   private ArrayList<Event> eventData;

   public EventController() {

   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      table_view.setPlaceholder(new Label("Er is geen content om te weergeven"));
      setMainFrameTitle(EVENTS_TITLE);

      eventData = new ArrayList<>();
      eventData.add(new Event("WijnFestijn", "Poelgeest"));


      nameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));

      table_view.setItems(FXCollections.observableArrayList(eventData));
       table_view.setRowFactory(table -> {
           TableRow<Event> row = new TableRow<>();
           row.getStyleClass().add("pane");
           return row;
       });

   }

  public class Event{

//      public String eventID;
      public String eventName;
      public String eventPlace;
      public String eventAdress;
      public String eventDate;

      public Event(String eventName, String eventPlace) {
         this.eventName = eventName;
         this.eventPlace = eventPlace;

      }

      public String getEventName() {
         return eventName;
      }

      public void setEventName(String eventName) {
         this.eventName = eventName;
      }
   }
}