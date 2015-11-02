package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import IPSEN2.controllers.mail.MailController;
import IPSEN2.generators.pdf.InvoiceGenerator;
import IPSEN2.models.order.Order;
import IPSEN2.services.guest.GuestService;
import IPSEN2.services.order.OrderService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class OrderController extends ContentLoader implements Initializable{

   private int selectedGuestID;
   private int selectedOrderID;

   @FXML
   private TableView table_view;
   @FXML private TableColumn checkBoxColumn;
   @FXML private TableColumn idColumn;
   @FXML private TableColumn lastNameColumn;
   @FXML private TableColumn totalAmountColumn;
   @FXML private TableColumn statusColumn;
   @FXML private TableColumn invoiceColumn;

   private ObservableList<Order> orderData;

   private OrderService orderService;
   private static ArrayList<Integer> selectedRows;

   public OrderController() {

   }

   @FXML
   private void handleMailButton() {
      if (selectedRows.size() != 0) {
         ArrayList<Integer> guestIDs = new ArrayList<>();

         selectedRows.forEach(row -> guestIDs.add(orderService.find(row).getGuest().getId())
         );
         addContent(new MailController(guestIDs), MAIL);
      }
   }

   @FXML
   private void handleAddButton(){
      addContent(SELECT_GUEST_DIALOG);
   }

   @FXML
   public void handleEditButton() {
      if (selectedGuestID != 0) {
         addContent(new EditOrderController(), EDIT_ORDER_DIALOG);
      }
   }

   public void handleRemoveButton() {
      if (selectedRows.size() != 0) {


         for (Integer row : selectedRows) {
            System.out.println(orderService.remove(row));
         }
      }

      orderData = FXCollections.observableArrayList(orderService.all());
      addContent(ORDER);
   }
   private void setOnTableRowClickedListener() {
      table_view.setRowFactory(table -> {
         TableRow<Order> row = new TableRow<>();
         row.getStyleClass().add("pane");
         row.setOnMouseClicked(event -> {
            selectedGuestID = row.getTableView().getSelectionModel().getSelectedItem().getId();
            selectedOrderID = row.getTableView().getSelectionModel().getSelectedItem().getId();
            addContent(new AddOrderController(selectedGuestID, selectedOrderID), EDIT_ORDER_DIALOG);
         });
         return row;
      });
   }

   private Callback createCheckBoxCellCallBack() {
      Callback checkBoxCellCallBack = new Callback<TableColumn.CellDataFeatures<Order, CheckBox>, ObservableValue<CheckBox>>() {

         @Override
         public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Order, CheckBox> cellDataFeatures) {
            CheckBox checkBox = new CheckBox();
            checkBox.setSelected(cellDataFeatures.getValue().getSelected());
            checkBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observableValue,
                                                     Boolean oldValue, Boolean newValue) -> {
               cellDataFeatures.getValue().setSelected(newValue.booleanValue());

               selectedOrderID = cellDataFeatures.getValue().getId();
               if (newValue.booleanValue()) {
                  selectedRows.add(selectedOrderID);
               } else if (!newValue.booleanValue()) {
                  selectedRows.remove(selectedRows.indexOf(selectedOrderID));
                  selectedGuestID = 0;
               }
            });
            return new SimpleObjectProperty(checkBox);
         }
      };
      return  checkBoxCellCallBack;
   }

   private Callback createInvoiceButtonCellCallBack() {
      Callback deleteButtonCellCallBack = new Callback<TableColumn.CellDataFeatures<Order, Button>, ObservableValue<Button>>() {

         @Override
         public ObservableValue<Button> call(TableColumn.CellDataFeatures<Order, Button> cellDataFeatures) {
            Button invoiceButton = new Button("Open");
            invoiceButton.setStyle("-fx-font-size: 14");

            int orderID = cellDataFeatures.getValue().getId();

            invoiceButton.setOnAction(event -> {
               for( Iterator<Order> iterator = orderService.all().iterator(); iterator.hasNext() ; )
               {
                  Order order = iterator.next();
                  if(order.getId() == orderID) {

                     Path directory = Paths.get(System.getProperty("user.dir") + "/src/IPSEN2/invoice");
                     if (!(directory.toString().contains(" " + orderID + ".pdf"))) {
                        try {
                           new InvoiceGenerator().generate(orderService.find(orderID));
                        } catch(Exception e) {
                           e.printStackTrace();
                        }
                     }

                     try {
                        Files.walk(directory).forEach(
                                fileDirectory -> {
                                   if ((fileDirectory).toString().contains(" " + orderID + ".pdf")) {
                                      try {
                                         Desktop.getDesktop().open(fileDirectory.toFile());
                                      } catch (IOException e) {
                                         e.printStackTrace();
                                      }
                                   }
                                }
                        );
                     } catch (IOException e) {
                        e.printStackTrace();
                     }
                  }
               }

            });

            return new SimpleObjectProperty(invoiceButton);
         }
      };
      return  deleteButtonCellCallBack;
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      ContentLoader.setMainFrameTitle(ContentLoader.ORDERS_TITLE);
      selectedRows = new ArrayList<>();
      table_view.setPlaceholder(new Label("Er is geen content om te weergeven"));
      orderService = new OrderService();
      orderData = FXCollections.observableArrayList(orderService.all());
      table_view.setItems(orderData);

      setOnTableRowClickedListener();

      checkBoxColumn.setCellValueFactory(createCheckBoxCellCallBack());
      idColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
      lastNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
         public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> param) {
            if (param.getValue() != null && param.getValue().getGuest().getLastName() != null) {
               return new SimpleStringProperty(param.getValue().getGuest().getLastName());
            }
            return new SimpleStringProperty("");
         }
      });
      totalAmountColumn.setCellValueFactory(new PropertyValueFactory<Order, Double>("totalAmount"));
      statusColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
         public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> param) {
            if (param.getValue() != null && param.getValue().getStatus().getName() != null) {
               return new SimpleStringProperty(param.getValue().getStatus().getName());
            }
            return new SimpleStringProperty("");
         }
      });
      invoiceColumn.setCellValueFactory(createInvoiceButtonCellCallBack());

   }



}