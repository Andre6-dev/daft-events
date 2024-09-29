package dp.devandre.daftevents.user.application.service;

import dp.devandre.daftevents.shared.EntityExistsException;
import dp.devandre.daftevents.shared.InvalidCredentialsException;
import dp.devandre.daftevents.user.application.dto.request.AuthenticateUserRequest;
import dp.devandre.daftevents.user.application.dto.request.CreateUserRequest;
import dp.devandre.daftevents.user.application.dto.response.ActivationCodeResponse;
import dp.devandre.daftevents.user.application.dto.response.AuthenticateUserResponse;
import dp.devandre.daftevents.user.application.port.in.AuthenticateUserUseCase;
import dp.devandre.daftevents.user.domain.ActivationCode;
import dp.devandre.daftevents.user.domain.User;
import dp.devandre.daftevents.user.infrastructure.security.jwt.JwtUtils;
import dp.devandre.daftevents.user.infrastructure.security.model.UserDetailsSecurityImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService implements AuthenticateUserUseCase {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;
    private final ActivationCodeService activationCodeService;

    @Override
    public ActivationCodeResponse register(CreateUserRequest request) {
        log.info("Registering user with email: {}", request.email());

        if (userService.isUserEmailExists(request.email())) {
            log.error("User with email: {} already exists", request.email());
            throw new EntityExistsException("User with email: " + request.email() + " already exists");
        }

        User newUser = userService.createUserCommand(request);

        activationCodeService.sendNewActivationCode(newUser);
        log.info("Activation code sent to email: {}", request.email());

        return ActivationCodeResponse.builder()
                .message("Activation code sent to email: " + request.email())
                .build();
    }

    @Override
    public AuthenticateUserResponse authenticate(AuthenticateUserRequest request) {
        Authentication authentication;
        log.info("Authenticating user with email: {}", request.email());
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (DisabledException e) {
            throw new DisabledException("User is disabled");
        }catch (BadCredentialsException e) {
            log.error("Invalid credentials for user with email: {}", request.email());
            throw new InvalidCredentialsException("Invalid credentials");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsSecurityImpl userDetails = (UserDetailsSecurityImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateTokenFromUsername(userDetails);

        User user = userService.getUserByEmail(userDetails.getUsername());

        tokenService.deleteTokenByUser(user);
        tokenService.createToken(user, jwt);
        log.info("User with email: {} authenticated", request.email());

        return AuthenticateUserResponse.builder()
                .jwt(jwt)
                .email(userDetails.getUsername())
                .build();
    }

    @Override
    public ActivationCodeResponse activate(String key) {
        ActivationCode activationCode = activationCodeService.getActivationCodeByKey(key);

        activationCodeService.checkActivationCodeExpiration(activationCode);
        userService.updateUserEnabledStatus(activationCode.getUser().getEmail(), true);
        activationCodeService.deleteActivationCodeById(activationCode.getId());

        log.info("User with email: {} has successfully activated, ", activationCode.getUser().getEmail());
        return ActivationCodeResponse.builder()
                .message("User with email: " + activationCode.getUser().getEmail() + " has successfully activated")
                .build();
    }
}
