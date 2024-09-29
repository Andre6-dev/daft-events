package dp.devandre.daftevents.user.application.service;

import dp.devandre.daftevents.shared.ActivationCodeExpiredException;
import dp.devandre.daftevents.user.application.port.in.ActivationCodeUseCases;
import dp.devandre.daftevents.user.application.port.out.ActivationCodePort;
import dp.devandre.daftevents.user.domain.ActivationCode;
import dp.devandre.daftevents.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivationCodeService implements ActivationCodeUseCases {

    private final ActivationCodePort activationCodePort;
    private final EmailService emailService;

    @Override
    public ActivationCode getActivationCodeByKey(String key) {
        return activationCodePort.getActivationCodeByKey(key);
    }

    @Override
    public void checkActivationCodeExpiration(ActivationCode activationCode) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = activationCode.getExpirationDate();

        if (expirationDate.isBefore(now)) {
            deleteActivationCodeById(activationCode.getId());
            sendNewActivationCode(activationCode.getUser());

            long minutes = ChronoUnit.MINUTES.between(expirationDate, now);

            throw new ActivationCodeExpiredException(
                    "Activation code expired. New activation code sent to email: " + activationCode.getUser().getEmail() +
                            ". Activation code expired for " + minutes + " minutes."
            );
        }
    }

    @Override
    public void deleteActivationCodeById(Long id) {
        activationCodePort.deleteActivationCodeById(id);
    }

    @Override
    public void sendNewActivationCode(User user) {
        log.info("Sending new activation code to user with email: {}", user.getEmail());
        ActivationCode activationCode = ActivationCode.builder()
                .user(user)
                .key(UUID.randomUUID().toString())
                .expirationDate(LocalDateTime.now().plusHours(2L))
                .build();

        emailService.sendActivationCode(activationCode);
        activationCodePort.saveActivationCode(activationCode);
    }
}
