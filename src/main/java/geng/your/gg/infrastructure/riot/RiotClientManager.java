package geng.your.gg.infrastructure.riot;

import geng.your.gg.api.dto.SummonerInfoDto;
import geng.your.gg.api.manager.ExternalApiManager;
import geng.your.gg.infrastructure.riot.dto.match.MatchIdsDto;
import geng.your.gg.infrastructure.riot.dto.match.SimpleMatchDto;
import geng.your.gg.infrastructure.riot.dto.user.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RiotClientManager implements ExternalApiManager {

    private final RiotClient riotClient;

    @Override
    public AccountDto getUserAccount(String gameName, String tagLine) {
        return riotClient.getGameUserAccount(gameName, tagLine);
    }

    @Override
    public SummonerInfoDto getSummonerInfo(String gameName, String tagLine, String puuid) {
        return riotClient.getSummoner(puuid).toInfoDto(gameName, tagLine);
    }

    @Override
    public MatchIdsDto getMatchIds(int start, int count, String puuid) {
        return riotClient.getMatchIds(start, count, puuid);
    }

    @Override
    public SimpleMatchDto getSimpleMatch(String matchId) {
        return riotClient.getSimpleMatch(matchId);
    }

}
