package dp.devandre.daftevents.user.application.port.out;

import dp.devandre.daftevents.user.domain.ActivationCode;

public interface ActivationCodePort {

    void saveActivationCode(ActivationCode activationCode);
    ActivationCode getActivationCodeByKey(String key);
    ActivationCode getActivationCodeByUserEmail(String email);
    void deleteActivationCodeById(Long id);
}
