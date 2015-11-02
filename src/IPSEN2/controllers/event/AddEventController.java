package IPSEN2.controllers.event;

import IPSEN2.ContentLoader;
import IPSEN2.services.event.EventService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Philip on 13-10-15.
 */
public class AddEventController extends ContentLoader implements Initializable{

    private String name;
    private String city;
    private String address;
    private String houseNumber;
    private String zipCode;
    private LocalDate date;

    private HashMap data;
    private EventService service;

    @FXML private Pane submitButton, cancelButton;
    @FXML private TextField eventNameTextField, cityTextField,
            addressTextField, houseNumberTextField, zipCodeTextField;

    @FXML private DatePicker datePicker;

    /**
     * Instantiates a new Add event controller.
     */
    public AddEventController() {
    }

    @FXML
    private void handleCancelButton() {
        addContent(EVENTS);
    }

    @FXML
    private void handleSubmitButton() {
        name = eventNameTextField.getText();
        city = cityTextField.getText();
        address = addressTextField.getText();
        houseNumber = houseNumberTextField.getText();
        zipCode = zipCodeTextField.getText();
        date =  datePicker.getValue();
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        if (date == null) {
            date = LocalDate.now();
        }

        data = new HashMap();
        data.put("name", name);
        data.put("zipCode", zipCode);
        data.put("street", address);
        data.put("houseNumber", houseNumber);
        data.put("country", "Nederland");
        data.put("city", city);
        data.put("startDate", dateFormatter.format(date));
        data.put("endDate", dateFormatter.format(date));

        eventId = service.add(data);

        if (eventId != -1)
        {
            addContent(EVENTS);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());
        service = new EventService();
    }
}
