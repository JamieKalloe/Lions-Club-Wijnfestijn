package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.models.order.WineOrder;
import IPSEN2.services.guest.GuestService;
import IPSEN2.services.order.OrderService;
import IPSEN2.services.order.WineOrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
/*
  private void createWineIDAndQuanityContainer() throws IOException{
        Pane wineIDAndQuantityContainer = new Pane();
        TextField wineIDTextField = new TextField();
        Label wineInfoLabel = new Label("Wijnnaam ,soort en streek");
        TextField wineQuantityTextField = new TextField();

        wineIDAndQuantityContainer.setPrefWidth(611);
        wineIDTextField.setPrefWidth(167);
        wineQuantityTextField.setPrefWidth(41);


        wineInfoLabel.setTextFill(Paint.valueOf("#999999"));
        wineInfoLabel.setLayoutX(252);
        wineInfoLabel.setFont(new Font("Roboto", 18));
        wineQuantityTextField.setLayoutX(570);

        wineIDTextFieldList.add(wineIDTextField);
        wineQuantityTextFieldList.add(wineQuantityTextField);



        wineIDAndQuantityContainer.getChildren().addAll(wineIDTextField, wineInfoLabel, wineQuantityTextField);

        if (wineAndQuantityList.size() != 0){
            yPosition += 50;
            wineIDAndQuantityContainer.setLayoutY(yPosition);
        } else {
            yPosition = 0;
            wineIDAndQuantityContainer.setLayoutY(yPosition);

        }

        wineIDandQuantityWrapper.getChildren().add(wineIDAndQuantityContainer);
        wineAndQuantityList.add(wineIDAndQuantityContainer);
    }

    private void createDeleteButton() throws IOException{
        ImageView deleteButton = new ImageView("/IPSEN2/images/DeleteButton2.png");
        deleteButton.setLayoutY(yPosition);
        deleteButton.getStyleClass().add("buttonWithoutHover");

        deleteButtonWrapper.getChildren().add(deleteButton);
        deleteButtonList.add(deleteButton);

        handleDeleteButton(deleteButton);
    }

    private void handleDeleteButton(ImageView deleteButton){
        deleteButton.setOnMouseClicked(event -> deleteRow(event));
    } */


//    public void deleteRow(MouseEvent event) {
//        for (int i = 0; i < deleteButtonList.size(); i++) {
//
//            if (event.getSource() == deleteButtonList.get(i)) {
//                repositionListItems(i);
//                deleteButtonWrapper.getChildren().remove(deleteButtonList.get(i));
//                wineIDandQuantityWrapper.getChildren().remove(wineAndQuantityList.get(i));
//                deleteButtonList.remove(i);
//                wineAndQuantityList.remove(i);
//                yPosition -= 50;
//
//            }
//        }
//    }


//    private void repositionListItems(int index) {
//        for (int j = index; j < deleteButtonList.size(); j++) {
//            if (j > index) {
//                deleteButtonWrapper.getChildren().get(j).setLayoutY(deleteButtonWrapper.getChildren().get(j).getLayoutY() - 50);
//                wineIDandQuantityWrapper.getChildren().get(j).setLayoutY(wineIDandQuantityWrapper.getChildren().get(j).getLayoutY() - 50);
//            }
//
//        }
//
//        addWineButton.setLayoutY(addWineButton.getLayoutY() - 50);
//    }


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
            quantityColumn.setCellValueFactory(new PropertyValueFactory<WineOrder, String>("amount"));

            HashMap orderData = new HashMap();

            ArrayList<String> amounts = new ArrayList<>();

            amounts.add(3 + "");
            amounts.add(2 + "");
            orderData.put("guestId", selectedGuestID);
            orderData.put("eventId", eventId);
            orderData.put("orderStatusId", "1");
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
