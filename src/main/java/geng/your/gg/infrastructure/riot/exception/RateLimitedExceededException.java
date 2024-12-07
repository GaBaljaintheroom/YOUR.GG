package geng.your.gg.infrastructure.riot.exception;

import geng.your.gg.global.error.ErrorCode;
import geng.your.gg.global.error.exception.BusinessException;

public class RateLimitedExceededException extends BusinessException {

    public RateLimitedExceededException() {
        super(ErrorCode.RATE_LIMITED_EXCEEDED_ERROR);
    }
}
