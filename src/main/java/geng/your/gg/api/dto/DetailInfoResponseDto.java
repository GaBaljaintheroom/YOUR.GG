package geng.your.gg.api.dto;

import geng.your.gg.infrastructure.riot.dto.match.DetailInfoDto;
import java.util.List;
import lombok.Builder;

@Builder
public record DetailInfoResponseDto(
    List<DetailParticipantResponseDto> participants
) {

    public static DetailInfoResponseDto of(DetailInfoDto infoDto) {
        return DetailInfoResponseDto.builder()
            .participants(getParticipant(infoDto))
            .build();
    }

    private static List<DetailParticipantResponseDto> getParticipant(DetailInfoDto infoDto) {
        return infoDto.participants().stream()
            .map(DetailParticipantResponseDto::from)
            .toList();
    }

}
