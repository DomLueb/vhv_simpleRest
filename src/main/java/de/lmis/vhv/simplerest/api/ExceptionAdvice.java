package de.lmis.vhv.simplerest.api;

import de.lmis.vhv.simplerest.business.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorDto> handlePersonNotFoundException(PersonNotFoundException exception) {
        var errorDto = new ErrorDto();
        errorDto.setErrorCode("PERSON_NOT_FOUND_CODE");
        errorDto.setMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }
}
