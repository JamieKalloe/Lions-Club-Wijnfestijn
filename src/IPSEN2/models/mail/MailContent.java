package IPSEN2.models.mail;

/**
 * Created by Jamie on 28-9-2015.
 */
public class MailContent {

    private MailType type;

    private final String THANK_MAIL_TITLE = "Bedankt voor uw deelname!";
    private final String EVENT_MAIL_TITLE = "Wijnfestijn 2015 - Nog 1 week te gaan!";
    private final String INVOICE_MAIL_TITLE = "Factuur - bestelling Wijnfestijn 2015 - Lions Club Oegstgeest";
    private final String NOORDMAN_MAIL_TITLE = "Bestelling Lions Club Oegstgeest";

    private final String THANK_MAIL_CONTENT = "Beste klant,\n\nBij deze willen wij u bedanken voor het deelnemen aan het wijnfestijn 2015 van de Lions Club Oegstgeest.\nGraag tot volgend jaar!\n\nMet vriendelijke groet,\nLions Club Oegstgeest";
    private final String EVENT_MAIL_CONTENT = "Beste klant\n\n Over 1 week begint het wijnfestijn van de Lions Club Oegstgeest.\nWij hopen u daar te zien!\n\nMet vriendelijke groet,\nLions Club Oegstgeest";
    private final String INVOICE_MAIL_CONTENT = "Beste klant,\n\nBedankt voor uw bestelling bij de Lions Club!\nIn de bijlage vindt u de factuur van uw bestelling.\n\nMet vriendelijke groet,\nLions Club Oegstgeest";
    private final String NOORMAN_MAIL_CONTENT = "Beste leverancier Noordman,\n\nIn de bijlage vindt u onze bestelling.\nMocht u vragen hebben, neem dan contact op.\n\nMet vriendelijke groet,\nLions Club Oegstgeest";

    private final String DEFAULT_MAIL_CONTENT = "Dit is een automatisch gegenereerde mail";
    private final String DEFAULT_MAIL_TITLE = "Test mail";

    public MailContent(MailType type) {
        this.type = type;
    }

    public String generateTitle() {

        switch (type) {
            case THANK:
                return this.THANK_MAIL_TITLE;
            case EVENT:
                return this.EVENT_MAIL_TITLE;
            case INVOICE:
                return this.INVOICE_MAIL_TITLE;
            case NOORDMAN:
                return this.NOORDMAN_MAIL_TITLE;
        }

        return this.DEFAULT_MAIL_TITLE;
    }

    public String generateContent() {

        switch(type) {
            case THANK:
                return this.THANK_MAIL_CONTENT;
            case EVENT:
                return this.EVENT_MAIL_CONTENT;
            case INVOICE:
                return this.INVOICE_MAIL_CONTENT;
            case NOORDMAN:
                return this.NOORMAN_MAIL_CONTENT;
        }

        return this.DEFAULT_MAIL_CONTENT;
    }
}
