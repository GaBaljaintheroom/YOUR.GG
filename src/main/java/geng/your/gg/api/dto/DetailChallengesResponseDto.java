package geng.your.gg.api.dto;

import geng.your.gg.infrastructure.riot.dto.match.DetailChallengesDto;
import lombok.Builder;

@Builder
public record DetailChallengesResponseDto(
    int totalDamageDealtToChampions,
    float killParticipation,
    int controlWardsPlaced
) {

    public static DetailChallengesResponseDto from(DetailChallengesDto dto) {
        return DetailChallengesResponseDto.builder()
            .totalDamageDealtToChampions(dto.totalDamageDealtToChampions())
            .killParticipation(dto.killParticipation())
            .controlWardsPlaced(dto.controlWardsPlaced())
            .build();
    }

}
