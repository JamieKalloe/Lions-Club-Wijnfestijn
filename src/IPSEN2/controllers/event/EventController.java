package IPSEN2.controllers.event;

import IPSEN2.ContentLoader;
import IPSEN2.controllers.handlers.TableViewSelectHandler;
import IPSEN2.controllers.listeners.TableViewListener;
import IPSEN2.models.TableViewItem;
import IPSEN2.models.event.Event;
import IPSEN2.services.event.EventService;
import IPSEN2.services.message.Messaging;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EventController extends ContentLoader implements Initializable, TableViewListener {


    @FXML TableView<TableViewItem> tableView;
    @FXML TableColumn<Event, String> nameColumn;
    @FXML TableColumn<Event, String> idColumn;
    @FXML TableColumn<Event, String> eventPlaceNameColumn;
    @FXML TableColumn<Event, String> eventAddressColumn;
    @FXML TableColumn<Event, String> eventDateColumn;

    @FXML Pane eventToolTip;

    private  ObservableList<Event> eventData;
    private  ArrayList<Integer> selectedRows;
    private EventService eventService;
    private int selectedEventId;
    private ResourceBundle resources;

    public void handleNextButton() {
        if (eventId != 0) {
            addContent(resources.getString("MAINMENU"));
        }
    }

    /**
     * Handle remove button.
     */
    public void handleRemoveButton() {
        if(selectedRows.size() != 0) {
            selectedRows.forEach(row -> {
                eventService.remove(row);
            });
            eventId = 0;
            eventData = FXCollections.observableArrayList(eventService.all());
            addContent(resources.getString("EVENTS"));
        } else {
                Messaging.getInstance().show(
                        "Foutmelding",
                        "Verwijderfout",
                        "Er is geen wijn geselecteerd"
                );
            }
    }

   public void openAddEventMenu() throws IOException {
        addContent(new AddEventController(), resources.getString("EDIT_EVENT_DIALOG"));
    }

    public void showToolTip() {
        eventToolTip.setVisible(true);
        FadeTransition animation = new FadeTransition(Duration.millis(200), eventToolTip);
        animation.setFromValue(0);
        animation.setToValue(1.0);
        animation.play();
    }

    public void hideToolTip(){
        eventToolTip.setVisible(false);
    }


    @Override
    public void setSelectedRows(ArrayList selectedRows) {
        this.selectedRows = selectedRows;
    }

    @Override
    public void setSelectedItem(int selectedItemId) {

        if (selectedItemId != 0){
            this.selectedEventId = selectedItemId;
            showToolTip();
        } else {
            this.selectedEventId = eventId;
            hideToolTip();
        }

        eventId = selectedItemId;
    }

    @Override
    public void openEditMenu() {
        addContent(new EditEventController(this.selectedEventId),resources.getString("EDIT_EVENT_DIALOG"));
    }

    private void showTable() {
        selectedRows = new ArrayList();
        eventService = new EventService();
        eventData = FXCollections.observableArrayList(eventService.all());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        eventPlaceNameColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        eventAddressColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        new TableViewSelectHandler(tableView, this).createCheckBoxColumn();

        tableView.setItems(FXCollections.observableArrayList(eventData));
        tableView.setPlaceholder(new Label("Voeg een evenement toe"));
    }

    @Override
   public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        setMainFrameTitle(resources.getString("EVENTS_TITLE"));
        showTable();
    }
}