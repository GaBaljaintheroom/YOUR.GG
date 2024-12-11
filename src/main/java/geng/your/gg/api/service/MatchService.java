package geng.your.gg.api.service;

import geng.your.gg.api.dto.DetailMatchResponseDto;
import geng.your.gg.api.dto.SimpleMatchResponseDto;
import geng.your.gg.api.manager.ExternalApiManager;
import geng.your.gg.infrastructure.riot.dto.match.DetailMatchDto;
import geng.your.gg.infrastructure.riot.dto.match.MatchIdsDto;
import geng.your.gg.infrastructure.riot.dto.match.SimpleMatchDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchService {

    private static final String SUMMONERS_RIFT_GAME_MODE = "CLASSIC";
    private static final String END_GAME = "GameComplete";
    private static final int REQUIRED_MATCH_COUNT = 20;
    private static final int MAX_REQUESTS = 5;

    private final ExternalApiManager externalApiManager;
    private final Executor asyncExecutor;

    @Cacheable(value = "simpleMatchResponseCache", key = "#puuid")
    public List<SimpleMatchResponseDto> getSimpleMatchInfo(int start, int end, String puuid) {
        List<SimpleMatchResponseDto> matchDtos = new ArrayList<>();
        int currentStart = start;
        int requestCount = 0;

        while (requestCount++ < MAX_REQUESTS) {
            MatchIdsDto matchIdsDto = externalApiManager.getMatchIds(currentStart, end,
                puuid);

            List<CompletableFuture<SimpleMatchResponseDto>> futures = matchIdsDto.matchIds()
                .stream()
                .map(matchId -> {
                    return CompletableFuture.supplyAsync(
                            () -> externalApiManager.getSimpleMatch(matchId), asyncExecutor)
                        .thenApply(match -> {
                            return canAddMatch(match) ? SimpleMatchResponseDto.from(match,
                                matchId, puuid) : null;
                        });
                })
                .toList();

            List<SimpleMatchResponseDto> results = futures.stream()
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .toList();

            matchDtos.addAll(results);

            if (matchDtos.size() >= REQUIRED_MATCH_COUNT) {
                return matchDtos.subList(0, REQUIRED_MATCH_COUNT);
            }

            currentStart += end;

            if (matchIdsDto.matchIds().isEmpty()) {
                break;
            }
        }

        return matchDtos;
    }

    @Cacheable(value = "detailMatchResponseCache", key = "#matchId")
    public DetailMatchResponseDto getDetailMatchInfoByMatchId(String matchId) {
        DetailMatchDto detailMatch = externalApiManager.getDetailMatch(matchId);

        return DetailMatchResponseDto.from(detailMatch);
    }

    private boolean canAddMatch(SimpleMatchDto match) {
        return match.info().gameMode().equals(SUMMONERS_RIFT_GAME_MODE)
            && match.info().endOfGameResult().equals(END_GAME);
    }
}
