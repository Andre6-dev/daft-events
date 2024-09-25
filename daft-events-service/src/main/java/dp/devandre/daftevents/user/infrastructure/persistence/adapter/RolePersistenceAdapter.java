package dp.devandre.daftevents.user.infrastructure.persistence.adapter;

import dp.devandre.daftevents.user.application.port.out.LoadRolePort;
import dp.devandre.daftevents.user.domain.Role;
import dp.devandre.daftevents.user.infrastructure.persistence.mapper.RoleMapper;
import dp.devandre.daftevents.user.infrastructure.persistence.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RolePersistenceAdapter implements LoadRolePort {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RolePersistenceAdapter(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Optional<Role> loadRoleById(Integer id) {
        return roleRepository.findById(id)
                .map(roleMapper::mapToDomainEntity);
    }

    @Override
    public Optional<Role> loadRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .map(roleMapper::mapToDomainEntity);
    }

    @Override
    public List<Role> loadAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::mapToDomainEntity)
                .toList();
    }
}
