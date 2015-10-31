package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import IPSEN2.models.order.Order;
import IPSEN2.services.order.OrderService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.ResourceBundle;

public class OrderController extends ContentLoader implements Initializable{

   private int selectedGuestID;
   private int selectedOrderID;

   @FXML
   private TableView table_view;
   @FXML private TableColumn idColumn;
   @FXML private TableColumn lastNameColumn;
   @FXML private TableColumn totalAmountColumn;
   @FXML private TableColumn statusColumn;
   @FXML private TableColumn invoiceColumn;

   private OrderService orderService;

   public OrderController() {

   }

   public void handleAddButton() throws IOException {
      addContent(SELECT_GUEST_DIALOG);
   }

   public void handleEditButton() throws IOException {
      if (selectedGuestID != 0) {
         addContent(new EditOrderController(), EDIT_ORDER_DIALOG);
      }
   }

   public void handleRemoveButton() {

   }
   private void setOnTableRowClickedListener() {
      table_view.setRowFactory(table -> {
         TableRow<Order> row = new TableRow<>();
         row.getStyleClass().add("pane");
         row.setOnMouseClicked(event -> {
            selectedGuestID = row.getTableView().getSelectionModel().getSelectedItem().getGuest().getId();
            selectedOrderID = row.getTableView().getSelectionModel().getSelectedItem().getId();
            addContent(new AddOrderController(selectedGuestID, selectedOrderID), EDIT_ORDER_DIALOG);
         });
         return row;
      });
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
                     try {
                        Files.walk(Paths.get(System.getProperty("user.dir") + "/src/IPSEN2/invoice")).forEach(
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
      table_view.setPlaceholder(new Label("Er is geen content om te weergeven"));
      orderService = new OrderService();

      table_view.setItems(FXCollections.observableArrayList(orderService.all()));

      setOnTableRowClickedListener();

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