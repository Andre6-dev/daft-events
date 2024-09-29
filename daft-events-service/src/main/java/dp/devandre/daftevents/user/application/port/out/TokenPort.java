package dp.devandre.daftevents.user.application.port.out;

import dp.devandre.daftevents.user.domain.User;

public interface TokenPort {

    void createToken(User user, String jwt);

    void deleteTokenByUser(User user);

    String isTokenValid(String jwt);


}
