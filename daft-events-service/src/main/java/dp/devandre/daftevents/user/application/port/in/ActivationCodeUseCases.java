package dp.devandre.daftevents.user.application.port.in;

import dp.devandre.daftevents.user.domain.ActivationCode;
import dp.devandre.daftevents.user.domain.User;

public interface ActivationCodeUseCases {

    ActivationCode getActivationCodeByKey(String key);
    void checkActivationCodeExpiration(ActivationCode activationCode);
    void deleteActivationCodeById(Long id);
    void sendNewActivationCode(User user);
}
