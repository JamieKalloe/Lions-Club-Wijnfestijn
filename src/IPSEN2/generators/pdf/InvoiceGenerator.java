package IPSEN2.generators.pdf;

import IPSEN2.models.guest.Guest;
import IPSEN2.models.order.Order;
import IPSEN2.models.order.WineOrder;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mdbaz on 24-09-2015.
 */
public class InvoiceGenerator {

    public String generate(Order order) throws DocumentException, IOException{
        Date invoiceDate = new Date();
        Guest guest = order.getGuest();
        guest.setOrder(order);
        Document document = new Document();
        Font defaultFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        String filePath = ""+new SimpleDateFormat("YYYY-MM-dd").format(invoiceDate)+" - "+order.getId()+".pdf";
        order.setInvoicePath(filePath);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
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

        Paragraph invoiceDetails = new Paragraph("Factuurdatum: " + new SimpleDateFormat("dd MMMM YYYY").format(invoiceDate) + "\n" +
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

        for(WineOrder wineOrder : order.getWineOrders() ) {
            orderTable.addCell(new Paragraph(""+wineOrder.getWine().getWineID(), defaultFont));
            orderTable.addCell(new Paragraph(""+wineOrder.getAmount(), defaultFont));
            wineCell.setPhrase(new Phrase(wineOrder.getWine().getName(), defaultFont));
            orderTable.addCell(wineCell);
            orderTable.completeRow();
        }
        orderTable.addCell(" ");
        orderTable.completeRow();
        Font totalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        PdfPCell totalAmount = new PdfPCell(new Paragraph(""+order.getTotalAmount(), totalFont));
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

        document.add(new Paragraph("U kunt uw wijnen ophalen op <Date> tussen <startTime> en <endTime> h.", defaultFont));
        document.add(new Paragraph("Adres:", defaultFont));

        PdfPTable addressTable = new PdfPTable(1);
        addressTable.setSpacingBefore(5);
        addressTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        addressTable.getDefaultCell().setPaddingLeft(35);
        addressTable.addCell(new Paragraph("Naam wijnhandelaar", defaultFont));
        addressTable.addCell(new Paragraph("Straat + huisnummer", defaultFont));
        addressTable.addCell(new Paragraph("Postcode + Plaats", defaultFont));
        addressTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        document.add(addressTable);

        document.close();
        System.out.println("Succesfully generated invoice: " + order.getId()+" on Date: "+new SimpleDateFormat("dd MMMM YYYY").format(invoiceDate));
        return filePath;
    }
}
