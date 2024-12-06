package geng.your.gg.api.service;

import geng.your.gg.api.dto.SummonerInfoDto;
import geng.your.gg.api.manager.ExternalApiManager;
import geng.your.gg.infrastructure.riot.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ExternalApiManager externalApiManager;

    public SummonerInfoDto getSummonerInfo(String gameName, String tagLine) {
        AccountDto account = externalApiManager.getUserAccount(gameName, tagLine);

        return externalApiManager.getSummonerInfo(gameName + tagLine, account.puuid());
    }

}
