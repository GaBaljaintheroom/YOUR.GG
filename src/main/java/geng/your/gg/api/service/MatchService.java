package geng.your.gg.api.service;

import geng.your.gg.api.dto.MatchResponseDto;
import geng.your.gg.api.manager.ExternalApiManager;
import geng.your.gg.infrastructure.riot.dto.match.MatchDto;
import geng.your.gg.infrastructure.riot.dto.match.MatchIdsDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchService {

    private static final String SUMMONERS_RIFT_GAME_MODE = "CLASSIC";
    private static final String END_GAME = "GameComplete";
    private static final int REQUIRED_MATCH_COUNT = 20;
    private static final int MAX_REQUESTS = 5;

    private final ExternalApiManager externalApiManager;

    public List<MatchResponseDto> getSimpleMatchInfo(int start, int end, String puuid) {
        List<MatchResponseDto> matchDtos = new ArrayList<>();
        int currentStart = start;
        int currentEnd = end;

        int requestCount = 0;

        while (requestCount++ < MAX_REQUESTS) {
            MatchIdsDto matchIdsDto = externalApiManager.getMatchIds(currentStart, currentEnd, puuid);

            for (String matchId : matchIdsDto.matchIds()) {
                MatchDto match = externalApiManager.getMatch(matchId);

                if (canAddMatch(match)) {
                    matchDtos.add(MatchResponseDto.from(match, puuid));
                }

                if (matchDtos.size() >= REQUIRED_MATCH_COUNT) {
                    return matchDtos.subList(0, REQUIRED_MATCH_COUNT);
                }
            }

            currentStart = currentEnd + 1;
            currentEnd += 20;

            if (matchIdsDto.matchIds().isEmpty()) {
                break;
            }
        }

        return matchDtos;
    }

    private boolean canAddMatch(MatchDto match) {
        return match.info().gameMode().equals(SUMMONERS_RIFT_GAME_MODE)
            && match.info().endOfGameResult().equals(END_GAME);
    }
}
