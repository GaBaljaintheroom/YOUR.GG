package geng.your.gg.infrastructure.riot.dto;

import geng.your.gg.api.dto.SummonerInfoDto;

public record SummonerDto(
    String accountId,
    int profileIconId,
    long revisionDate,
    String id,
    String puuid,
    long summonerLevel
) {

    public SummonerInfoDto toInfoDto(String gameName, String tagLine) {
        return SummonerInfoDto.builder()
            .gameName(gameName)
            .tagLine(tagLine)
            .profileImage(
                "http://ddragon.leagueoflegends.com/cdn/13.24.1/img/profileicon/"
                 + profileIconId + ".png")
            .summonerLevel(summonerLevel)
            .build();
    }
}
