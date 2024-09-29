package dp.devandre.daftevents.user.application.port.in;

import dp.devandre.daftevents.user.application.dto.request.CreateUserRequest;
import dp.devandre.daftevents.user.application.dto.response.CreateUserResponse;
import dp.devandre.daftevents.user.domain.User;

public interface CreateUserUseCase {

    User createUserCommand(CreateUserRequest createUserRequest);
    void updateUserEnabledStatus(String email, boolean enabled);
}
