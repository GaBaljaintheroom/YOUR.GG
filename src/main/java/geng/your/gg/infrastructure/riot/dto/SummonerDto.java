package geng.your.gg.infrastructure.riot.dto;

public record SummonerDto(
    String accountId,
    int profileIconId,
    long revisionDate,
    String id,
    String puuid,
    long summonerLevel
) {

}
