package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import IPSEN2.controllers.handlers.TableViewSelectHandler;
import IPSEN2.controllers.listeners.TableViewListener;
import IPSEN2.controllers.mail.MailController;
import IPSEN2.generators.pdf.InvoiceGenerator;
import IPSEN2.models.TableViewItem;
import IPSEN2.models.order.Order;
import IPSEN2.services.message.Messaging;
import IPSEN2.services.order.OrderService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The type Order controller.
 */
public class OrderController extends ContentLoader implements Initializable, TableViewListener {

   private int selectedOrderID;

   @FXML private TableView<TableViewItem> tableView;
   @FXML private TableColumn idColumn;
   @FXML private TableColumn lastNameColumn;
   @FXML private TableColumn totalAmountColumn;
   @FXML private TableColumn statusColumn;
   @FXML private TableColumn invoiceColumn;

   private ResourceBundle resources;

   private ObservableList<TableViewItem> orderData;

   private OrderService orderService;
   private  ArrayList<Integer> selectedRows;


   /**
    * Handles add button
    */
   @FXML
   private void handleAddButton(){
      addContent(resources.getString("SELECT_GUEST_DIALOG"));
   }

   /**
    * Hanldes mail button
    */
   @FXML
   private void handleMailButton() {
      if (selectedRows.size() != 0) {

         addContent(new MailController(selectedRows, 3), resources.getString("MAIL"));
      }
   }

   /**
    * Handle remove button.
    */
   public void handleRemoveButton() {
      if (selectedRows.size() != 0) {
        selectedRows.forEach(row -> orderService.remove(row));
         orderData = FXCollections.observableArrayList(orderService.all());
         addContent(resources.getString("ORDER"));
         } else {
         Messaging.getInstance().show(
                 "Foutmelding",
                 "Verwijderfout",
                 "Er is geen bestelling geselecteerd"
         );
      }
   }

   @Override
   public void setSelectedRows(ArrayList selectedRows) {
      this.selectedRows = selectedRows;
   }


   @Override
   public void setSelectedItem(int selectedItemId) {
      this.selectedOrderID = selectedItemId;
   }


   @Override
   public void openEditMenu() {
      addContent(new EditOrderController(selectedOrderID, null), resources.getString("EDIT_ORDER_DIALOG"));
   }

   /**
    * Creates  table cell with open invoice button and listener for all items inside TableView
    *
    * @return returns the CallBack of the attached checkbox cell
    */
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

   /**
    * Shows all TableView Items <br>
    * Sets TableViewSelectHandler for TableView Object
    */
   private void showTable() {
      TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(tableView, this);
      tableViewSelectHandler.createCheckBoxColumn();
      tableViewSelectHandler.createSelectAllCheckBox();

      tableView.setItems(orderData);

      idColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
      lastNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
         public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> param) {
            if (param.getValue() != null && param.getValue().getGuest().getLastName() != null) {
               return new SimpleStringProperty(param.getValue().getGuest().getLastName());
            }
            return new SimpleStringProperty("");
         }
      });

      totalAmountColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
         public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> param) {
            if (param.getValue() != null && param.getValue().getTotalAmount() != 0) {
               NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
               return new SimpleStringProperty("€ " + numberFormat.format(param.getValue().getTotalAmount()).replace(" €", ""));
            }
            return new SimpleStringProperty("€ 0,00");
         }
      });
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

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      this.resources = resources;
      ContentLoader.setMainFrameTitle(resources.getString("ORDERS_TITLE"));
      selectedRows = new ArrayList<>();
      tableView.setPlaceholder(new Label("Er is geen content om te weergeven"));
      orderService = new OrderService();
      orderData = FXCollections.observableArrayList(orderService.all());

      showTable();


   }

}