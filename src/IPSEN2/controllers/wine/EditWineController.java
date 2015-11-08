package IPSEN2.controllers.wine;

import IPSEN2.ContentLoader;
import IPSEN2.models.wine.Wine;
import IPSEN2.services.wine.WineService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-10-15.
 */
public class EditWineController extends ContentLoader implements Initializable{

    @FXML
    private RadioButton typeWit, typeRood, typeRose, typeCava;

    @FXML private TextField wineNameTextField;
    @FXML private TextField wineTypeTextField;
    @FXML private TextField countryNameTextField;
    @FXML private TextField regionNameTextField;
    @FXML private TextField yearTextField;
    @FXML private TextField priceTextField;

    @FXML private Pane submitButton, cancelButton;

    private String name;
    private int type;
    private String country;
    private String region;
    private int year;
    private int pressedButtonIndex;
    private double price;

    private HashMap data;
    private WineService service;
    private int selectedWineID;
    private ResourceBundle resources;

    public EditWineController(int selectedWineID) {
        this.selectedWineID = selectedWineID;
    }

    //@FXML
    public void handleCancelButton() {
        addContent(resources.getString("WINE"));
    }

    public void handleSubmitButton() {
        this.selectedWineID = selectedWineID;
        name = wineNameTextField.getText();

        // Wine-type
        if (typeWit.isSelected()) {
            type = 1;
        } else if (typeRood.isSelected()) {
            type = 2;
        } else if (typeCava.isSelected()) {
            type = 3;
        } else if (typeRose.isSelected()) {
            type = 4;
        }
        //type = wineTypeTextField.getText();
        country = countryNameTextField.getText();
        region = regionNameTextField.getText();
        year = Integer.parseInt(yearTextField.getText());
        price = Double.parseDouble(priceTextField.getText());

        data = new HashMap();

       // data.put("id", selectedWineID);
        data.put("name", name);
        data.put("country", country);
        data.put("region", region);
        data.put("year", year);
        data.put("typeId", type);
        data.put("price", price);
        service.edit(selectedWineID,data);

        addContent(resources.getString("WINE"));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        service = new WineService();


        Wine wine = service.find(selectedWineID);
        wineNameTextField.setText(wine.getName());
        countryNameTextField.setText(wine.getCountry());
        regionNameTextField.setText(wine.getRegion());
        yearTextField.setText(String.valueOf(wine.getYear()));
        priceTextField.setText(String.valueOf(wine.getPrice()));

        int wineTypeId = wine.getType().getId();
        // Winetype thing
        if (wineTypeId == 1) {
            typeWit.setSelected(true);
        } else if (wineTypeId == 2) {
            typeRood.setSelected(true);
        } else if (wineTypeId == 3) {
            typeCava.setSelected(true);
        } else if (wineTypeId == 4) {
            typeRose.setSelected(true);
        }
        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());

    }


}