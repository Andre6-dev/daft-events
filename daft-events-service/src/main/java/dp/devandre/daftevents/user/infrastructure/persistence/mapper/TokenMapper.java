package dp.devandre.daftevents.user.infrastructure.persistence.mapper;

import dp.devandre.daftevents.user.domain.Token;
import dp.devandre.daftevents.user.infrastructure.persistence.model.TokenJpaEntity;
import dp.devandre.daftevents.user.infrastructure.persistence.model.UserJpaEntity;
import dp.devandre.daftevents.user.infrastructure.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenMapper {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public Token mapToDomain(TokenJpaEntity tokenJpaEntity) {
        return Token.builder()
                .id(tokenJpaEntity.getId())
                .jwt(tokenJpaEntity.getJwt())
                .user(userMapper.mapToDomainEntity(tokenJpaEntity.getUser()))
                .revoked(tokenJpaEntity.isRevoked())
                .expired(tokenJpaEntity.isExpired())
                .expirationDate(tokenJpaEntity.getExpirationDate())
                .creationDate(tokenJpaEntity.getCreationDate())
                .build();
    }

    public TokenJpaEntity mapToJpa(Token token) {

        UserJpaEntity userJpaEntity = userRepository.findById(token.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return TokenJpaEntity.builder()
                .id(token.getId())
                .jwt(token.getJwt())
                .user(userJpaEntity)
                .revoked(token.isRevoked())
                .expired(token.isExpired())
                .expirationDate(token.getExpirationDate())
                .creationDate(token.getCreationDate())
                .build();
    }
}
