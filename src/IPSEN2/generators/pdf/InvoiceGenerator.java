package IPSEN2.generators.pdf;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * Created by mdbaz on 24-09-2015.
 */
public class InvoiceGenerator {

    public void createInvoice(String filename) throws DocumentException, IOException{
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(filename));

        document.open();
        PdfPTable table = new PdfPTable(2);

        table.addCell("LOL");
        table.addCell("LOL");
        table.addCell("LOL");
        table.addCell("LOL");
        table.addCell("LOL");
        table.addCell("LOL");
        table.addCell("LOL");
        document.add(table);

        document.close();
    }
}
