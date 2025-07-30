package br.com.monee.api.exception;

import br.com.monee.api.exception.custom.DuplicateRecordException;
import br.com.monee.api.exception.custom.UnprocessableEntityException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Escuta as exceções lançadas nos controllers que estejam relacionadas
     * à entidades não processáveis. Essas exceções são disparadas
     * quando uma entidade viola a annotation valid do record DTO da classe
     * @param e exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldError = e.getFieldErrors();
        List<ErrorDto> errorDtosList = fieldError
                .stream()
                .map(fe -> new ErrorDto(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return new ResponseErrorDTO(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação, campo obrigatório não informado",
                errorDtosList
        );
    }

    /**
     * Escuta as exceções lançadas nos controllers que estejam relacionadas
     * com registros duplicados.
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO handleDuplicateRecordException(DuplicateRecordException e){
        return new ResponseErrorDTO(HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                null);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseErrorDTO handleUnprocessableEntityException(UnprocessableEntityException e){
        return new ResponseErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                e.getMessage(),
                null);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseErrorDTO handleEntityNotFoundException (EntityNotFoundException e) {
        return new ResponseErrorDTO(HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                null);
    }
}
