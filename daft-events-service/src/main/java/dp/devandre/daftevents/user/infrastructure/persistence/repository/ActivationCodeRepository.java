package dp.devandre.daftevents.user.infrastructure.persistence.repository;

import dp.devandre.daftevents.user.infrastructure.persistence.model.ActivationCodeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivationCodeRepository extends JpaRepository<ActivationCodeJpaEntity, Long> {

  Optional<ActivationCodeJpaEntity> findActivationCodeJpaEntityByKey(String key);
  Optional<ActivationCodeJpaEntity> findActivationCodeJpaEntityByUser_Email(String email);
}