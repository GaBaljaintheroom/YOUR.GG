package geng.your.gg.infrastructure.riot.dto.match;

public record DetailParticipantDto(
    String puuid,
    boolean win,
    String championName,
    int champLevel,
    int goldEarned,
    int totalMinionsKilled,
    int assists,
    int deaths,
    int kills,
    int wardsPlaced,
    int wardsKilled,
    int totalDamageDealtToChampions,
    DetailChallengesDto challenges
) {

}
