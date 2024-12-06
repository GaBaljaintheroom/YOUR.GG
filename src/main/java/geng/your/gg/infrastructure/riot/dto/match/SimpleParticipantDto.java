package geng.your.gg.infrastructure.riot.dto.match;

public record SimpleParticipantDto(
    String puuid,
    boolean win,
    int assists,
    int deaths,
    int kills,
    String championName
) {

}
