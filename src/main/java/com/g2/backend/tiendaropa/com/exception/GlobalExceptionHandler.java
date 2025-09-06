package com.g2.backend.tiendaropa.com.exception;

import com.g2.backend.tiendaropa.com.util.EmailErrorNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private EmailErrorNotifier emailErrorNotifier;

    @ExceptionHandler(Exception.class) // este método debe ser invocado cada vez que cualquier Exception.class aparezca
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        emailErrorNotifier.enviarNotificacionError(
                "Error CRITICO en el backend de LunaStore",
                "Ha ocurrido un error:\n\n" + ex.getMessage() + "\n\n"

        );

        return ResponseEntity.internalServerError().body("Ha ocurrido un error inesperado.");
    }
}
