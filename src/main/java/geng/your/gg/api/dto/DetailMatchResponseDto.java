package geng.your.gg.api.dto;

import geng.your.gg.infrastructure.riot.dto.match.DetailMatchDto;

public record DetailMatchResponseDto(
    DetailInfoResponseDto info
) {

    public static DetailMatchResponseDto from(DetailMatchDto matchDto) {
        return new DetailMatchResponseDto(
            DetailInfoResponseDto.of(matchDto.info())
        );
    }

}
