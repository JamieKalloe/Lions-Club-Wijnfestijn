package IPSEN2.controllers.merchant;

import IPSEN2.ContentLoader;
import IPSEN2.controllers.mail.MailController;
import IPSEN2.models.merchant.Merchant;
import IPSEN2.services.merchant.MerchantService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-11-15.
 */
public class MerchantController extends ContentLoader implements Initializable {

    @FXML private TableView<Merchant> table_view;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn emailColumn;
    @FXML private TableColumn checkBoxColumn;


    private static ArrayList<Integer> selectedRows;
    private int selectedMerchantID;
    private MerchantService merchantService;
    private ArrayList<Merchant> merchantData;

    @FXML private void handleAddButton() {
        addContent(new AddMerchantController(), ADD_MERCHANT_DIALOG);
    }

    @FXML private void handleRemoveButton() {
        if (selectedRows.size() != 0) {

            for (Integer row : selectedRows) {
                merchantService.remove(row);
            }
            addContent(MERCHANT);
        }
    }

    @FXML private void handleMailButton() {
        if (selectedRows.size() != 0) {
            addContent(new MailController(selectedRows, 1) ,  MAIL);
        }
    }

    @FXML private void openEditMerchantMenu() {
        addContent(new EditMerchantController(selectedMerchantID), ADD_MERCHANT_DIALOG);
    }

    private void setOnTableRowClickedListener() {
        table_view.setRowFactory(table -> {
            TableRow<Merchant> row = new TableRow<>();
            row.getStyleClass().add("pane");
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    selectedMerchantID = row.getTableView().getSelectionModel().getSelectedItem().getId();
                    openEditMerchantMenu();
                }
            });
            return row;
        });
    }


    private Callback createCheckBoxCellCallBack() {
        Callback checkBoxCellCallBack = new Callback<TableColumn.CellDataFeatures<Merchant, CheckBox>, ObservableValue<CheckBox>>() {

            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Merchant, CheckBox> cellDataFeatures) {
                CheckBox checkBox = new CheckBox();
                checkBox.setSelected(cellDataFeatures.getValue().getSelected());
                checkBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observableValue,
                                                         Boolean oldValue, Boolean newValue) -> {
                    cellDataFeatures.getValue().setSelected(newValue.booleanValue());

                    selectedMerchantID = cellDataFeatures.getValue().getId();
                    if (newValue.booleanValue()) {
                        selectedRows.add(selectedMerchantID);
                    } else if (!newValue.booleanValue()) {
                        selectedRows.remove(selectedRows.indexOf(selectedMerchantID));
                        selectedMerchantID = 0;
                    }
                });
                return new SimpleObjectProperty(checkBox);
            }
        };
        return  checkBoxCellCallBack;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMainFrameTitle(MERCHANT_TITLE);
        selectedRows = new ArrayList<>();

        merchantService = new MerchantService();

        checkBoxColumn.setCellValueFactory(createCheckBoxCellCallBack());
        idColumn.setCellValueFactory(new PropertyValueFactory<Merchant, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Merchant, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Merchant, String>("email"));

        setOnTableRowClickedListener();

        table_view.setItems(FXCollections.observableArrayList(merchantService.all()));


    }
}
