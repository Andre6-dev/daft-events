package dp.devandre.daftevents.user.application.dto;

public record CreateUserRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String documentNumber,
        String phoneNumber,
        String address
) {
}
