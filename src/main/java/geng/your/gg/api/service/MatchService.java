package geng.your.gg.api.service;

import geng.your.gg.api.manager.ExternalApiManager;
import geng.your.gg.infrastructure.riot.dto.match.MatchIdsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final ExternalApiManager externalApiManager;

    public void getMatchDetail(int start, int end, String puuid) {
        MatchIdsDto matchIds = externalApiManager.getMatchIds(start, end, puuid);
    }
}
