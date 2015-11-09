package IPSEN2.controllers.guest;

import IPSEN2.ContentLoader;
import IPSEN2.controllers.handlers.TableViewSelectHandler;
import IPSEN2.controllers.listeners.TableViewListener;
import IPSEN2.controllers.mail.MailController;
import IPSEN2.generators.csv.ImportCSV;
import IPSEN2.models.TableViewItem;
import IPSEN2.models.attendee.Attendee;
import IPSEN2.models.guest.Guest;
import IPSEN2.services.attendee.AttendeeService;
import IPSEN2.services.guest.GuestService;
import IPSEN2.services.message.Messaging;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;


public class GuestController extends ContentLoader implements Initializable, TableViewListener {

    @FXML TableView<TableViewItem> tableView;
    @FXML TableColumn idColumn;
    @FXML TableColumn firstNameColumn;
    @FXML TableColumn lastNameColumn;
    @FXML TableColumn emailColumn;
    @FXML TableColumn attendedColumn;

    public int selectedGuestID;
    private GuestService guestService;
    private  ObservableList<TableViewItem> attendeeData;
    private  ArrayList<Integer> selectedRows;
    private AttendeeService attendeeService;

    private ResourceBundle resources;

    /**
     * Handles add button
     */
    public void handleAddButton() throws IOException {
        if (eventId != 0) {
            addContent(new AddGuestController(), resources.getString("EDIT_GUEST_DIALOG"));
        }
    }

    /**
     * Handles remove button
     */
    public void handleRemoveButton() {
        if (selectedRows.size() != 0) {
            selectedRows.forEach(row -> guestService.removeAsAttendee(row, eventId));
            } else {
            Messaging.getInstance().show(
                    "Foutmelding",
                    "Verwijderfout",
                    "Er is geen gast geselecteerd"
            );
        }

        addContent(resources.getString("GUESTS"));
    }


    /**
     * Handles import button
     *<br>
     * Imports a CSV file with all required attributes of Object Guest
     */
    public void importCSVFile() throws Exception {
        //TODO: delete test code, debug only.
        if (eventId != 0) {
            ImportCSV importCSV = new ImportCSV();
            importCSV.importGuests(eventId);
            attendeeData = FXCollections.observableArrayList(guestService.findAttendeesForEvent(eventId));
            addContent(resources.getString("GUESTS"));
        }
    }

    /**
     * Handles mail button
     */
    public void handleMailButton() {
        if (selectedRows.size() != 0) {
            lastWindow = "GuestMenu";
            addContent(new MailController(selectedRows, 2), resources.getString("MAIL"));
        }
    }

    @Override
    public void openEditMenu(){
        if (selectedGuestID != 0 ) {
            addContent(new EditGuestController(selectedGuestID), resources.getString("EDIT_GUEST_DIALOG"));
        }
    }

    @Override
    public void setSelectedRows(ArrayList selectedRows) {
        this.selectedRows = selectedRows;
    }


    @Override
    public void setSelectedItem(int selectedItemId) {
        this.selectedGuestID = selectedItemId;
    }

    /**
     * Creates checkbox cells and listeners for all items in tableView
     *
     * @return returns the CallBack of the attached checkbox cell
     */
    private Callback createAttendedCellCallBack() {
        Callback attendedCellCallBack = new Callback<TableColumn.CellDataFeatures<Guest, CheckBox>, ObservableValue<CheckBox>>() {

            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Guest, CheckBox> cellDataFeatures) {
                CheckBox checkBox = new CheckBox();
                Attendee attendee = attendeeService.find(cellDataFeatures.getValue().getId());
                checkBox.setSelected(attendee.isAttended());
                checkBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observableValue,
                                                         Boolean oldValue, Boolean newValue) -> {
                    cellDataFeatures.getValue().setAttended(newValue.booleanValue());
                    HashMap data = new HashMap();
                    data.put("guest_id",  attendee.getGuestID());
                    data.put("event_id", attendee.getEventID());
                    if (newValue) {
                        data.put("attended", "1");
                    } else {
                        data.put("attended", "0");
                    }
                    attendeeService.update(data);
                });
                return new SimpleObjectProperty(checkBox);
            }
        };
        return  attendedCellCallBack;
    }

    /**
     * Shows all TableView Items <br>
     * Sets TableViewSelectHandler for TableView Object
     */
    private void showTable() {
        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(tableView, this);
        tableViewSelectHandler.createCheckBoxColumn();

        idColumn.setCellValueFactory(new PropertyValueFactory<Guest, Integer>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("email"));
        attendedColumn.setCellValueFactory(createAttendedCellCallBack());

        tableView.setItems(attendeeData);

        if (eventId == 0) {
            tableView.setPlaceholder(new Label("Er is nog geen event geselecteerd"));

        } else {
            tableView.setPlaceholder(new Label("Er is geen content om te weergeven"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        ContentLoader.setMainFrameTitle(resources.getString("GUESTS_TITLE"));
        selectedRows = new ArrayList<>();
        guestService = new GuestService();

        attendeeService = new AttendeeService();
        attendeeData = FXCollections.observableArrayList(guestService.findAttendeesForEvent(eventId));

        showTable();

    }
}