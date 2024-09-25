package dp.devandre.daftevents.user.infrastructure.web;

import dp.devandre.daftevents.user.application.port.in.GetRoleUseCase;
import dp.devandre.daftevents.user.domain.Role;
import dp.devandre.daftevents.user.infrastructure.utils.ApiConstants;
import dp.devandre.daftevents.user.infrastructure.utils.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_V1_ROLES)
@RequiredArgsConstructor
public class RoleController {

    private final GetRoleUseCase getRoleUseCase;

    @GetMapping("/roleName/{name}")
    public ResponseEntity<Object> getRoleByName(@PathVariable("name") String name) {
        return ResponseHandler.generateResponse(HttpStatus.OK, getRoleUseCase.getRoleByName(name), true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRole(@PathVariable("id") Integer id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, getRoleUseCase.getRoleById(id), true);
    }

    @GetMapping()
    public ResponseEntity<Object> getRoles() {
        return ResponseHandler.generateResponse(HttpStatus.OK, getRoleUseCase.getRoles(), true);
    }
}
