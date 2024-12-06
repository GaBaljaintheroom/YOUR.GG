package geng.your.gg.infrastructure.riot.dto.match;

import java.util.List;

public record InfoDto(
    long gameDuration,
    String gameMode,
    long gameStartTimestamp,
    List<SimpleParticipantDto> participants
) {

}
