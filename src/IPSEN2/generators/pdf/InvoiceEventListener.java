package IPSEN2.generators.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

/**
 * Created by mdbaz on 01-10-2015.
 */
public class InvoiceEventListener extends PdfPageEventHelper {

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        Font defaultFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);


        try {
            PdfPTable footerTable = new PdfPTable(3);
            footerTable.setTotalWidth(document.right(document.leftMargin()));

            footerTable.getDefaultCell().setBorder(Rectangle.TOP);
            footerTable.addCell(new Paragraph("Lionsclub Oegstgeest/Warmond", defaultFont));
            footerTable.completeRow();

            footerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            footerTable.addCell(new Paragraph("Betaalrekening", defaultFont));
            footerTable.addCell(new Paragraph(": wat nummers hierzo", defaultFont));
            footerTable.completeRow();
            footerTable.addCell(new Paragraph("Inschrijvnummer KvK Rijnland", defaultFont));
            footerTable.addCell(new Paragraph(": wat nummers hierzo ", defaultFont));
            footerTable.completeRow();
            footerTable.writeSelectedRows(0, -1, document.leftMargin(), document.bottom(footerTable.getTotalHeight()) - document.bottomMargin() + 15, writer.getDirectContent());

            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_RIGHT, new Phrase(String.format("%d", writer.getPageNumber()), defaultFont),
                    document.right(), document.bottom()-document.bottomMargin()+18, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
