package IPSEN2.controllers.event;

import IPSEN2.ContentLoader;
import IPSEN2.models.event.Event;
import IPSEN2.services.event.EventService;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EventController extends ContentLoader implements Initializable{


  @FXML
  private TableView<Event> table_view;

    @FXML
    private TableColumn<Event, String> checkBoxColumn;

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

    @FXML Pane eventToolTip;

   private static ObservableList<Event> eventData;
    private static ArrayList<Integer> selectedRows;
    private EventService eventService;
    private int selectedEventID;

    @FXML
    private void handleNextButton() {
        if (eventId != 0) {
            addContent(MAINMENU);
        }
    }

    /**
     * Handle remove button.
     */
    @FXML
    public void handleRemoveButton() {
        if(selectedRows.size() != 0) {


            selectedRows.forEach(row -> {
                System.out.println("removing row: " + row);
                eventService.remove(row);
            });
        }

        eventData = FXCollections.observableArrayList(eventService.all());
        addContent(EVENTS);
    }

    private Callback createCheckBoxCellCallBack() {
        Callback checkBoxCellCallBack = new Callback<TableColumn.CellDataFeatures<Event, CheckBox>, ObservableValue<CheckBox>>() {

            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Event, CheckBox> cellDataFeatures) {
                CheckBox checkBox = new CheckBox();
                checkBox.setSelected(cellDataFeatures.getValue().getSelected());
                checkBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observableValue,
                                                         Boolean oldValue, Boolean newValue) -> {
                    cellDataFeatures.getValue().setSelected(newValue.booleanValue());

                    eventId = cellDataFeatures.getValue().getId();
                    if (newValue.booleanValue()) {
                        selectedRows.add(eventId);
                        showToolTip();
                    } else if (!newValue.booleanValue()) {
                        selectedRows.remove(selectedRows.indexOf(eventId));
                        selectedEventID= 0;
                        eventToolTip.setVisible(false);
                    }
                });
                return new SimpleObjectProperty(checkBox);
            }
        };
        return  checkBoxCellCallBack;

    }

    @FXML
    private void openAddEventMenu() throws IOException {
        addContent(new AddEventController(), EDIT_EVENT_DIALOG);
    }

    private void setOnTableRowClickedListener() {
        table_view.setRowFactory(table -> {
            TableRow<Event> row = new TableRow<>();

            row.getStyleClass().add("pane");

            Duration maxTimeBetweenSequentialClicks = Duration.millis(300);

            PauseTransition clickTimer = new PauseTransition(maxTimeBetweenSequentialClicks);
            final IntegerProperty sequentialClickCount = new SimpleIntegerProperty(0);

            clickTimer.setOnFinished(event1 -> {
                Event event = row.getTableView().getSelectionModel().getSelectedItem();
                int count = sequentialClickCount.get();
                if (count == 1) {
                    row.getTableView().getSelectionModel().getSelectedItem().setSelected(!event.getSelected());
                    refreshTableView();
                    eventId = event.getId();
                    if (event.getSelected()) showToolTip();
                    else hideToolTip();
                }
                if (count == 2) {
                    addContent(new EditEventController(event.getId()), EDIT_EVENT_DIALOG);
                }
                sequentialClickCount.set(0);
            });

            row.setOnMousePressed(event -> {
                if (row.getTableView().getSelectionModel().getSelectedItem() != null) {
                    sequentialClickCount.set(sequentialClickCount.get() + 1);
                    clickTimer.playFromStart();
                }

            });
            return row;
        });
    }

    private void refreshTableView() {
        table_view.getColumns().get(0).setVisible(false);
        table_view.getColumns().get(0).setVisible(true);
    }

    private void showToolTip() {
        eventToolTip.setVisible(true);
        FadeTransition animation = new FadeTransition(Duration.millis(200), eventToolTip);
        animation.setFromValue(0);
        animation.setToValue(1.0);
        animation.play();
    }

    private void hideToolTip(){
        eventToolTip.setVisible(false);
    }

    @Override
   public void initialize(URL location, ResourceBundle resources) {
      table_view.setPlaceholder(new Label("Voeg een evenement toe"));

       setMainFrameTitle(EVENTS_TITLE);
       eventService = new EventService();

            eventData = FXCollections.observableArrayList(eventService.all());
            selectedRows = new ArrayList();

       checkBoxColumn.setCellValueFactory(createCheckBoxCellCallBack());
       idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
      nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
       eventPlaceNameColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
       eventAddressColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
       eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

      table_view.setItems(FXCollections.observableArrayList(eventData));

        setOnTableRowClickedListener();

   }

}