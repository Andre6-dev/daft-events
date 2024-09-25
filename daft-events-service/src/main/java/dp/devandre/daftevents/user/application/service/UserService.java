package dp.devandre.daftevents.user.application.service;

import dp.devandre.daftevents.user.application.dto.CreateUserRequest;
import dp.devandre.daftevents.user.application.dto.CreateUserResponse;
import dp.devandre.daftevents.user.application.dto.UserListResponse;
import dp.devandre.daftevents.user.application.mapper.UserListResponseMapper;
import dp.devandre.daftevents.user.application.port.in.CreateUserUseCase;
import dp.devandre.daftevents.user.application.port.in.GetUserUseCase;
import dp.devandre.daftevents.user.application.port.out.LoadUserPort;
import dp.devandre.daftevents.user.application.port.out.SaveUserPort;
import dp.devandre.daftevents.user.domain.User;
import dp.devandre.daftevents.shared.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements GetUserUseCase, CreateUserUseCase {

    private final LoadUserPort loadUserPort;
    private final SaveUserPort saveUserPort;
    private final UserListResponseMapper userListResponseMapper;

    @Override
    public User getUser(Integer id) {
        log.info("Getting user with id: {}", id);
        return loadUserPort.loadUser(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<UserListResponse> getUsers() {
        log.info("Getting all users");
        return loadUserPort.loadUsers()
                .stream()
                .map(userListResponseMapper::toDto)
                .toList();
    }

    @Override
    public CreateUserResponse createUserCommand(CreateUserRequest createUserRequest) {
        log.info("Creating user with email: {}", createUserRequest.email());
        if (saveUserPort.existsByEmail(createUserRequest.email())) {
            throw new IllegalArgumentException("Email already exists: " + createUserRequest.email());
        }

        return null;
    }
}
