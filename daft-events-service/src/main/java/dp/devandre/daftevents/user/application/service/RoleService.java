package dp.devandre.daftevents.user.application.service;

import dp.devandre.daftevents.user.application.port.in.GetRoleUseCase;
import dp.devandre.daftevents.user.application.port.out.LoadRolePort;
import dp.devandre.daftevents.user.domain.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService implements GetRoleUseCase {

    private final LoadRolePort loadRolePort;

    @Override
    public Role getRoleById(Integer id) {
        log.info("Getting role with id: {}", id);
        return loadRolePort.loadRoleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with id: " + id));
    }

    @Override
    public Role getRoleByName(String name) {
        log.info("Getting role with name: {}", name);
        return loadRolePort.loadRoleByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with name: " + name));
    }

    @Override
    public List<Role> getRoles() {
        log.info("Getting all roles");
        return loadRolePort.loadAllRoles();
    }
}
