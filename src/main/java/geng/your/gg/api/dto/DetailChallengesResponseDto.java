package geng.your.gg.api.dto;

import geng.your.gg.infrastructure.riot.dto.match.DetailChallengesDto;
import java.text.DecimalFormat;
import lombok.Builder;

@Builder
public record DetailChallengesResponseDto(
    String killParticipation,
    int controlWardsPlaced
) {

    public static DetailChallengesResponseDto from(DetailChallengesDto dto) {
        DecimalFormat df = new DecimalFormat("#.0");
        return DetailChallengesResponseDto.builder()
            .killParticipation(df.format(dto.killParticipation() * 100))
            .controlWardsPlaced(dto.controlWardsPlaced())
            .build();
    }

}
