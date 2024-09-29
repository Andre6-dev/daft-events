package dp.devandre.daftevents.user.application.dto.response;

import lombok.Builder;

@Builder
public record ActivationCodeResponse(
        String message
) {
}
