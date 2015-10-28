package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.services.guest.GuestService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Philip on 28-10-15.
 */
public class SelectGuestController extends ContentLoader implements Initializable{

    @FXML private TableView<Guest> table_view;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn firstNameColumn;
    @FXML private TableColumn lastNameColumn;
    @FXML private TableColumn emailColumn;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn attendedColumn;

    private int selectedGuestID;
    private static ObservableList<Guest> attendeeData;

    private void setOnTableRowClickedListener() {
        table_view.setRowFactory(table -> {
            TableRow<Guest> row = new TableRow<>();
            row.getStyleClass().add("pane");
            row.setOnMouseClicked(event -> {
                selectedGuestID = row.getTableView().getSelectionModel().
                        getSelectedItem().getId();
                    addContent(new AddOrderController(selectedGuestID), EDIT_ORDER_DIALOG);

            });
            return row;
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Guest, Integer>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("email"));

        GuestService service = new GuestService();
        attendeeData = FXCollections.observableArrayList(service.findAttendeesForEvent(eventId));
        table_view.setItems(attendeeData);

        setOnTableRowClickedListener();
    }
}
