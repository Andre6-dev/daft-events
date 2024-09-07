package dp.devandre.daftevents.user.infrastructure.persistence.mapper;

import dp.devandre.daftevents.user.domain.Role;
import dp.devandre.daftevents.user.domain.User;
import dp.devandre.daftevents.user.infrastructure.persistence.model.RoleJpaEntity;
import dp.devandre.daftevents.user.infrastructure.persistence.model.UserJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToDomainEntity(UserJpaEntity userJpaEntity) {
        return new User(
                userJpaEntity.getId(),
                userJpaEntity.getUsername(),
                userJpaEntity.getEmail(),
                userJpaEntity.getPasswordHash(),
                userJpaEntity.getFirstName(),
                userJpaEntity.getLastName(),
                userJpaEntity.getDocumentNumber(),
                userJpaEntity.getPhoneNumber(),
                userJpaEntity.getAddress(),
                userJpaEntity.getProfileUrl(),
                userJpaEntity.getEnabled(),
                userJpaEntity.getNonLocked(),
                userJpaEntity.getUsingMfa(),
                userJpaEntity.getCreatedAt(),
                userJpaEntity.getUpdatedAt(),
                mapRolestoDomainEntities(userJpaEntity.getRoleJpaEntities())
        );
    }

    public UserJpaEntity mapToJpaEntity(User user) {
        if (user == null) {
            return null;
        }

        UserJpaEntity userJpaEntity = new UserJpaEntity();
        userJpaEntity.setId(user.getId());
        userJpaEntity.setUsername(user.getUsername());
        userJpaEntity.setEmail(user.getEmail());
        userJpaEntity.setPasswordHash(user.getPassword());
        userJpaEntity.setFirstName(user.getFirstName());
        userJpaEntity.setLastName(user.getLastName());
        userJpaEntity.setDocumentNumber(user.getDocumentNumber());
        userJpaEntity.setPhoneNumber(user.getPhoneNumber());
        userJpaEntity.setAddress(user.getAddress());
        userJpaEntity.setProfileUrl(user.getProfileUrl());
        userJpaEntity.setEnabled(user.getEnabled());
        userJpaEntity.setNonLocked(user.getNonLocked());
        userJpaEntity.setUsingMfa(user.getUsingMfa());
        userJpaEntity.setCreatedAt(user.getCreatedAt());
        userJpaEntity.setUpdatedAt(user.getUpdatedAt());
        userJpaEntity.setRoleJpaEntities(mapRolestoJpaEntities(user.getRoles()));

        return userJpaEntity;
    }

    private Set<Role> mapRolestoDomainEntities(Set<RoleJpaEntity> roleJpaEntities) {
        return roleJpaEntities.stream()
                .map(roleJpaEntity -> new Role(
                        roleJpaEntity.getId(),
                        roleJpaEntity.getRoleName(),
                        roleJpaEntity.getPermission())
                )
                .collect(Collectors.toSet());
    }

    private Set<RoleJpaEntity> mapRolestoJpaEntities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new RoleJpaEntity(
                        role.getId(),
                        role.getRoleName(),
                        role.getPermission()
                ))
                .collect(Collectors.toSet());
    }
}
