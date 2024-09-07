package dp.devandre.daftevents.user.infrastructure.persistence.repository;

import dp.devandre.daftevents.user.infrastructure.persistence.model.RoleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleJpaEntity, Integer> {

    Optional<RoleJpaEntity> findByRoleName(String roleName);
}