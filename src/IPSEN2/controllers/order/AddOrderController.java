package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.models.order.WineOrder;
import IPSEN2.services.guest.GuestService;
import IPSEN2.services.order.OrderService;
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
    @FXML private TableView<WineOrder> table_view;
    @FXML private TableColumn wineNameColumn;
    @FXML private TableColumn quantityColumn;
    @FXML private TableColumn deleteButtonColumn;

    private static ObservableList<WineOrder> wineOrderData;

    @FXML
    private Label customerNameLabel;

    private double yPosition;
    private int selectedGuestID;
    private int selectedOrderID;
    private ArrayList<String> selectedWineIDs;
    private GuestService guestService;
    private WineOrderService wineOrderService;
    private OrderService orderService;

    private Guest guest;

    public AddOrderController(int selectedGuestID) {
        this.selectedGuestID = selectedGuestID;
    }

    public AddOrderController(int selectedGuestID, int selectedOrderID) {
        this.selectedGuestID = selectedGuestID;
        this.selectedOrderID = selectedOrderID;
    }

    public AddOrderController(int selectedGuestID, ArrayList<String> selectedWineIDs) {
        this.selectedGuestID = selectedGuestID;
        this.selectedWineIDs = selectedWineIDs;
    }

    public void handleCancelButton() {
        wineOrderData = null;
        addContent(ORDER);
    }

    public void handleSubmitButton() {
        HashMap orderData = new HashMap();
        ArrayList<String> amounts= new ArrayList<>();
        if (wineOrderData.size() != 0) {
            if (selectedWineIDs == null) {
                selectedWineIDs = new ArrayList<>();
            }
            selectedWineIDs.clear();
            wineOrderData.forEach(wineOrder -> {
                amounts.add(wineOrder.getAmount() + "");


                selectedWineIDs.add(wineOrder.getWine().getWineID() + "");
            });
            orderData.put("guestId", selectedGuestID);
            orderData.put("eventId", eventId);
            orderData.put("orderStatusId", "2");
            orderData.put("wineIDs", selectedWineIDs);
            orderData.put("amounts", amounts);
            if (selectedOrderID != 0) {
                orderService.edit(selectedOrderID, orderData);
            } else {
                orderService.add(orderData);
            }

        }
        wineOrderData = null;
        addContent(ORDER);
    }
      public void handleAddWineButton(){
        addContent( new SelectWineController(selectedGuestID), SELECT_WINE_DIALOG);
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
        return  textFieldCellCallBack;
    }

    private Callback createDeleteButtonCellCallBack() {
        Callback deleteButtonCellCallBack = new Callback<TableColumn.CellDataFeatures<WineOrder, Button>, ObservableValue<Button>>() {

            @Override
            public ObservableValue<Button> call(TableColumn.CellDataFeatures<WineOrder, Button> cellDataFeatures) {
               Button deleteButton = new Button();
                deleteButton.getStyleClass().addAll( "deleteButton", "buttonWithoutHover");
                deleteButton.setGraphic(new ImageView("/IPSEN2/images/deleteIcon.png"));

                int wineID = cellDataFeatures.getValue().getWine().getWineID();

                deleteButton.setOnAction(event -> {
                    for( Iterator<WineOrder> iterator = wineOrderData.iterator(); iterator.hasNext() ; )
                    {
                        WineOrder wineOrder = iterator.next();
                        if(wineOrder.getWine().getWineID() == wineID)
                        {
                            iterator.remove();
                        }
                    }
                    table_view.setItems(wineOrderData);
                });

                return new SimpleObjectProperty(deleteButton);
                }

        };
        return  deleteButtonCellCallBack;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        guestService = new GuestService();
        guest = guestService.find(selectedGuestID);
        wineOrderService = new WineOrderService();
        orderService= new OrderService();
        WineService wineService = new WineService();



        if (wineOrderData == null) {
            wineOrderData = FXCollections.observableArrayList(new ArrayList<>());
        }

        if (selectedOrderID != 0) {
            System.out.println("selectedOrder id is not null");
            wineOrderData = FXCollections.observableArrayList(wineOrderService.allForOrder(selectedOrderID));
            table_view.setItems(FXCollections.observableArrayList(wineOrderData));
        } else{
            table_view.setItems(wineOrderData);
        }
        customerNameLabel.setText(guest.getFirstName() + " " + guest.getLastName());

        addWineButton.setOnMouseClicked(event -> handleAddWineButton());

        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());

        wineNameColumn.setCellValueFactory(new PropertyValueFactory<WineOrder, String>("name"));
        quantityColumn.setCellValueFactory(createTextFieldCellCallBack());
        deleteButtonColumn.setCellValueFactory(createDeleteButtonCellCallBack());

        if (selectedWineIDs != null) {

            selectedWineIDs.forEach(selectedWineID -> {
                WineOrder wineOrder = new WineOrder(Integer.parseInt(selectedWineID), 1);
                wineOrder.setWine(wineService.find(Integer.parseInt(selectedWineID)));
                wineOrderData.add(wineOrder);
            });
        }



    }
}
