package geng.your.gg.infrastructure.riot.exception;

import geng.your.gg.global.error.ErrorCode;
import geng.your.gg.global.error.exception.BusinessException;

public class BadeRequestException extends BusinessException {

    public BadeRequestException() {
        super(ErrorCode.BAD_REQUEST_ERROR);
    }
}
