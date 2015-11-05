package IPSEN2.controllers.guest;

import IPSEN2.ContentLoader;
import IPSEN2.controllers.mail.MailController;
import IPSEN2.generators.csv.ImportCSV;
import IPSEN2.models.attendee.Attendee;
import IPSEN2.models.guest.Guest;
import IPSEN2.services.attendee.AttendeeService;
import IPSEN2.services.guest.GuestService;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;


public class GuestController extends ContentLoader implements Initializable{

    @FXML private  TableView<Guest> table_view;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn firstNameColumn;
    @FXML private TableColumn lastNameColumn;
    @FXML private TableColumn emailColumn;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn attendedColumn;

    public int selectedGuestID;
    private GuestService service;
    private static ObservableList<Guest> guestData;
    private static ObservableList<Guest> attendeeData;
    private static ArrayList<Integer> selectedRows;
    private CheckBox selectAllCheckBox;
    private static  boolean selected;
    private static boolean keepCurrentData = false;
    private static int currentEventId = 0;
    private AttendeeService attendeeService;

    @FXML
    private Pane removeButton;


    public void handleAddButton() throws IOException {
        if (eventId != 0) {
            keepCurrentData = false;
            addContent(new AddGuestController(), EDIT_GUEST_DIALOG);
        }
    }

    public void handleRemoveButton() {


        if (selectedRows.size() != 0) {
            selected = false;

            for (Integer row : selectedRows) {
                //if (guestData.get(selectedRows.indexOf(row)).getAttended()) {
                System.out.println("removing " + row);
                attendeeService.delete(row);
            }
        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText("Opgelet!");
//            alert.setContentText("U heeft geen items geselecteerd om te verwijderen!");
//
//            alert.showAndWait();
        }



        attendeeData = FXCollections.observableArrayList(service.findAttendeesForEvent(eventId));
        addContent(GUESTS);

    }

    public void handleMailButton() {
        if (selectedRows.size() != 0) {
            selected = false;
            lastWindow = "GuestMenu";
            addContent(new MailController(selectedRows, 2), MAIL);
        }
    }

    public void openEditGuestMenu(){
        if (selectedGuestID != 0 ) {

            selected = false;
            keepCurrentData = false;
            addContent(new EditGuestController(selectedGuestID), EDIT_GUEST_DIALOG);
        }


    }

    @FXML
    private void importCSVFile() {
        //TODO: delete test code, debug only.
        if (eventId != 0) {
        try {
            ImportCSV importCSV = new ImportCSV();
            importCSV.importGuests();
            guestData.forEach(guest -> {
                HashMap attendeeData = new HashMap();

                attendeeData.put("guestID", guest.getId());
                attendeeData.put("eventID", eventId);
                attendeeData.put("zipCode", guest.getAddress().getZipCode());
                attendeeData.put("street", guest.getAddress().getStreet());
                attendeeData.put("houseNumber", guest.getAddress().getHouseNumber());
                attendeeData.put("country", guest.getAddress().getCountry());
                attendeeData.put("city", guest.getAddress().getCity());
                attendeeData.put("referralName", guest.getReferral());
                attendeeService.create(attendeeData);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

            keepCurrentData = false;
        addContent(GUESTS);
    }}

    private void setOnTableRowClickedListener() {
        table_view.setRowFactory(table -> {
            TableRow<Guest> row = new TableRow<>();
            row.getStyleClass().add("pane");
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    selectedGuestID = row.getTableView().getSelectionModel().getSelectedItem().getId();
                    openEditGuestMenu();
                }
            });
            return row;
        });
    }

    private void createSelectAllCheckBox() {
        selectAllCheckBox = new CheckBox();
        selectAllCheckBox.setSelected(selected);
        checkBoxColumn.setGraphic(selectAllCheckBox);
        selectAllCheckBox.setOnAction(event -> {
            selected = selectAllCheckBox.isSelected();
            if (selected) {
                selectedRows.clear();
            }

            attendeeData.forEach(guest -> {
                guest.setSelected(selected);
                if (selected) {
                    selectedRows.add(guest.getId());
                } else {
                    selectedRows.clear();
                }
            });
            keepCurrentData = true;
            addContent(GUESTS);
            selectAllCheckBox.setSelected(selected);
        });

    }

    private Callback createCheckBoxCellCallBack() {
        Callback checkBoxCellCallBack = new Callback<TableColumn.CellDataFeatures<Guest, CheckBox>, ObservableValue<CheckBox>>() {

            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Guest, CheckBox> cellDataFeatures) {
                CheckBox checkBox = new CheckBox();
                checkBox.setSelected(cellDataFeatures.getValue().getSelected());
                checkBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observableValue,
                                                         Boolean oldValue, Boolean newValue) -> {
                    cellDataFeatures.getValue().setSelected(newValue.booleanValue());

                    selectedGuestID = cellDataFeatures.getValue().getId();
                    if (newValue.booleanValue()) {
                        selectedRows.add(selectedGuestID);
                    } else if (!newValue.booleanValue()) {
                        selectedRows.remove(selectedRows.indexOf(selectedGuestID));
                        selectedGuestID = 0;
                    }
                });
                return new SimpleObjectProperty(checkBox);
            }
        };
        return  checkBoxCellCallBack;
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContentLoader.setMainFrameTitle(ContentLoader.GUESTS_TITLE);
        service = new GuestService();
        attendeeService = new AttendeeService();

        if (currentEventId != eventId || currentEventId == 0) {
            keepCurrentData = false;
        }

        if (!keepCurrentData) {
                currentEventId = eventId;
                guestData = FXCollections.observableArrayList(service.all());
                attendeeData = FXCollections.observableArrayList(service.findAttendeesForEvent(eventId));
                selectedRows = new ArrayList<>();
            }



        setOnTableRowClickedListener();

        checkBoxColumn.setCellValueFactory(createCheckBoxCellCallBack());
        idColumn.setCellValueFactory(new PropertyValueFactory<Guest, Integer>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("email"));
        attendedColumn.setCellValueFactory(createAttendedCellCallBack());


        table_view.setItems(attendeeData);


        createSelectAllCheckBox();

        if (eventId == 0) {
            table_view.setPlaceholder(new Label("Er is nog geen event geselecteerd"));

        } else {
            table_view.setPlaceholder(new Label("Er is geen content om te weergeven"));
        }
    }
}