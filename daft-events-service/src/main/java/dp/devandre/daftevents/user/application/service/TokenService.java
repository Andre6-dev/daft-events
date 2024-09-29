package dp.devandre.daftevents.user.application.service;

import dp.devandre.daftevents.user.application.port.in.TokenUseCases;
import dp.devandre.daftevents.user.application.port.out.TokenPort;
import dp.devandre.daftevents.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService implements TokenUseCases {

    private final TokenPort tokenPort;

    @Override
    public void createToken(User user, String jwt) {
        log.info("Creating token for user with email: {}", user.getEmail());
        tokenPort.createToken(user, jwt);
    }

    @Override
    public void deleteTokenByUser(User user) {
        log.info("Deleting token for user with email: {}", user.getEmail());
        tokenPort.deleteTokenByUser(user);
    }

    @Override
    public String isTokenValid(String jwt) {
        log.info("Checking if token is valid");
        return tokenPort.isTokenValid(jwt);
    }
}
