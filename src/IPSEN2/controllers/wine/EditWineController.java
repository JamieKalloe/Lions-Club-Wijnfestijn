package IPSEN2.controllers.wine;

import IPSEN2.ContentLoader;
import IPSEN2.models.wine.Wine;
import IPSEN2.models.wine.WineType;
import IPSEN2.services.wine.WineService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-10-15.
 */
public class EditWineController extends ContentLoader implements Initializable{

    @FXML
    private SplitMenuButton wineTypeButton;

    @FXML
    private MenuItem typeWit, typeRood, typeRose, typeCava;


    @FXML private TextField wineNameTextField;
    @FXML private TextField countryNameTextField;
    @FXML private TextField regionNameTextField;
    @FXML private TextField yearTextField;
    @FXML private TextField priceTextField;

    @FXML private Pane submitButton, cancelButton;

    private String name;
    private String country;
    private String region;
    private int year;
    private int pressedButtonIndex;
    private double price;
    ArrayList<WineType> types;

    private HashMap data;
    private WineService service;
    private int selectedWineID;

    public EditWineController(int selectedWineID) {
        this.selectedWineID = selectedWineID;
    }

    @FXML
    public void handleWineTypeButton(ActionEvent event) {
        Object wineType = event.getSource();

        pressedButtonIndex = wineTypeButton.getItems().indexOf(wineType);

        wineTypeButton.setText(types.get(pressedButtonIndex).getName());
    }

    public void handleCancelButton() {
        addContent(WINE);
    }

    public void handleSubmitButton() {
        name = wineNameTextField.getText();
        country = countryNameTextField.getText();
        region = regionNameTextField.getText();
        year = Integer.parseInt(yearTextField.getText());
        price = Double.parseDouble(priceTextField.getText());

        data = new HashMap();

        data.put("name", name);
        data.put("country", country);
        data.put("region", region);
        data.put("year", year);
        data.put("type_id", pressedButtonIndex);
        data.put("price", price);
        service.subscribe(data);

        addContent(WINE);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        service = new WineService();

        Object wineType = service.find(selectedWineID).getType();
        pressedButtonIndex = wineTypeButton.getItems().indexOf(wineType);
        wineTypeButton.setText(service.find(selectedWineID).getType().getName());

        Wine wine = service.find(selectedWineID);
        wineNameTextField.setText(wine.getName());
        countryNameTextField.setText(wine.getCountry());
        regionNameTextField.setText(wine.getRegion());
        yearTextField.setText(String.valueOf(wine.getYear()));
        priceTextField.setText(String.valueOf(wine.getPrice()));
        wineTypeButton.setText(wine.getType().getName());

        //wine.getType().getName());
        // wineTypeButton.set

        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());


    }


}