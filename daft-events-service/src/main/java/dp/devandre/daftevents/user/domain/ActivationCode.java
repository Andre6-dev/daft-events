package dp.devandre.daftevents.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivationCode {

    private Long id;
    private User user;
    private String key;
    private LocalDateTime expirationDate;
}
