package dp.devandre.daftevents.user.application.port.out;

import dp.devandre.daftevents.user.domain.Role;

public interface SaveRolePort {
    Role saveRole(Role role);
}
