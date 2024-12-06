package geng.your.gg.api.manager;

import geng.your.gg.api.dto.SummonerInfoDto;
import geng.your.gg.infrastructure.riot.dto.match.MatchDto;
import geng.your.gg.infrastructure.riot.dto.match.MatchIdsDto;
import geng.your.gg.infrastructure.riot.dto.user.AccountDto;

public interface ExternalApiManager {

    AccountDto getUserAccount(String gameName, String tagLine);

    SummonerInfoDto getSummonerInfo(String gameName, String tagLine, String puuid);

    MatchIdsDto getMatchIds(int start, int count, String puuid);

    MatchDto getMatch(String matchId);

}
