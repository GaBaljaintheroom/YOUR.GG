package geng.your.gg.api.dto;

import geng.your.gg.infrastructure.riot.dto.match.SimpleMatchDto;

public record SimpleMatchResponseDto(
    SimpleInfoResponseDto info
) {

    public static SimpleMatchResponseDto from(SimpleMatchDto matchDto, String matchId, String puuid) {
        return new SimpleMatchResponseDto(
            SimpleInfoResponseDto.of(matchDto.info(), matchId, puuid)
        );
    }

}
