package geng.your.gg.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //global
    INTERNAL_SERVER_ERROR(500, "GLOBAL-001", "서버에 오류가 발생하였습니다."),

    //Riot API
    // 400
    BAD_REQUEST_ERROR(400, "RT-001", "잘못된 요청입니다."),
    UNAUTHORIZED_ERROR(401, "RT-002", "잘못된 인증 요창입니다."),
    FORBIDDEN_ERROR(403, "RT-003", "권한 부여를 거부했습니다."),
    NOT_FOUND_ERROR(404, "RT-004", "요청과 일치하는 항목을 찾지 못했습니다."),
    UNSUPPORTED_MEDIA_TYPE_ERROR(415, "RT-005", "요청 본문이 지원되지 않는 형식입니다."),
    RATE_LIMITED_EXCEEDED_ERROR(429, "RT-006", "지정된 기간 동안 허용된 API 호출 횟수가 초과되었습니다."),

    //500
    RIOT_SERVER_ERROR(500, "RT-007", "라이엇 서버에 오류가 발생하였습니다."),
    SERVICE_UNAVAILABLE(503, "RT-008", "현재 서버가 요청을 처리할 수 없는 상태입니다.");

    private final int status;
    private final String code;
    private final String message;
}
