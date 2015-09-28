package IPSEN2.models.mail;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Jamie on 28-9-2015.
 */
public class MailFactory {

    public Mail generate(MailType mailType) {

        switch(mailType) {
            case THANK:
                return new Mail("ipsen2groep1@hotmail.com", "Thank Mail", "Thank Content", "D:\\opdracht_wc4_ifit.pdf");

            case REMINDER:
                return new Mail("ipsen2groep1@hotmail.com", "Remind Mail", "Remind Content", "D:\\opdracht_wc4_ifit.pdf");

            case INVOICE:
                return new Mail("ipsen2groep1@hotmail.com", "Invoice Mail", "Invoice Content", "D:\\opdracht_wc4_ifit.pdf");

            case MERCHANT:
                return new Mail("ipsen2groep1@hotmail.com", "Merchant Mail", "Merchant Content", "D:\\opdracht_wc4_ifit.pdf");

            case EVENT:
                return new Mail("ipsen2groep1@hotmail.com", "Event Mail", this.getMailContent(), "D:\\opdracht_wc4_ifit.pdf");
        }

        return new Mail("", "", "", "");
    }

    private String readFile(String path, Charset encoding) throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }


    private String getMailContent() {
        String content = "";
        try {

            content = this.readFile("C:\\Users\\Jamie\\Documents\\Mails\\tekstt.txt", StandardCharsets.UTF_8);
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
