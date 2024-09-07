package dp.devandre.daftevents.user.application.service;

import dp.devandre.daftevents.user.application.port.in.GetUserUseCase;
import dp.devandre.daftevents.user.application.port.out.LoadUserPort;
import dp.devandre.daftevents.user.domain.User;
import dp.devandre.daftevents.shared.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements GetUserUseCase {

    private final LoadUserPort loadUserPort;

    @Override
    public User getUser(Integer id) {
        log.info("Getting user with id: {}", id);
        return loadUserPort.loadUser(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }
}
