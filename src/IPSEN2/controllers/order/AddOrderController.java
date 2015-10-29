package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.models.order.WineOrder;
import IPSEN2.services.guest.GuestService;
import IPSEN2.services.order.OrderService;
import IPSEN2.services.order.WineOrderService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-10-15.
 */
public class AddOrderController extends ContentLoader implements Initializable {

    @FXML
    private AnchorPane wineIDandQuantityWrapper, deleteButtonWrapper;

    @FXML
    private Pane cancelButton, submitButton, addWineButton;

    @FXML private TableView<WineOrder> table_view;
    @FXML private TableColumn wineNameColumn;
    @FXML private TableColumn quantityColumn;
    @FXML private TableColumn deleteButtonColumn;

    private ArrayList<Pane> wineAndQuantityList;
    private ArrayList<ImageView> deleteButtonList;
    private static ObservableList<WineOrder> wineOrderData;

    private ArrayList<TextField> wineIDTextFieldList, wineQuantityTextFieldList;
    @FXML
    private Label customerNameLabel;

    private double yPosition;
    private int selectedGuestID;
    private ArrayList<String> selectedWineIDs;
    private GuestService guestService;
    private WineOrderService wineOrderService;
    private  OrderService orderService;

    private Guest guest;

    public AddOrderController(int selectedGuestID) {
        this.selectedGuestID = selectedGuestID;
    }

    public AddOrderController(int selectedGuestID, ArrayList<String> selectedWineIDs) {
        this.selectedGuestID = selectedGuestID;
        this.selectedWineIDs = selectedWineIDs;
    }

    public void handleCancelButton() {
        addContent(ORDER);
    }

    public void handleSubmitButton() {
//        HashMap data = new HashMap();
//        data.put("guestId", selectedGuestID);
//        data.put("eventId", 1);
//        data.put("orderStatusId", 1);
//        ArrayList<String> wineIDs = new ArrayList<String>();
//        ArrayList<String> amounts = new ArrayList<String>();
//        wineAndQuantityList.forEach(row -> {
//            int index = wineAndQuantityList.indexOf(row);
//            wineIDs.add(wineIDTextFieldList.get(index).getText());
//            amounts.add(wineQuantityTextFieldList.get(index).getText());
//            System.out.println("wine ID: " + wineIDTextFieldList.get(index).getText());
//            System.out.println("Quantity: " + wineQuantityTextFieldList.get(index).getText());
//        });
//        data.put("wineIDs", wineIDs);
//        data.put("amounts", amounts);
//        new OrderService().add(data);
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
                    cellDataFeatures.getValue().setAmount(Integer.parseInt(newValue));


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

                deleteButton.setOnAction(event -> {
                    wineOrderService.delete(cellDataFeatures.getValue().getOrderID(), cellDataFeatures.getValue().getWine().getWineID());
                    wineOrderData = FXCollections.observableArrayList(wineOrderService.
                            allForOrder((orderService.all().size())));
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


        customerNameLabel.setText(guest.getFirstName() + " " + guest.getLastName());

        addWineButton.setOnMouseClicked(event -> handleAddWineButton());

        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());


        if (selectedWineIDs != null) {
            wineNameColumn.setCellValueFactory(new PropertyValueFactory<WineOrder, String>("name"));
            quantityColumn.setCellValueFactory(createTextFieldCellCallBack());
            deleteButtonColumn.setCellValueFactory(createDeleteButtonCellCallBack());

            HashMap orderData = new HashMap();

            ArrayList<String> amounts = new ArrayList<>();

            selectedWineIDs.forEach(selectedWineID -> amounts.add("1"));
            orderData.put("guestId", selectedGuestID);
            orderData.put("eventId", eventId);
            orderData.put("orderStatusId", "2");
            orderData.put("wineIDs", selectedWineIDs);
            orderData.put("amounts", amounts);
            orderService.add(orderData);
        }


        wineOrderData = FXCollections.observableArrayList(wineOrderService.
                allForOrder((orderService.all().size())));
        table_view.setItems(wineOrderData);

//        addWineButton.setOnMouseClicked(event -> {
//            try {
//                handleAddWineButton();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        deleteButtonList = new ArrayList<>();
//        wineAndQuantityList = new ArrayList<>();
//        wineIDTextFieldList = new ArrayList<>();
//        wineQuantityTextFieldList = new ArrayList<>();
//
//        try {
//           createWineIDAndQuanityContainer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//            createDeleteButton();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//        });
    }
}
