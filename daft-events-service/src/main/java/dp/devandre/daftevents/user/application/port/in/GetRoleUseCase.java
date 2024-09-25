package dp.devandre.daftevents.user.application.port.in;

import dp.devandre.daftevents.user.domain.Role;

import java.util.List;

public interface GetRoleUseCase {

    Role getRoleById(Integer id);
    Role getRoleByName(String name);
    List<Role> getRoles();
}
