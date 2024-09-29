package dp.devandre.daftevents.user.infrastructure.persistence.adapter;

import dp.devandre.daftevents.user.application.port.out.TokenPort;
import dp.devandre.daftevents.user.domain.Token;
import dp.devandre.daftevents.user.domain.User;
import dp.devandre.daftevents.user.infrastructure.persistence.mapper.TokenMapper;
import dp.devandre.daftevents.user.infrastructure.persistence.model.enums.TokenType;
import dp.devandre.daftevents.user.infrastructure.persistence.repository.TokenRepository;
import dp.devandre.daftevents.user.infrastructure.security.jwt.JwtUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class TokenPersistenceAdapter implements TokenPort {

    private final TokenRepository tokenRepository;
    private final TokenMapper tokenMapper;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public TokenPersistenceAdapter(TokenRepository tokenRepository, TokenMapper tokenMapper, JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.tokenRepository = tokenRepository;
        this.tokenMapper = tokenMapper;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void createToken(User user, String jwt) {
        tokenRepository.save(
                tokenMapper.mapToJpa(
                        Token.builder()
                                .jwt(jwt)
                                .user(user)
                                .tokenType(TokenType.BEARER)
                                .expirationDate(null)
                                .expired(false)
                                .revoked(false)
                                .build()
                )
        );
    }

    @Override
    public void deleteTokenByUser(User user) {
        tokenRepository.findByUser_Id(user.getId())
                .ifPresent(tokenRepository::delete);
    }

    @Override
    public String isTokenValid(String jwt) {
        UserDetails userDetails = extractUserDetails(jwt);
        boolean isTokenValid = tokenRepository.findByJwt(jwt)
                .map(token -> !token.isExpired() && !token.isRevoked())
                .orElse(false);
        if (isTokenValid && jwtUtils.validateJwtToken(jwt)) {
            return userDetails.getUsername();
        } else {
            throw new RuntimeException("Invalid token");
        }
    }

    private UserDetails extractUserDetails(String jwt) {
        String email = jwtUtils.getEmailFromToken(jwt);
        return userDetailsService.loadUserByUsername(email);
    }
}
