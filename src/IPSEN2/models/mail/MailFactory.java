package IPSEN2.models.mail;

import IPSEN2.generators.csv.ExportCSV;
import IPSEN2.generators.pdf.InvoiceGenerator;
import IPSEN2.models.guest.Guest;
import IPSEN2.models.merchant.Merchant;
import IPSEN2.services.merchant.MerchantService;
import IPSEN2.services.order.OrderService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Jamie on 28-9-2015.
 */
public class MailFactory {

    private String mail;
    private File file;
    private Guest receiver;
    private int selectedID;
    private MerchantService merchantService;

    /**
     * Instantiates a new Mail factory.
     *
     * @param selectedID the selected id
     */
    public MailFactory(int selectedID) {
        merchantService = new MerchantService();
        Merchant merchant = merchantService.find(selectedID);
        receiver = new Guest(merchant.getName(), merchant.getEmail());
    }

    /**
     * Instantiates a new Mail factory.
     *
     * @param guest the guest
     */
    public MailFactory(Guest guest) {
        this.receiver = guest;
    }

    /**
     * Instantiates a new Mail factory.
     *
     * @param merchant the merchant
     */
    public MailFactory(Merchant merchant) {
        this.mail = merchant.getEmail();
        this.receiver = new Guest(merchant.getName(), merchant.getEmail());
    }


    /**
     * Generates a new mail according to the given type (enum).
     *
     * @param mailType the mail type
     * @return the mail
     * @throws IOException the io exception
     * @throws Exception   the exception
     */
    public Mail generate(MailType mailType) throws IOException, Exception {

        switch(mailType) {
            case THANK:
                return new Mail(this.receiver.getEmail(), "Bedankt voor uw deelname! - Lions Club", this.getMailContent(MailType.THANK, receiver));

            case REMINDER:
                return new Mail(this.receiver.getEmail(), "Uw factuur is nog niet voldaan! - Lions Club", this.getMailContent(MailType.REMINDER, receiver), this.getInvoice(receiver.getOrder().getId()).getAbsolutePath());

            case INVOICE:
                return new Mail(this.receiver.getEmail(), "Uw factuur - Lions Club", this.getMailContent(MailType.INVOICE, receiver), this.getInvoice(receiver.getOrder().getId()).getAbsolutePath());

            case MERCHANT:
                return new Mail(this.receiver.getEmail(), "Wijn bestelling - Lions Club", this.getMailContent(MailType.MERCHANT, receiver), new ExportCSV().createMerchantCSV().getAbsolutePath());

            case EVENT:
                return new Mail(this.receiver.getEmail(), "Het evenement begint binnenkort weer! - Lions Club", this.getMailContent(MailType.EVENT, receiver));
        }

        return new Mail("", "", "", "");
    }

    /**
     * reads the file (containing the mail template data).
     * @param path
     * @param encoding
     * @return returns the content of the text file as a string.
     * @throws IOException
     */
    private String readFile(String path, Charset encoding) throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    /**
     * Generates the correct file path for each system.
     * @param path file path
     * @return
     */
    private String getFilePath(String path) {

        String optimizedPath = "";
        String[] directories = path.split("/");
        String fileName = directories[directories.length - 1];

        for(int i = 0; i < directories.length -1; i++) {
            optimizedPath += directories[i] + File.separator;
        }

        optimizedPath += fileName;

        return optimizedPath;
    }

    /**
     * Returns the invoice pdf file for the given order ID
     * @param orderID id of the order
     * @return file (pdf) invoice
     * @throws IOException
     */
    private File getInvoice(int orderID) throws IOException {
        System.out.println(orderID);
        OrderService orderService = new OrderService();
//        this.file = null;
        Path directory = Paths.get(System.getProperty("user.dir") + "/src/IPSEN2/invoice");
        if (!(directory.toString().contains(" " + orderID + ".pdf"))) {
            try {
                new InvoiceGenerator().generate(orderService.find(orderID));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        try {
            Files.walk(directory).forEach(
                    fileDirectory -> {
                        if ((fileDirectory).toString().contains(" " + orderID + ".pdf")) {
                            file = fileDirectory.toFile();
                        }
                        });
            } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * Returns the content of each mail for the given type (enum)
     * @param mailType type of the mail
     * @param guest guest for which the mail content should be generated
     * @return
     */
    private String getMailContent(MailType mailType, Guest guest) {

        String content = "";

        try {
            switch (mailType) {
                case THANK:
                    content = this.readFile(new File(getFilePath("src/IPSEN2/resources/THANK.txt")).getAbsolutePath(), StandardCharsets.UTF_8);
                    content = String.format(content,
                            getSalutation(guest.getGender()),
                            guest.getLastName(),
                            "Wijnfestijn 2015",
                            "Lions Club Oegstgeest");
                    break;

                case REMINDER:
                    content = this.readFile(new File(getFilePath("src/IPSEN2/resources/REMINDER.txt")).getAbsolutePath(), StandardCharsets.UTF_8);
                    content = String.format(content,
                            getSalutation(guest.getGender()),
                            guest.getLastName(),
                            "Lions Club Oegstgeest");
                    break;

                case INVOICE:
                    content = this.readFile(new File(getFilePath("src/IPSEN2/resources/INVOICE.txt")).getAbsolutePath(), StandardCharsets.UTF_8);
                    content = String.format(content,
                            getSalutation(guest.getGender()),
                            guest.getLastName(),
                            "Wijnfestijn 2015",
                            "Lions Club Oegstgeest");
                    break;

                case MERCHANT:
                    content = this.readFile(new File(getFilePath("src/IPSEN2/resources/MERCHANT.txt")).getAbsolutePath(), StandardCharsets.UTF_8);
                    content = String.format(content, "heer/mevrouw", "Wijnfestijn 2015", "Lions Club Oegstgeest");
                    break;

                case EVENT:
                    content = this.readFile(new File(getFilePath("src/IPSEN2/resources/EVENT.txt")).getAbsolutePath(), StandardCharsets.UTF_8);
                    content = String.format(content,
                            getSalutation(guest.getGender()),
                            guest.getLastName(),
                            "Wijnfestijn 2015",
                            "Oegstgeest",
                            "Lions Club Oegstgeest");
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

    /**
     * returns the proper (string) salutation for each gender
     * @param gender gener of the guest
     * @return string salutation
     */
    private String getSalutation(String gender) {
        String aanhef = "";
        if(gender.toUpperCase().equals("M")) {
            aanhef = "Meneer";
        } else {
            aanhef = "Mevrouw";
        }

        return aanhef;
    }
}
