package geng.your.gg.api.dto;

import geng.your.gg.infrastructure.riot.dto.match.SimpleParticipantDto;
import lombok.Builder;

@Builder
public record SimpleParticipantResponseDto(
    boolean win,
    int assists,
    int deaths,
    int kills,
    String championImage
) {
    public static SimpleParticipantResponseDto from(SimpleParticipantDto dto) {
        return SimpleParticipantResponseDto.builder()
            .win(dto.win())
            .assists(dto.assists())
            .deaths(dto.deaths())
            .kills(dto.kills())
            .championImage(getChampionImage(dto))
            .build();
    }

    private static String getChampionImage(SimpleParticipantDto dto) {
        return String.format(
            "https://ddragon.leagueoflegends.com/cdn/14.23.1/img/champion/%s.png",
            dto.championName()
        );
    }

}
