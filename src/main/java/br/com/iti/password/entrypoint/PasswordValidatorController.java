package br.com.iti.password.entrypoint;

import br.com.iti.password.usecase.InvalidPasswordException;
import br.com.iti.password.usecase.PasswordValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
@Slf4j
public class PasswordValidatorController {
    private final PasswordValidator passwordValidator;

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity changePassword(@RequestBody ChangePasswordRequest request) throws InvalidPasswordException {
        log.info("Received a request to change password");
        passwordValidator.execute(request.getPassword());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
