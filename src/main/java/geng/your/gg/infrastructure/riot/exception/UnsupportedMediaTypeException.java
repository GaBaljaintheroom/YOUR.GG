package geng.your.gg.infrastructure.riot.exception;

import geng.your.gg.global.error.ErrorCode;
import geng.your.gg.global.error.exception.BusinessException;

public class UnsupportedMediaTypeException extends BusinessException {

    public UnsupportedMediaTypeException() {
        super(ErrorCode.UNSUPPORTED_MEDIA_TYPE_ERROR);
    }
}
