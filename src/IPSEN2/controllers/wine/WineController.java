package IPSEN2.controllers.wine;

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

public class WineController extends ContentLoader implements Initializable{

    @FXML private TableView table_view;

    private int selectedWineID;

    public void handleAddButton() throws IOException{
        addContent(new AddWineController(), EDIT_WINE_DIALOG);
    }

    public void handleEditButton() throws IOException {
        if (selectedWineID != 0) {
            addContent(new EditWineController(), EDIT_WINE_DIALOG);
        }
    }

    public void handleRemoveButton() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContentLoader.setMainFrameTitle(ContentLoader.WINES_TITLE);
        table_view.setPlaceholder(new Label("Er is geen content om te weergeven"));
    }


    private class RowSelectChangeListener implements ChangeListener {

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            try{
                selectedWineID = 0;
                System.out.println(selectedWineID);} catch (NullPointerException e){
                System.out.print("No items in table to select");
                selectedWineID = 0;
            }

        }
    }
}