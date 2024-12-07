package geng.your.gg.api.service;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import geng.your.gg.api.dto.SummonerInfoDto;
import geng.your.gg.api.fixture.ResponseDtoFixture;
import geng.your.gg.api.manager.ExternalApiManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private final ExternalApiManager externalApiManager = mock(ExternalApiManager.class);
    private final UserService userService = new UserService(externalApiManager);

    @Test
    @DisplayName("소환사의 정보를 조회한다")
    void getSummonerInfo() {
        // given
        String gameName = "testGameName";
        String tagLine = "testTagLine";

        given(externalApiManager.getUserAccount(gameName, tagLine))
            .willReturn(ResponseDtoFixture.accountDto());
        given(externalApiManager.getSummonerInfo(gameName, tagLine, ResponseDtoFixture.accountDto().puuid()))
            .willReturn(ResponseDtoFixture.summonerInfoDto());

        // when
        SummonerInfoDto summonerInfo = userService.getSummonerInfo(gameName, tagLine);

        // then
        assertSoftly(softly -> {
            softly.assertThat(summonerInfo).isNotNull();
            softly.assertThat(summonerInfo.gameName()).isNotEmpty();
            softly.assertThat(summonerInfo.tagLine()).isNotEmpty();
            softly.assertThat(summonerInfo.profileImage()).isNotEmpty();
            softly.assertThat(summonerInfo.summonerLevel()).isNotNull();
        });
    }
}
