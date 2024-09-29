package dp.devandre.daftevents.user.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthenticateUserRequest(
        @Email @NotNull String email,
        @Size(min = 5, max = 25) @NotNull String password
) {
}
