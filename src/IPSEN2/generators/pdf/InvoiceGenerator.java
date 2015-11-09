package IPSEN2.generators.pdf;

import IPSEN2.models.guest.Guest;
import IPSEN2.models.merchant.Merchant;
import IPSEN2.models.order.Order;
import IPSEN2.models.order.WineOrder;
import IPSEN2.services.merchant.MerchantService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mdbaz on 24-09-2015.
 */
public class InvoiceGenerator {

    /**
     * Generate Invoice pdf file
     *
     * @param order the order
     * @throws DocumentException the document exception
     * @throws IOException       the io exception
     */
    public void generate(Order order) throws DocumentException, IOException{
        Date invoiceDate = order.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM YYYY");
        Guest guest = order.getGuest();
        guest.setOrder(order);
        System.out.println("invoice generator orderID: " + order.getId());
        Document document = new Document();
        Font defaultFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.dir") + "/src/IPSEN2/invoice/"
                + new SimpleDateFormat("dd-MM-yyyy").format(invoiceDate) + " - " + order.getId() + ".pdf"));
        document.setMargins(30, 30, 30, 65);
        writer.setPageEvent(new InvoiceEventListener());
        document.open();
        Paragraph header = new Paragraph("Lionsclub Oegstgeest/Warmond", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);
        Paragraph address = new Paragraph(guest.getFirstName()+ " "+guest.getPrefix()+ " " + guest.getLastName()+ "\n" +
                guest.getAddress().getStreet()+ " " + guest.getAddress().getHouseNumber() + "\n" +
                guest.getAddress().getZipCode() + " "+guest.getAddress().getCity(), defaultFont);
        address.setSpacingBefore(35);
        address.setSpacingAfter(25);
        address.setLeading(15);
        document.add(address);

        Paragraph invoiceDetails = new Paragraph("Factuurdatum: " + sdf.format(invoiceDate) + "\n" +
                "FactuurNummer: "+ order.getId()+ " \n" +
                "Debiteurennummer: "+guest.getId(), defaultFont);

        invoiceDetails.setSpacingAfter(15);
        invoiceDetails.setLeading(15);
        document.add(invoiceDetails);

        Paragraph subject = new Paragraph("Betreft: Onderwerp factuur", defaultFont);
        subject.setSpacingAfter(30);
        document.add(subject);

        PdfPTable orderTable = new PdfPTable(10);
        PdfPCell wineCell = new PdfPCell(new Paragraph("Wijn", defaultFont));
        wineCell.setColspan(5);
        wineCell.setBorder(Rectangle.BOTTOM);
        orderTable.getDefaultCell().setPaddingBottom(10);
        orderTable.getDefaultCell().setBorder(Rectangle.BOTTOM);;

        orderTable.addCell(new Paragraph("Code", defaultFont));
        orderTable.addCell(new Paragraph("Aantal", defaultFont));
        orderTable.addCell(wineCell);
        orderTable.addCell(new Paragraph("Jaar", defaultFont));
        orderTable.addCell(new Paragraph("Per Fles", defaultFont));
        orderTable.addCell(new Paragraph("Bedrag", defaultFont));

        orderTable.getDefaultCell().setPaddingBottom(0);
        orderTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        wineCell.setBorder(Rectangle.NO_BORDER);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);

        for(WineOrder wineOrder : order.getWineOrders() ) {
            orderTable.addCell(new Paragraph(""+wineOrder.getWine().getId(), defaultFont));
            orderTable.addCell(new Paragraph(""+wineOrder.getAmount(), defaultFont));
            wineCell.setPhrase(new Phrase(wineOrder.getWine().getName(), defaultFont));
            orderTable.addCell(wineCell);
            orderTable.addCell(new Paragraph("" + wineOrder.getWine().getYear(), defaultFont));
            orderTable.addCell(new Paragraph("€ "+ numberFormat.format(wineOrder.getWine().getPrice()).replace(" €", ""), defaultFont));
            orderTable.addCell(new Paragraph("€ "+ numberFormat.format(wineOrder.getAmount() *
                    wineOrder.getWine().getPrice()).replace(" €", ""), defaultFont));

            orderTable.completeRow();
        }

        orderTable.addCell(" ");
        orderTable.completeRow();
        Font totalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        PdfPCell totalAmount = new PdfPCell(new Paragraph("€ "+ numberFormat.format(order.getTotalAmount()).replace(" €", ""), totalFont));
        totalAmount.setBorder(Rectangle.TOP);
        totalAmount.setPaddingTop(10);
        PdfPCell totalCell = new PdfPCell(new Paragraph("Totaal", totalFont));
        totalCell.setPaddingTop(10);
        totalCell.setBorder(Rectangle.NO_BORDER);
        orderTable.addCell(totalCell);
        PdfPCell fillerCell = new PdfPCell(new Paragraph(""));
        fillerCell.setColspan(8);
        fillerCell.setBorder(Rectangle.NO_BORDER);
        orderTable.addCell(fillerCell);
        orderTable.addCell(totalAmount);

        orderTable.setSpacingBefore(15);
        orderTable.setSpacingAfter(30);
        orderTable.setWidthPercentage(95);
        orderTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        document.add(orderTable);

        Paragraph retrievalDetails = new Paragraph("Wij verzoeken u vriendelijk het totaalbedrag binnen 7 dagen na factuurdatum over te maken op bankrekening <bankAccountNr> t.n.v <bankAccountName> onder vermelding van het factuurnummer", defaultFont);
        retrievalDetails.setLeading(15);
        retrievalDetails.setSpacingAfter(20);
        document.add(retrievalDetails);

        document.add(new Paragraph("U kunt uw wijnen ophalen op " +  sdf.format(invoiceDate) , defaultFont));
        document.add(new Paragraph("Adres:", defaultFont));

        PdfPTable addressTable = new PdfPTable(1);
        addressTable.setSpacingBefore(5);
        addressTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        addressTable.getDefaultCell().setPaddingLeft(35);
        MerchantService merchantService = new MerchantService();
        Merchant merchant = merchantService.find(merchantService.all().get(0).getId());
        addressTable.addCell(new Paragraph(merchant.getName(), defaultFont));
        addressTable.addCell(new Paragraph(merchant.getAddress().getStreet()
                + " " + merchant.getAddress().getHouseNumber(), defaultFont));
        addressTable.addCell(new Paragraph(merchant.getAddress().getZipCode() + " " +
                merchant.getAddress().getCity(), defaultFont));
        addressTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        document.add(addressTable);

        document.close();
        System.out.println("Succesfully generated IPSEN2.invoice: " + order.getId()+" on Date: "+ sdf.format(invoiceDate));
    }
}
