package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderController extends ContentLoader implements Initializable{

   private int selectedOrderID;

   @FXML
   private TableView table_view;

   public OrderController() {

   }

   public void handleAddButton() throws IOException {
      addContent(new AddOrderController(), EDIT_ORDER_DIALOG);
   }

   public void handleEditButton() throws IOException {
      if (selectedOrderID != 0) {
         addContent(new EditOrderController(), EDIT_ORDER_DIALOG);
      }
   }

   public void handleRemoveButton() {

   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      ContentLoader.setMainFrameTitle(ContentLoader.ORDERS_TITLE);
      table_view.setPlaceholder(new Label("Er is geen content om te weergeven"));
   }

   private class RowSelectChangeListener implements ChangeListener {

      @Override
      public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//         try {
//            selectedGuestID = table_view.getSelectionModel().getSelectedItem().getGuestID();
//            System.out.println(selectedGuestID);} catch (NullPointerException e){
//            System.out.print("No items in table to select");
//            selectedGuestID = 0;
//         }

      }
   }

}