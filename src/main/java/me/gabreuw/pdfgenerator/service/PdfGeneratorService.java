package me.gabreuw.pdfgenerator.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.log4j.Log4j2;
import me.gabreuw.pdfgenerator.service.exception.CannotGeneratePDFException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lowagie.text.Element.ALIGN_CENTER;
import static com.lowagie.text.Element.ALIGN_LEFT;

@Log4j2
@Service
public class PdfGeneratorService {

    public void export(HttpServletResponse response) {
        try (Document document = new Document(PageSize.A4)) {
            PdfWriter.getInstance(document, response.getOutputStream()); // Escrever o documento ("document") na resposta HTTP ("response")

            document.open();

            Paragraph title = new Paragraph("This is a title.", getFontTitle());
            title.setAlignment(ALIGN_CENTER);

            Paragraph paragraph = new Paragraph("This is a paragraph.", getFontParagraph());
            paragraph.setAlignment(ALIGN_LEFT);

            document.add(title);
            document.add(paragraph);
        } catch (IOException e) {
            log.warn("Cannot generate PDF. {}", e.getMessage());
            throw new CannotGeneratePDFException();
        }
    }

    private Font getFontTitle() {
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        return fontTitle;
    }

    private Font getFontParagraph() {
        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        return fontParagraph;
    }

}
