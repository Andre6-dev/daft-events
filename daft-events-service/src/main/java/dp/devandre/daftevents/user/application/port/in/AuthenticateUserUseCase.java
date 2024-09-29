package dp.devandre.daftevents.user.application.port.in;

import dp.devandre.daftevents.user.application.dto.request.AuthenticateUserRequest;
import dp.devandre.daftevents.user.application.dto.request.CreateUserRequest;
import dp.devandre.daftevents.user.application.dto.response.ActivationCodeResponse;
import dp.devandre.daftevents.user.application.dto.response.AuthenticateUserResponse;

public interface AuthenticateUserUseCase {
    ActivationCodeResponse register(CreateUserRequest request);
    AuthenticateUserResponse authenticate(AuthenticateUserRequest request);
    ActivationCodeResponse activate(String key);
}
