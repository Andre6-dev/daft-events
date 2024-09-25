package dp.devandre.daftevents.user.application.dto;

import dp.devandre.daftevents.user.domain.Role;

import java.time.LocalDateTime;
import java.util.Set;

public record UserListResponse(
        Integer id,
        String username,
        String email,
        String documentNumber,
        LocalDateTime createdAt,
        String profileUrl,
        Set<Role> roles
) {
}
