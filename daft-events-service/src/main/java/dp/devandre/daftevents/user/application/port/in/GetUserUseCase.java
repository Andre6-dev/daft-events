package dp.devandre.daftevents.user.application.port.in;

import dp.devandre.daftevents.user.application.dto.response.UserListResponse;
import dp.devandre.daftevents.user.domain.User;

import java.util.List;

public interface GetUserUseCase {
    User getUser(Integer id);
    User getUserByEmail(String email);
    List<UserListResponse> getUsers();
    boolean isUserEmailExists(String email);
}
