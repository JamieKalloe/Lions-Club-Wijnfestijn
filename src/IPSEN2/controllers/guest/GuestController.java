package IPSEN2.controllers.guest;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.services.guest.GuestService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GuestController extends ContentLoader implements Initializable{

    @FXML
    private  TableView<Guest> table_view;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn firstNameColumn;
    @FXML private TableColumn lastNameColumn;
    @FXML private TableColumn emailColumn;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn attendedColumn;

    public int selectedGuestID;
    private GuestService service;
    private  ObservableList<Guest> guestData;
    private ArrayList<Integer> selectedRows;


    public void handleAddButton() throws IOException {
     addContent(new AddGuestController(), EDIT_GUEST_DIALOG);

    }

    public void handleRemoveButton() {
        selectedRows.forEach(row -> service.remove(row + 1));

        table_view.setItems(FXCollections.observableArrayList(service.all()));
    }



    public void handleEditButton() throws IOException{
        if (selectedGuestID != 0) {
            addContent(new EditGuestController(selectedGuestID), EDIT_GUEST_DIALOG);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContentLoader.setMainFrameTitle(ContentLoader.GUESTS_TITLE);
        service = new GuestService();

       // guestData = service.all();

        guestData = FXCollections.observableArrayList(service.all());

        selectedRows = new ArrayList<>();

        table_view.setItems(guestData);
        idColumn.setCellValueFactory(new PropertyValueFactory<Guest, Integer>("guestID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("email"));
//        attendedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));
      //  attendedColumn.setCellValueFactory(new PropertyValueFactory<Guest, SimpleBooleanProperty>("attended"));

        checkBoxColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Guest, CheckBox>, ObservableValue<CheckBox>>() {

               @Override
               public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Guest, CheckBox> cellDataFeatures) {
                   CheckBox checkBox = new CheckBox();
                   checkBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observableValue,
                                                            Boolean oldValue, Boolean newValue) -> {
                       cellDataFeatures.getValue().setAttended(newValue.booleanValue());
                       selectedGuestID = cellDataFeatures.getValue().getGuestID();
                       if (newValue.booleanValue()) {
                           selectedRows.add(cellDataFeatures.getValue().getGuestID());
                       } else if (!newValue.booleanValue()){
                           selectedRows.remove(selectedRows.indexOf(selectedGuestID));
                           selectedGuestID = 0;
                       }
                   });
                   return new SimpleObjectProperty(checkBox);
               }
        });


            table_view.setPlaceholder(new Label("Er is geen content om te weergeven"));

        }

    }