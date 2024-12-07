package geng.your.gg.api.dto;

import geng.your.gg.infrastructure.riot.dto.match.SimpleMatchDto;

public record MatchResponseDto(
    InfoResponseDto info
) {

    public static MatchResponseDto from(SimpleMatchDto matchDto, String puuid) {
        return new MatchResponseDto(
            InfoResponseDto.of(matchDto.info(), puuid)
        );
    }

}
