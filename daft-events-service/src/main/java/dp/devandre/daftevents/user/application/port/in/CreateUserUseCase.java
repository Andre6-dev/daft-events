package dp.devandre.daftevents.user.application.port.in;

import dp.devandre.daftevents.user.application.dto.CreateUserRequest;
import dp.devandre.daftevents.user.application.dto.CreateUserResponse;

public interface CreateUserUseCase {

    CreateUserResponse createUserCommand(CreateUserRequest createUserRequest);
}
