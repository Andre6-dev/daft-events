package dp.devandre.daftevents.user.application.port.in;

import dp.devandre.daftevents.user.domain.User;

public interface GetUserUseCase {
    User getUser(Integer id);
}
