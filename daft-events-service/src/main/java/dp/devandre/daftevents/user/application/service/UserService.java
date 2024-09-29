package dp.devandre.daftevents.user.application.service;

import dp.devandre.daftevents.user.application.dto.request.CreateUserRequest;
import dp.devandre.daftevents.user.application.dto.response.CreateUserResponse;
import dp.devandre.daftevents.user.application.dto.response.UserListResponse;
import dp.devandre.daftevents.user.application.mapper.UserListResponseMapper;
import dp.devandre.daftevents.user.application.port.in.CreateUserUseCase;
import dp.devandre.daftevents.user.application.port.in.GetRoleUseCase;
import dp.devandre.daftevents.user.application.port.in.GetUserUseCase;
import dp.devandre.daftevents.user.application.port.out.LoadUserPort;
import dp.devandre.daftevents.user.application.port.out.SaveUserPort;
import dp.devandre.daftevents.user.domain.Role;
import dp.devandre.daftevents.user.domain.User;
import dp.devandre.daftevents.shared.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements GetUserUseCase, CreateUserUseCase {

    private static final String defaultProfileUrl = "https://images.squarespace-cdn.com/content/v1/5eb48d3fef49df153d320521/1618032587947-LCJQDE1Q9CGKRRM774H2/Daft+Punk+Toy+Face+II+.jpg";

    private final LoadUserPort loadUserPort;
    private final SaveUserPort saveUserPort;
    private final GetRoleUseCase getRoleUseCase;
    private final UserListResponseMapper userListResponseMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUser(Integer id) {
        log.info("Getting user with id: {}", id);
        return loadUserPort.loadUser(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Getting user with email: {}", email);
        return loadUserPort.loadUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
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
    public boolean isUserEmailExists(String email) {
        log.info("Checking if user email exists: {}", email);
        return saveUserPort.existsByEmail(email);
    }

    @Override
    public User createUserCommand(CreateUserRequest createUserRequest) {
        log.info("Creating user with email: {}", createUserRequest.email());

        Role role = getRoleUseCase.getRoleByName("ROLE_USER");
        String username = generateUsername(createUserRequest);

        User userToRegister = User.builder()
                .firstName(createUserRequest.firstName())
                .lastName(createUserRequest.lastName())
                .username(username)
                .email(createUserRequest.email())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .documentNumber(createUserRequest.documentNumber())
                .phoneNumber(createUserRequest.phoneNumber())
                .address(createUserRequest.address())
                .profileUrl(defaultProfileUrl)
                .enabled(false) // User is not enabled by default
                .nonLocked(true)
                .usingMfa(false)
                .isTwoFactorEnabled(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .roles(Set.of(role))
                .build();

        return saveUserPort.saveUser(userToRegister);
    }

    @Override
    public void updateUserEnabledStatus(String email, boolean enabled) {
        log.info("Updating user enabled status with email: {}", email);
        saveUserPort.updateUserEnabledStatus(email, enabled);
    }

    private String generateUsername(CreateUserRequest createUserRequest) {
        String firstName = createUserRequest.firstName();
        String lastName = createUserRequest.lastName();
        String documentNumber = createUserRequest.documentNumber();
        return firstName.charAt(0) + lastName.charAt(0) + documentNumber;
    }
}
