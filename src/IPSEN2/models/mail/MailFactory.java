package IPSEN2.models.mail;

/**
 * Created by Jamie on 28-9-2015.
 */
public class MailFactory {

    public Mail generate(MailType mailType) {

        switch(mailType) {
            case THANK:
                //maak thank mail
                break;

            case REMINDER:
                //maak remind mail
                break;

            case INVOICE:
                //maak invoice mail
                break;

            case MERCHANT:
                //maak merchant mail
                break;

            case EVENT:
                //maak eventmail
                break;
        }

        return new Mail("", "", "", "");
    }

    //haal text uit een file, format de string met een speciaal token.

}
