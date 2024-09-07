package dp.devandre.daftevents.user.infrastructure.web;

import dp.devandre.daftevents.user.application.port.in.GetUserUseCase;
import dp.devandre.daftevents.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final GetUserUseCase getUserUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        User user = getUserUseCase.getUser(id);
        return ResponseEntity.ok(user);
    }
}
