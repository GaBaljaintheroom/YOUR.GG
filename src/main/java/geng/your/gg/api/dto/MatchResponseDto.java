package geng.your.gg.api.dto;

import geng.your.gg.infrastructure.riot.dto.match.MatchDto;

public record MatchResponseDto(
    InfoResponseDto info
) {

    public static MatchResponseDto from(MatchDto matchDto, String puuid) {
        return new MatchResponseDto(
            InfoResponseDto.of(matchDto.info(), puuid)
        );
    }

}
