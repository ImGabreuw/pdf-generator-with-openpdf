package me.gabreuw.pdfgenerator.controller;

import lombok.RequiredArgsConstructor;
import me.gabreuw.pdfgenerator.service.PdfGeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
public class PdfExporterController {

    private final PdfGeneratorService pdfGeneratorService;

    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        String currentDateTime = LocalDateTime
                .now()
                .format(DateTimeFormatter.ISO_DATE_TIME);

        // informar ao browser o tipo de arquivo presente na resposta
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        pdfGeneratorService.export(response);
    }

}
