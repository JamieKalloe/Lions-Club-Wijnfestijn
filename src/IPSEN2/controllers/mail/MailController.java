package IPSEN2.controllers.mail;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.models.mail.Mail;
import IPSEN2.models.mail.MailFactory;
import IPSEN2.services.guest.GuestService;
import IPSEN2.services.mail.MailService;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-11-15.
 */
public class MailController extends ContentLoader implements Initializable{

    @FXML private ListView<String> listView;
    @FXML private TextArea mailTextArea;
    @FXML private Pane submitButton, cancelButton;

    private ArrayList<Integer> selectedGuestIDs;
    private String selectedMailType;
    private MailService mailService;
    private ArrayList<String> mailTypes;
    private GuestService guestService;
    private Mail mail;

    public MailController(ArrayList<Integer> selectedGuestIDs) {
        this.selectedGuestIDs = selectedGuestIDs;
    }

    private void handleSubmitButton() {
        for (Integer guestID : selectedGuestIDs) {
            try {
                Guest guest = guestService.find(guestID);
                new MailService().send(new MailFactory().generate(mailService.getMailType(selectedMailType), guest));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        addContent(GUESTS);
    }

    private void handleCancelButton() {
        addContent(GUESTS);
    }

    private void handleListView(Event event) {
        selectedMailType = ((ListView) event.getSource()).getSelectionModel().getSelectedItem().toString();
        mail = (mailService.getMail(selectedGuestIDs.get(0), mailService.getMailType(selectedMailType)));
        mailTextArea.setText(mail.getContent());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        guestService = new GuestService();
        mailService = new MailService();
        mailTypes = new ArrayList<>(Arrays.asList("Dankmail", "Uitnodigingsmail"));
        listView.setItems(FXCollections.observableArrayList(mailTypes));
        listView.setOnMouseClicked(event -> handleListView(event));

        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());
//       listView.setCellFactory(createListCellCallBack());
    }
}
