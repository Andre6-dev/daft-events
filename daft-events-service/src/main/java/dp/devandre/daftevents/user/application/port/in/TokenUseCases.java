package dp.devandre.daftevents.user.application.port.in;

import dp.devandre.daftevents.user.domain.User;

public interface TokenUseCases {

    void createToken(User user, String jwt);

    void deleteTokenByUser(User user);

    String isTokenValid(String jwt);
}
