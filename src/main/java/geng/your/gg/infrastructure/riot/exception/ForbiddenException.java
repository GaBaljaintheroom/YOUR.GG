package geng.your.gg.infrastructure.riot.exception;

import geng.your.gg.global.error.ErrorCode;
import geng.your.gg.global.error.exception.BusinessException;

public class ForbiddenException extends BusinessException {

    public ForbiddenException() {
        super(ErrorCode.FORBIDDEN_ERROR);
    }
}
