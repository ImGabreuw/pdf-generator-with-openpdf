package me.gabreuw.pdfgenerator.controller;

import lombok.RequiredArgsConstructor;
import me.gabreuw.pdfgenerator.service.PdfGeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;

@Controller
@RequestMapping(path = "/pdf")
@RequiredArgsConstructor
public class PdfExporterController {

    private final PdfGeneratorService pdfGeneratorService;

    @GetMapping
    public void generatePDF(HttpServletResponse response) {
        response.setContentType(APPLICATION_PDF_VALUE);

        // informar ao browser o tipo de arquivo presente na resposta
        response.setHeader(
                CONTENT_DISPOSITION,
                "attachment; filename=pdf_" + getCurrentDateTime() + ".pdf"
        );

        pdfGeneratorService.export(response);
    }

    private String getCurrentDateTime() {
        return LocalDateTime
                .now()
                .format(DateTimeFormatter.ISO_DATE_TIME);
    }

}
