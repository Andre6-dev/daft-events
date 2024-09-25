package dp.devandre.daftevents.user.infrastructure.persistence.mapper;

import dp.devandre.daftevents.user.domain.Role;
import dp.devandre.daftevents.user.infrastructure.persistence.model.RoleJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role mapToDomainEntity(RoleJpaEntity roleJpaEntity) {
        return Role.builder()
                .id(roleJpaEntity.getId())
                .roleName(roleJpaEntity.getRoleName())
                .permission(roleJpaEntity.getPermission())
                .build();
    }

    public RoleJpaEntity mapToJpaEntity(Role role) {
        if (role == null) {
            return null;
        }

        RoleJpaEntity roleJpaEntity = new RoleJpaEntity();
        roleJpaEntity.setId(role.getId());
        roleJpaEntity.setRoleName(role.getRoleName());
        roleJpaEntity.setPermission(role.getPermission());

        return roleJpaEntity;
    }
}
