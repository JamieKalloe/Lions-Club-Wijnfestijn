package IPSEN2.models.mail;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

/**
 * Created by Jamie on 28-9-2015.
 */
public class MailFactory {

    public Mail generate(MailType mailType) {

        switch(mailType) {
            case THANK:
                return new Mail("ipsen2groep1@hotmail.com", "Bedankt voor uw deelname! - Lions Club", this.getMailContent(MailType.THANK), "D:\\opdracht_wc4_ifit.pdf");

            case REMINDER:
                return new Mail("ipsen2groep1@hotmail.com", "Uw factuur is nog niet voldaan! - Lions Club", this.getMailContent(MailType.REMINDER), "D:\\opdracht_wc4_ifit.pdf");

            case INVOICE:
                return new Mail("ipsen2groep1@hotmail.com", "Uw factuur - Lions Club", this.getMailContent(MailType.EVENT.INVOICE), "D:\\opdracht_wc4_ifit.pdf");

            case MERCHANT:
                return new Mail("ipsen2groep1@hotmail.com", "Wijn bestelling - Lions Club", this.getMailContent(MailType.MERCHANT), "D:\\opdracht_wc4_ifit.pdf");

            case EVENT:
                return new Mail("ipsen2groep1@hotmail.com", "Het evenement begint binnenkort weer! - Lions Club", this.getMailContent(MailType.EVENT), "D:\\opdracht_wc4_ifit.pdf");
        }

        return new Mail("", "", "", "");
    }

    private String readFile(String path, Charset encoding) throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }


    private String getMailContent(MailType mailType) {

        String content = "";

        try {
            switch (mailType) {
                case THANK:
                    content = this.readFile("C:\\Users\\Jamie\\Documents\\Mails\\THANK.txt", StandardCharsets.UTF_8);
                    content = String.format(Locale.getDefault(), content, "meneer", "de Vries", "Wijnfestijn 2015", "Lions Club Oegstgeest");
                    break;

                case REMINDER:
                    content = this.readFile("C:\\Users\\Jamie\\Documents\\Mails\\REMINDER.txt", StandardCharsets.UTF_8);
                    content = String.format(Locale.getDefault(), content, "meneer", "de Vries", "Lions Club Oegstgeest");
                    break;

                case INVOICE:
                    content = this.readFile("C:\\Users\\Jamie\\Documents\\Mails\\INVOICE.txt", StandardCharsets.UTF_8);
                    content = String.format(Locale.getDefault(), content, "meneer", "de Vries", "Wijnfestijn 2015", "Lions Club Oegstgeest");
                    break;

                case MERCHANT:
                    content = this.readFile("C:\\Users\\Jamie\\Documents\\Mails\\INVOICE.txt", StandardCharsets.UTF_8);
                    content = String.format(Locale.getDefault(), content, "heer/mevrouw", "Wijnfestijn 2015", "Lions Club Oegstgeest");
                    break;

                case EVENT:
                    content = this.readFile("C:\\Users\\Jamie\\Documents\\Mails\\EVENT.txt", StandardCharsets.UTF_8);
                    content = String.format(Locale.getDefault(), content, "meneer", "de Vries", "Wijnfestijn 2015", "Oegstgeest", "Lions Club Oegstgeest");
                    break;

                default:
                    break;
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    //haal text uit een file, format de string met een speciaal token.
    //bufferedReader, haalt tekst uit de file (private method).
    //stop tekst in string, format de string met de data vanuit order (-> guest etc).

}
