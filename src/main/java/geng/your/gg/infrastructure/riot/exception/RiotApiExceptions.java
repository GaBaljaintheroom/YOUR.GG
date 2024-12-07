package geng.your.gg.infrastructure.riot.exception;

import geng.your.gg.global.error.exception.BusinessException;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

public enum RiotApiExceptions {
    BAD_REQUEST_EXCEPTION(new BadeRequestException()),
    UNAUTHORIZED_EXCEPTION(new UnauthorizedException()),
    FORBIDDEN_EXCEPTION(new ForbiddenException()),
    NOT_FOUND_EXCEPTION(new NotFoundException()),
    UNSUPPORTED_MEDIA_TYPE_ERROR(new UnsupportedMediaTypeException()),
    RATE_LIMITED_EXCEEDED_ERROR(new RateLimitedExceededException()),
    RIOT_SERVER_ERROR(new RiotServerException()),
    SERVICE_UNAVAILABLE(new ServiceUnavailableException());

    RiotApiExceptions(BusinessException businessException) {
        this.businessException = businessException;
    }

    private final BusinessException businessException;

    public static void handleResponse(ResponseEntity<?> response) {
        Optional<RiotApiExceptions> exception = Arrays.stream(RiotApiExceptions.values())
            .filter(
                ex -> ex.businessException.getErrorCode().getStatus()
                    == response.getStatusCode().value()
            )
            .findFirst();

        if (exception.isPresent()) {
            throw exception.get().businessException;
        }
    }

}
