package me.gabreuw.pdfgenerator.service.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@ResponseStatus(code = SERVICE_UNAVAILABLE)
public class CannotGeneratePDFException extends RuntimeException {

    public CannotGeneratePDFException() {
        super("Cannot generate PDF.");
    }

}
