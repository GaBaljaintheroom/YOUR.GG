package geng.your.gg.infrastructure.riot.dto.match;

import java.util.List;

public record InfoDto(
    String endOfGameResult,
    long gameDuration,
    String gameMode,
    long gameStartTimestamp,
    List<SimpleParticipantDto> participants
) {

}