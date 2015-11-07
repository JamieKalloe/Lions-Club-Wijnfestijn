package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.models.order.WineOrder;
import IPSEN2.services.guest.GuestService;
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
public class AddOrderController extends ContentLoader implements Initializable {


    @FXML private Pane cancelButton, submitButton, addWineButton;
    @FXML private TableView<WineOrder> tableView;
    @FXML private TableColumn wineNameColumn;
    @FXML private TableColumn quantityColumn;
    @FXML private TableColumn deleteButtonColumn;

    private static ObservableList<WineOrder> wineOrderData;

    @FXML private Label customerNameLabel;
    @FXML private ComboBox orderStatusComboBox;

    private ArrayList<Integer> selectedWineIDs;
    private GuestService guestService;
    private WineOrderService wineOrderService;
    private OrderService orderService;
    private OrderStatusService orderStatusService;
    private WineService wineService;
    private static int orderStatusID;
    private  int selectedGuestID;

    private Guest guest;


    public AddOrderController(int selectedID) {
           this.selectedGuestID = selectedID;
    }

    public AddOrderController(int selectedGuestID, ArrayList<Integer> selectedWineIDs) {
        this.selectedGuestID = selectedGuestID;
        this.selectedWineIDs = selectedWineIDs;
        wineService = new WineService();
    }


    public void handleCancelButton() {
        wineOrderData = null;
        addContent(ORDER);
    }

    public void handleSubmitButton() {
        if (wineOrderData.size() != 0) {
            HashMap orderData = new HashMap();
            ArrayList<Integer> amounts= new ArrayList<>();
                wineOrderData.forEach(wineOrder -> {
                    amounts.add(wineOrder.getAmount());
                });

            orderData.put("guestId", selectedGuestID);
                orderData.put("eventId", eventId);
                orderData.put("orderStatusId", orderStatusID + "");
                orderData.put("wineIDs", selectedWineIDs);
                orderData.put("amounts", amounts);
                orderService.add(orderData);

        }
        orderStatusID = 0;
        wineOrderData = null;
        addContent(ORDER);
    }

    private void handleOrderStatusComboBox() {
        orderStatusService.all().forEach(orderStatus -> {
            if (orderStatus.getName().equals(orderStatusComboBox.getValue())) {
                orderStatusID = orderStatus.getId();
            }
        });
    }

    public void handleAddWineButton(){
              addContent( new SelectWineController(selectedGuestID, false), SELECT_WINE_DIALOG);
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
                    }
                });
                return new SimpleObjectProperty(textField);
            }
        };
        return  textFieldCellCallBack;
    }

    private Callback createDeleteButtonCellCallBack() {
        Callback deleteButtonCellCallBack = new Callback<TableColumn.CellDataFeatures<WineOrder, Button>, ObservableValue<Button>>() {

            @Override
            public ObservableValue<Button> call(TableColumn.CellDataFeatures<WineOrder, Button> cellDataFeatures) {
               Button deleteButton = new Button();
                deleteButton.getStyleClass().addAll( "deleteButton", "buttonWithoutHover");
                deleteButton.setGraphic(new ImageView("/IPSEN2/images/deleteIcon.png"));

                int wineID = cellDataFeatures.getValue().getWine().getId();

                deleteButton.setOnAction(event -> {
                    for( Iterator<WineOrder> iterator = wineOrderData.iterator(); iterator.hasNext() ; )
                    {
                        WineOrder wineOrder = iterator.next();
                        if(wineOrder.getWine().getId() == wineID)
                        {
                            iterator.remove();
                            selectedWineIDs.remove(selectedWineIDs.indexOf(wineID));
                        }
                    }
                    tableView.setItems(wineOrderData);
                });

                return new SimpleObjectProperty(deleteButton);
                }
        };
        return  deleteButtonCellCallBack;
    }

    private void initializeWineData() {
        if(selectedWineIDs != null) {
        selectedWineIDs.forEach(selectedWineID -> {
            WineOrder wineOrder = new WineOrder(selectedWineID, 1);
            wineOrder.setWine(wineService.find(selectedWineID));
            wineOrderData.add(wineOrder);
        });} else {
            wineOrderData = FXCollections.observableArrayList(new ArrayList<>());
        }
    }

    private void initializeComboBox() {
        orderStatusService.all().forEach(orderStatus ->
                orderStatusComboBox.getItems().addAll(orderStatusService.
                        find(orderStatus.getId()).getName()));

        if (wineOrderData != null) {
            orderStatusComboBox.setValue(orderStatusService.find(orderStatusID).getName());
        } else {
            orderStatusComboBox.setValue(orderStatusService.all().get(orderStatusService.all().size() - 1).getName());
            orderStatusID = orderStatusService.all().get(orderStatusService.all().size() - 1).getId();
        }

        initializeWineData();
        orderStatusComboBox.setOnAction(event -> handleOrderStatusComboBox());
    }

    private void showTables() {
        wineNameColumn.setCellValueFactory(new PropertyValueFactory<WineOrder, String>("name"));
        quantityColumn.setCellValueFactory(createTextFieldCellCallBack());
        deleteButtonColumn.setCellValueFactory(createDeleteButtonCellCallBack());
        tableView.setItems(wineOrderData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.guestService = new GuestService();
        this.wineOrderService = new WineOrderService();
        this.orderService = new OrderService();
        this.orderStatusService = new OrderStatusService();
        this. wineService = new WineService();
        this.guest = guestService.find(selectedGuestID);
        initializeComboBox();

        this.customerNameLabel.setText(guest.getFirstName() + " " + guest.getLastName());

        showTables();

        addWineButton.setOnMouseClicked(event -> handleAddWineButton());
        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());
    }
}
