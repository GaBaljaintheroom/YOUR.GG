package geng.your.gg.infrastructure.riot.client;

import geng.your.gg.infrastructure.riot.dto.match.DetailMatchDto;
import geng.your.gg.infrastructure.riot.dto.match.MatchIdsDto;
import geng.your.gg.infrastructure.riot.dto.match.SimpleMatchDto;
import geng.your.gg.infrastructure.riot.dto.user.AccountDto;
import geng.your.gg.infrastructure.riot.dto.user.SummonerDto;
import geng.your.gg.infrastructure.riot.exception.RiotApiExceptions;
import geng.your.gg.infrastructure.riot.property.RiotApiProperty;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class RiotClient {

    private final RiotApiProperty riotApiProperty;

    public AccountDto getGameUserAccount(String gameName, String tagLine) {
        ResponseEntity<AccountDto> response = ResponseEntity.of(Optional.empty());
        try {
            response = RestClient.create(
                    riotApiProperty.asiaBaseURL() + getUserAccountURL(gameName, tagLine))
                .get()
                .headers(this::createHeaders)
                .retrieve()
                .toEntity(AccountDto.class);
        } catch (HttpClientErrorException e) {
            RiotApiExceptions.handleResponse(response);
        }

        return response.getBody();
    }

    public SummonerDto getSummoner(String puuid) {
        ResponseEntity<SummonerDto> response = ResponseEntity.of(Optional.empty());
        try {
            response = RestClient.create(
                    riotApiProperty.summonerBaseURL() + getSummonerURL(puuid))
                .get()
                .headers(this::createHeaders)
                .retrieve()
                .toEntity(SummonerDto.class);

        } catch (HttpClientErrorException e) {
            RiotApiExceptions.handleResponse(response);
        }

        return response.getBody();
    }

    public MatchIdsDto getMatchIds(int start, int count, String puuid) {
        ResponseEntity<List<String>> response = ResponseEntity.of(Optional.empty());
        try {
            response =  RestClient.create(
                    riotApiProperty.asiaBaseURL() + getMatchIdsURL(puuid, start, count))
                .get()
                .headers(this::createHeaders)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<String>>() {
                });
        } catch (HttpClientErrorException e) {
            RiotApiExceptions.handleResponse(response);
        }

        return MatchIdsDto.from(response.getBody());
    }

    public SimpleMatchDto getSimpleMatch(String matchId) {
        ResponseEntity<SimpleMatchDto> response = ResponseEntity.of(Optional.empty());
        try {
            response = RestClient.create(
                    riotApiProperty.asiaBaseURL() + getMatchURL(matchId))
                .get()
                .headers(this::createHeaders)
                .retrieve()
                .toEntity(SimpleMatchDto.class);
        } catch (HttpClientErrorException e) {
            RiotApiExceptions.handleResponse(response);
        }

        return response.getBody();
    }

    public DetailMatchDto getDetailMatch(String matchId) {
        ResponseEntity<DetailMatchDto> response = ResponseEntity.of(Optional.empty());
        try {
            response = RestClient.create(
                    riotApiProperty.asiaBaseURL() + getMatchURL(matchId))
                .get()
                .headers(this::createHeaders)
                .retrieve()
                .toEntity(DetailMatchDto.class);
        } catch (HttpClientErrorException e) {
            RiotApiExceptions.handleResponse(response);
        }

        return response.getBody();
    }

    private static String getUserAccountURL(String gameName, String tagLine) {
        return String.format("/riot/account/v1/accounts/by-riot-id/%s/%s", gameName, tagLine);
    }

    private String getSummonerURL(String puuid) {
        return String.format("/lol/summoner/v4/summoners/by-puuid/%s", puuid);
    }

    private String getMatchIdsURL(String puuid, int start, int end) {
        return String.format("/lol/match/v5/matches/by-puuid/%s/ids?start=%d&count=%d", puuid,
            start, end);
    }

    private String getMatchURL(String matchId) {
        return String.format("/lol/match/v5/matches/%s", matchId);
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
