package dp.devandre.daftevents.shared;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j(topic = "global-exception-handler")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFoundException(UserNotFoundException ex) {
        log.error("User not found", ex);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle(ex.getClass().getName());
        return problemDetail;
    }

    @ExceptionHandler(EntityExistsException.class)
    public ProblemDetail handleEntityExistsException(EntityExistsException ex) {
        log.error("Entity already exists", ex);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle(ex.getClass().getName());
        return problemDetail;
    }

    @ExceptionHandler({
            InvalidCredentialsException.class,
    })
    public ProblemDetail handleInvalidCredentialsException(Exception ex) {
        log.error("Invalid credentials", ex);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        problemDetail.setTitle(ex.getClass().getName());
        return problemDetail;
    }

    @ExceptionHandler(ActivationCodeExpiredException.class)
    public ProblemDetail handleActivationCodeExpiredException(ActivationCodeExpiredException ex) {
        log.error("Activation code expired", ex);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle(ex.getClass().getName());
        return problemDetail;
    }
}
