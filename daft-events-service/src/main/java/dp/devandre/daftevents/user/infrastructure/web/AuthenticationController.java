package dp.devandre.daftevents.user.infrastructure.web;

import dp.devandre.daftevents.user.application.dto.request.AuthenticateUserRequest;
import dp.devandre.daftevents.user.application.dto.request.CreateUserRequest;
import dp.devandre.daftevents.user.application.port.in.AuthenticateUserUseCase;
import dp.devandre.daftevents.user.infrastructure.utils.ApiConstants;
import dp.devandre.daftevents.user.infrastructure.utils.ResponseHandler;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(ApiConstants.API_V1_AUTHENTICATION)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticateUserUseCase authenticateUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody CreateUserRequest request) {
        return ResponseHandler.generateResponse(HttpStatus.CREATED, authenticateUserUseCase.register(request), true);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@Valid @RequestBody AuthenticateUserRequest request) {
        return ResponseHandler.generateResponse(HttpStatus.CREATED, authenticateUserUseCase.authenticate(request), true);
    }

    @GetMapping("/activate")
    public ResponseEntity<Object> activate(
            @RequestParam @Pattern(regexp = "^\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}$", message = "Invalid activation code")
            String activationCode
    ) {
        return ResponseHandler.generateResponse(HttpStatus.OK, authenticateUserUseCase.activate(activationCode), true);
    }
}
