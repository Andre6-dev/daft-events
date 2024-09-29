package dp.devandre.daftevents.user.application.port.out;

import dp.devandre.daftevents.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface LoadUserPort {
    Optional<User> loadUser(Integer id);
    Optional<User> loadUserByEmail(String email);
    List<User> loadUsers();
    Boolean existsByEmail(String email);
}
