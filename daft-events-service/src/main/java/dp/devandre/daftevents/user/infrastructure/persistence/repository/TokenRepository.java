package dp.devandre.daftevents.user.infrastructure.persistence.repository;

import dp.devandre.daftevents.user.infrastructure.persistence.model.TokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenJpaEntity, Long> {

  Optional<TokenJpaEntity> findByUser_Id(Integer userId);

  Optional<TokenJpaEntity> findByJwt(String jwt);
}