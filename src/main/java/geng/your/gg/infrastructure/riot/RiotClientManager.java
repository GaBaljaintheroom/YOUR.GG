package geng.your.gg.infrastructure.riot;

import geng.your.gg.api.manager.ExternalApiManager;
import geng.your.gg.infrastructure.riot.dto.AccountDto;
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

}
