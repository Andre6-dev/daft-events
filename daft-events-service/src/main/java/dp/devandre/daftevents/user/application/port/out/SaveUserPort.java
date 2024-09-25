package dp.devandre.daftevents.user.application.port.out;

import dp.devandre.daftevents.user.domain.User;

public interface SaveUserPort {
    User saveUser(User user);
    Boolean existsByEmail(String email);
}
