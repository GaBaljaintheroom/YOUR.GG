package geng.your.gg.infrastructure.riot.exception;

import geng.your.gg.global.error.ErrorCode;
import geng.your.gg.global.error.exception.BusinessException;

public class ServiceUnavailableException extends BusinessException {

    public ServiceUnavailableException() {
        super(ErrorCode.SERVICE_UNAVAILABLE);
    }
}
