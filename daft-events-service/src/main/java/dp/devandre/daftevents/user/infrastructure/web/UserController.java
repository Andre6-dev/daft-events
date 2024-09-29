package dp.devandre.daftevents.user.infrastructure.web;

import dp.devandre.daftevents.user.application.port.in.GetUserUseCase;
import dp.devandre.daftevents.user.infrastructure.utils.ApiConstants;
import dp.devandre.daftevents.user.infrastructure.utils.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.API_V1_USERS)
@RequiredArgsConstructor
public class UserController {

    private final GetUserUseCase getUserUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") Integer id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, getUserUseCase.getUser(id), true);
    }

    @GetMapping()
    public ResponseEntity<Object> getUsers() {
        return ResponseHandler.generateResponse(HttpStatus.OK, getUserUseCase.getUsers(), true);
    }
}
