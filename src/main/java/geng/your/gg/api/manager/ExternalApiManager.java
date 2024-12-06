package geng.your.gg.api.manager;

import geng.your.gg.api.dto.SummonerInfoDto;
import geng.your.gg.infrastructure.riot.dto.AccountDto;

public interface ExternalApiManager {

    AccountDto getUserAccount(String gameName, String tagLine);

    SummonerInfoDto getSummonerInfo(String name, String puuid);

}
