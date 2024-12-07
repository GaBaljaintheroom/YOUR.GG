
package geng.your.gg.infrastructure.riot.exception;

import geng.your.gg.global.error.ErrorCode;
import geng.your.gg.global.error.exception.BusinessException;

public class RiotServerException extends BusinessException {

    public RiotServerException() {
        super(ErrorCode.RIOT_SERVER_ERROR);
    }
}
