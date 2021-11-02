package me.gabreuw.pdfgenerator.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PdfGeneratorService {

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream()); // Escrever o documento ("document") na resposta HTTP ("response")

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph title = new Paragraph("This is a title.", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        Paragraph paragraph = new Paragraph("This is a paragraph.", fontParagraph);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(title);
        document.add(paragraph);

        document.close();
    }

}
