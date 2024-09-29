package dp.devandre.daftevents.user.infrastructure.persistence.mapper;

import dp.devandre.daftevents.user.domain.ActivationCode;
import dp.devandre.daftevents.user.infrastructure.persistence.model.ActivationCodeJpaEntity;
import dp.devandre.daftevents.user.infrastructure.persistence.model.UserJpaEntity;
import dp.devandre.daftevents.user.infrastructure.persistence.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ActivationCodeMapper {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public ActivationCodeMapper(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public ActivationCode mapToDomainEntity(ActivationCodeJpaEntity activationCodeJpaEntity) {
        return ActivationCode.builder()
                .id(activationCodeJpaEntity.getId())
                .key(activationCodeJpaEntity.getKey())
                .user(userMapper.mapToDomainEntity(activationCodeJpaEntity.getUser()))
                .expirationDate(activationCodeJpaEntity.getExpirationDate())
                .build();
    }

    public ActivationCodeJpaEntity mapToJpaEntity(ActivationCode activationCode) {

        UserJpaEntity userJpaEntity = userRepository.findByEmail(activationCode.getUser().getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + activationCode.getUser().getEmail()));

        return ActivationCodeJpaEntity.builder()
                .id(activationCode.getId())
                .key(activationCode.getKey())
                .user(userJpaEntity)
                .expirationDate(activationCode.getExpirationDate())
                .build();
    }
}
