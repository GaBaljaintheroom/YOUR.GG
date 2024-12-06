package geng.your.gg.api.fixture;


import geng.your.gg.api.dto.SummonerInfoDto;
import geng.your.gg.infrastructure.riot.dto.user.AccountDto;

public class ResponseDtoFixture {

    public static AccountDto accountDto() {
        return new AccountDto("testPuuid", "testGameName", "testTagLine");
    }

    public static SummonerInfoDto summonerInfoDto() {
        return SummonerInfoDto.builder()
            .gameName("testGameName")
            .tagLine("testTagLine")
            .profileImage("testProfileImage")
            .summonerLevel(999)
            .build();
    }
}
