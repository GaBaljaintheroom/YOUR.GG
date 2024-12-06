package geng.your.gg.api.manager;

import geng.your.gg.api.dto.SummonerInfoDto;
import geng.your.gg.infrastructure.riot.dto.AccountDto;
import geng.your.gg.infrastructure.riot.dto.MatchIdsDto;

public interface ExternalApiManager {

    AccountDto getUserAccount(String gameName, String tagLine);

    SummonerInfoDto getSummonerInfo(String gameName, String tagLine, String puuid);

    MatchIdsDto getMatchIds(int start, int count, String puuid);

}
