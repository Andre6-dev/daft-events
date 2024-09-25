package dp.devandre.daftevents.user.infrastructure.web;

import dp.devandre.daftevents.user.application.dto.UserListResponse;
import dp.devandre.daftevents.user.application.port.in.GetUserUseCase;
import dp.devandre.daftevents.user.domain.User;
import dp.devandre.daftevents.user.infrastructure.utils.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_V1_USERS)
@RequiredArgsConstructor
public class UserController {

    private final GetUserUseCase getUserUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        User user = getUserUseCase.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping()
    public ResponseEntity<List<UserListResponse>> getUsers() {
        List<UserListResponse> users = getUserUseCase.getUsers();
        return ResponseEntity.ok(users);
    }
}
