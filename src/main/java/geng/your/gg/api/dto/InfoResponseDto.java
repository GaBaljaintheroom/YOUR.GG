package geng.your.gg.api.dto;

import geng.your.gg.infrastructure.riot.dto.match.InfoDto;
import java.time.Instant;
import java.util.Date;
import java.util.NoSuchElementException;
import lombok.Builder;

@Builder
public record InfoResponseDto(
    String gameDuration,
    Date gameStartTime,
    SimpleParticipantResponseDto participant
) {

    public static InfoResponseDto of(InfoDto infoDto, String puuid) {
        return InfoResponseDto.builder()
            .gameDuration(getFormattedGameDuration(infoDto.gameDuration()))
            .gameStartTime(getGameStartTime(infoDto.gameStartTimestamp()))
            .participant(getParticipant(infoDto, puuid))
            .build();
    }


    private static String getFormattedGameDuration(long gameDuration) {
        long hours = gameDuration / 3600;
        long minutes = (gameDuration % 3600) / 60;
        long seconds = gameDuration % 60;

        if (hours == 0) {
            return String.format("%d분 %d초", minutes, seconds);
        }

        return String.format("%d시간 %d분 %d초", hours, minutes, seconds);
    }

    private static Date getGameStartTime(long gameStartTimestamp) {
        return Date.from(Instant.ofEpochMilli(gameStartTimestamp));
    }

    private static SimpleParticipantResponseDto getParticipant(InfoDto infoDto, String puuid) {
        return infoDto.participants().stream()
            .filter(participant -> participant.puuid().equals(puuid))
            .findFirst()
            .map(SimpleParticipantResponseDto::from)
            .orElseThrow(NoSuchElementException::new);
    }

}
