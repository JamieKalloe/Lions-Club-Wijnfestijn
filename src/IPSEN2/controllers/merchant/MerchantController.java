package IPSEN2.controllers.merchant;

import IPSEN2.ContentLoader;
import IPSEN2.controllers.handlers.TableViewSelectHandler;
import IPSEN2.controllers.listeners.TableViewListener;
import IPSEN2.controllers.mail.MailController;
import IPSEN2.models.TableViewItem;
import IPSEN2.models.merchant.Merchant;
import IPSEN2.services.merchant.MerchantService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-11-15.
 */
public class MerchantController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private TableView<TableViewItem> tableView;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn emailColumn;

    private int selectedMerchantID;
    private MerchantService merchantService;
    public ObservableList<TableViewItem> merchantData;
    private ArrayList<Integer> selectedRows;

    public void handleAddButton() {
        addContent(new AddMerchantController(), ADD_MERCHANT_DIALOG);
    }

    public void handleRemoveButton() {
        if (selectedRows.size() != 0)
            selectedRows.forEach(row -> merchantService.remove(row));
            addContent(MERCHANT);
        }


    public void handleMailButton() {
        if (selectedRows.size() != 0) {
            addContent(new MailController(selectedRows, 1) ,  MAIL);
        }
    }

    @Override
    public void setSelectedRows(ArrayList selectedRows) {
        this.selectedRows = selectedRows;
    }

    @Override
    public void setSelectedItem(int selectedItemId) {
        this.selectedMerchantID = selectedItemId;
    }

    @Override
    public void openEditMenu() {
        addContent(new EditMerchantController(this.selectedMerchantID), ADD_MERCHANT_DIALOG);
    }

    private void showTable() {
        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(tableView, this);
        tableViewSelectHandler.createCheckBoxColumn();

        idColumn.setCellValueFactory(new PropertyValueFactory<Merchant, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Merchant, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Merchant, String>("email"));

        tableView.setItems(FXCollections.observableArrayList(merchantService.all()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMainFrameTitle(MERCHANT_TITLE);
        selectedRows = new ArrayList<>();
        merchantService = new MerchantService();
        merchantData = FXCollections.observableArrayList(merchantService.all());

        showTable();
    }

}
