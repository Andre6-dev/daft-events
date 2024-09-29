package dp.devandre.daftevents.user.infrastructure.persistence.mapper;

import dp.devandre.daftevents.user.domain.Role;
import dp.devandre.daftevents.user.domain.User;
import dp.devandre.daftevents.user.infrastructure.persistence.model.RoleJpaEntity;
import dp.devandre.daftevents.user.infrastructure.persistence.model.UserJpaEntity;
import dp.devandre.daftevents.user.infrastructure.persistence.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final RoleRepository roleRepository;

    public UserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

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
                userJpaEntity.getIsTwoFactorEnabled(),
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
//        userJpaEntity.setId(user.getId());
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
        userJpaEntity.setIsTwoFactorEnabled(user.getIsTwoFactorEnabled());
        userJpaEntity.setCreatedAt(user.getCreatedAt());
        userJpaEntity.setUpdatedAt(user.getUpdatedAt());

        Set<RoleJpaEntity> managedRoles = user.getRoles().stream()
                .map(role -> roleRepository.findById(role.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + role.getId())))
                .collect(Collectors.toSet());
        userJpaEntity.setRoleJpaEntities(managedRoles);

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
}
