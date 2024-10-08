package dp.devandre.daftevents.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String phoneNumber;
    private String address;
    private String profileUrl;
    private Boolean enabled;
    private Boolean nonLocked;
    private Boolean usingMfa;
    private Boolean isTwoFactorEnabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<Role> roles = new LinkedHashSet<>();
}
