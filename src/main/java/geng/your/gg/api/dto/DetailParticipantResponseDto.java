package geng.your.gg.api.dto;

import geng.your.gg.infrastructure.riot.dto.match.DetailParticipantDto;
import lombok.Builder;

@Builder
public record DetailParticipantResponseDto(
    String puuid,
    boolean win,
    String championImage,
    int champLevel,
    int goldEarned,
    int totalMinionsKilled,
    int assists,
    int deaths,
    int kills,
    int wardsPlaced,
    int wardsKilled,
    int totalDamageDealtToChampions,
    DetailChallengesResponseDto challenges
) {

    public static DetailParticipantResponseDto from(DetailParticipantDto dto) {
        return DetailParticipantResponseDto.builder()
            .puuid(dto.puuid())
            .win(dto.win())
            .championImage(getChampionImage(dto))
            .champLevel(dto.champLevel())
            .goldEarned(dto.goldEarned())
            .totalMinionsKilled(dto.totalMinionsKilled())
            .assists(dto.assists())
            .deaths(dto.deaths())
            .kills(dto.kills())
            .wardsPlaced(dto.wardsPlaced())
            .wardsKilled(dto.wardsKilled())
            .totalDamageDealtToChampions(dto.totalDamageDealtToChampions())
            .challenges(DetailChallengesResponseDto.from(dto.challenges()))
            .build();
    }

    private static String getChampionImage(DetailParticipantDto dto) {
        return String.format(
            "https://ddragon.leagueoflegends.com/cdn/14.23.1/img/champion/%s.png",
            dto.championName()
        );
    }

}
