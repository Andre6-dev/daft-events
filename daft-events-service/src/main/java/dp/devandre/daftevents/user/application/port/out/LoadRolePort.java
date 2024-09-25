package dp.devandre.daftevents.user.application.port.out;

import dp.devandre.daftevents.user.domain.Role;

import java.util.List;
import java.util.Optional;

public interface LoadRolePort {

    Optional<Role> loadRoleById(Integer id);
    Optional<Role> loadRoleByName(String roleName);
    List<Role> loadAllRoles();
}
