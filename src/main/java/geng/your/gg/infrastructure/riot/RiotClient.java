package geng.your.gg.infrastructure.riot;

import geng.your.gg.infrastructure.riot.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class RiotClient {

    private final RiotApiProperty riotApiProperty;

    public AccountDto getGameUserAccount(String gameName, String tagLine) {
        ResponseEntity<AccountDto> origin = RestClient.create(
                riotApiProperty.baseURL() + getUserAccountURL(gameName, tagLine))
            .get()
            .headers(this::createHeaders)
            .retrieve()
            .toEntity(AccountDto.class);

        return origin.getBody();
    }

    private static String getUserAccountURL(String gameName, String tagLine) {
        return String.format("/riot/account/v1/accounts/by-riot-id/%s/%s", gameName, tagLine);
    }

    private void createHeaders(HttpHeaders headers) {
        headers.add("User-Agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) "
                + "Chrome/130.0.0.0 Safari/537.36");
        headers.add("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.add("Origin", "https://developer.riotgames.com");
        headers.add("X-Riot-Token", riotApiProperty.apiKey());
    }
}