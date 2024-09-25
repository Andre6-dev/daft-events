package dp.devandre.daftevents.user.application.port.in;

import dp.devandre.daftevents.user.application.dto.UserListResponse;
import dp.devandre.daftevents.user.domain.User;

import java.util.List;

public interface GetUserUseCase {
    User getUser(Integer id);
    List<UserListResponse> getUsers();
}
