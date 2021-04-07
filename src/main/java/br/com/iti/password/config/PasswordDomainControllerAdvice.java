package br.com.iti.password.config;

import br.com.iti.password.usecase.InvalidPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class PasswordDomainControllerAdvice {

    /**
     * https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/422
     * https://jsonapi.org/format/#errors
     */
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorEntity> handleInvalidPasswordException(InvalidPasswordException exception) {
        log.error("error validating password", exception);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ErrorEntity.builder()
                        .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                        .title("Password validation problem")
                        .detail(exception.getMessage())
                        .build());
    }
}
