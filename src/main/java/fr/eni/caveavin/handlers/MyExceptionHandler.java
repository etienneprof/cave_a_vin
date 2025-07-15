package fr.eni.caveavin.handlers;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class MyExceptionHandler {
    private final MessageSource messageSource;

    public MyExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> capturerException(MethodArgumentNotValidException manve, Locale locale) {
        StringBuilder message = new StringBuilder();

        message.append(messageSource.getMessage("exception.bouteille.titre", null, locale));
        message.append("\n\t");

        for (var error : manve.getAllErrors()) {
            message.append(error.getDefaultMessage());
            message.append("\n\t");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message.toString());
    }


}
