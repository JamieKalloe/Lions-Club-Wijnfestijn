package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.models.order.WineOrder;
import IPSEN2.services.order.OrderService;
import IPSEN2.services.order.OrderStatusService;
import IPSEN2.services.order.WineOrderService;
import IPSEN2.services.wine.WineService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-10-15.
 */
public class EditOrderController extends ContentLoader implements Initializable {

    @FXML private Pane cancelButton, submitButton, addWineButton;
    @FXML private TableView<WineOrder> tableView;
    @FXML private TableColumn wineNameColumn;
    @FXML private TableColumn quantityColumn;
    @FXML private TableColumn deleteButtonColumn;
    @FXML private Label customerNameLabel;
    @FXML private ComboBox orderStatusComboBox;

    private static ObservableList<WineOrder> wineOrderData;
    private ArrayList<Integer> selectedWineIDs;
    private int selectedOrderId;

    private OrderService orderService;
    private OrderStatusService orderStatusService;
    private WineService wineService;
    private WineOrderService wineOrderService;
    private static int orderStatusId;
    private ResourceBundle resources;


    public EditOrderController(int selectedOrderId, ArrayList<Integer> selectedWineIDs) {
        this.selectedOrderId = selectedOrderId;
        this.selectedWineIDs =  selectedWineIDs;
    }

    public void handleCancelButton() {
        wineOrderData = null;
        addContent(resources.getString("ORDER"));
    }

    @FXML
    public void handleSubmitButton() {
        HashMap newOrderData = new HashMap();

        newOrderData.put("guestId", orderService.find(selectedOrderId).getGuest().getId());
        newOrderData.put("eventId", eventId);
        newOrderData.put("orderStatusId", orderStatusId + "");

        orderService.edit(selectedOrderId, newOrderData);

        wineOrderData.forEach(wineOrder -> wineOrderService.delete(selectedOrderId, wineOrder.getWine().getId()));
        wineOrderData.forEach(wineOrder -> {
            HashMap wineOrderData = new HashMap();
            wineOrderData.put("orderID", selectedOrderId);
            wineOrderData.put("wineID" , wineOrder.getWine().getId());
            wineOrderData.put("amount", wineOrder.getAmount());
            wineOrderService.create(wineOrderData);
        });

        addContent(resources.getString("ORDER"));
    }


    private void handleOrderStatusComboBox() {
        orderStatusService.all().forEach(orderStatus -> {
            if (orderStatus.getName().equals(orderStatusComboBox.getValue())) {
                orderStatusId = orderStatus.getId();
            }
        });
    }

    public void handleAddWineButton() {
        addContent(new SelectWineController(selectedOrderId, true), resources.getString("SELECT_WINE_DIALOG"));
    }

    private Callback createTextFieldCellCallBack() {
        Callback textFieldCellCallBack = new Callback<TableColumn.CellDataFeatures<WineOrder, TextField>, ObservableValue<TextField>>() {

            @Override
            public ObservableValue<TextField> call(TableColumn.CellDataFeatures<WineOrder, TextField> cellDataFeatures) {
                TextField textField = new TextField();
                textField.setText(cellDataFeatures.getValue().getAmount() + "");
                textField.textProperty().addListener((ObservableValue<? extends String> observableValue,
                                                      String oldValue, String newValue) -> {

                    if (!newValue.equals("")) {
                        cellDataFeatures.getValue().setAmount(Integer.parseInt(newValue));
                        System.out.println("amount: " + cellDataFeatures.getValue().getAmount());
                    }
                });
                return new SimpleObjectProperty(textField);
            }
        };
        return textFieldCellCallBack;
    }

    private Callback createDeleteButtonCellCallBack() {
        Callback deleteButtonCellCallBack = new Callback<TableColumn.CellDataFeatures<WineOrder, Button>, ObservableValue<Button>>() {

            @Override
            public ObservableValue<Button> call(TableColumn.CellDataFeatures<WineOrder, Button> cellDataFeatures) {
                Button deleteButton = new Button();
                deleteButton.getStyleClass().addAll("deleteButton", "buttonWithoutHover");
                deleteButton.setGraphic(new ImageView("/IPSEN2/images/deleteIcon.png"));

                int wineID = cellDataFeatures.getValue().getWine().getId();

                deleteButton.setOnAction(event -> {
                    for (Iterator<WineOrder> iterator = wineOrderData.iterator(); iterator.hasNext(); ) {
                        WineOrder wineOrder = iterator.next();
                        if (wineOrder.getWine().getId() == wineID) {
                            iterator.remove();
                            wineOrderService.delete(selectedOrderId, wineID);
                        }
                    }
                    tableView.setItems(wineOrderData);
                });

                return new SimpleObjectProperty(deleteButton);
            }
        };
        return deleteButtonCellCallBack;
    }

    private void initializeWineData() {
        if(selectedWineIDs != null) {
            selectedWineIDs.forEach(selectedWineID -> {
                WineOrder wineOrder = new WineOrder(selectedWineID, 1);
                wineOrder.setWine(wineService.find(selectedWineID));
                wineOrderData.add(wineOrder);
            });} else {
            wineOrderData = FXCollections.observableArrayList(orderService.find(selectedOrderId).getWineOrders());
        }
    }

    private void initializeComboBox() {
        orderStatusService.all().forEach(orderStatus ->
                orderStatusComboBox.getItems().addAll(orderStatusService.
                        find(orderStatus.getId()).getName()));

        if (wineOrderData != null) {
            orderStatusComboBox.setValue(orderStatusService.find(orderStatusId).getName());
        } else {
            orderStatusComboBox.setValue(orderStatusService.all().get(orderStatusService.all().size() - 1).getName());
            orderStatusId = orderStatusService.all().get(orderStatusService.all().size() - 1).getId();
        }
        initializeWineData();
        orderStatusComboBox.setOnAction(event -> handleOrderStatusComboBox());
    }

    private void showTable() {
        wineNameColumn.setCellValueFactory(new PropertyValueFactory<WineOrder, String>("name"));
        quantityColumn.setCellValueFactory(createTextFieldCellCallBack());
        deleteButtonColumn.setCellValueFactory(createDeleteButtonCellCallBack());

        tableView.setItems(wineOrderData);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        orderService = new OrderService();
        orderStatusService = new OrderStatusService();
        wineOrderService = new WineOrderService();
        wineService = new WineService();
        Guest guest  = orderService.find(selectedOrderId).getGuest();
        customerNameLabel.setText(guest.getFirstName() + " " + guest.getLastName());
        initializeComboBox();

        showTable();

        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());
        addWineButton.setOnMouseClicked(event -> handleAddWineButton());
    }
}
