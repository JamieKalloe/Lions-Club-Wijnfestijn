package IPSEN2.services.message;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * The Messaging service.
 * Date of creation: 29-10-15.
 *
 * @author Thomas Neuteboom
 */
public class Messaging {

    public boolean isShowingMessage;

    private static Messaging messagingInstance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized Messaging getInstance()
    {
        if(messagingInstance == null)
        {
            messagingInstance = new Messaging();
        }

        return messagingInstance;
    }

    /**
     * Show message.
     *
     * @param title   the title
     * @param header  the header
     * @param message the message
     */
    public void show(String title, String header, String message)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        if (!this.isShowingMessage)
        {
            this.isShowingMessage = true;

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
                this.isShowingMessage = false;
            }
        }
    }
}
